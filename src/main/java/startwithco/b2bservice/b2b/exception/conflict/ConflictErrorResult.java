package startwithco.b2bservice.b2b.exception.conflict;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ConflictErrorResult {

    VENDOR_NAME_DUPLICATION_CONFLICT_EXCEPTION(HttpStatus.CONFLICT.value(), "Vendor Name Duplication Conflict Exception", "VNDCE001"),
    CONSUMER_NAME_DUPLICATION_CONFLICT_EXCEPTION(HttpStatus.CONFLICT.value(), "Consumer Name Duplication Conflict Exception", "CNDCE002");

    private final int httpStatus;
    private final String message;
    private final String code;
}
