package startwithco.b2bservice.b2b.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "B2B")
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Getter
@Builder(toBuilder = true)
public class B2BEntity {
    @Id
    @Column(name = "b2b_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long b2bSeq;
}
