package startwithco.b2bservice.client.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import startwithco.b2bservice.base.BaseTimeEntity;
import startwithco.b2bservice.vendor.domain.VendorEntity;

@Entity
@Table(name = "STAT_ENTITY")
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Getter
@SuperBuilder
public class ClientEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_seq")
    private Long clientSeq;

    @Column(name = "client_name", nullable = false)
    private String clientName;

    @Column(name = "logo_image_url", nullable = false)
    private String logoImageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_seq", nullable = false)
    private VendorEntity vendor;
}
