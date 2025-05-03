package startwithco.b2bservice.b2b.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import startwithco.b2bservice.b2b.dto.B2bRequest;
import startwithco.b2bservice.b2b.dto.B2bResponse;
import startwithco.b2bservice.b2b.dto.B2bResponse.LoginResponse;
import startwithco.b2bservice.b2b.entity.ConsumerEntity;
import startwithco.b2bservice.b2b.entity.VendorEntity;
import startwithco.b2bservice.b2b.exception.badRequest.BadRequestErrorResult;
import startwithco.b2bservice.b2b.exception.badRequest.BadRequestException;
import startwithco.b2bservice.b2b.repository.B2bRepository;
import startwithco.b2bservice.b2b.service.mapper.B2bMapper;

import java.util.Date;
import java.util.UUID;

import static startwithco.b2bservice.b2b.dto.B2bRequest.*;

@Service
@RequiredArgsConstructor
public class B2bServiceImpl implements B2bService{

    private final B2bRepository b2bRepository;
    private final B2bMapper b2bMapper;
    private final BCryptPasswordEncoder encoder;
//    @Value("${jwt.accessTokenExpiration}")
    private final long accessTokenExpiration = 86400000L;
//    @Value("${jwt.refreshTokenExpiration}")
    private final long refreshTokenExpiration = 2592000000L;

    private final String jwtSecret = "SDF@!@@강ㄴㅁㄹㅁ@1@#11wsvp[.=-$2vse웨232423ㄹㅇㄴㄹ34ㅇㄴㄹ[0@#$fsvjkeqaz사ckㅈ가rㅏㅣ2#@$ew4#@$q121ㄹㅇㄹ#$.,?<ㅡㅡㅡ:ㅃㅋEWRaw@df@ㅈㄱ3ㅃㄹㄹ빠ㅏㅏ:빨Da";

    @Override
    @Transactional
    public void saveVendor(SaveVendorRequest request) {

        // 유효성 검사
        b2bRepository.isDuplicatedVendorName(request.vendorName());

        //vendor 저장
        b2bRepository.saveVendor(b2bMapper.toVendorEntity(
                request,encoder.encode(request.password())
        ));
    }

    @Override
    @Transactional
    public void saveConsumer(SaveConsumerRequest request) {

        // 유효성 검사
        b2bRepository.isDuplicatedConsumerName(request.consumerName());

        // consumer 저장
        b2bRepository.saveConsumer(b2bMapper.toConsumerEntity(
                request,encoder.encode(request.password())
        ));

    }

    @Override
    public LoginResponse loginVendor(LoginRequest request) {
        // 이메일 조회
        VendorEntity vendorEntity = b2bRepository.findVendorByEmail(request.email());
        // 비밀번호 검증
        checkPassword(vendorEntity.getEncodedPassword(), request.password());

        String tokenFamilyId = UUID.randomUUID().toString();
        String accessToken = generateAccessToken(vendorEntity.getVendorSeq(), tokenFamilyId);
        String refreshToken = generateRefreshToken(vendorEntity.getVendorSeq(), tokenFamilyId);

        // refreshToken 저장

        return new LoginResponse(accessToken, refreshToken);
    }

    @Override
    public LoginResponse loginConsumer(LoginRequest request) {
        // 이메일 조회
        ConsumerEntity consumerEntity = b2bRepository.findConsumerByEmail(request.email());
        // 비밀번호 검증
        checkPassword(consumerEntity.getEncodedPassword(), request.password());

        // jwt 생성
        String tokenFamilyId = UUID.randomUUID().toString();
        String accessToken = generateAccessToken(consumerEntity.getConsumerSeq(), tokenFamilyId);
        String refreshToken = generateRefreshToken(consumerEntity.getConsumerSeq(), tokenFamilyId);

        // refreshToken 저장


        return  new LoginResponse(accessToken, refreshToken);
    }

    private void checkPassword(String password, String requestPassword) {
        if(!encoder.matches(password, requestPassword)) {
            throw new BadRequestException(BadRequestErrorResult.PASSWORD_BAD_REQUEST_EXCEPTION);
        }
    }

    private String generateAccessToken(Long seq, String tokenFamilyId) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + accessTokenExpiration);


        return Jwts.builder()
                .setSubject(String.valueOf(seq))
                .claim("tokenFamilyId", tokenFamilyId)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String generateRefreshToken(Long seq, String tokenFamilyId) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + refreshTokenExpiration);

        return Jwts.builder()
                .setSubject(String.valueOf(seq))
                .claim("tokenFamilyId", tokenFamilyId)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
}
