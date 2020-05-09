package com.saswat.mycovidapplication;

public class StatesModel {
    private String stateName,stateCases,stateDeath,stateRecovered;

    public StatesModel() {
    }

    public StatesModel(String stateName, String stateCases, String stateDeath, String stateRecovered) {
        this.stateName = stateName;
        this.stateCases = stateCases;
        this.stateDeath = stateDeath;
        this.stateRecovered = stateRecovered;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getStateCases() {
        return stateCases;
    }

    public void setStateCases(String stateCases) {
        this.stateCases = stateCases;
    }

    public String getStateDeath() {
        return stateDeath;
    }

    public void setStateDeath(String stateDeath) {
        this.stateDeath = stateDeath;
    }

    public String getStateRecovered() {
        return stateRecovered;
    }

    public void setStateRecovered(String stateRecovered) {
        this.stateRecovered = stateRecovered;
    }
}
