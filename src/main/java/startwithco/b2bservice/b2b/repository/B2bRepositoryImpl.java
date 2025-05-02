package startwithco.b2bservice.b2b.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import startwithco.b2bservice.b2b.entity.ConsumerEntity;
import startwithco.b2bservice.b2b.entity.VendorEntity;
import startwithco.b2bservice.b2b.exception.conflict.ConflictErrorResult;
import startwithco.b2bservice.b2b.exception.conflict.ConflictException;

@Repository
@RequiredArgsConstructor
public class B2bRepositoryImpl implements B2bRepository {

    private final VendorJpaRepository vendorJpaRepository;
    private final ConsumerJpaRepository consumerJpaRepository;

    @Override
    public void isDuplicatedVendorName(String vendorName) {

        vendorJpaRepository.findByVendorName(vendorName)
                .ifPresent(entity -> {
                    throw new ConflictException(ConflictErrorResult.VENDOR_NAME_DUPLICATION_CONFLICT_EXCEPTION);
                });
    }

    @Override
    public VendorEntity saveVendor(VendorEntity vendorEntity) {
        return vendorJpaRepository.save(vendorEntity);
    }

    @Override
    public void isDuplicatedConsumerName(String consumerName) {
        consumerJpaRepository.findByConsumerName(consumerName)
                .ifPresent(entity -> {
                    throw new ConflictException(ConflictErrorResult.CONSUMER_NAME_DUPLICATION_CONFLICT_EXCEPTION);
                });
    }

    @Override
    public ConsumerEntity saveConsumer(ConsumerEntity consumerEntity) {
        return consumerJpaRepository.save(consumerEntity);
    }
}
