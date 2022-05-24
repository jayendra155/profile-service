package com.user.profile.model.mapper;

import com.user.profile.model.domain.jpa.AddressEntity;
import com.user.profile.model.domain.jpa.BusinessProfileEntity;
import com.user.profile.model.domain.jpa.SubscriptionEntity;
import com.user.profile.model.domain.mongo.model.BusinessProfileUpdateRequestDoc;
import com.user.profile.model.dto.AddressDTO;
import com.user.profile.model.dto.BusinessProfileDTO;
import com.user.profile.model.dto.BusinessProfileUpdateRequestDTO;
import com.user.profile.model.dto.BusinessProfileUpdateResponseDTO;
import com.user.profile.model.dto.SubscriptionDTO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-02T09:34:00+0530",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11.0.14.1 (Eclipse Adoptium)"
)
public class BusinessProfileMapperImpl implements BusinessProfileMapper {

    @Override
    public BusinessProfileEntity fromDTO(BusinessProfileDTO dto) {
        if ( dto == null ) {
            return null;
        }

        BusinessProfileEntity businessProfileEntity = new BusinessProfileEntity();

        businessProfileEntity.setUserId( dto.getOwnerId() );
        businessProfileEntity.setId( dto.getId() );
        businessProfileEntity.setAddresses( addressDTOSetToAddressEntitySet( dto.getAddresses() ) );
        businessProfileEntity.setSubscriptions( subscriptionDTOSetToSubscriptionEntitySet( dto.getSubscriptions() ) );
        businessProfileEntity.setCompanyName( dto.getCompanyName() );
        businessProfileEntity.setLegalName( dto.getLegalName() );
        businessProfileEntity.setEmail( dto.getEmail() );
        businessProfileEntity.setWebsite( dto.getWebsite() );
        businessProfileEntity.setTaxIdentifierDocName( dto.getTaxIdentifierDocName() );
        businessProfileEntity.setTaxIdentifierDocId( dto.getTaxIdentifierDocId() );

        return businessProfileEntity;
    }

    @Override
    public BusinessProfileEntity fromRequestDTOtoEntity(BusinessProfileUpdateRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        BusinessProfileEntity businessProfileEntity = new BusinessProfileEntity();

        businessProfileEntity.setId( dto.getId() );
        businessProfileEntity.setAddresses( addressDTOSetToAddressEntitySet( dto.getAddresses() ) );
        businessProfileEntity.setCompanyName( dto.getCompanyName() );
        businessProfileEntity.setLegalName( dto.getLegalName() );
        businessProfileEntity.setEmail( dto.getEmail() );
        businessProfileEntity.setWebsite( dto.getWebsite() );
        businessProfileEntity.setTaxIdentifierDocName( dto.getTaxIdentifierDocName() );
        businessProfileEntity.setTaxIdentifierDocId( dto.getTaxIdentifierDocId() );

        return businessProfileEntity;
    }

    @Override
    public BusinessProfileDTO fromEntity(BusinessProfileEntity entity) {
        if ( entity == null ) {
            return null;
        }

        BusinessProfileDTO businessProfileDTO = new BusinessProfileDTO();

        businessProfileDTO.setOwnerId( entity.getUserId() );
        businessProfileDTO.setId( entity.getId() );
        businessProfileDTO.setAddresses( addressEntitySetToAddressDTOSet( entity.getAddresses() ) );
        businessProfileDTO.setSubscriptions( subscriptionEntitySetToSubscriptionDTOSet( entity.getSubscriptions() ) );
        businessProfileDTO.setCompanyName( entity.getCompanyName() );
        businessProfileDTO.setLegalName( entity.getLegalName() );
        businessProfileDTO.setEmail( entity.getEmail() );
        businessProfileDTO.setWebsite( entity.getWebsite() );
        businessProfileDTO.setTaxIdentifierDocName( entity.getTaxIdentifierDocName() );
        businessProfileDTO.setTaxIdentifierDocId( entity.getTaxIdentifierDocId() );

        return businessProfileDTO;
    }

    @Override
    public BusinessProfileUpdateResponseDTO fromEntitytoResponseDTO(BusinessProfileEntity entity) {
        if ( entity == null ) {
            return null;
        }

        BusinessProfileUpdateResponseDTO businessProfileUpdateResponseDTO = new BusinessProfileUpdateResponseDTO();

        if ( entity.getId() != null ) {
            businessProfileUpdateResponseDTO.setId( String.valueOf( entity.getId() ) );
        }

        return businessProfileUpdateResponseDTO;
    }

