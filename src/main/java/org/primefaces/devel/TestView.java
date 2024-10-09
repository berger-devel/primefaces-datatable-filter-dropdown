package org.primefaces.test;

import jakarta.annotation.PostConstruct;
import jakarta.faces.model.SelectItem;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Named
@ViewScoped
public class TestView implements Serializable {

    private TestData propertiesModel;
    private List<PropertyOfObject> filteredProperties;
    private List<TestObject> objects;
    private List<TestObject> filteredObjects;
    private TestObject currentObject;
    private boolean displayProperties;
    private PropertyCategory categoryFilter;

    @PostConstruct
    public void init() {
        propertiesModel = new TestData();
        objects = IntStream.range(0, 7).mapToObj(
                i -> new TestObject(i, propertiesModel.getWrappedData().get(i % propertiesModel.count(null)))
        ).toList();
    }

    public List<TestObject> getObjects() {
        return objects;
    }

    public TestData getPropertiesModel() {
        return propertiesModel;
    }

    public void setFilteredProperties(List<PropertyOfObject> filteredProperties) {
        this.filteredProperties = filteredProperties;
    }

    public List<PropertyOfObject> getFilteredProperties() {
        return filteredProperties;
    }

    public TestObject getCurrentObject() {
        return currentObject;
    }

    public boolean isDetailView() {
        return currentObject != null;
    }

    public void viewDetails(String objectId) {
        currentObject = objects.stream().filter(o -> o.getId().equals(objectId)).findFirst().orElse(null);
    }

    public void closeDetails() {
        displayProperties = false;
        currentObject = null;
    }

    public boolean isDisplayProperties() {
        return displayProperties;
    }

    public void setDisplayProperties(boolean displayProperties) {
        this.displayProperties = displayProperties;
        categoryFilter = currentObject.getProperty().getCategory();
    }

    public void setCategoryFilter(PropertyCategory categoryFilter) {
        this.categoryFilter = categoryFilter;
    }

    public PropertyCategory getCategoryFilter() {
        return categoryFilter;
    }

    public List<SelectItem> getCategoryFilters() {
        return Stream.of(PropertyCategory.values()).map(SelectItem::new).toList();
    }

    public void assignCategory() {
        int a = 0;
    }

    public List<TestObject> getFilteredObjects() {
        return filteredObjects;
    }

    public void setFilteredObjects(List<TestObject> filteredObjects) {
        this.filteredObjects = filteredObjects;
    }
}
