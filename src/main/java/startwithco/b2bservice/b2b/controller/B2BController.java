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
import startwithco.b2bservice.b2b.dto.B2bResponse;
import startwithco.b2bservice.b2b.exception.badRequest.BadRequestErrorResult;
import startwithco.b2bservice.b2b.exception.badRequest.BadRequestException;
import startwithco.b2bservice.b2b.exception.badRequest.BadRequestExceptionHandler;
import startwithco.b2bservice.b2b.exception.conflict.ConflictExceptionHandler;
import startwithco.b2bservice.b2b.exception.server.ServerExceptionHandler;
import startwithco.b2bservice.b2b.service.B2bService;

import static startwithco.b2bservice.b2b.dto.B2bRequest.*;
import static startwithco.b2bservice.b2b.dto.B2bResponse.*;

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
        validateSaveVendorRequest(request);

        // 저장
        b2bService.saveVendor(request);


        return ResponseEntity.ok().body(BaseResponse.ofSuccess(HttpStatus.OK.value(), "SUCCESS"));
    }

    private void validateSaveVendorRequest(SaveVendorRequest request){

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
        validateSaveConsumerRequest(request);

        // 저장
        b2bService.saveConsumer(request);


        return ResponseEntity.ok().body(BaseResponse.ofSuccess(HttpStatus.OK.value(), "SUCCESS"));
    }

    private void validateSaveConsumerRequest(SaveConsumerRequest request){

        if (request.consumerName() == null || request.email() == null || request.phoneNum() == null
                || request.password() == null || request.industry() == null) {
            throw new BadRequestException(BadRequestErrorResult.DTO_BAD_REQUEST_EXCEPTION);
        }

    }

    @PostMapping(value = "/login/vendor",name = "벤더 로그인")
    @Operation(summary = "login Vendor API", description = "벤더 로그인 API")
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
                    responseCode = "EB003",
                    description = "400 EMAIL_BAD_REQUEST_EXCEPTION",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ConflictExceptionHandler.ErrorResponse.class))),
            @ApiResponse(
                    responseCode = "PB004",
                    description = "400 PASSWORD_BAD_REQUEST_EXCEPTION",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ConflictExceptionHandler.ErrorResponse.class)))
    })
    public ResponseEntity<BaseResponse<LoginResponse>> loginVendor(@Valid @RequestBody LoginRequest request) {

        // DTO 유효성 검사
        validateLoginRequest(request);

        // 저장
        LoginResponse loginResponse = b2bService.loginVendor(request);

        return ResponseEntity.ok().body(BaseResponse.ofSuccess(HttpStatus.OK.value(), loginResponse));
    }

    @PostMapping(value = "/login/consumer",name = "수요 기업 로그인")
    @Operation(summary = "login Consumer API", description = "수요 기업 로그인 API")
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
                    responseCode = "EB003",
                    description = "400 EMAIL_BAD_REQUEST_EXCEPTION",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ConflictExceptionHandler.ErrorResponse.class))),
            @ApiResponse(
                    responseCode = "PB004",
                    description = "400 PASSWORD_BAD_REQUEST_EXCEPTION",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ConflictExceptionHandler.ErrorResponse.class)))
    })
    public ResponseEntity<BaseResponse<LoginResponse>> loginConsumer(@Valid @RequestBody LoginRequest request) {

        // DTO 유효성 검사
        validateLoginRequest(request);

        // 저장
        LoginResponse loginResponse = b2bService.loginConsumer(request);

        return ResponseEntity.ok().body(BaseResponse.ofSuccess(HttpStatus.OK.value(), loginResponse));
    }

    private void validateLoginRequest(LoginRequest request) {
        if(request.email() == null || request.password() == null) {
            throw new BadRequestException(BadRequestErrorResult.DTO_BAD_REQUEST_EXCEPTION);
        }
    }

}
