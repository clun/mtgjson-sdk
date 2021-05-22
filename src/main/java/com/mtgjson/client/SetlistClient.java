package com.mtgjson.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.mtgjson.MtgJsonClient;
import com.mtgjson.domain.Expansion;
import com.mtgjson.domain.JsonFile;
import com.mtgjson.utils.IOUtils;
import com.mtgjson.utils.JacksonMapper;

public class SetlistClient extends AbstractClient {
    
    /** {@inheritDoc} */
    protected String getFileName() {
        return "SetList.json";
    }
    
    /**
     * Access from URL.
     */
    public List<Expansion> findAll() {
        try {
            return parseSetList(new URL(MtgJsonClient.URL + getFileName()).openConnection().getInputStream());
        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot Access URL to list Expansions", e);
        }
    }
    
    /**
     * The file is "small" and we can use core Jackson.
     */
    public List<Expansion> parseSetList(InputStream in) {
        try {
            return JacksonMapper.getInstance().readValue(in, new TypeReference<JsonFile<List<Expansion>>>(){}).getData();
        } catch (Exception e) {
            throw new IllegalArgumentException("Cannot Parse Input Json", e);
        } finally {
            IOUtils.close(in);
        }
    }
    
    /**
     * Access from Local file.
     */
    public List<Expansion> parseSetList(File f) {
        try {
            return parseSetList(new FileInputStream(f));
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Cannot Access File " + f.getAbsolutePath(), e);
        }
    }
    
}
