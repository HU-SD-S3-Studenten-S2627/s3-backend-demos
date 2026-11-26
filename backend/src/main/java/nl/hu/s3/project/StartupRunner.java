package nl.hu.s3.project;

import jakarta.transaction.Transactional;
import nl.hu.s3.project.lobby.domain.Player;
import nl.hu.s3.project.lobby.data.PlayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
public class StartupRunner implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(StartupRunner.class);
    private DemoContainer demos;

    public StartupRunner(DemoContainer demos) {
        this.demos = demos;
    }

    @Override
    public void run(String... args) throws Exception {

        StopWatch stopWatch = new StopWatch();
        logger.debug("Inserting Players");
        stopWatch.start();
        this.demos.insertPlayers(5000);
//        this.demos.insertPlayersWithPreferences(500);
//        this.demos.weirdRelations();
        stopWatch.stop();
        logger.debug("Inserted Players took " + stopWatch.getTotalTimeMillis() + " ms");
    }
}
