package com.leaudro.series.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by hemobile on 17/11/15.
 */
@DatabaseTable
public class Person extends Persona {

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Persona character;

    public Persona getCharacter() {
        return character;
    }

    public void setCharacter(Persona character) {
        this.character = character;
    }
}
