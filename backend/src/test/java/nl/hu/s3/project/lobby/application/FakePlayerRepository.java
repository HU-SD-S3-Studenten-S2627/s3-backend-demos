package nl.hu.s3.project.lobby.application;

import nl.hu.s3.project.lobby.data.PlayerRepository;
import nl.hu.s3.project.lobby.domain.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FakePlayerRepository implements PlayerRepository {

    private List<Player> players = new ArrayList<>();

    @Override
    public List<Player> findAll() {
        return Collections.unmodifiableList(players);
    }

    @Override
    public Player findById(String username) {
        return players.stream().filter(p -> p.getUsername().equals(username)).findAny().orElse(null);
    }

    @Override
    public Player save(Player player) {
        this.players.add(player);

        return player;
    }

    @Override
    public void delete(Player player) {
        this.players.remove(player);
    }
}
