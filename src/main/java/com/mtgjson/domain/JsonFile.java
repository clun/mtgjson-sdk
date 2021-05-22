package com.mtgjson.domain;

import java.io.Serializable;

/**
 * Data to be extracted from MTG JSON
 * 
 * @author Cedrick LUNVEN (@clunven)
 *
 * @param <T>
 *      data to be extracted
 */
public class JsonFile<T> implements Serializable {

    /** Serial. */
    private static final long serialVersionUID = 902507766974100911L;

    /** Mtg Json version */
    private Meta meta;

    /** Extracted Data. */
    private T data;

    /**
     * Getter accessor for attribute 'meta'.
     *
     * @return current value of 'meta'
     */
    public Meta getMeta() {
        return meta;
    }

    /**
     * Setter accessor for attribute 'meta'.
     * 
     * @param meta
     *            new value for 'meta '
     */
    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    /**
     * Getter accessor for attribute 'data'.
     *
     * @return current value of 'data'
     */
    public T getData() {
        return data;
    }

    /**
     * Setter accessor for attribute 'data'.
     * 
     * @param data
     *            new value for 'data '
     */
    public void setData(T data) {
        this.data = data;
    }
}
