package startwithco.b2bservice.consumer.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import startwithco.b2bservice.base.BaseTimeEntity;

@Entity
@Table(name = "CONSUMER_ENTITY")
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Getter
@Builder
public class ConsumerEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "consumer_seq")
    private Long consumerSeq;

    @Column(name = "consumer_name", nullable = false)
    private String consumerName;

    @Column(name = "encoded_password", nullable = false)
    private String encodedPassword;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "industry", nullable = false)
    private String industry;

    @Column(name = "company_image", nullable = true)
    private String companyImage;
}
