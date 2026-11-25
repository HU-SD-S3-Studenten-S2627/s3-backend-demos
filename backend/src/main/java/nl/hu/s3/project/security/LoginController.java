package nl.hu.s3.project.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import nl.hu.s3.project.lobby.domain.Player;
import nl.hu.s3.project.lobby.data.PlayerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Date;
import java.time.Instant;

import static java.time.temporal.ChronoUnit.DAYS;
import static nl.hu.s3.project.security.SecurityConfig.SECRET;

@RestController
public class LoginController {

    private final PlayerRepository players;

    public LoginController(PlayerRepository players) {
        this.players = players;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        // Look up the player by the supplied username.
        Player player = players.findById(request.username());
        if (player == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        // NOTE: A real production application should use a secure password encoder and database storage.
        String expectedPassword = request.username() + "pwd";
        if (!request.password().equals(expectedPassword)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        // Create a signed JWT that remains valid for one day.
        var builder = Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(SECRET.getBytes()), Jwts.SIG.HS512)
                .issuer("HU-SD-S3")
                .expiration(Date.from(Instant.now().plus(1, DAYS)))
                .subject(player.getUsername());
        if (player.getUsername().equals("Player0")) {
            // Assign the admin role to the configured administrator account.
            builder = builder.claim("role", "ADMIN");
        }
        return new LoginResponse(builder.compact());
    }

}
