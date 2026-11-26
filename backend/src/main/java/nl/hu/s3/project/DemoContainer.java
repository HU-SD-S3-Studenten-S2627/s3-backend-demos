package nl.hu.s3.project;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import nl.hu.s3.project.lobby.data.PlayerRepository;
import nl.hu.s3.project.lobby.domain.Game;
import nl.hu.s3.project.lobby.domain.Player;
import nl.hu.s3.project.lobby.domain.Preference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Transactional
public class DemoContainer {
    private final PlayerRepository players;
    private Logger logger = LoggerFactory.getLogger(DemoContainer.class);

    private EntityManager entities;

    public DemoContainer(PlayerRepository playerDao, EntityManager entities){
        this.players = playerDao;
        this.entities = entities;
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

    public void weirdRelations(){
        Player p1 = new Player("Bob");
        this.players.save(p1);
        Player p2 = new Player("Fred");
        this.players.save(p2);

        Game g = new Game();
        g.getPlayers().add(p1);
        p2.setGame(g);

        this.entities.persist(g);
        this.entities.flush();
        this.entities.clear();

        Game gFound = this.entities.find(Game.class, g.getId());


        logger.debug("Created game: " + g.getPlayers());
        logger.debug("Resulting game: " + gFound.getPlayers());


    }
}
