package startwithco.b2bservice.b2b.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "CONSUMER")
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Getter
@Builder(toBuilder = true)
public class ConsumerEntity {

    @Id
    @Column(name = "consumer_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long consumerSeq;
    @Column(name = "consumer_name", nullable = false)
    private String consumerName;
    @Column(name = "password", nullable = false)
    private String encodedPassword;
    @Column(name = "industry", nullable = false)
    private String industry;
    @Column(name = "email", nullable = false)
    private String email;

}
