package nl.hu.s3.project.lobby.data;

import jakarta.persistence.EntityManager;
import nl.hu.s3.project.lobby.domain.Player;
import nl.hu.s3.project.lobby.domain.Preference;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class PlayerPersistenceTests {

    @Autowired
    private EntityManager entities;

    @Test
    public void canSavePlayer(){
        Player p = new Player("Bob", "Bob de Vries", "bob@example.org");
        entities.persist(p);
        entities.flush();
        entities.clear();
        Player found = entities.find(Player.class, p.getUsername());

        assertEquals(p.getFullName(), found.getFullName());
        assertEquals(p.getEmailAddress(), found.getEmailAddress());
    }

    @Test
    public void canSavePlayerWithPreferences(){
        Player p = new Player("Bob", "Bob de Vries", "bob@example.org");
        p.addPreference(new Preference("Testbaarheid", 0));
        entities.persist(p);
        entities.flush();
        entities.clear();
        Player found = entities.find(Player.class, p.getUsername());

        assertEquals(p.getFullName(), found.getFullName());
        assertEquals(p.getEmailAddress(), found.getEmailAddress());
    }
}
