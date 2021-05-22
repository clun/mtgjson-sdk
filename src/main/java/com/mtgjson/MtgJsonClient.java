package com.mtgjson;

import com.mtgjson.client.AllIdentifiersClient;
import com.mtgjson.client.AllPricesClient;
import com.mtgjson.client.AllPrintingsClient;
import com.mtgjson.client.SetClient;
import com.mtgjson.client.SetlistClient;

/**
 * Helper to consume and work with files exposed on MtgJson.com.
 *
 * @author Cedrick LUNVEN (@clunven)
 */
public class MtgJsonClient {
    
    /** Core URL. */
    public static final String URL                 = "https://mtgjson.com/api/v5/";
   
    public static final String URL_ATOMIC_CARDS    = URL + "AtomicCards.json";
    public static final String URL_CARD_TYPES      = URL + "CardTypes.json";
    public static final String URL_ENUMVALUES      = URL + "EnumValues.json";
    public static final String URL_KEYWORDS        = URL + "Keywords.json";
    public static final String URL_ALL_SETFILE_ZIP = URL + "AllSetFiles.zip";
    public static final String URL_ALL_DECKS_ZIP   = URL + "AllDeckFiles.zip";
    
    public static AllPricesClient allPrices() {
        return new AllPricesClient();
    }
    
    public static AllIdentifiersClient allIdentifiers() {
        return new AllIdentifiersClient();
    }
    
    public static AllPrintingsClient allPrintings() {
        return new AllPrintingsClient();
    }
    
    public static SetlistClient setList() {
        return new SetlistClient();
    }
    
    public static SetClient set(String code) {
        return new SetClient(code);
    }

}
