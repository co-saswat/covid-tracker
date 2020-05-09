package com.saswat.mycovidapplication;

import java.util.List;

public class CountryModel {
    private String flag,country,cases,death,todaycases,todaydeaths,recovered,activecases,criticalcases;

    public CountryModel() {
    }

    public CountryModel(String flag, String country, String cases, String death, String todaycases, String todaydeaths, String recovered, String activecases, String criticalcases) {
        this.flag = flag;
        this.country = country;
        this.cases = cases;
        this.death = death;
        this.todaycases = todaycases;
        this.todaydeaths = todaydeaths;
        this.recovered = recovered;
        this.activecases = activecases;
        this.criticalcases = criticalcases;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public String getDeath() {
        return death;
    }

    public void setDeath(String death) {
        this.death = death;
    }

    public String getTodaycases() {
        return todaycases;
    }

    public void setTodaycases(String todaycases) {
        this.todaycases = todaycases;
    }

    public String getTodaydeaths() {
        return todaydeaths;
    }

    public void setTodaydeaths(String todaydeaths) {
        this.todaydeaths = todaydeaths;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getActivecases() {
        return activecases;
    }

    public void setActivecases(String activecases) {
        this.activecases = activecases;
    }

    public String getCriticalcases() {
        return criticalcases;
    }

    public void setCriticalcases(String criticalcases) {
        this.criticalcases = criticalcases;
    }
}
