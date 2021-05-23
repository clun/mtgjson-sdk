package com.mtgjson.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.mtgjson.domain.CardPrice;
import com.mtgjson.domain.CardPriceProvider;

/**
 * Parsing file `AllPrices.json`.
 *
 * @author Cedrick LUNVEN (@clunven)
 */
public class AllPricesClient extends AbstractClient implements JsonStreamingParser<CardPrice> {
    
    /** {@inheritDoc} */
    protected String getFileName() {
        return "AllPrices.json";
    }
    
    /** {@inheritDoc} */
    public void parse(String filePath, Consumer<CardPrice> processor) {
        Objects.requireNonNull(filePath);
        Objects.requireNonNull(processor);
        try {
            File f = new File(filePath);
            if (f.exists() && f.canRead()) {
                parse(new FileInputStream(f), processor);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
    
    /**
     * Parsing the inputStream.
     *
     * @param is
     * @param cardProcessor
     * @throws Exception
     */
    private void parse(InputStream is, Consumer<CardPrice> cardProcessor) 
    throws Exception {
        // Create a factory for creating a JsonParser instance
        JsonFactory jsonFactory = new JsonFactory();
        // Create a JsonParser instance
        try (JsonParser jsonParser = jsonFactory.createParser(is)) {
            jsonParser.nextToken(); // root
            jsonParser.nextToken(); // data
            jsonParser.nextToken(); // Begin Card
            jsonParser.nextToken(); // Begin Id
            while(!jsonParser.isClosed()) {
                CardPrice card = new CardPrice();
                card.setCardId(jsonParser.getCurrentName());
                // Level1 : Card Format
                jsonParser.nextToken();
                jsonParser.nextToken();
                while("paper".equalsIgnoreCase(jsonParser.getCurrentName()) ||
                      "mtgo".equalsIgnoreCase(jsonParser.getCurrentName())) {
                    String format = jsonParser.getCurrentName();
                    card.getProviders().put(format, new HashMap<String, CardPriceProvider>());
                    // Level2: Provider    
                    jsonParser.nextToken();
                    jsonParser.nextToken();
                    while("tcgplayer".equalsIgnoreCase(jsonParser.getCurrentName())   ||
                          "cardmarket".equalsIgnoreCase(jsonParser.getCurrentName())  ||
                          "cardhoarder".equalsIgnoreCase(jsonParser.getCurrentName()) ||
                          "cardkingdom".equalsIgnoreCase(jsonParser.getCurrentName())) {
                        // Level3: Prices
                        CardPriceProvider cp = parseCardProvider(jsonParser);
                        card.getProviders().get(format).put(cp.getName(), cp);
                    }
                }
                CompletableFuture.runAsync(()-> cardProcessor.accept(card));
           }
        }
    }
    
    private CardPriceProvider parseCardProvider(JsonParser jsonParser) throws IOException {
        CardPriceProvider provider = new CardPriceProvider();
        provider.setName(jsonParser.getCurrentName());
        // Level 3: BuyList : Retail
        jsonParser.nextToken();
        jsonParser.nextToken();
        boolean done = false;
        String lvl3 = "";
        while(!done) {
            if (null != jsonParser.getCurrentName()) {
                switch(jsonParser.getCurrentName()) {
                    case "buylist":
                        //System.out.println("--buylist");
                        lvl3 = "buylist";
                        jsonParser.nextToken();
                    break;
                    case "retail":
                        //System.out.println("--retail");
                        lvl3 = "retail";
                        jsonParser.nextToken();
                    break;
                    case "foil":
                        //System.out.println("---foil");
                        if (lvl3.equals("buylist")) {
                            provider.setBuylistFoil(readPriceMap(jsonParser));
                            //System.out.println("----buylistFoil:" + provider.getBuylistFoil().size());
                        } else if (lvl3.equals("retail")) {
                            provider.setRetailFoil(readPriceMap(jsonParser));
                            //System.out.println("----retailFoil:" + provider.getRetailFoil().size());
                        } else {
                            //System.out.println("cannot find lvl3");
                        }   
                    break;
                    case "normal":
                        if (lvl3.equals("buylist")) {
                            provider.setBuylistNormal(readPriceMap(jsonParser));
                            //System.out.println("----buylistNormal:" + provider.getBuylistNormal().size());
                        } else if (lvl3.equals("retail")) {
                            provider.setRetailNormal(readPriceMap(jsonParser));
                            //System.out.println("----retailNormal:" + provider.getRetailNormal().size());
                        } else {
                            //System.out.println("cannot find lvl3");
                        }
                    break;
                    case "currency":
                        provider.setCurrency(jsonParser.getText());
                        jsonParser.nextToken();
                    break;
                    default:
                        done = true;
                    break;     
                }
            } else {
                // on Last line
                done = true;
            }
            while (jsonParser.nextToken() == JsonToken.END_OBJECT) {
                jsonParser.nextToken();
            }
        }
        return provider;
    }
    
    private Map<String, Double > readPriceMap(JsonParser jsonParser) throws IOException {
        Map<String, Double > prices = new HashMap<String, Double>();
        if (jsonParser.currentToken().isStructStart()) {
            jsonParser.nextToken();
            while (jsonParser.currentToken() != JsonToken.END_OBJECT) {
                String date =  jsonParser.getCurrentName();
                Double value = jsonParser.getValueAsDouble();
                prices.put(date, value);
                //System.out.println("d=" + date + ",v=" + value);
                jsonParser.nextToken();
            }
        }
        return prices;
    }

}
