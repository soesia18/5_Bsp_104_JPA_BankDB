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
@DiscriminatorValue("SPAR")
public class Sparkonto extends Konto {
    @Column(precision = 19, scale = 2)
    private BigDecimal guthabenzinssatz;

    public Sparkonto(@NonNull long number, @NonNull BigDecimal kontostand, BigDecimal guthabenzinssatz) {
        super(number, kontostand);
        this.guthabenzinssatz = guthabenzinssatz;
    }
}
