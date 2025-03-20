package com.preeti.util;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.preeti.tests.vendarportal.model.VendorPortalTestData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

public class JsonUtil {

    public static final Logger log = LoggerFactory.getLogger(JsonUtil.class);
    private static final ObjectMapper mapper = new ObjectMapper(); //Responsible for converting input stream to java object
    public static <T> T getTestData(String path, Class<T> type){ // T is an object of any type. We have used the Java Generics concept
        try(InputStream stream = ResourceLoader.getResource(path)){
            return mapper.readValue(stream, type);
        } catch(Exception e){
            log.error("unable to read test data {}", path, e);
        }
        return null;
    }


}
