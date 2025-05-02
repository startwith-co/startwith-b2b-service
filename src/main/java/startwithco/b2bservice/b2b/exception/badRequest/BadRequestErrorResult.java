package startwithco.b2bservice.b2b.exception.badRequest;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum BadRequestErrorResult {
    BAD_REQUEST_EXCEPTION(HttpStatus.BAD_REQUEST.value(), "BAD REQUEST EXCEPTION", "BRE001"),
    DTO_BAD_REQUEST_EXCEPTION(HttpStatus.BAD_REQUEST.value(), "요청 데이터 형식 오류", "DB002");

    private final int httpStatus;
    private final String message;
    private final String code;
}
