package com.example.p_demo.common;

import com.ctc.wstx.util.StringUtil;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.math.BigInteger;

public class IsbnDeserializer extends JsonDeserializer<BigInteger> {
    @Override
    public BigInteger deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        String s = jsonParser.getValueAsString();
        if (StringUtils.isNotBlank(s)){
            return new BigInteger(s.replace("-",""));
        }
        return null;
    }
}
