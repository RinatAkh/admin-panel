package org.example.entity.dao;

import org.example.entity.Player;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class FilePlayerDao implements PlayerDao {

    private final String filePath = "/Users/rinatahmetgariev/Desktop/Код/admin-panel-for-rpg/listOfPlayers.txt";
    @Override
    public void savePlayers(List<Player> listOfOrders) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filePath))){
            objectOutputStream.writeObject(listOfOrders);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Player> getListOfPlayers() {
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            return  (List<Player>) objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
