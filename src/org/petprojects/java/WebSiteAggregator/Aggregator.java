package org.petprojects.java.WebSiteAggregator;

import org.petprojects.java.WebSiteAggregator.model.HHStrategy;
import org.petprojects.java.WebSiteAggregator.model.Model;
import org.petprojects.java.WebSiteAggregator.model.MoikrugStrategy;
import org.petprojects.java.WebSiteAggregator.model.Provider;
import org.petprojects.java.WebSiteAggregator.view.HtmlView;


public class Aggregator {

    public static void main(String[] args) {

        HtmlView view = new HtmlView();
        Provider providerHH = new Provider(new HHStrategy());
        Provider providerMK = new Provider(new MoikrugStrategy());
        Model model = new Model(view, new Provider[] {providerHH, providerMK});

        view.setController(new Controller(model));
        view.userCitySelectEmulationMethod();


    }

}
