package org.launchcode.codingevents.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.*;
import java.time.LocalDate;
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
    @Size(max=500, message = "500 characters max")
    private String description;
    @ManyToOne
    @NotNull(message="must include a valid category")
    private EventCategory eventCategory;
    @NotBlank(message="must enter location")
    @Size(min=3, max=50, message="location should be 3-50 characters")
    private String location;
    @FutureOrPresent(message="event must not be in the past")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate eventDate;
    private boolean regRequired;
    @Positive(message="must be a positive number")
    private int numAttend;
    @NotBlank(message="must enter description")
    @Email(message="invalid email format")
    private String contactEmail;



    public Event() { }

    public Event(String name, String description, EventCategory eventCategory, String location, LocalDate eventDate, boolean regRequired, int numAttend, String contactEmail) {
        this.name = name;
        this.description = description;
        this.eventCategory = eventCategory;
        this.location = location;
        this.eventDate = eventDate;
        this.regRequired = regRequired;
        this.numAttend = numAttend;
        this.contactEmail = contactEmail;
    }

    //  Getter Setter Salad
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public EventCategory getEventCategory() { return eventCategory; }
    public void setEventCategory(EventCategory eventCategory) { this.eventCategory = eventCategory; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public LocalDate getEventDate() { return eventDate; }
    public void setEventDate(LocalDate aDate) { this.eventDate = aDate; }
    public boolean isRegRequired() { return regRequired; }
    public void setRegRequired(boolean regRequired) { this.regRequired = regRequired; }
    public int getNumAttend() { return numAttend; }
    public void setNumAttend(int aNum) { this.numAttend = aNum; }
    public String getContactEmail() { return contactEmail; }
    public void setContactEmail(String newEmail) {
        this.contactEmail = newEmail;
    }

    @Override
    public String toString() {
        return this.getName();
    }


}