    @Override
    public AddressEntity fromAdressDTO(AddressDTO dto) {
        if ( dto == null ) {
            return null;
        }

        AddressEntity addressEntity = new AddressEntity();

        addressEntity.setBusinessProfile( addressDTOToBusinessProfileEntity( dto ) );
        addressEntity.setLine1( dto.getLine1() );
        addressEntity.setLine2( dto.getLine2() );
        addressEntity.setCity( dto.getCity() );
        addressEntity.setState( dto.getState() );
        addressEntity.setCountry( dto.getCountry() );
        addressEntity.setZipcode( dto.getZipcode() );

        return addressEntity;
    }

    @Override
    public AddressDTO fromAddressEntity(AddressEntity entity) {
        if ( entity == null ) {
            return null;
        }

        AddressDTO addressDTO = new AddressDTO();

        addressDTO.setProfileId( entityBusinessProfileId( entity ) );
        addressDTO.setLine1( entity.getLine1() );
        addressDTO.setLine2( entity.getLine2() );
        addressDTO.setCity( entity.getCity() );
        addressDTO.setState( entity.getState() );
        addressDTO.setCountry( entity.getCountry() );
        addressDTO.setZipcode( entity.getZipcode() );

        return addressDTO;
    }

    @Override
    public SubscriptionEntity fromSubscriptionDTO(SubscriptionDTO dto) {
        if ( dto == null ) {
            return null;
        }

        SubscriptionEntity subscriptionEntity = new SubscriptionEntity();

        subscriptionEntity.setBusinessProfile( subscriptionDTOToBusinessProfileEntity( dto ) );
        subscriptionEntity.setId( dto.getId() );
        subscriptionEntity.setSubscriptionServiceName( dto.getSubscriptionServiceName() );
        subscriptionEntity.setSubscriptionStatus( dto.getSubscriptionStatus() );

        return subscriptionEntity;
    }

    @Override
    public SubscriptionDTO fromSubscriptionEntity(SubscriptionEntity entity) {
        if ( entity == null ) {
            return null;
        }

        SubscriptionDTO subscriptionDTO = new SubscriptionDTO();

        subscriptionDTO.setBusinessProfileId( entityBusinessProfileId1( entity ) );
        subscriptionDTO.setId( entity.getId() );
        subscriptionDTO.setSubscriptionServiceName( entity.getSubscriptionServiceName() );
        subscriptionDTO.setSubscriptionStatus( entity.getSubscriptionStatus() );

        return subscriptionDTO;
    }

    @Override
    public Collection<SubscriptionDTO> fromSubscriptionEntities(Collection<SubscriptionEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        Collection<SubscriptionDTO> collection = new ArrayList<SubscriptionDTO>( entities.size() );
        for ( SubscriptionEntity subscriptionEntity : entities ) {
            collection.add( fromSubscriptionEntity( subscriptionEntity ) );
        }

        return collection;
    }

    @Override
    public BusinessProfileUpdateRequestDoc fromUpdateRequestDTO(BusinessProfileUpdateRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        BusinessProfileUpdateRequestDoc businessProfileUpdateRequestDoc = new BusinessProfileUpdateRequestDoc();

        businessProfileUpdateRequestDoc.setBusinessProfileId( dto.getId() );
        Set<AddressDTO> set = dto.getAddresses();
        if ( set != null ) {
            businessProfileUpdateRequestDoc.setAddresses( new HashSet<AddressDTO>( set ) );
        }
        businessProfileUpdateRequestDoc.setCompanyName( dto.getCompanyName() );
        businessProfileUpdateRequestDoc.setLegalName( dto.getLegalName() );
        businessProfileUpdateRequestDoc.setEmail( dto.getEmail() );
        businessProfileUpdateRequestDoc.setWebsite( dto.getWebsite() );
        businessProfileUpdateRequestDoc.setTaxIdentifierDocName( dto.getTaxIdentifierDocName() );
        businessProfileUpdateRequestDoc.setTaxIdentifierDocId( dto.getTaxIdentifierDocId() );

        return businessProfileUpdateRequestDoc;
    }

    @Override
    public BusinessProfileUpdateResponseDTO fromUpdateDocument(BusinessProfileUpdateRequestDoc doc) {
        if ( doc == null ) {
            return null;
        }

        BusinessProfileUpdateResponseDTO businessProfileUpdateResponseDTO = new BusinessProfileUpdateResponseDTO();

        businessProfileUpdateResponseDTO.setBusinessProfileId( doc.getBusinessProfileId() );
        businessProfileUpdateResponseDTO.setStatus( doc.getStatus() );
        businessProfileUpdateResponseDTO.setRequestTime( doc.getRequestTime() );
        businessProfileUpdateResponseDTO.setLastUpdatedOn( doc.getLastUpdatedOn() );

        businessProfileUpdateResponseDTO.setId( doc.getId().toString() );

        return businessProfileUpdateResponseDTO;
    }

