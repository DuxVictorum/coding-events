package org.launchcode.codingevents.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

@MappedSuperclass   // Ensures that the extending classes will still get id values stored
public abstract class AbstractEntity {

    @Id
    @GeneratedValue
    private int id;

    public AbstractEntity() {
    }

    public int getId() { return id; }
    public void setId(int newID) { this.id = newID; }   // Will this even work now that it's managed by ORM?

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity entity = (Event) o;
        return getId() == entity.getId();
    }
    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
