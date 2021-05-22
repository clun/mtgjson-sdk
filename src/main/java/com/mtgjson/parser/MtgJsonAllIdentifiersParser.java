package com.mtgjson.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.mtgjson.domain.Card;
import com.mtgjson.domain.CardForeignData;
import com.mtgjson.domain.CardRuling;

public class MtgJsonAllIdentifiersParser  implements JsonStreamingParser<Card> {

    /** Logger for the class. */
    private static final Logger LOGGER = LoggerFactory.getLogger(MtgJsonAllIdentifiersParser.class);
    
    public void parse(InputStream is, Consumer<Card> cardProcessor) throws JsonParseException, IOException {
        // Create a factory for creating a JsonParser instance
        JsonFactory jsonFactory = new JsonFactory();
        // Create a JsonParser instance
        try (JsonParser jsonParser = jsonFactory.createParser(is)) {
            jsonParser.nextToken(); // root
            jsonParser.nextToken(); // data
            jsonParser.nextToken(); // Begin Card
            jsonParser.nextToken(); // Begin Id
            while(!jsonParser.isClosed()) {
                Card card = new Card();
                LOGGER.info("New Card -> {}",jsonParser.getCurrentName());
                // Enter the cards
                jsonParser.nextToken();
                jsonParser.nextToken();
                boolean nextCard = false;
                while(!nextCard) {
                    switch(jsonParser.getCurrentName()) {
                        case "artist":
                            card.setArtist(readValueString(jsonParser));
                        break;
                        case "asciiName":
                            card.setAsciiName(readValueString(jsonParser));
                        break;
                        case "availability":
                            card.setAvailability(readList(jsonParser));
                        break;
                        case "borderColor":
                            card.setBorderColor(readValueString(jsonParser));
                        break;
                        case "colorIdentity":
                            card.setColorIdentity(readList(jsonParser));
                        break;
                        case "colors":
                            card.setAvailability(readList(jsonParser));
                        break;
                        case "colorIndicator":
                            card.setColorIndicator(readList(jsonParser));
                        break;
                        case "convertedManaCost":
                            card.setConvertedManaCost(readValueDouble(jsonParser));
                        break;
                        case "count":
                            card.setCount(readValueInt(jsonParser));
                        break;
                        case "edhrecRank":
                            card.setEdhrecRank(readValueInt(jsonParser));
                        break;
                        
                        case "duelDeck":
                        case "faceConvertedManaCost":
                        case "faceName":
                        case "flavorName":
                        case "hand":
                        case "hasContentWarning":
                        case "hasAlternativeDeckLimit":
                        case "isAlternative":
                        case "isFoil":
                        case "isFullArt":
                        case "isOnlineOnly":
                        case "isOversized":
                        case "isReserved":
                        case "isStorySpotlight":
                        case "isTextless":
                        case "isTimeshifted":
                        case "life":
                        case "loyalty":
                        case "side":
                        case "watermark":
                            readValueString(jsonParser);
                        break;
                        
                        case "leadershipSkills":
                            readMap(jsonParser);
                        break;
                        
                        case "frameEffects":
                        case "variations":
                        case "otherFaceIds":
                        case "reverseRelated":    
                            readList(jsonParser); 
                        break;
                        
                        case "flavorText":
                            card.setFlavorText(readValueString(jsonParser));
                        break;
                        
                        case "foreignData":
                            card.setForeignData(readForeignData(jsonParser));
                        break;
                        case "frameVersion":
                            card.setFrameVersion(readValueString(jsonParser));
                        break;
                        case "hasFoil":
                            card.setHasFoil(readValueBool(jsonParser));
                        break;
                        case "hasNonFoil": 
                            card.setHasNonFoil(readValueBool(jsonParser));
                        break;
                        case "identifiers":
                            card.setIdentifiers(readMap(jsonParser));
                        break;
                        case "isPromo": 
                            card.setPromo(readValueBool(jsonParser));
                        break;
                        case "isReprint": 
                            card.setReprint(readValueBool(jsonParser));
                        break;
                        case "isStarter":
                            card.setStarter(readValueBool(jsonParser));
                        break;
                        case "keywords":
                            card.setKeywords(readList(jsonParser));
                        break;
                        case "layout": 
                            card.setLayout(readValueString(jsonParser));
                        break;
                        case "legalities": 
                            card.setLegalities(readMap(jsonParser));
                        break;
                        case "manaCost": 
                            card.setManaCost(readValueString(jsonParser));
                        break;
                        case "name":
                            card.setName(readValueString(jsonParser));
                        break;
                        case "number":
                            card.setNumber(readValueString(jsonParser));
                        break;
                        case "power":
                            card.setPower(readValueString(jsonParser));
                        case "printings":
                            card.setPrintings(readList(jsonParser));
                        break;
                        case "promoTypes": 
                            card.setPromoTypes(readList(jsonParser));
                        break;
                        case "purchaseUrls":
                            card.setPurchaseUrls(readMap(jsonParser));
                        break;
                        case "rarity":
                             card.setRarity(readValueString(jsonParser));
                        break;
                        case "rulings":
                            card.setRulings(readRuling(jsonParser));
                        break;
                        case "setCode":
                            card.setSetCode(readValueString(jsonParser));
                        break;
                        case "subtypes":
                            card.setSubtypes(readList(jsonParser));
                        break;
                        case "supertypes":
                            card.setSubtypes(readList(jsonParser));
                        break;
                        case "text":
                            card.setText(readValueString(jsonParser));
                        break;
                        case "toughness":
                            card.setToughness(readValueString(jsonParser));
                        break;
                        case "type": 
                            card.setType(readValueString(jsonParser));
                        break;
                        case "types": 
                            card.setTypes(readList(jsonParser));
                        break;
                        case "uuid":
                            card.setUuid(readValueString(jsonParser));
                        break;
                        case "originalText":
                            card.setOriginalText(readValueString(jsonParser));
                        break;
                        case "originalType":
                            card.setOriginalType(readValueString(jsonParser));
                        break;

                        
                        default:
                            while (jsonParser.nextToken() == JsonToken.END_OBJECT) {
                                jsonParser.nextToken();
                            }
                            nextCard=true;
                            CompletableFuture.runAsync(() -> cardProcessor.accept(card));
                        break;
                    }
                }
            }
        }
    }
    
