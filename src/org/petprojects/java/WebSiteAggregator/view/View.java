package org.petprojects.java.WebSiteAggregator.view;

import org.petprojects.java.WebSiteAggregator.Controller;
import org.petprojects.java.WebSiteAggregator.vo.Vacancy;

import java.util.List;

public interface View {

    void update(List<Vacancy> vacancies);
    void setController(Controller controller);

}
