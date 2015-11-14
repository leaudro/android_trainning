package com.helabs.campbrasileiro.model;

/**
 * Created by hemobile on 14/11/15.
 */
public class Team {

    private int id;
    private String name;
    private String urlLogo;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
