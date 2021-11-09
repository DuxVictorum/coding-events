package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventCategoryRepository;
import org.launchcode.codingevents.data.EventRepository;
import org.launchcode.codingevents.models.Event;
import org.launchcode.codingevents.models.EventCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("events")
public class EventController {

    @Autowired      // Uses dependency injection; looks for a @Repository class and then "injects" it wherever needed
    private EventRepository eventRepository;

    @Autowired
    private EventCategoryRepository eventCategoryRepository;

    @GetMapping    // lives at: '/events'
    public String displayEvents(@RequestParam(required = false) Integer categoryId, Model model) {
        if (categoryId == null) {
            model.addAttribute("title", "All Events");
            model.addAttribute("events", eventRepository.findAll());
        } else {
            Optional<EventCategory> result = eventCategoryRepository.findById(categoryId);
            if (result.isEmpty()) {
                model.addAttribute("title", "Invalid Category ID: " + categoryId);
            } else {
                EventCategory category = result.get();
                model.addAttribute("title", "Events in category: " + category.getName());
                model.addAttribute("events", category.getEvents());
            }
        }
        return "events/index";
    }

    @GetMapping("create")   // lives at: '/events/create'
    public String renderNewEventForm(Model model) {
        model.addAttribute("title", "Create Event");
        model.addAttribute("event", new Event());
        model.addAttribute("categories", eventCategoryRepository.findAll());
        return "events/create";
    }

    @PostMapping("create")  // lives at: '/events/create'
    public String handleNewEventForm(@ModelAttribute @Valid Event newEvent, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Create Event");
            System.out.println(newEvent.getEventCategory());
            model.addAttribute("categories", eventCategoryRepository.findAll());
            return "events/create";
        }
        eventRepository.save(newEvent);
        return "redirect:";
    }
    @GetMapping("delete")
    public String displayDeleteEventForm(Model model) {
        model.addAttribute("title", "Delete an event from the list");
        model.addAttribute("events", eventRepository.findAll());
        return "events/delete";
    }
    @PostMapping("delete")
    public String handleDeleteEventform(@RequestParam(required = false) int[] eventIds) {
        if (eventIds != null) {
            for (int id : eventIds) {
                eventRepository.deleteById(id);
            }
        }
        return "redirect:";
    }

//    ** Don't bother with Edit functionality right now **

//    @GetMapping("edit/{id}")
//    public String displayEditForm(Model model, @PathVariable int id) {
//        Event eventToEdit = EventData.getById(id);
//        model.addAttribute("event", eventToEdit);
//        model.addAttribute("title", "Edit Event " + eventToEdit.getName() +
//                " (id=" + id + ")");
//        model.addAttribute("types", EventType.values());
//        return "events/edit";
//    }
//    @PostMapping("edit")
//    public String processEditForm(@ModelAttribute @Valid Event eventToEdit, Errors errors, Model model, int id) {
////        System.out.println(id);      -->  Testing for debugging
//        eventToEdit.setUid(id);
//        if (errors.hasErrors()) {
//            model.addAttribute("title", "Edit Event " + eventToEdit.getName() +
//                    " (id=" + eventToEdit.getId() + ")");
//            return "events/edit";
//        }
//        Event eventEdited = EventData.getById(id);
//        eventEdited.setName(eventToEdit.getName());
//        eventEdited.setDescription(eventToEdit.getDescription());
//        eventEdited.setType(eventToEdit.getType());
//        eventEdited.setLocation(eventToEdit.getLocation());
//        eventEdited.setEventDate(eventToEdit.getEventDate());
//        eventEdited.setRegRequired(eventToEdit.isRegRequired());
//        eventEdited.setNumAttend(eventToEdit.getNumAttend());
//        eventEdited.setContactEmail(eventToEdit.getContactEmail());
//        return "redirect:";
//    }

}
