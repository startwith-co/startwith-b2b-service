package startwithco.b2bservice.b2b.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import startwithco.b2bservice.b2b.base.BaseResponse;
import startwithco.b2bservice.b2b.dto.B2bRequest;
import startwithco.b2bservice.b2b.exception.badRequest.BadRequestErrorResult;
import startwithco.b2bservice.b2b.exception.badRequest.BadRequestException;
import startwithco.b2bservice.b2b.exception.badRequest.BadRequestExceptionHandler;
import startwithco.b2bservice.b2b.exception.conflict.ConflictExceptionHandler;
import startwithco.b2bservice.b2b.exception.server.ServerExceptionHandler;
import startwithco.b2bservice.b2b.service.B2bService;

import static startwithco.b2bservice.b2b.dto.B2bRequest.*;

@RestController
@RequestMapping("/api/b2b-service")
@RequiredArgsConstructor
@Tag(name = "b2b", description = "담당자 (송인준)")
public class B2BController {

    private final B2bService b2bService;


    @PostMapping(value = "/signup/vendor",name = "벤더 가입")
    @Operation(summary = "join Vendor API", description = "벤더 가입 API")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "요청에 성공하였습니다.",
                    useReturnTypeSchema = true),
            @ApiResponse(
                    responseCode = "S500",
                    description = "500 SERVER_ERROR",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ServerExceptionHandler.ErrorResponse.class))),
            @ApiResponse(
                    responseCode = "DB002",
                    description = "400 Invalid DTO Parameter errors",
                    content = @Content(
                            schema = @Schema(
                                    implementation = BadRequestExceptionHandler.ErrorResponse.class))),
            @ApiResponse(
                    responseCode = "VNDCE001",
                    description = "409 VENDOR_NAME_DUPLICATION_CONFLICT_EXCEPTION",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ConflictExceptionHandler.ErrorResponse.class)))
    })
    public ResponseEntity<BaseResponse<String>> saveVendor(@Valid @RequestBody SaveVendorRequest request) {

        // DTO 유효성 검사
        saveVendorRequestDto(request);

        // 저장
        b2bService.saveVendor(request);


        return ResponseEntity.ok().body(BaseResponse.ofSuccess(HttpStatus.OK.value(), "SUCCESS"));
    }

    private void saveVendorRequestDto(SaveVendorRequest request){

        if (request.vendorName() == null || request.email() == null || request.phoneNum() == null
            || request.password() == null) {
            throw new BadRequestException(BadRequestErrorResult.DTO_BAD_REQUEST_EXCEPTION);
        }

    }

    @PostMapping(value = "/signup/consumer",name = "수요 기업 가입")
    @Operation(summary = "join Consumer API", description = "수요 기업 가입 API")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "요청에 성공하였습니다.",
                    useReturnTypeSchema = true),
            @ApiResponse(
                    responseCode = "S500",
                    description = "500 SERVER_ERROR",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ServerExceptionHandler.ErrorResponse.class))),
            @ApiResponse(
                    responseCode = "DB002",
                    description = "400 Invalid DTO Parameter errors",
                    content = @Content(
                            schema = @Schema(
                                    implementation = BadRequestExceptionHandler.ErrorResponse.class))),
            @ApiResponse(
                    responseCode = "CNDCE002",
                    description = "409 CONSUMER_NAME_DUPLICATION_CONFLICT_EXCEPTION",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ConflictExceptionHandler.ErrorResponse.class)))
    })
    public ResponseEntity<BaseResponse<String>> saveConsumer(@Valid @RequestBody SaveConsumerRequest request) {

        // DTO 유효성 검사
        saveConsumerRequestDto(request);

        // 저장
        b2bService.saveConsumer(request);


        return ResponseEntity.ok().body(BaseResponse.ofSuccess(HttpStatus.OK.value(), "SUCCESS"));
    }

    private void saveConsumerRequestDto(SaveConsumerRequest request){

        if (request.consumerName() == null || request.email() == null || request.phoneNum() == null
                || request.password() == null || request.industry() == null) {
            throw new BadRequestException(BadRequestErrorResult.DTO_BAD_REQUEST_EXCEPTION);
        }

    }

}
