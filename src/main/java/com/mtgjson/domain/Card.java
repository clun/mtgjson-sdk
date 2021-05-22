package com.mtgjson.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Card to be saved.
 */
@Data
@NoArgsConstructor
@ToString
public class Card implements Serializable {

    /** Serial. */
    private static final long serialVersionUID = 2395589463527245137L;

    private String uuid;
    private String artist;
    private String asciiName;
    private List<String> availability = new ArrayList<>();
    private String borderColor;
    private List<String> colorIndicator = new ArrayList<>();
    private List<String> colorIdentity = new ArrayList<>();
    private List<String> colors = new ArrayList<>();
    private String scryfallId;
    private Double convertedManaCost;
    private int edhrecRank;
    private int count;
    private String flavorText;
    private List<CardForeignData> foreignData = new ArrayList<>();
    private String frameVersion;
    private boolean hasFoil;
    private boolean hasNonFoil;
    private Map<String, String> identifiers = new HashMap<>();
    private boolean isPromo;
    private boolean isReprint;
    private boolean isStarter;
    private boolean isOnlineOnly;
    private List<String> keywords = new ArrayList<>();
    private String layout;
    private Map<String, String> legalities = new HashMap<>();
    private String manaCost;
    private String name;
    private String number;
    private String power;
    private List<String> printings = new ArrayList<>();
    private List<String> promoTypes = new ArrayList<>();
    private Map<String, String> purchaseUrls = new HashMap<>();
    private String rarity;
    private List<CardRuling> rulings = new ArrayList<>();
    private String setCode;
    private List<String> subtypes = new ArrayList<>();
    private List<String> supertypes = new ArrayList<>();
    private String text;
    private String toughness;
    private String type;
    private List<String> types = new ArrayList<>();
    private String originalText;
    private String originalType;

}
