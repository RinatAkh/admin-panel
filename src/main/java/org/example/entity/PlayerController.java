package org.example.entity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/rest/players")
    public void getPlayerListController() {
        playerService.getListOfPlayers();
    }

    @GetMapping("/rest/players/count") // ДОПИСАТЬ В ПАРАМЕТРАХ REQUESTPARAM
    public Integer getPlayerCount(String name, String title, Race race, Profession profession,
                               Long after, Long before, Boolean banned, Integer minExperience,
                               Integer maxExperience, Integer minLevel, Integer maxLevel) {
        return playerService.getNumberOfFilteredPlayers(name, title, race, profession, after, before,
                banned, minExperience, maxExperience, minLevel, maxLevel);
    }

    @GetMapping("/rest/players/{id}")
    public Player getPlayerByIdController(@PathVariable("id") Long id) {
        return playerService.getPlayerById(id);
    }


}
