package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventData;
import org.launchcode.codingevents.models.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("events")
public class EventController {

    @GetMapping    // lives at: '/events'
    public String displayAllEvents(Model model) {
        if (EventData.getAll().size() == 0) {
            EventData.add(new Event("GatewayCon 3000", "Local gathering of coders", "ardvark@hotmail.com"));
            EventData.add(new Event("SkunkWay Programmers", "Smells nice anyway", "babydigz@snuffles.org"));
        }
        model.addAttribute("events", EventData.getAll());
        return "events/index";
    }

    @GetMapping("create")   // lives at: '/events/create'
    public String renderNewEventForm(Model model) {
        model.addAttribute("title", "Create Event");
        model.addAttribute("event", new Event());
        return "events/create";
    }

    @PostMapping("create")  // lives at: '/events/create'
    public String handleNewEventForm(@ModelAttribute @Valid Event newEvent, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Create Event");
            return "events/create";
        }
        EventData.add(newEvent);
        return "redirect:";
    }
    @GetMapping("delete")
    public String displayDeleteEventForm(Model model) {
        model.addAttribute("title", "Delete Events");
        model.addAttribute("events", EventData.getAll());
        return "events/delete";
    }
    @PostMapping("delete")
    public String handleDeleteEventform(@RequestParam(required = false) int[] eventIds) {
        if (eventIds != null) {
            for (int uid : eventIds) {
                EventData.remove(uid);
            }
        }
        return "redirect:";
    }
    @GetMapping("edit/{uid}")
    public String displayEditForm(Model model, @PathVariable int uid) {
        Event eventToEdit = EventData.getById(uid);
        model.addAttribute(eventToEdit);
        model.addAttribute("title", "Edit Event " + eventToEdit.getName() +
                " (id=" + uid + ")");
        return "events/edit";
    }
    @PostMapping("edit")
    public String processEditForm(int uid, String name, String description) {
        Event eventEdited = EventData.getById(uid);
        eventEdited.setName(name);
        eventEdited.setDescription(description);
        return "redirect:";
    }

}
