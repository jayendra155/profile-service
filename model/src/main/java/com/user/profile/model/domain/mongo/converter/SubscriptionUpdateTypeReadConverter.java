package com.user.profile.model.domain.mongo.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.user.profile.model.commons.SubscriptionUpdateType;


/**
 * @author jayensingh
 * <p>
 * <p>
 * 27/04/22
 */
@Service
@ReadingConverter
public class SubscriptionUpdateTypeReadConverter implements Converter<String, SubscriptionUpdateType> {

    @Override
    public SubscriptionUpdateType convert(final String source) {
        return StringUtils.hasLength(source) ? null : SubscriptionUpdateType.valueOf(source);
    }
}
