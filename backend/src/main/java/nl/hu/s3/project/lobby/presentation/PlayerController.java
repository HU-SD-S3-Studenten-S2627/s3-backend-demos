package nl.hu.s3.project.lobby.presentation;

import jakarta.transaction.Transactional;
import nl.hu.s3.project.lobby.application.PlayerResponse;
import nl.hu.s3.project.lobby.application.PlayerService;
import nl.hu.s3.project.lobby.domain.Player;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/players")
/**
 * Controller for player management and authentication functionality.
 * This controller provides a RESTful API while keeping response construction in one place.
 */
public class PlayerController {
    private final PlayerService players;

    public PlayerController(PlayerService players) {
        this.players = players;
    }

    @GetMapping
    public List<PlayerResponse> getPlayers() {
        return this.players.findAll();
    }

    @PostMapping
    public ResponseEntity<PlayerResponse> addPlayer(@RequestBody PlayerResponse request) {
        // Validate all required input fields before accessing the database.
        validate(request.username(), request.name(), request.email());

        PlayerResponse response = this.players.addPlayer(request.username(), request.name(), request.email());
        URI location = URI.create("/players/" + request.username());
        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("/{name}")
    public PlayerResponse getPlayer(@PathVariable String name) {
        if(!this.players.exists(name)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return this.players.find(name);
    }

    @PutMapping("/{username}")
    public PlayerResponse updatePlayer(@PathVariable String username, @RequestBody UpdatePlayer request) {
        // Validate first so invalid data is never passed to the repository.
        validate(username, request.name(), request.email());
        if(!this.players.exists(username)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return this.players.update(username, request.name(), request.email());

    }

    @DeleteMapping("/{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removePlayer(@PathVariable String name) {
        if(!this.players.exists(name)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        players.delete(name);
    }


    private static void validate(String username, String name, String email) {
        // Perform explicit checks to provide user-friendly validation messages.
        if (username == null || username.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "username is empty");
        }
        if (name == null || name.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "name is empty");
        }
        if (email == null || email.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "email is empty");
        }
    }


}
