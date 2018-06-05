package org.petprojects.java.WebSiteAggregator.model;

import org.petprojects.java.WebSiteAggregator.vo.Vacancy;

import java.util.List;

public class Provider {

    private Strategy strategy;

    public Provider(Strategy strategy) {
        this.strategy = strategy;
    }

    //methods

    public List<Vacancy> getJavaVacancies(String searchString) {
        return strategy.getVacancies(searchString);
    }


    //Getters and setters

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
}
