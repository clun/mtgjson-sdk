package com.mtgjson.client;

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
    void parse(String filePath, Consumer<K> processor);
    
}
