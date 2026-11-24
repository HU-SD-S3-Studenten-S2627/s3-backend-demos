package nl.hu.s3.project.lobby.application;

/**
 * Response object containing the public profile information for a player.
 */
public record PlayerResponse(String username, String name, String email) {
}
