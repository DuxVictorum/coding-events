package org.launchcode.codingevents.models;

import java.util.Objects;

public class Event {

    private String name;
    private String description;
    private int uid;
    private static int nextId = 1;

    public Event(String name, String description) {
        this.name = name;
        this.description = description;
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
