package dubbelf.fr.ludotechtp.bo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "clients")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noClient;

    @Column(nullable = false, length = 50)
    @NonNull
    private String nom;

    @Column(nullable = false, length = 50)
    @NonNull
    private String prenom;

    @Column(nullable = false, length = 50)
    @NonNull
    private String email;

    @Column(nullable = false, length = 50)
    @NonNull
    private String noTelephone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "no_adresse", referencedColumnName = "noAdresse")
    private Adresse adresse;
}
