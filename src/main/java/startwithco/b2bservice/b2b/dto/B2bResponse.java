package startwithco.b2bservice.b2b.dto;

public class B2bResponse {

    public record LoginResponse(
            String accessToken,
            String refreshToken
    ) {
    }
}
