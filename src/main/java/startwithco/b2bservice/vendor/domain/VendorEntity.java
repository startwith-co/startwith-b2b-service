package startwithco.b2bservice.vendor.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import startwithco.b2bservice.base.BaseTimeEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "VENDOR_ENTITY")
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Getter
@Builder
public class VendorEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vendor_seq")
    private Long vendorSeq;

    @Column(name = "vendor_name", nullable = false)
    private String vendorName;

    @Column(name = "manager", nullable = false)
    private String manager;

    @Column(name = "phone_num", nullable = false)
    private String phoneNum;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String encodedPassword;

    @Column(name = "business_license_image", nullable = false)
    private String businessLicenseImage;

    @Column(name = "audit", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean audit = false;

    @Column(name = "vendor_image", nullable = true)
    private String vendorImage;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "business_hour", nullable = true)
    private String businessHour;

    @Column(name = "average_response_hour", nullable = true)
    private LocalDateTime averageResponseHour;
}
