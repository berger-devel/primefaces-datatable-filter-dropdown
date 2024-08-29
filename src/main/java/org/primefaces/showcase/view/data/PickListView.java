package org.primefaces.showcase.view.data;

import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.PostConstruct;
//import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
//import jakarta.inject.Inject;
//import jakarta.inject.Named;

//import org.primefaces.event.SelectEvent;
import org.primefaces.event.TransferEvent;
//import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DualListModel;
import org.primefaces.showcase.domain.Country;
import org.primefaces.showcase.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

//@Named
//@RequestScoped
@Component("pickListView")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class PickListView {

//    @Inject
    CountryService service;

//    private DualListModel<String> cities;
    private DualListModel<Country> countries;

    @Autowired
    public PickListView(CountryService service) {
        this.service = service;
    }

    @PostConstruct
    public void init() {
        //Cities
//        List<String> citiesSource = new ArrayList<>();
//        List<String> citiesTarget = new ArrayList<>();
//
//        citiesSource.add("San Francisco");
//        citiesSource.add("London");
//        citiesSource.add("Paris");
//        citiesSource.add("Istanbul");
//        citiesSource.add("Berlin");
//        citiesSource.add("Barcelona");
//        citiesSource.add("Rome");
//
//        cities = new DualListModel<>(citiesSource, citiesTarget);

        //Countries
        List<Country> countriesSource = service.getCountries().subList(0, 10);
        List<Country> countriesTarget = new ArrayList<>();

        countries = new DualListModel<>(countriesSource, countriesTarget);

    }

//    public DualListModel<String> getCities() {
//        return cities;
//    }
//
//    public void setCities(DualListModel<String> cities) {
//        this.cities = cities;
//    }

    public CountryService getService() {
        return service;
    }

    public void setService(CountryService service) {
        this.service = service;
    }

    public DualListModel<Country> getCountries() {
        return countries;
    }

    public void setCountries(DualListModel<Country> countries) {
        this.countries = countries;
    }

    public void onTransfer(TransferEvent event) {
//        StringBuilder builder = new StringBuilder();
//        for (Object item : event.getItems()) {
//            builder.append(((Country) item).getName()).append("<br />");
//        }

        FacesMessage msg = new FacesMessage();
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        msg.setSummary("Items Transferred");
//        msg.setDetail(builder.toString());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}