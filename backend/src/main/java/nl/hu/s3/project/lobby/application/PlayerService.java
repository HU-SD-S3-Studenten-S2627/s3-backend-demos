package nl.hu.s3.project.lobby.application;

import jakarta.transaction.Transactional;
import nl.hu.s3.project.lobby.data.PlayerRepository;
import nl.hu.s3.project.lobby.domain.Player;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class PlayerService {

    private PlayerRepository players;

    public PlayerService(PlayerRepository players){
        this.players = players;
    }

    public boolean exists(String username){
        return this.players.findById(username) != null;
    }

    public List<PlayerResponse> findAll() {
        // Build response objects manually so persistence entities are not leaked to the API.
        List<PlayerResponse> result = new ArrayList<>();
        for (Player player : players.findAll()) {
            result.add(response(player));
        }
        return result;
    }

    private static PlayerResponse response(Player player) {
        PlayerResponse result = new PlayerResponse(
                player.getUsername(),
                player.getFullName(),
                player.getEmailAddress(),
                player.getPreferences().stream().map(p -> new PreferenceDTO(p.getName(), p.getMeasure())).toList()
        );
        return result;
    }

    public PlayerResponse addPlayer(String username, String name, String email) {
        Player player = players.save(new Player(username, name, email));
        return response(player);
    }

    private Player findInternal(String username) {
        return players.findById(username);
    }

    public void delete(String name) {
        this.players.delete(findInternal(name));
    }

    public PlayerResponse find(String name){
        return response(findInternal(name));
    }

    public PlayerResponse update(String username, String name, String email) {
        Player p = findInternal(username);
        p.update(name, email);
        return response(p);
    }
}
