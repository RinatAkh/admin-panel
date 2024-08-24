package org.example.entity;

import org.example.entity.dao.FilePlayerDao;
import org.example.entity.dao.PlayerDao;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Player player1 = new Player();
        player1.setId(1L);
        Player player2 = new Player();
        player2.setId(2L);
        Player player3 = new Player();
        player3.setId(3L);
        Player player4 = new Player();
        player4.setId(4L);

        List<Player> listOfPlayers = new ArrayList<>();
        listOfPlayers.add(player1);
        listOfPlayers.add(player2);
        listOfPlayers.add(player3);
        listOfPlayers.add(player4);

        PlayerDao playerDao = new FilePlayerDao();
        playerDao.savePlayers(listOfPlayers);
        List<Player> listOfPlayersDao = playerDao.getListOfPlayers();
        System.out.println(listOfPlayersDao);
    }
}
