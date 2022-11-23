package at.kaindorf.bank.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class Adresse {
    @Id
    @GeneratedValue
    @Column(name = "adresse_id")
    private int id;
    @NonNull
    private String hausnummer;
    @NonNull
    private String ort;
    @NonNull
    private String plz;
    @NonNull
    private String strasse;
}
