package nl.hu.s3.project.lobby.data;

import nl.hu.s3.project.lobby.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository2 extends JpaRepository<Player, String> {
}