    @Override
    public List<BusinessProfileUpdateResponseDTO> fromListOfDocument(List<BusinessProfileUpdateRequestDoc> docs) {
        if ( docs == null ) {
            return null;
        }

        List<BusinessProfileUpdateResponseDTO> list = new ArrayList<BusinessProfileUpdateResponseDTO>( docs.size() );
        for ( BusinessProfileUpdateRequestDoc businessProfileUpdateRequestDoc : docs ) {
            list.add( fromUpdateDocument( businessProfileUpdateRequestDoc ) );
        }

        return list;
    }

    @Override
    public BusinessProfileEntity fromDocumentToEntity(BusinessProfileUpdateRequestDoc doc) {
        if ( doc == null ) {
            return null;
        }

        BusinessProfileEntity businessProfileEntity = new BusinessProfileEntity();

        businessProfileEntity.setAddresses( addressDTOSetToAddressEntitySet1( doc.getAddresses() ) );
        businessProfileEntity.setId( doc.getBusinessProfileId() );
        businessProfileEntity.setCompanyName( doc.getCompanyName() );
        businessProfileEntity.setLegalName( doc.getLegalName() );
        businessProfileEntity.setEmail( doc.getEmail() );
        businessProfileEntity.setWebsite( doc.getWebsite() );
        businessProfileEntity.setTaxIdentifierDocName( doc.getTaxIdentifierDocName() );
        businessProfileEntity.setTaxIdentifierDocId( doc.getTaxIdentifierDocId() );

        return businessProfileEntity;
    }

    protected Set<AddressEntity> addressDTOSetToAddressEntitySet(Set<AddressDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<AddressEntity> set1 = new HashSet<AddressEntity>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( AddressDTO addressDTO : set ) {
            set1.add( fromAdressDTO( addressDTO ) );
        }

        return set1;
    }

    protected Set<SubscriptionEntity> subscriptionDTOSetToSubscriptionEntitySet(Set<SubscriptionDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<SubscriptionEntity> set1 = new HashSet<SubscriptionEntity>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( SubscriptionDTO subscriptionDTO : set ) {
            set1.add( fromSubscriptionDTO( subscriptionDTO ) );
        }

        return set1;
    }

    protected Set<AddressDTO> addressEntitySetToAddressDTOSet(Set<AddressEntity> set) {
        if ( set == null ) {
            return null;
        }

        Set<AddressDTO> set1 = new HashSet<AddressDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( AddressEntity addressEntity : set ) {
            set1.add( fromAddressEntity( addressEntity ) );
        }

        return set1;
    }

    protected Set<SubscriptionDTO> subscriptionEntitySetToSubscriptionDTOSet(Set<SubscriptionEntity> set) {
        if ( set == null ) {
            return null;
        }

        Set<SubscriptionDTO> set1 = new HashSet<SubscriptionDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( SubscriptionEntity subscriptionEntity : set ) {
            set1.add( fromSubscriptionEntity( subscriptionEntity ) );
        }

        return set1;
    }

    protected BusinessProfileEntity addressDTOToBusinessProfileEntity(AddressDTO addressDTO) {
        if ( addressDTO == null ) {
            return null;
        }

        BusinessProfileEntity businessProfileEntity = new BusinessProfileEntity();

        businessProfileEntity.setId( addressDTO.getProfileId() );

        return businessProfileEntity;
    }

    private Long entityBusinessProfileId(AddressEntity addressEntity) {
        if ( addressEntity == null ) {
            return null;
        }
        BusinessProfileEntity businessProfile = addressEntity.getBusinessProfile();
        if ( businessProfile == null ) {
            return null;
        }
        Long id = businessProfile.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected BusinessProfileEntity subscriptionDTOToBusinessProfileEntity(SubscriptionDTO subscriptionDTO) {
        if ( subscriptionDTO == null ) {
            return null;
        }

        BusinessProfileEntity businessProfileEntity = new BusinessProfileEntity();

        businessProfileEntity.setId( subscriptionDTO.getBusinessProfileId() );

        return businessProfileEntity;
    }

    private Long entityBusinessProfileId1(SubscriptionEntity subscriptionEntity) {
        if ( subscriptionEntity == null ) {
            return null;
        }
        BusinessProfileEntity businessProfile = subscriptionEntity.getBusinessProfile();
        if ( businessProfile == null ) {
            return null;
        }
        Long id = businessProfile.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected Set<AddressEntity> addressDTOSetToAddressEntitySet1(Set<AddressDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<AddressEntity> set1 = new HashSet<AddressEntity>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( AddressDTO addressDTO : set ) {
            set1.add( fromAdressDTO( addressDTO ) );
        }

        return set1;
    }
}
