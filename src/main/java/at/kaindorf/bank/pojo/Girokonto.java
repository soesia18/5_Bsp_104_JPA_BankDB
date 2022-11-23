package at.kaindorf.bank.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("GIRO")
public class Girokonto extends Konto {
    @Column(precision = 19, scale = 2)
    private BigDecimal kreditlimit;
    @Column(precision = 19, scale = 2)
    private BigDecimal sollzinssatz;
    @Column(precision = 19, scale = 2)
    private BigDecimal ueberziehungszinssatz;

    public Girokonto(@NonNull long number, @NonNull BigDecimal kontostand, BigDecimal kreditlimit, BigDecimal sollzinssatz, BigDecimal ueberziehungszinssatz) {
        super(number, kontostand);
        this.kreditlimit = kreditlimit;
        this.sollzinssatz = sollzinssatz;
        this.ueberziehungszinssatz = ueberziehungszinssatz;
    }
}
