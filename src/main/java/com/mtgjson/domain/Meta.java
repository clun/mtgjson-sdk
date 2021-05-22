package com.mtgjson.domain;

public class Meta {
    
    private String date;
    
    private String version;

    /**
     * Getter accessor for attribute 'date'.
     *
     * @return
     *       current value of 'date'
     */
    public String getDate() {
        return date;
    }

    /**
     * Setter accessor for attribute 'date'.
     * @param date
     * 		new value for 'date '
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Getter accessor for attribute 'version'.
     *
     * @return
     *       current value of 'version'
     */
    public String getVersion() {
        return version;
    }

    /**
     * Setter accessor for attribute 'version'.
     * @param version
     * 		new value for 'version '
     */
    public void setVersion(String version) {
        this.version = version;
    }

}
