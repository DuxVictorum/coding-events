package org.launchcode.codingevents.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
public class EventDetails extends AbstractEntity {

    @NotBlank(message="must enter description")
    @Size(max=500, message = "500 characters max")
    private String description;
    @NotBlank(message="must enter location")
    @Size(min=3, max=50, message="location should be 3-50 characters")
    private String location;
    @FutureOrPresent(message="event must not be in the past")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate eventDate;
    private boolean regRequired;
    @Positive(message="must be a positive number")
    private int numAttend;
    @NotBlank(message="must include email")
    @Email(message="invalid email format")
    private String contactEmail;

    public EventDetails(String description, String location, LocalDate eventDate,
                        boolean regRequired, int numAttend, String contactEmail) {

        this.description = description;
        this.location = location;
        this.eventDate = eventDate;
        this.regRequired = regRequired;
        this.numAttend = numAttend;
        this.contactEmail = contactEmail;
    }

    public EventDetails() { }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
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
        return description;
    }
}
