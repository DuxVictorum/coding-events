package org.launchcode.codingevents.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Event extends AbstractEntity {

    /* Event validation:
     * 1) name is 3-50 characters
     * 2) description has 500 char max
     */
    @NotBlank(message="must provide event name")
    @Size(min=3, max=50, message="name should be 3-50 characters")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)    // This cascades actions (save, delete) down to
    @Valid      // This goes down *into* the object and checks validation at that sub-level too
    @NotNull
    private EventDetails eventDetails;

    @ManyToOne
    @NotNull(message="must include a valid category")
    private EventCategory eventCategory;

    @ManyToMany
    public final List<Tag> tags = new ArrayList();

    public Event() { }

    public Event(String name, EventCategory eventCategory) {
        this.name = name;
        this.eventCategory = eventCategory;
    }

    //  Getter Setter Salad
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public EventCategory getEventCategory() { return eventCategory; }
    public void setEventCategory(EventCategory eventCategory) { this.eventCategory = eventCategory; }
    public EventDetails getEventDetails() { return eventDetails; }
    public void setEventDetails(EventDetails eventDetails) { this.eventDetails = eventDetails; }
    public List<Tag> getTags() { return tags; }

    public void addTag(Tag tag) {
        this.tags.add(tag);
    }

    @Override
    public String toString() {
        return this.getName();
    }


}
