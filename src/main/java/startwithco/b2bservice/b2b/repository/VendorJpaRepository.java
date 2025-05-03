package startwithco.b2bservice.b2b.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import startwithco.b2bservice.b2b.entity.VendorEntity;

import java.util.Optional;

@Repository
public interface VendorJpaRepository extends JpaRepository<VendorEntity, Long> {

    Optional<VendorEntity> findByVendorName(String vendorName);
    Optional<VendorEntity> findByEmail(String email);
}