    private String readValueString(JsonParser jsonParser) throws IOException {
        jsonParser.nextToken();
        LOGGER.debug(" + {} = {}",jsonParser.getCurrentName(), jsonParser.getText());
        String val = jsonParser.getText();
        jsonParser.nextToken();
        return val;
    }
    
    private int readValueInt(JsonParser jsonParser) throws IOException {
        return Integer.parseInt(readValueString(jsonParser));
    }
    
    private double readValueDouble(JsonParser jsonParser) throws IOException {
        return Double.parseDouble(readValueString(jsonParser));
    }
    
    private boolean readValueBool(JsonParser jsonParser) throws IOException {
        return Boolean.parseBoolean(readValueString(jsonParser));
    }
    
    private List<String> readList(JsonParser jsonParser) throws IOException {
        List<String> values = new ArrayList<>();
        String listname = jsonParser.getCurrentName();
        do {
            jsonParser.nextToken();
            values.add(jsonParser.getText());
        } while (jsonParser.currentToken() != JsonToken.END_ARRAY); 
        values.remove("[");
        values.remove("]");
        LOGGER.debug(" + {} = {}", listname, jsonParser.getCurrentName());
        jsonParser.nextToken();
        return values;
    }
    
    private List<CardRuling> readRuling(JsonParser jsonParser) throws IOException {
        List< CardRuling > rulingList = new ArrayList<>();
        /*
        do {
            jsonParser.nextToken();
            MtgJsonCardRuling c = new MtgJsonCardRuling();
            Map<String, String> map = readMap(jsonParser);
            if (!map.isEmpty()) {
                c.setDate(map.get("date"));
                c.setText(map.get("text"));
            }
            rulingList.add(c);
        } while (jsonParser.currentToken() != JsonToken.END_ARRAY); 
        jsonParser.nextToken();*/
        do {
            jsonParser.nextToken();
        } while (jsonParser.currentToken() != JsonToken.END_ARRAY);
        jsonParser.nextToken();
        return rulingList;
    }
    
    private List<CardForeignData> readForeignData(JsonParser jsonParser) throws IOException {
        List< CardForeignData > foreignDataList = new ArrayList<>();
        /*
        jsonParser.nextToken(); // Start Array
        do {
            Map<String, String> map = new HashMap<>();
            System.out.print(jsonParser.getCurrentName() + "=");
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.END_ARRAY) {
                jsonParser.nextToken();
                do {
                  String key=jsonParser.getCurrentName();
                  jsonParser.nextToken();
                  map.put(key, jsonParser.getText());
                  jsonParser.nextToken();
                } while (jsonParser.currentToken() != JsonToken.END_OBJECT);
            }
            if (!map.isEmpty()) {
                MtgJsonCardForeignData c = new MtgJsonCardForeignData();
                c.setLanguage(map.get("language"));
                c.setName(map.get("name"));
                c.setText(map.get("text"));
                c.setType(map.get("type"));
                foreignDataList.add(c);
               
                System.out.println(jsonParser.currentToken());
                System.out.println(jsonParser.getText());
                Map<String, String> map2 = readMap(jsonParser);
                System.out.println(map2);
                System.exit(1);
            }
            */
        do {
            jsonParser.nextToken();
        } while (jsonParser.currentToken() != JsonToken.END_ARRAY);
        jsonParser.nextToken();
        return foreignDataList;
    }
    
    private Map<String, String> readMap(JsonParser jsonParser) throws IOException {
       Map<String, String> returnedMap = new HashMap<>();
       jsonParser.nextToken();
       String mapName = jsonParser.getCurrentName();
       jsonParser.nextToken();
       if (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
           do {
             String key=jsonParser.getCurrentName();
             jsonParser.nextToken();
             returnedMap.put(key, jsonParser.getText());
             jsonParser.nextToken();
           } while (jsonParser.currentToken() != JsonToken.END_OBJECT);
       }
       jsonParser.nextToken();
       LOGGER.debug(" + {} = {}", mapName, returnedMap);
       return returnedMap;
    }
}
