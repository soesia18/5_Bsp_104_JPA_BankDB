package at.kaindorf.bank.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class Kunde {
    @Id
    @GeneratedValue
    @Column(name = "kunde_id")
    private int id;
    @NonNull
    private Date geburtsdatum;
    @NonNull
    private char geschlecht;
    @NonNull
    private int kundennummer;
    @NonNull
    private String nachname;
    @NonNull
    private String vorname;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "wohnort_id")
    private Adresse adresse;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(inverseJoinColumns = @JoinColumn(name = "kunden_id"), joinColumns = @JoinColumn(name = "konten_id"))
    @JsonIgnore
    private List<Konto> kontos = new ArrayList<>();
}
