package nl.hu.s3.project;

import jakarta.transaction.Transactional;
import nl.hu.s3.project.chips.domain.ChipsAccount;
import nl.hu.s3.project.chips.data.ChipsRepository;
import nl.hu.s3.project.lobby.domain.Player;
import nl.hu.s3.project.lobby.data.PlayerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Transactional
public class StartupRunner implements CommandLineRunner {

    private final PlayerRepository playerDAO;
    private final ChipsRepository chips;

    public StartupRunner(PlayerRepository playerDAO, ChipsRepository chips) {
        this.playerDAO = playerDAO;
        this.chips = chips;
    }

    @Override
    public void run(String... args) throws Exception {
        if(playerDAO.findAll().isEmpty()){
            playerDAO.save(new Player("freezeybob", "Bob De Vries", "bob@example.com"));
            ChipsAccount account = chips.findOrCreate("freezeybob");
            account.deposit(100L);
            playerDAO.save(new Player("AliceDeVries", "Alice De Vries", "alice@example.com"));
            account = chips.findOrCreate("AliceDeVries");
            account.deposit(100L);
            playerDAO.save(new Player("bubbahotep", "Bubba Hotep", "swingingmummy@example.com"));
            chips.findOrCreate("bubbahotep");
        }
    }
}
