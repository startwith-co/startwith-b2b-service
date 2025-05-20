package startwithco.b2bservice.stat.domain;

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
public class StatEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stat_seq")
    private Long statSeq;

    @Column(name = "label", nullable = false)
    private String label;

    @Column(name = "percentage", nullable = false)
    private Long percentage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_seq")
    private VendorEntity vendor;

    @Enumerated(EnumType.STRING)
    @Column(name = "stat_type", nullable = false)
    private STAT_TYPE statType;

    public enum STAT_TYPE {
        SALES_SCALE,
        INDUSTRY_TYPE,
    }
}
