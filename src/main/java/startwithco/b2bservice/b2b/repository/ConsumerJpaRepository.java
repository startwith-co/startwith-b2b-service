package startwithco.b2bservice.b2b.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import startwithco.b2bservice.b2b.entity.ConsumerEntity;

import java.util.Optional;

public interface ConsumerJpaRepository extends JpaRepository<ConsumerEntity, Long> {

    Optional<ConsumerEntity> findByConsumerName(String consumerName);

    Optional<ConsumerEntity> findByEmail(String email);
}

