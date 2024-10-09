package org.primefaces.devel;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class TestObject implements Serializable {

    private String id;
    private PropertyOfObject property;

    public TestObject(Integer id, PropertyOfObject property) {
        this.id = String.format("obj-%d", id);
        this.property = property;
    }

    public String getId() {
        return id;
    }

    public PropertyOfObject getProperty() {
        return property;
    }

    public void setProperty(PropertyOfObject property) {
        this.property = property;
    }

    public String toString() {
        return "TestObject [id=" + id + ", property=" + property + "]";
    }
}
