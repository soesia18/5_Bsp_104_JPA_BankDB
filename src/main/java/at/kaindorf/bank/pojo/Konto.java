package at.kaindorf.bank.pojo;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "kontoart", discriminatorType = DiscriminatorType.STRING)
@ToString
@EqualsAndHashCode
public abstract class Konto {
    @Id
    @GeneratedValue
    @Column(name = "konto_id")
    private int id;
    @NonNull
    private long nummer;
    @NonNull
    @Column(precision = 19, scale = 2)
    private BigDecimal kontostand;

    @ManyToMany(mappedBy = "kontos")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Kunde> kunden = new ArrayList<>();
}
