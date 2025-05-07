package startwithco.b2bservice.vendor.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "VENDOR_ENTITY")
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Getter
@Builder
public class VendorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vendor_seq")
    private Long vendorSeq;

    @Column(name = "vendor_name", nullable = false)
    private String vendorName;

    @Column(name = "phone_num", nullable = false)
    private String phoneNum;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "encoded_password", nullable = false)
    private String encodedPassword;

    @Column(name = "business_license_image", nullable = false)
    private String businessLicenseImage;
}
