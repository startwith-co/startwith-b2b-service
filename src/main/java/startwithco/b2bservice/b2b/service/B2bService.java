package startwithco.b2bservice.b2b.service;

import startwithco.b2bservice.b2b.dto.B2bResponse;

import static startwithco.b2bservice.b2b.dto.B2bRequest.*;
import static startwithco.b2bservice.b2b.dto.B2bResponse.*;

public interface B2bService {

    void saveVendor(SaveVendorRequest request);
    void saveConsumer(SaveConsumerRequest request);

    LoginResponse loginVendor(LoginRequest request);
    LoginResponse loginConsumer(LoginRequest request);
}
