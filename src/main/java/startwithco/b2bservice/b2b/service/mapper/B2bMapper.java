package startwithco.b2bservice.b2b.service.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import startwithco.b2bservice.b2b.dto.B2bRequest;
import startwithco.b2bservice.b2b.entity.ConsumerEntity;
import startwithco.b2bservice.b2b.entity.VendorEntity;

import static startwithco.b2bservice.b2b.dto.B2bRequest.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface B2bMapper {

    @Mapping(target = "vendorSeq", ignore = true)
    VendorEntity toVendorEntity(SaveVendorRequest request, String encodedPassword);

    @Mapping(target = "consumerSeq", ignore = true)
    ConsumerEntity toConsumerEntity(SaveConsumerRequest request, String encodedPassword);

}
