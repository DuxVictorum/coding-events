package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.models.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("events")
public class EventController {

    private static List<Event> events = new ArrayList<>();

    @GetMapping    // lives at: '/events'
    public String displayAllEvents(Model model) {
        model.addAttribute("events", events);
        return "events/index";
    }

    @GetMapping("create")   // lives at: '/events/create'
    public String renderNewEventForm() {
        return "events/create";
    }

    @PostMapping("create")  // lives at: '/events/create'
    public String handleNewEventForm(@RequestParam String eventName,
                                     @RequestParam String eventDescription) {
        if (!eventName.equals("")) {
            events.add(new Event(eventName, eventDescription));
        }
        return "redirect:";
    }
}
