package org.primefaces.devel;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode
public class PropertyOfObject implements Serializable {
    private String id;
    private PropertyCategory category;

    public PropertyOfObject(Integer id, PropertyCategory category) {
        this.id = String.format("prop-%d", id);
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PropertyCategory getCategory() {
        return category;
    }

    public void setCategory(PropertyCategory category) {
        this.category = category;
    }

    public String toString() {
        return "PropertyOfObject [id=" + id + ", category=" + category + "]";
    }
}
