package nl.hu.s3.project;

import jakarta.transaction.Transactional;
import nl.hu.s3.project.lobby.domain.Player;
import nl.hu.s3.project.lobby.data.PlayerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Transactional
public class StartupRunner implements CommandLineRunner {

    private final PlayerRepository playerDAO;

    public StartupRunner(PlayerRepository playerDAO) {
        this.playerDAO = playerDAO;
    }

    @Override
    public void run(String... args) throws Exception {
        if(playerDAO.findAll().isEmpty()){
            playerDAO.save(new Player("freezeybob", "Bob De Vries", "bob@example.com"));
            playerDAO.save(new Player("AliceDeVries", "Alice De Vries", "alice@example.com"));
            playerDAO.save(new Player("bubbahotep", "Bubba Hotep", "swingingmummy@example.com"));
        }
    }
}
