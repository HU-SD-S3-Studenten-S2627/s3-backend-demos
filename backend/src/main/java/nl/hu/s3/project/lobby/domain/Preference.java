package nl.hu.s3.project.lobby.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Preference {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private int value;

    protected Preference(){}

    public Preference(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}
