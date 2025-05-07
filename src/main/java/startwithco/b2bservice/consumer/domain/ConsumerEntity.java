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

    @Enumerated(EnumType.STRING)
    @Column(name = "industry", nullable = false)
    private INDUSTRY industry;

    public enum INDUSTRY {
        MANUFACTURING,         // 제조
        LOGISTICS,             // 물류/유통
        IT_TELECOMMUNICATION,  // 정보통신/IT
        CONSTRUCTION,          // 건설
        FINANCE,               // 금융
        REAL_ESTATE,           // 부동산/임대
        ACCOMMODATION,         // 숙박
        FOOD_SERVICE,          // 요식/외식
        ARTS,                  // 예술
        SPORTS,                // 스포츠
        ENVIRONMENT_ENERGY,    // 환경/에너지
        EDUCATION,             // 교육
        PUBLIC,                // 공공
        AGRICULTURE_FORESTRY_FISHERY // 농업/임업/어업
    }
}
