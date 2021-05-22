package com.mtgjson.java_sdk;

import java.io.File;
import java.util.function.Consumer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.mtgjson.MtgJsonClient;
import com.mtgjson.domain.Card;
import com.mtgjson.domain.CardPrice;
import com.mtgjson.domain.Expansion;
import com.mtgjson.parser.MtgJsonAllPricesParser;

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
        String fileName = "/tmp/allPrices_2021_05_21.json";
        
        // Download the file first
        File allPriceFiles = new File(fileName);
        if (!allPriceFiles.exists()) {
            MtgJsonClient.allPrices().downloadJson(fileName);
        }
        
        try {
            new MtgJsonAllPricesParser().parse(allPriceFiles, new Consumer<CardPrice>() {
                public void accept(CardPrice cardPrice) {
                    System.out.println("Processing card : " + cardPrice.getCardId());
                } 
            });
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
    
}
