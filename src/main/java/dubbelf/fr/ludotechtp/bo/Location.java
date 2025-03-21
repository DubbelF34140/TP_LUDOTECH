package dubbelf.fr.ludotechtp.bo;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "locations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer noLocation;

    @Column(nullable = false)
    private LocalDate dateDebut;

    @Column
    private LocalDate dateRetour;

    @Column(nullable = false)
    private double tarifJour;

    @ManyToOne
    @JoinColumn(name = "no_client", referencedColumnName = "noClient", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "no_exemplaire", referencedColumnName = "noExemplaire", nullable = false)
    private Exemplaire exemplaire;
}
