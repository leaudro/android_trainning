package com.helabs.campbrasileiro.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.google.gson.annotations.SerializedName;

/**
 * Created by hemobile on 14/11/15.
 */
@Table(name = "Team")
public class Team extends Model {

    @Column(index = true, unique = true, name = "_id")
    private int id;

    @Column
    private String name;

    @Column
    @SerializedName("logo_url")
    private String urlLogo;

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

    public void setId(int id) {
        this.id = id;
    }

    public Team getById() {
        return new Select()
                .from(Team.class)
                .where("_id = ?", id)
                .executeSingle();
    }
}
