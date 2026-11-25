package nl.hu.s3.project.lobby.domain;

import jakarta.persistence.*;

@Entity
/**
 * Persistence entity for player profile information.
 * Getters and setters are provided for compatibility with JPA and update operations.
 */
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

//    @Id
    @Column(name = "username")
    private String username;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "mail")
    private String emailAddress;

    protected Player() {
    }

    public Player(String username, String fullName, String emailAddress) {
        if(username == null){
            throw new NullPointerException("username");
        }
        if(fullName == null){
            throw new NullPointerException("fullName");
        }
        if(emailAddress == null){
            throw new NullPointerException("emailAddress");
        }
        this.username = username;
        this.fullName = fullName;
        this.emailAddress = emailAddress;
    }

    public Player(String username) {
        this(username, "", "");
    }

    public String getUsername() {
        return username;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void update(String fullName, String emailAddress){
        this.emailAddress = emailAddress;
        this.fullName = fullName;
    }

}
