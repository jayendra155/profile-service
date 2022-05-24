package com.user.profile.worker.util;

import java.util.function.Predicate;
import org.springframework.http.HttpStatus;
import lombok.extern.slf4j.Slf4j;


/**
 * @author jayensingh
 * <p>
 * <p>
 * 02/05/22
 */
@Slf4j
public class WebUtils {

    public static final Predicate<HttpStatus> ANY_OTHER_THAN_2XX = status -> !status.is2xxSuccessful();
}
