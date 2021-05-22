package com.mtgjson.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * CardPrice in MtgJson
 */
public class CardPrice {
    
    // Card id
    private String cardId;
    
    // Paper, List CardPriceProvider
    private Map<String, Map<String, CardPriceProvider> > providers = new HashMap<>();

    /**
     * Getter accessor for attribute 'cardId'.
     *
     * @return
     *       current value of 'cardId'
     */
    public String getCardId() {
        return cardId;
    }

    /**
     * Setter accessor for attribute 'cardId'.
     * @param cardId
     * 		new value for 'cardId '
     */
    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    /**
     * Getter accessor for attribute 'providers'.
     *
     * @return
     *       current value of 'providers'
     */
    public Map<String, Map<String,CardPriceProvider>> getProviders() {
        return providers;
    }

    /**
     * Setter accessor for attribute 'providers'.
     * @param providers
     * 		new value for 'providers '
     */
    public void setProviders(Map<String, Map<String,CardPriceProvider>> providers) {
        this.providers = providers;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("CardPrice [cardId=");
        builder.append(cardId);
        builder.append(", providers=");
        builder.append(providers);
        builder.append("]");
        return builder.toString();
    }

    
    
}
