package com.helabs.campbrasileiro.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by hemobile on 14/11/15.
 */
@Table(name = "Match")
public class Match extends Model {

    @Column(index = true, unique = true, name = "_id")
    private int id;

    @Column
    @SerializedName("home_team_score")
    private int homeTeamScore;

    @Column
    @SerializedName("away_team_score")
    private int awayTeamScore;

    @Column
    @SerializedName("home_team")
    private Team homeTeam;

    @Column
    @SerializedName("away_team")
    private Team awayTeam;

    @Column
    private int round;

    @Column
    @SerializedName("season_id")
    private int seasonId;

    @Column
    private String place;

    @Column
    private Date date;

    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    public void setHomeTeamScore(int homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    public int getAwayTeamScore() {
        return awayTeamScore;
    }

    public void setAwayTeamScore(int awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(int seasonId) {
        this.seasonId = seasonId;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Match getById() {
        return new Select()
                .from(Match.class)
                .where("_id = ?", id)
                .executeSingle();
    }
}