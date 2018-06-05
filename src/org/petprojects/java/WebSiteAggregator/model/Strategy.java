package org.petprojects.java.WebSiteAggregator.model;

import org.petprojects.java.WebSiteAggregator.vo.Vacancy;

import java.util.List;

public interface Strategy {

    List<Vacancy> getVacancies(String searchString);
}
