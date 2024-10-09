package org.primefaces.test;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TestData extends LazyDataModel<PropertyOfObject> {

    private final List<PropertyOfObject> properties;

    public TestData() {
        properties = IntStream.range(0, 100).mapToObj(i ->
                new PropertyOfObject(i, PropertyCategory.values()[i % PropertyCategory.values().length])).toList();
        setWrappedData(properties);
    }

    @Override
    public List<PropertyOfObject> load(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
        Stream<PropertyOfObject> propertiesStream = properties.stream();
        FilterMeta filterMeta = filterBy.get("category");
        if (filterMeta != null) {
            propertiesStream = propertiesStream.filter(property -> Objects.equals(filterMeta.getFilterValue(), property.getCategory()));
        }
        setWrappedData(propertiesStream.skip(first).limit(pageSize).toList());
        return getWrappedData();
    }

    @Override
    public int count(Map<String, FilterMeta> map) {
        return properties.size();
    }
}
