package org.launchcode.codingevents.models;

import javax.validation.constraints.*;
import java.util.Objects;

public class Event {

    /* Event validation:
     * 1) name is 3-50 characters
     * 2) description has 500 char max
     */
    @NotBlank
    @Size(min=3, max=50, message="Event name must be between 3 and 50 characters")
    private String name;
    @NotBlank
    @Size(max=500, message = "Must include description, 500 characters max")
    private String description;
    @Email(message="Invalid email, try again")
    private String contactEmail;
    private int uid;
    private static int nextId = 1;

    public Event(String name, String description, String contactEmail) {
        this.name = name;
        this.description = description;
        this.contactEmail = contactEmail;
        this.uid = nextId;
        nextId++;
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
