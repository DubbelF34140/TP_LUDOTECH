package dubbelf.fr.ludotechtp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RetourLocationResponseDTO {
    private Long noFacture;
    private LocalDate datePaiement;
    private Boolean estPaye;
    private List<LocationDetailsDTO> locationsRetournees;
    private double montantTotal;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LocationDetailsDTO {
        private Integer noLocation;
        private LocalDate dateDebut;
        private LocalDate dateRetour;
        private double tarifJour;
        private int nombreJours;
        private double montantLocation;
        private String titreJeu;
        private String codeBarreExemplaire;
        private String nomClient;
    }
}