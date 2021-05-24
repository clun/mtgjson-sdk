package com.mtgjson.java_sdk;

import java.io.File;
import java.util.function.Consumer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.mtgjson.MtgJsonClient;
import com.mtgjson.client.AllIdentifiersClient;
import com.mtgjson.domain.Card;
import com.mtgjson.domain.CardPrice;
import com.mtgjson.domain.Expansion;

/**
 * Unit test for simple App.
 */
public class MtgJsonTest {
    
    @Test
    public void testListExpansions() {
        MtgJsonClient
               .setList()
               .findAll().stream()
               .map(Expansion::getName)
               .forEach(System.out::println);
    }
    
    @Test
    public void testExistingExpansion() {
        MtgJsonClient.set("10e")
               .find().get()
               .getCards()
               .stream().map(Card::getName)
               .forEach(System.out::println);
    }
    
    @Test
    public void testNotExistingExpansion() {
        Assertions.assertTrue(MtgJsonClient.set("11e").find().isEmpty());
    }
    
    @Test
    public void testSha256() {
        System.out.println(MtgJsonClient.allPrices().getChecksum());
        System.out.println(MtgJsonClient.allPrices().getChecksumZIP());
        System.out.println(MtgJsonClient.allIdentifiers().getChecksum());
    }
    
    @Test
    public void parseAllPrice() {
        String fileName = "/Users/cedricklunven/Downloads/allPrices_2021_05_21.json";
        
        // Download the file first
        File allPriceFiles = new File(fileName);
        if (!allPriceFiles.exists()) {
            MtgJsonClient.allPrices().downloadJson(fileName);
        }
        
        MtgJsonClient.allPrices().parse(fileName, new Consumer<CardPrice>() {
          public void accept(CardPrice cardPrice) {
            System.out.println("Processing card : " + cardPrice.getCardId());
          } 
        });
    }
    
    @Test
    public void parseAllIdentifiers() {
        AllIdentifiersClient allIdentifiers = MtgJsonClient.allIdentifiers();
        String fileName = "/Users/cedricklunven/Downloads/allIdentifiers_2021_05_22.json";
        
        // Download the file first
        File f = new File(fileName);
        if (!f.exists()) allIdentifiers.downloadJson(fileName);
        
        allIdentifiers.parse(fileName, new Consumer<Card>() {
          public void accept(Card c) {
            System.out.println("Processing card : " + c.getName());
          } 
        });
    }
    
    public void parseAllPrintings() {
        MtgJsonClient.allPrintings().downloadJson("/tmp/allPrintings.json");
        MtgJsonClient.allPrintings().downloadBZ2("");
        MtgJsonClient.allPrintings().downloadGZ("");
        MtgJsonClient.allPrintings().downloadXZ("");
        MtgJsonClient.allPrintings().downloadZip("");
        MtgJsonClient.allPrintings().downloadAll("");
        
        
        MtgJsonClient.allPrintings().getChecksum();
        MtgJsonClient.allPrintings().getChecksumBZ2();
        MtgJsonClient.allPrintings().getChecksumGZ();
        MtgJsonClient.allPrintings().getChecksumXZ();
        MtgJsonClient.allPrintings().getChecksumZIP();
        
        
    }
    
}
