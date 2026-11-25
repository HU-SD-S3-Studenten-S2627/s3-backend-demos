package nl.hu.s3.project.lobby.application;

import java.util.ArrayList;
import java.util.List;

/**
 * Response object containing the public profile information for a player.
 */
public record PlayerResponse(String username, String name, String email, List<PreferenceDTO> preferences) {
    public PlayerResponse(String username, String name, String email){
        this(username, name, email, new ArrayList<>());
    }

}
