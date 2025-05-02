package startwithco.b2bservice.b2b.repository;

import startwithco.b2bservice.b2b.entity.ConsumerEntity;
import startwithco.b2bservice.b2b.entity.VendorEntity;

public interface B2bRepository {

    void isDuplicatedVendorName (String vendorName);

    VendorEntity saveVendor(VendorEntity vendorEntity);

    void isDuplicatedConsumerName (String consumerName);

    ConsumerEntity saveConsumer(ConsumerEntity consumerEntity);
}
