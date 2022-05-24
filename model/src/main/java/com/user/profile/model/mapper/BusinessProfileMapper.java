package com.user.profile.model.mapper;

import java.util.Collection;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import com.user.profile.model.domain.mongo.model.BusinessProfileUpdateRequestDoc;
import com.user.profile.model.dto.BusinessProfileUpdateRequestDTO;
import com.user.profile.model.dto.BusinessProfileUpdateResponseDTO;
import com.user.profile.model.domain.jpa.AddressEntity;
import com.user.profile.model.domain.jpa.BusinessProfileEntity;
import com.user.profile.model.domain.jpa.SubscriptionEntity;
import com.user.profile.model.dto.AddressDTO;
import com.user.profile.model.dto.BusinessProfileDTO;
import com.user.profile.model.dto.SubscriptionDTO;


/**
 * @author jayensingh
 * <p>
 * <p>
 * 19/04/22
 */
@Mapper
public interface BusinessProfileMapper {

    BusinessProfileMapper MAPPER_INSTANCE = Mappers.getMapper(BusinessProfileMapper.class);

    @Mapping(source = "ownerId", target = "userId")
    BusinessProfileEntity fromDTO(BusinessProfileDTO dto);

    //@Mapping(source = "ownerId", target = "userId")
    BusinessProfileEntity fromRequestDTOtoEntity(BusinessProfileUpdateRequestDTO dto);

    @Mapping(source = "userId", target = "ownerId")
    BusinessProfileDTO fromEntity(BusinessProfileEntity entity);

    //@Mapping(source = "userId", target = "ownerId")
    BusinessProfileUpdateResponseDTO fromEntitytoResponseDTO(BusinessProfileEntity entity);

    @Mapping(source = "profileId", target = "businessProfile.id")
    AddressEntity fromAdressDTO(AddressDTO dto);

    @Mapping(source = "businessProfile.id", target = "profileId")
    AddressDTO fromAddressEntity(AddressEntity entity);

    @Mapping(source = "businessProfileId", target = "businessProfile.id")
    SubscriptionEntity fromSubscriptionDTO(SubscriptionDTO dto);

    @Mapping(source = "businessProfile.id", target = "businessProfileId")
    SubscriptionDTO fromSubscriptionEntity(SubscriptionEntity entity);

    Collection<SubscriptionDTO> fromSubscriptionEntities(Collection<SubscriptionEntity> entities);

    @Mapping(source = "id", target = "businessProfileId")
    @Mapping(ignore = true, target = "id")
    BusinessProfileUpdateRequestDoc fromUpdateRequestDTO(BusinessProfileUpdateRequestDTO dto);

    @Mapping(expression = "java(doc.getId().toString())", target = "id")
    BusinessProfileUpdateResponseDTO fromUpdateDocument(BusinessProfileUpdateRequestDoc doc);

    List<BusinessProfileUpdateResponseDTO> fromListOfDocument(List<BusinessProfileUpdateRequestDoc> docs);

    @Mapping(source = "addresses", target = "addresses")
    @Mapping(source = "businessProfileId", target = "id")
//    @Mapping(ignore = true, target = "id")
    BusinessProfileEntity fromDocumentToEntity(BusinessProfileUpdateRequestDoc doc);

}
