package nl.hu.s3.project.lobby.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class PlayerTests {

    @Test
    public void valuesCannotBeNull(){
        assertThrows(NullPointerException.class, () -> {
            Player p = new Player(null, null, null);
        });
    }
}
