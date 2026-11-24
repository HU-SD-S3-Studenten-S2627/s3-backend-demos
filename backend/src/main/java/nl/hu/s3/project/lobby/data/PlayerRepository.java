package nl.hu.s3.project.lobby.data;

import nl.hu.s3.project.lobby.domain.Player;

import java.util.List;

public interface PlayerRepository {
    List<Player> findAll();

    Player findById(String username);

    default boolean existsById(String username){
        return this.findById(username) != null;
    }

    Player save(Player player);

    void delete(Player player);
}
