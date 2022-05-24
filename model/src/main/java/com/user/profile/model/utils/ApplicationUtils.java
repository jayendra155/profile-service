package com.user.profile.model.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;


/**
 * @author jayensingh
 * <p>
 * <p>
 * 18/04/22
 */
public class ApplicationUtils {

    private static ApplicationUtils applicationUtils = new ApplicationUtils();

    private ApplicationUtils() {
    }

    @Getter
    private ObjectMapper objectMapper = new ObjectMapper();

    public static ApplicationUtils getInstance() {
        return applicationUtils;
    }
}
