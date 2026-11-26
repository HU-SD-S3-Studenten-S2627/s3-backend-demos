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

    private int measure;

    protected Preference(){}

    public Preference(String name, int value) {
        this.name = name;
        this.measure = value;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getMeasure() {
        return measure;
    }
}
