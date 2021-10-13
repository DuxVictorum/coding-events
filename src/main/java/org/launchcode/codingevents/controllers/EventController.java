package org.launchcode.codingevents.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("events")
public class EventController {

    private static HashMap<String, String> events = new HashMap<>();

    @GetMapping    // lives at: '/events'
    public String displayAllEvents(Model model) {
        events.put("LaunchCode Soiree", "Meet & Greet fundraiser");
        events.put("Coding Monkeys", "Weekly gathering of geeks");
        events.put("GatewayCoders 2021", "JavaScript party");
        model.addAttribute("events", events);
        return "events/index";
    }

    @GetMapping("create")   // lives at: '/events/create'
    public String renderNewEventForm() {
        return "events/create";
    }

    @PostMapping("create")  // lives at: '/events/create'
    public String handleNewEventForm(@RequestParam String eventName, @RequestParam String eventDesc) {
        if (!eventName.equals("")) {
            events.put(eventName, eventDesc);
        }
        return "redirect:";
    }
}
