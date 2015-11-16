package com.helabs.campbrasileiro.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by hemobile on 14/11/15.
 */
@Table(name = "Team")
public class Team extends Model {

    @Column(unique = true)
    private String name;

    @Column
    private String urlLogo;

    public Team() {

    }

    public Team(String name, String urlLogo) {
        this.name = name;
        this.urlLogo = urlLogo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlLogo() {
        return urlLogo;
    }

    public void setUrlLogo(String urlLogo) {
        this.urlLogo = urlLogo;
    }
}
