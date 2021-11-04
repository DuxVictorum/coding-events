package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventData;
import org.launchcode.codingevents.models.Event;
import org.launchcode.codingevents.models.EventType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("events")
public class EventController {

    @GetMapping    // lives at: '/events'
    public String displayAllEvents(Model model) {
        if (EventData.getAll().size() == 0) {
            EventData.add(new Event("GatewayCon 3000", "Local gathering of coders", EventType.MEETUP, "Chesterfield", LocalDate.parse("2021-11-06"), true, 45, "ardvark@hotmail.com"));
            EventData.add(new Event("SkunkWay Programmers", "Smells nice anyway", EventType.CONFERENCE, "Kirkwood", LocalDate.parse("2022-02-25"), true, 89342, "babydigz@snuffles.org"));
        }
        model.addAttribute("events", EventData.getAll());
        return "events/index";
    }

    @GetMapping("create")   // lives at: '/events/create'
    public String renderNewEventForm(Model model) {
        model.addAttribute("title", "Create Event");
        model.addAttribute("event", new Event());
        model.addAttribute("types", EventType.values());
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
        model.addAttribute("event", eventToEdit);
        model.addAttribute("title", "Edit Event " + eventToEdit.getName() +
                " (id=" + uid + ")");
        model.addAttribute("types", EventType.values());
        return "events/edit";
    }
    @PostMapping("edit")
    public String processEditForm(@ModelAttribute @Valid Event eventToEdit, Errors errors, Model model, int uid) {
//        System.out.println(uid);      -->  Testing for debugging
        eventToEdit.setUid(uid);
        if (errors.hasErrors()) {
            model.addAttribute("title", "Edit Event " + eventToEdit.getName() +
                    " (id=" + eventToEdit.getUid() + ")");
            return "events/edit";
        }
        Event eventEdited = EventData.getById(uid);
        eventEdited.setName(eventToEdit.getName());
        eventEdited.setDescription(eventToEdit.getDescription());
        eventEdited.setType(eventToEdit.getType());
        eventEdited.setLocation(eventToEdit.getLocation());
        eventEdited.setEventDate(eventToEdit.getEventDate());
        eventEdited.setRegRequired(eventToEdit.isRegRequired());
        eventEdited.setNumAttend(eventToEdit.getNumAttend());
        eventEdited.setContactEmail(eventToEdit.getContactEmail());
        return "redirect:";
    }

}
