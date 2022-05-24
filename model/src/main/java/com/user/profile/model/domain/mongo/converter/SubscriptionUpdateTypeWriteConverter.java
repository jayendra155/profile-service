package com.user.profile.model.domain.mongo.converter;

import java.util.Optional;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.stereotype.Service;
import com.user.profile.model.commons.SubscriptionUpdateType;


/**
 * @author jayensingh
 * <p>
 * <p>
 * 27/04/22
 */
@Service
@WritingConverter
public class SubscriptionUpdateTypeWriteConverter implements Converter<SubscriptionUpdateType, String> {

    @Override
    public String convert(final SubscriptionUpdateType source) {
        return Optional.ofNullable(source).map(SubscriptionUpdateType::name)
                .orElse(null);
    }
}
