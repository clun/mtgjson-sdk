package com.mtgjson.domain;

import java.util.HashMap;
import java.util.Map;

public class CardPriceProvider {
    
    private String name;
    private String currency;
    private Map<String, Double> buylistFoil   = new HashMap<>();
    private Map<String, Double> buylistNormal = new HashMap<>();
    private Map<String, Double> retailNormal  = new HashMap<>();
    private Map<String, Double> retailFoil    = new HashMap<>();

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
     * Getter accessor for attribute 'currency'.
     *
     * @return
     *       current value of 'currency'
     */
    public String getCurrency() {
        return currency;
    }
    /**
     * Setter accessor for attribute 'currency'.
     * @param currency
     * 		new value for 'currency '
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    /**
     * Getter accessor for attribute 'buylistFoil'.
     *
     * @return
     *       current value of 'buylistFoil'
     */
    public Map<String, Double> getBuylistFoil() {
        return buylistFoil;
    }
    /**
     * Setter accessor for attribute 'buylistFoil'.
     * @param buylistFoil
     * 		new value for 'buylistFoil '
     */
    public void setBuylistFoil(Map<String, Double> buylistFoil) {
        this.buylistFoil = buylistFoil;
    }
    /**
     * Getter accessor for attribute 'buylistNormal'.
     *
     * @return
     *       current value of 'buylistNormal'
     */
    public Map<String, Double> getBuylistNormal() {
        return buylistNormal;
    }
    /**
     * Setter accessor for attribute 'buylistNormal'.
     * @param buylistNormal
     * 		new value for 'buylistNormal '
     */
    public void setBuylistNormal(Map<String, Double> buylistNormal) {
        this.buylistNormal = buylistNormal;
    }
    /**
     * Getter accessor for attribute 'retailNormal'.
     *
     * @return
     *       current value of 'retailNormal'
     */
    public Map<String, Double> getRetailNormal() {
        return retailNormal;
    }
    /**
     * Setter accessor for attribute 'retailNormal'.
     * @param retailNormal
     * 		new value for 'retailNormal '
     */
    public void setRetailNormal(Map<String, Double> retailNormal) {
        this.retailNormal = retailNormal;
    }
    /**
     * Getter accessor for attribute 'retailFoil'.
     *
     * @return
     *       current value of 'retailFoil'
     */
    public Map<String, Double> getRetailFoil() {
        return retailFoil;
    }
    /**
     * Setter accessor for attribute 'retailFoil'.
     * @param retailFoil
     * 		new value for 'retailFoil '
     */
    public void setRetailFoil(Map<String, Double> retailFoil) {
        this.retailFoil = retailFoil;
    }
    /** {@inheritDoc} */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("CardPriceProvider [name=");
        builder.append(name);
        builder.append(", currency=");
        builder.append(currency);
        builder.append(", buylistFoil=");
        builder.append(buylistFoil);
        builder.append(", buylistNormal=");
        builder.append(buylistNormal);
        builder.append(", retailNormal=");
        builder.append(retailNormal);
        builder.append(", retailFoil=");
        builder.append(retailFoil);
        builder.append("]");
        return builder.toString();
    }

}
