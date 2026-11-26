package nl.hu.s3.project.lobby.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Game {

    public Long getId() {
        return id;
    }

    @Id
    @GeneratedValue
    private Long id;

    public Game(){}

    @OneToMany(mappedBy = "game")
//    @OneToMany()
//    @JoinColumn(name = "some_game_id")
    private List<Player> players = new ArrayList<>();

    public List<Player> getPlayers() {
        return players;
    }
}
