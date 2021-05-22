package com.mtgjson.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A set in Magic The Gathering is a pool of cards released together and designed for the 
 * same play environment. Cards in a set can be obtained either randomly through booster 
 * packs, or in box sets that have a fixed selection of cards.
 *
 * @see https://mtg.gamepedia.com/Set
 * @author Cedrick LUNVEN (@clunven)
 */
@JsonIgnoreProperties
public class Expansion implements Serializable {
    
	/** serial. */
    private static final long serialVersionUID = -8241839095274759876L;
    
    /** parse as a Date. */
    public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
    
    @JsonProperty("baseSetSize")
    private Integer baseSetSize;
    
    @JsonProperty("block")
    private String block;
    
    @JsonProperty("code")
    private String code;
    
    @JsonProperty("isFoilOnly")
    private Boolean isFoilOnly;
    
    @JsonProperty("isNonFoilOnly")
    private Boolean isNonFoilOnly;
    
    @JsonProperty("isOnlineOnly")
    private Boolean isOnlineOnly;
    
    @JsonProperty("keyruneCode")
    private String keyruneCode;
    
    @JsonProperty("name")
    private String name;
    
    @JsonProperty("releaseDate")
    private String releaseDate;
    
    @JsonProperty("tcgplayerGroupId")
    private Integer tcgplayerGroupId;
    
    @JsonProperty("totalSetSize")
    private Integer totalSetSize;
    
    @JsonProperty("type")
    private String type;
    
    @JsonProperty("cards")
    private List<Card> cards = new ArrayList<>();
    
    /**
     * Getter accessor for attribute 'baseSetSize'.
     *
     * @return
     *       current value of 'baseSetSize'
     */
    public Integer getBaseSetSize() {
        return baseSetSize;
    }

    /**
     * Setter accessor for attribute 'baseSetSize'.
     * @param baseSetSize
     * 		new value for 'baseSetSize '
     */
    public void setBaseSetSize(Integer baseSetSize) {
        this.baseSetSize = baseSetSize;
    }

    /**
     * Getter accessor for attribute 'block'.
     *
     * @return
     *       current value of 'block'
     */
    public String getBlock() {
        return block;
    }

    /**
     * Setter accessor for attribute 'block'.
     * @param block
     * 		new value for 'block '
     */
    public void setBlock(String block) {
        this.block = block;
    }

    /**
     * Getter accessor for attribute 'code'.
     *
     * @return
     *       current value of 'code'
     */
    public String getCode() {
        return code;
    }

    /**
     * Setter accessor for attribute 'code'.
     * @param code
     * 		new value for 'code '
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Getter accessor for attribute 'isFoilOnly'.
     *
     * @return
     *       current value of 'isFoilOnly'
     */
    public Boolean getIsFoilOnly() {
        return isFoilOnly;
    }

    /**
     * Setter accessor for attribute 'isFoilOnly'.
     * @param isFoilOnly
     * 		new value for 'isFoilOnly '
     */
    public void setIsFoilOnly(Boolean isFoilOnly) {
        this.isFoilOnly = isFoilOnly;
    }

    /**
     * Getter accessor for attribute 'isNonFoilOnly'.
     *
     * @return
     *       current value of 'isNonFoilOnly'
     */
    public Boolean getIsNonFoilOnly() {
        return isNonFoilOnly;
    }

    /**
     * Setter accessor for attribute 'isNonFoilOnly'.
     * @param isNonFoilOnly
     * 		new value for 'isNonFoilOnly '
     */
    public void setIsNonFoilOnly(Boolean isNonFoilOnly) {
        this.isNonFoilOnly = isNonFoilOnly;
    }

    /**
     * Getter accessor for attribute 'isOnlineOnly'.
     *
     * @return
     *       current value of 'isOnlineOnly'
     */
    public Boolean getIsOnlineOnly() {
        return isOnlineOnly;
    }

    /**
     * Setter accessor for attribute 'isOnlineOnly'.
     * @param isOnlineOnly
     * 		new value for 'isOnlineOnly '
     */
    public void setIsOnlineOnly(Boolean isOnlineOnly) {
        this.isOnlineOnly = isOnlineOnly;
    }

    /**
     * Getter accessor for attribute 'keyruneCode'.
     *
     * @return
     *       current value of 'keyruneCode'
     */
    public String getKeyruneCode() {
        return keyruneCode;
    }

    /**
     * Setter accessor for attribute 'keyruneCode'.
     * @param keyruneCode
     * 		new value for 'keyruneCode '
     */
    public void setKeyruneCode(String keyruneCode) {
        this.keyruneCode = keyruneCode;
    }

    /**
     * Getter accessor for attribute 'name'.
     *
     * @return
     *       current value of 'name'
     */
    public String getName() {
        return name;
    }

    /**
     * Setter accessor for attribute 'name'.
     * @param name
     * 		new value for 'name '
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter accessor for attribute 'releaseDate'.
     *
     * @return
     *       current value of 'releaseDate'
     */
    public String getReleaseDate() {
        return releaseDate;
    }

    /**
     * Setter accessor for attribute 'releaseDate'.
     * @param releaseDate
     * 		new value for 'releaseDate '
     */
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * Getter accessor for attribute 'tcgplayerGroupId'.
     *
     * @return
     *       current value of 'tcgplayerGroupId'
     */
    public Integer getTcgplayerGroupId() {
        return tcgplayerGroupId;
    }

    /**
     * Setter accessor for attribute 'tcgplayerGroupId'.
     * @param tcgplayerGroupId
     * 		new value for 'tcgplayerGroupId '
     */
    public void setTcgplayerGroupId(Integer tcgplayerGroupId) {
        this.tcgplayerGroupId = tcgplayerGroupId;
    }

    /**
     * Getter accessor for attribute 'totalSetSize'.
     *
     * @return
     *       current value of 'totalSetSize'
     */
    public Integer getTotalSetSize() {
        return totalSetSize;
    }

    /**
     * Setter accessor for attribute 'totalSetSize'.
     * @param totalSetSize
     * 		new value for 'totalSetSize '
     */
    public void setTotalSetSize(Integer totalSetSize) {
        this.totalSetSize = totalSetSize;
    }

    /**
     * Getter accessor for attribute 'cards'.
     *
     * @return
     *       current value of 'cards'
     */
    public List<Card> getCards() {
        return cards;
    }

    /**
     * Setter accessor for attribute 'cards'.
     * @param cards
     * 		new value for 'cards '
     */
    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    /**
     * Getter accessor for attribute 'type'.
     *
     * @return
     *       current value of 'type'
     */
    public String getType() {
        return type;
    }

    /**
     * Setter accessor for attribute 'type'.
     * @param type
     * 		new value for 'type '
     */
    public void setType(String type) {
        this.type = type;
    }
    
}
