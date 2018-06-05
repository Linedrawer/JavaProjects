package org.petprojects.java.WebSiteAggregator.model;


import org.petprojects.java.WebSiteAggregator.view.View;
import org.petprojects.java.WebSiteAggregator.vo.Vacancy;

import java.util.ArrayList;
import java.util.List;

public class Model {

    private View view;
    private Provider[] providers;


    //constructor
    public Model(View view, Provider[] providers) {
        if (view == null || providers == null || providers.length == 0) {
            throw  new IllegalArgumentException();
        }
        this.view = view;
        this.providers = providers;
    }


    //methods
    public void selectCity(String city) {
        List<Vacancy> vacancies = new ArrayList<>();

        for (Provider provider : providers) {
            vacancies.addAll(provider.getJavaVacancies(city));
        }

        view.update(vacancies);
    }
}
