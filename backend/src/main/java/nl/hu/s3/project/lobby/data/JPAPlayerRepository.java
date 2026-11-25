package nl.hu.s3.project.lobby.data;

import jakarta.persistence.EntityManager;
import nl.hu.s3.project.lobby.domain.Player;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
/**
 * Repository providing CRUD operations for players.
 * The implementation uses EntityManager directly to provide full control over persistence.
 */
public class JPAPlayerRepository implements PlayerRepository {
    private final EntityManager entities;

    public JPAPlayerRepository(EntityManager entities) {
        this.entities = entities;
    }

    @Override
    public List<Player> findAll() {
        List<Player> foundPlayers = entities
                .createQuery("SELECT p FROM Player p", Player.class)
                .getResultList();
        return foundPlayers;
    }

    @Override
    public Player findById(String username) {
        Player foundPlayer = entities.find(Player.class, username);
        return foundPlayer;
    }

    @Override
    public boolean existsById(String username) {
        // Reuse EntityManager.find for an efficient primary-key lookup.
        Player existingPlayer = entities.find(Player.class, username);
        return existingPlayer != null;
    }

    @Override
    public Player save(Player player) {
        entities.persist(player);
        return player;
    }

    @Override
    public void delete(Player player) {
        entities.remove(player);
    }

}
