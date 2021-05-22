package com.mtgjson.utils;

import java.io.IOException;
import java.io.InputStream;

/**
 * Utility class.
 * 
 * @author Cedrick LUNVEN (@clunven)s
 */
public class IOUtils {

    /**
     * Hide default constructor
     */
    private IOUtils() {}
    
    public static void close(InputStream is) {
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
               // Ignore
            }
        }
    }
}
