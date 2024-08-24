package org.example.entity;


import org.example.entity.dao.FilePlayerDao;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {
    private FilePlayerDao filePlayerDao = new FilePlayerDao();

//1. получать список всех зарегистрированных игроков;
    public List<Player> getListOfPlayers() {
        return filePlayerDao.getListOfPlayers();
    }
//2. создавать нового игрока;
    public void createNewPlayer(String name, String title, Race race, Profession profession,
                               Date birthday, Boolean banned, Integer experience) {
        List<Player> listOfPlayers = filePlayerDao.getListOfPlayers();
        Player player = new Player();
        player.setName(name);
        player.setTitle(title);
        player.setRace(race);
        player.setProfession(profession);
        player.setBirthday(birthday);
        player.setBanned(banned);
        player.setExperience(experience);

        listOfPlayers.add(player);
        filePlayerDao.savePlayers(listOfPlayers);
    }
//3. редактировать характеристики существующего игрока;
    public void changePlayerProperties(Long id, Player player) {
        List<Player> listOfPlayers = filePlayerDao.getListOfPlayers();
        int index = 0;
        for(int i = 0; i < listOfPlayers.size(); i++) {
            if(listOfPlayers.get(i).getId() == id) {
                index = i;
                break;
            }
        }
        listOfPlayers.add(index, player);
    }
//4. удалять игрока
    public void deletePlayer(Long id) {
        List<Player> listOfPlayers = filePlayerDao.getListOfPlayers();
        int index = 0;
        for(int i = 0; i < listOfPlayers.size(); i++) {
            if(listOfPlayers.get(i).getId().equals(id)) {
                index = i;
                break;
            }
        }
        listOfPlayers.remove(index);
        filePlayerDao.savePlayers(listOfPlayers);
    }

//5. получать игрока по id;
    public Player getPlayerById(Long id) {
        List<Player> listOfPlayers = filePlayerDao.getListOfPlayers();
        return listOfPlayers.stream()
                .filter(player -> player.getId().equals(id))
                .findFirst()
                .get();
    }
//6. получать отфильтрованный список игроков в соответствии с переданными фильтрами;
    public List<Player> getListOfFilteredPlayers(String name, String title, Race race, Profession profession,
                                                 Long after, Long before, Boolean banned, Integer minExperience,
                                                 Integer maxExperience, Integer minLevel, Integer maxLevel) {
        List<Player> listOfPlayers = filePlayerDao.getListOfPlayers();
        Date after1 = new Date(after);
        Date before1 = new Date(before);
        return listOfPlayers.stream()
                .filter(player -> name == null || player.getName().contains(name))
                .filter(player -> title == null || player.getTitle().equals(title))
                .filter(player -> race == null || player.getRace().equals(race))
                .filter(player -> profession == null || player.getProfession().equals(profession))
                .filter(player -> banned == null || player.getBanned().equals(banned))
                .filter(player -> minLevel == null || player.getLevel() >= minLevel)
                .filter(player -> maxLevel == null || player.getLevel() <= maxLevel)
                .filter(player -> minExperience == null || player.getExperience() >= minExperience)
                .filter(player -> maxExperience == null || player.getExperience() <= maxExperience)
                .filter(player -> after == 0 || player.getBirthday().after(after1))
                .filter(player -> before == 0 || player.getBirthday().before(before1))
                .collect(Collectors.toList());
    }
//7. получать количество игроков, которые соответствуют фильтрам.
    public Integer getNumberOfFilteredPlayers(String name, String title, Race race, Profession profession,
                                              Long after, Long before, Boolean banned, Integer minExperience,
                                              Integer maxExperience, Integer minLevel, Integer maxLevel) {

        List<Player> listOfPlayers = filePlayerDao.getListOfPlayers();
        List<Player> listOfFilteredPlayers  = getListOfFilteredPlayers(name, title, race, profession, after, before,
                banned, minExperience, maxExperience, minLevel, maxLevel);
    return listOfFilteredPlayers.size();
    }
}
