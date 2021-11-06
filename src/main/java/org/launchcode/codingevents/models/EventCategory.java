package org.launchcode.codingevents.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
public class EventCategory extends AbstractEntity {

    @NotBlank(message="must not be blank")
    @Size(min=3, message="at least 3 characters")
    private String name;

    public EventCategory(String name) {
        this.name = name;
    }
    public EventCategory() { }

//    Getter Setter Salad
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Override
    public String toString() {
        return this.getName();
    }

}
