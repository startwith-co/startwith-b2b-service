package startwithco.b2bservice.consumer.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "CONSUMER_ENTITY")
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Getter
@Builder
public class ConsumerEntity {
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

    /*
     * TODO
     *  회원 가입 시 입력 받는 산업군이 뭔지 확인해야함
     * */
    public enum SOLUTION {
        CRM,
        ERP,
        HRP
    }

    public enum INDUSTRY {
        MANUFACTURING,
        MEDICAL,
        LOGISTICS
    }
}
