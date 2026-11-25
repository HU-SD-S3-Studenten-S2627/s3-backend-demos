package nl.hu.s3.project;

import jakarta.transaction.Transactional;
import nl.hu.s3.project.lobby.data.PlayerRepository;
import nl.hu.s3.project.lobby.domain.Player;
import org.springframework.stereotype.Component;

@Component
@Transactional
public class DemoContainer {
    private final PlayerRepository players;

    public DemoContainer(PlayerRepository playerDao){
        this.players = playerDao;
    }

    public void insertPlayers(int total) {
        if(players.findAll().isEmpty()){
            for(int i=0; i<total; i++){
                players.save(new Player("Player" + i));
            }
        }
    }
}
