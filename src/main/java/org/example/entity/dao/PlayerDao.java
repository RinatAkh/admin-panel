package org.example.entity.dao;

import org.example.entity.Player;

import java.util.List;

public interface PlayerDao {
    void savePlayers(List<Player> listOfOrders);
    List<Player> getListOfPlayers();
}
