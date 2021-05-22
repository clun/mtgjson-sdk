package com.mtgjson.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.mtgjson.MtgJsonClient;
import com.mtgjson.domain.Expansion;
import com.mtgjson.domain.JsonFile;
import com.mtgjson.utils.IOUtils;
import com.mtgjson.utils.JacksonMapper;

public class SetClient extends AbstractClient {
    
    private String setCode;
    
    public SetClient(String code) {
        this.setCode = code;
    }
    
    /** {@inheritDoc} */
    protected String getFileName() {
        return setCode.toUpperCase() + ".json";
    }
    
    public boolean exist() {
        return find().isPresent();
    }
    
    public Optional<Expansion> find()  {
        try {
            URL url = new URL(MtgJsonClient.URL + getFileName());
            HttpURLConnection urlConnection = (HttpURLConnection)  url.openConnection();
            return Optional.ofNullable(parseSet(urlConnection.getInputStream()));
        } catch(FileNotFoundException fne) {
            return Optional.empty();
        } catch (Exception e) {
            throw new IllegalArgumentException("Cannot Access URL to parse Expansion", e);
        }
    }
    
    /**
     * The file is "small" and we can use core Jackson.
     */
    public Expansion parseSet(InputStream in) {
        try {
            return JacksonMapper.getInstance().readValue(in, new TypeReference<JsonFile<Expansion>>(){}).getData();
        } catch (Exception e) {
            throw new IllegalArgumentException("Cannot Parse Input Json", e);
        } finally {
            IOUtils.close(in);
        }
    }
    
    /**
     * Access from Local file.
     */
    public Expansion parseSet(File f) {
        try {
            return parseSet(new FileInputStream(f));
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Cannot Access File " + f.getAbsolutePath(), e);
        }
    }
    
    

}
