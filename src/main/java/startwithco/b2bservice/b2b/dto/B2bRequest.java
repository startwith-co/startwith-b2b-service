package startwithco.b2bservice.b2b.dto;


public class B2bRequest {

    public record SaveVendorRequest(
            String vendorName,
            String password,
            String phoneNum,
            String email
    ) {
    }

    public record SaveConsumerRequest(
            String consumerName,
            String password,
            String phoneNum,
            String email,
            String industry
    ) {
    }

    public record LoginRequest(
            String email,
            String password
    ) {
    }



}
