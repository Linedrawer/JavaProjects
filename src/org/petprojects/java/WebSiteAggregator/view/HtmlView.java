package org.petprojects.java.WebSiteAggregator.view;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.petprojects.java.WebSiteAggregator.Controller;
import org.petprojects.java.WebSiteAggregator.vo.Vacancy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class HtmlView implements View {

    private final String filePath = "./src/" + this.getClass().getPackage().getName().replace('.', '/') + "/vacancies.html";

    private Controller controller;


    @Override
    public void update(List<Vacancy> vacancies) {

        try {
            updateFile(getUpdatedFileContent(vacancies));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }


    public void userCitySelectEmulationMethod() {
        controller.onCitySelect("Odessa");
    }


    private String getUpdatedFileContent(List<Vacancy> vacancies) {
        String fileContent = null;

        try {
            //get file
            Document document = getDocument();

            //find element(element with reference of template class in name
            Element templateElement = document.select(".template").first();
            //Make element copy
            Element cleanElement = templateElement.clone();
            //delete excess attributes and classes
            cleanElement.removeAttr("style");
            cleanElement.removeClass("template");
            //clean previously added jobs in this file
            document.select("tr[class=vacancy]").remove();

            //fill up vacancies
            for (Vacancy vacancy : vacancies) {

                //make new copy of cloned clean element
                Element vacancyElement = cleanElement.clone();
                //fill it up with current vacancies
                vacancyElement.getElementsByClass("city").first().text(vacancy.getCity());
                vacancyElement.getElementsByClass("companyName").first().text(vacancy.getCompanyName());
                vacancyElement.getElementsByClass("salary").first().text(vacancy.getSalary());
                //create link
                Element link = vacancyElement.getElementsByTag("a").first();
                link.text(vacancy.getTitle());
                link.attr("href", vacancy.getUrl());
                //add outerHtml element where data was put,
                //just before pattern <tr class="vacancy template" style="display: none">
                templateElement.before(vacancyElement.outerHtml());
            }

            //return full file content
            fileContent = document.html();



        } catch (IOException e) {
            e.printStackTrace();
            fileContent = "Some exception occurred";
        }


        return fileContent;
    }


    private void updateFile(String fileContent) throws IOException {

        FileWriter fileWriter = new FileWriter(filePath);
        fileWriter.write(fileContent);
        fileWriter.close();
    }


    protected Document getDocument() throws IOException {
        return Jsoup.parse(
                new File(filePath), "UTF-8");
    }
}
