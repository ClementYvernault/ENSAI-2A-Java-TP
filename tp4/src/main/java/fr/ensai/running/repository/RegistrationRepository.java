package fr.ensai.running.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.ensai.running.model.Athlete;
import fr.ensai.running.model.Competition;
import fr.ensai.running.model.Registration;   

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    // Liste toutes les inscriptions d’un athlète
    List<Registration> findByAthlete(Long athleteId);

    // Liste toutes les inscriptions à une compétition
    List<Registration> findByCompetition(Long competitionId);

     // Trouve une inscription à partir d’un athlète et d’une compétition
     Optional<Registration> findByAthleteAndCompetition(Athlete athlete, Competition competition);

     Optional<Registration> findByCompetition(Long competitionId);

}