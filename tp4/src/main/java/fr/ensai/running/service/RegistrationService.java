package fr.ensai.running.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.ensai.running.model.Registration;
import fr.ensai.running.repository.RegistrationRepository;

@Service
public class RegistrationService {
    private static final Logger log = LoggerFactory.getLogger(RegistrationService.class);
    
    @Autowired
    private RegistrationRepository registrationRepository;

    /**
     * List all registrations for an athlete
     * 
     * @return a list of all registrations.
     */
    public Optional<List<Registration>> findByAthleteId(Long athleteId) {
        return Optional.of(registrationRepository.findByAthlete(athleteId));
    }


    public Optional<List<Registration>> findByCompetitionId(Long competitionId) {
        return Optional.of(registrationRepository.findByCompetition(competitionId));
    }
}
