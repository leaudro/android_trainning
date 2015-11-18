package com.leaudro.series.model;

import java.io.Serializable;

/**
 * Created by hemobile on 17/11/15.
 */
public class Cast implements Serializable {

    private Person person;

    private Persona character;

    public Person getPerson() {
        person.setCharacter(character);
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}