package com.mtgjson.utils;

import java.text.SimpleDateFormat;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;

public class JacksonMapper {
    
    public static final SimpleDateFormat SDF_JSON  = new SimpleDateFormat("dd/MM/yyyy");
    
    /** Custom mapper to avoid errors.*/
    private static ObjectMapper _instance;
    
    private JacksonMapper() {};
    
    public static synchronized ObjectMapper getInstance() {
        if(_instance == null) {
            _instance = new ObjectMapper();
            _instance.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
            _instance.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
            _instance.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            _instance.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
            _instance.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            _instance.setDateFormat(SDF_JSON);
            _instance.setAnnotationIntrospector(new JacksonAnnotationIntrospector()); 
        }
        return _instance;
    }
    

}
