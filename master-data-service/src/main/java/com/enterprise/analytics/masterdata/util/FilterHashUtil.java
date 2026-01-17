package com.enterprise.analytics.masterdata.util;

import com.enterprise.analytics.masterdata.contract.AnalyticsFilterCriteria;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.security.MessageDigest;
import java.util.Base64;

public class FilterHashUtil {

    private static final ObjectMapper mapper = new ObjectMapper();

    public  static String hash(AnalyticsFilterCriteria filter) {
        try {
           String json = mapper.writeValueAsString(filter);
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(json.getBytes());
            return Base64.getUrlEncoder().withoutPadding().encodeToString(hash);
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate filter hash", e);
        }
    }

}
