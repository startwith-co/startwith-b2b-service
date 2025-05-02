package startwithco.b2bservice.b2b.exception.conflict;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ConflictException extends RuntimeException {
    private final ConflictErrorResult errorResult;
}
