package org.launchcode.codingevents.models;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class EventCategory extends AbstractEntity {

    @OneToMany(mappedBy = "eventCategory")
    private final List<Event> events = new ArrayList<>();

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
    public List<Event> getEvents() { return events; }

    @Override
    public String toString() {
        return this.getName();
    }

}
