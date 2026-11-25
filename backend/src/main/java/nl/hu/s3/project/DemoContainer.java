package nl.hu.s3.project;

import jakarta.transaction.Transactional;
import nl.hu.s3.project.lobby.data.PlayerRepository;
import nl.hu.s3.project.lobby.domain.Player;
import nl.hu.s3.project.lobby.domain.Preference;
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
                Player p = new Player("Player" + i);
                players.save(p);
            }
        }
    }

    public void insertPlayersWithPreferences(int total) {
        if(players.findAll().isEmpty()){
            for(int i=0; i<total; i++){
                Player p = new Player("Player" + i);
                p.addPreference(new Preference("RTS", (int)Math.floor(Math.random() * 10) + 1));
                p.addPreference(new Preference("Imsim", (int)Math.floor(Math.random() * 10) + 1));
                p.addPreference(new Preference("MOBA", (int)Math.floor(Math.random() * 10) + 1));
                players.save(p);
            }
        }
    }
}
