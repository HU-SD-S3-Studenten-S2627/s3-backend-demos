package nl.hu.s3.project.lobby.presentation;

/**
 * Request object used to update the editable details of an existing player.
 */
public record UpdatePlayer(String name, String email) {
}
