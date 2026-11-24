package nl.hu.s3.project.lobby.application;

import nl.hu.s3.project.lobby.data.PlayerRepository;
import nl.hu.s3.project.lobby.domain.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerServiceTests {

    @Test
    public void canCreatePlayer(){
        PlayerRepository repo = new FakePlayerRepository();
        PlayerService playerService = new PlayerService(repo);

        String bobsMail = "DatBobber44@example.org";
        playerService.addPlayer("Bob", "Bob De Vries", bobsMail);

        assertEquals(1, repo.findAll().size());
        assertEquals(bobsMail, repo.findById("Bob").getEmailAddress());
    }

    @Test
    public void canRemovePlayer(){
        PlayerRepository repo = new FakePlayerRepository();
        PlayerService playerService = new PlayerService(repo);

        repo.save(new Player("Bob"));
        Player theOtherOne = new Player("Fred");
        repo.save(theOtherOne);

        playerService.delete("Bob");

        assertEquals(1, repo.findAll().size());
        assertEquals(theOtherOne, repo.findAll().getFirst());
    }

    //etc.
}
