package org.launchcode.codingevents.data;

import org.launchcode.codingevents.models.Event;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

//  This class exists to store data (since we haven't learned SQL yet)
public class EventData {

    private static final Map<Integer, Event> events = new HashMap<>();

    //    Get all events
    public static Collection<Event> getAll() {
        return events.values();
    }
    //    Get a single event
    public static Event getById(int uid) {
        return events.get(uid);
    }
    //    Add an event
    public static void add(Event event) {
        events.put(event.getUid(), event);
    }
    //    Remove an event
    public static void remove(int uid) {
        events.remove(uid);
    }
}
