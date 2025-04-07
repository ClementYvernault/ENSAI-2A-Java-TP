package fr.ensai.running.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.ensai.running.model.Competition;
import fr.ensai.running.service.CompetitionService;

@Controller
@RequestMapping("/competition")
public class CompetitionController {

    @Autowired
    private CompetitionService competitionService;

    /**
     * Display all Competitions
     */
    @GetMapping()
    public String findAllCompetition(Model model) {
        model.addAttribute("competitions", competitionService.findAll());
        return "allCompetitions"; // allCompetition.html
    }

    /**
     * Redirection to the add form
     */
    @GetMapping("/addNew")
    public String addNewCompetition(Model model) {
        Competition competition = new Competition();
        model.addAttribute("competition", competition);
        return "createCompetition"; // createCompetition.html
    }

    /**
     * Find a Competition by id
     */
    @GetMapping("/competition/{id}")
    public Competition findCompetitionById(@PathVariable(value = "id") long id) {
        return competitionService.findById(id);
    }

    /**
     * Delete an Competition by id
     */
    @GetMapping("/delete/{id}")
    public String deleteCompetitionById(@PathVariable(value = "id") long id) {
        Competition competition = competitionService.findById(id);
        if (competition != null) {
            competitionService.deleteById(id);
        }
        return "redirect:/competition"; // redirection to endpoint /competition

    }
}