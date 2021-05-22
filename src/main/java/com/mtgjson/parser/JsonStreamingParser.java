package com.mtgjson.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * Helper to parse MtgJson files.
 *
 * @param <K>
 *      bean contains in the File.
 */
public interface JsonStreamingParser<K> {
    
    /**
     * Main Parser.
     *
     * @param in
     *      input stream
     * @param processor
     *      processor for an entry (callback)
     */
    void parse(InputStream in, Consumer<K> processor) 
    throws Exception;
    
    /**
     * Parse local file.
     *
     * @param f
     *      local file
     * @param processor
     *      processor for an entry (callback)
     */
    default void parse(File f, Consumer<K> processor) 
    throws Exception {
        Objects.requireNonNull(f);
        Objects.requireNonNull(processor);
        try {
            if (f.exists() && f.canRead()) {
                parse(new FileInputStream(f), processor);
            }
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
    }
    
    /**
     * Parsing file from an URL. Must be small (50MB)
     *
     * @param url
     *      url
     * @param processor
     *       processor for an entry (callback)
     */
    default void parse(URL url, Consumer<K> processor)
    throws Exception {
        Objects.requireNonNull(url);
        Objects.requireNonNull(processor);
        HttpURLConnection urlConnection;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            parse(urlConnection.getInputStream(), processor);
        } catch (IOException e) {
            // Cannot contact URL etc.
            throw new IllegalArgumentException(e);
        }
    }

}
