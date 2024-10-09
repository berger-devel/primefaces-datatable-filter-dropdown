package org.primefaces.devel;

import jakarta.annotation.PostConstruct;
import jakarta.faces.model.SelectItem;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Named
@ViewScoped
public class TestView implements Serializable {

    private TestData propertiesModel;
    private List<TestObject> objects;
    private TestObject currentObject;
    private boolean displayProperties;
    private PropertyCategory categoryFilter;

    @PostConstruct
    public void init() {
        propertiesModel = new TestData();
        objects = IntStream.range(0, 7).mapToObj(
                i -> new TestObject(i, propertiesModel.getWrappedData().get(i % propertiesModel.count(Collections.emptyMap())))
        ).toList();
    }

    public List<TestObject> getObjects() {
        return objects;
    }

    public TestData getPropertiesModel() {
        return propertiesModel;
    }

    public TestObject getCurrentObject() {
        return currentObject;
    }

    public boolean isDetailView() {
        return currentObject != null;
    }

    public void showDetails(String objectId) {
        currentObject = objects.stream().filter(o -> o.getId().equals(objectId)).findFirst().orElse(null);
    }

    public void closeDetails() {
        displayProperties = false;
        currentObject = null;
    }

    public boolean isDisplayProperties() {
        return displayProperties;
    }

    public void showProperties()
    {
        displayProperties = true;
        categoryFilter = currentObject.getProperty().getCategory();
    }

    public void hideProperties() {
        displayProperties = false;
    }

    public List<SelectItem> getCategoryFilters() {
        return Stream.of(PropertyCategory.values()).map(SelectItem::new).toList();
    }

    public void setCategoryFilter(PropertyCategory categoryFilter) {
        this.categoryFilter = categoryFilter;
    }

    public PropertyCategory getCategoryFilter() {
        return categoryFilter;
    }

    public void assignProperty(PropertyOfObject property) {
        hideProperties();
        setCategoryFilter(null);
        currentObject.setProperty(property);
    }
}
