package nl.hu.s3.project.security;

/**
 * Request object containing the credentials supplied during login.
 */
public record LoginRequest(String username, String password) {
}
