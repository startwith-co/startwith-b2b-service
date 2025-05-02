package startwithco.b2bservice.b2b.service;

import startwithco.b2bservice.b2b.dto.B2bRequest;

import static startwithco.b2bservice.b2b.dto.B2bRequest.*;

public interface B2bService {

    void saveVendor(SaveVendorRequest request);
    void saveConsumer(SaveConsumerRequest request);
}
