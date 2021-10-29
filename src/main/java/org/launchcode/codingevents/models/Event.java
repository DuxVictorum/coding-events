package org.launchcode.codingevents.models;

import javax.validation.constraints.*;
import java.util.Objects;

public class Event {

    /* Event validation:
     * 1) name is 3-50 characters
     * 2) description has 500 char max
     */
    @NotBlank(message="must provide event name")
    @Size(min=3, max=50, message="name should be 3-50 characters")
    private String name;
    @Size(max=500, message = "500 characters max")
    private String description;
    @NotBlank(message="must enter description")
    @Email(message="invalid email format")
    private String contactEmail;
    @NotBlank(message="must enter location")
    @Size(min=3, max=50, message="location should be 3-50 characters")
    private String location;

    private int uid;
    private static int nextId = 1;

    public Event() {
        this.uid = nextId;
        nextId++;
    }

    public Event(String name, String description, String location, String contactEmail) {
        this();
        this.name = name;
        this.description = description;
        this.location = location;
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
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getContactEmail() { return contactEmail; }
    public void setContactEmail(String newEmail) {
        this.contactEmail = newEmail;
    }
    public int getUid() {
        return this.uid;
    }

    @Override
    public String toString() {
        return this.getName();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return getUid() == event.getUid();
    }
    @Override
    public int hashCode() {
        return Objects.hash(getUid());
    }

}
