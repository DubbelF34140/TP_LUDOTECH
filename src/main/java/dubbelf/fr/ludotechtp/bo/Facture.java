package dubbelf.fr.ludotechtp.bo;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "factures")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Facture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noFacture;

    @Column
    private LocalDate datePaiement;

    @Column
    private Boolean payer;

    @OneToOne
    @JoinColumn(name = "no_location", referencedColumnName = "noLocation", nullable = false)
    private Location location;
}
