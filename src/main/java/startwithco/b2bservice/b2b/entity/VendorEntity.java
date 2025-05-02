package startwithco.b2bservice.b2b.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "VENDOR")
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Getter
@Builder(toBuilder = true)
public class VendorEntity {

    @Id
    @Column(name = "vendor_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vendorSeq;
    @Column(name = "vendor_name", nullable = false)
    private String vendorName;
    @Column(name = "phone_num", nullable = false)
    private String phoneNum;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "password", nullable = false)
    private String encodedPassword;

}
