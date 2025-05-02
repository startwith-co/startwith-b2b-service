package startwithco.b2bservice.b2b.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import startwithco.b2bservice.b2b.dto.B2bRequest;
import startwithco.b2bservice.b2b.repository.B2bRepository;
import startwithco.b2bservice.b2b.service.mapper.B2bMapper;

import static startwithco.b2bservice.b2b.dto.B2bRequest.*;

@Service
@RequiredArgsConstructor
public class B2bServiceImpl implements B2bService{

    private final B2bRepository b2bRepository;
    private final B2bMapper b2bMapper;
    private final BCryptPasswordEncoder encoder;

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
}
