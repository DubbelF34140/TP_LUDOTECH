package dubbelf.fr.ludotechtp.mapper;

import dubbelf.fr.ludotechtp.bo.Facture;
import dubbelf.fr.ludotechtp.bo.Location;
import dubbelf.fr.ludotechtp.dto.RetourLocationResponseDTO;
import dubbelf.fr.ludotechtp.dto.RetourLocationResponseDTO.LocationDetailsDTO;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

public class RetourLocationMapper {

    public static RetourLocationResponseDTO toResponseDTO(Facture facture, List<Location> locations) {
        List<LocationDetailsDTO> locationDetails = locations.stream()
                .map(RetourLocationMapper::toLocationDetailsDTO)
                .collect(Collectors.toList());

        double montantTotal = locationDetails.stream()
                .mapToDouble(LocationDetailsDTO::getMontantLocation)
                .sum();

        return new RetourLocationResponseDTO(
                facture.getNoFacture(),
                facture.getDatePaiement(),
                facture.getPayer(),
                locationDetails,
                montantTotal
        );
    }

    private static LocationDetailsDTO toLocationDetailsDTO(Location location) {
        LocalDate dateDebut = location.getDateDebut();
        LocalDate dateRetour = location.getDateRetour() != null ? location.getDateRetour() : LocalDate.now();

        // Calculer le nombre de jours (minimum 1)
        long nombreJours = Math.max(1, ChronoUnit.DAYS.between(dateDebut, dateRetour));

        // Calculer le montant
        double montantLocation = nombreJours * location.getTarifJour();

        return new LocationDetailsDTO(
                location.getNoLocation(),
                dateDebut,
                dateRetour,
                location.getTarifJour(),
                (int) nombreJours,
                montantLocation,
                location.getExemplaire().getJeu().getTitre(),
                location.getExemplaire().getCodebarre(),
                location.getClient().getNom() + " " + location.getClient().getPrenom()
        );
    }
}