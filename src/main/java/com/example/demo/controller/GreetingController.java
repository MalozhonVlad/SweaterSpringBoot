package com.example.demo.controller;

import com.example.demo.domain.Message;
import com.example.demo.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class GreetingController {

    private final MessageRepository messageRepository;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
                           Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping
    public String main(@RequestParam(required = false, defaultValue = "") String filter,  Model model) {
        Iterable<Message> messages;

        if (filter != null && !filter.isEmpty()) {
            messages = messageRepository.findByTag(filter);
        } else {
            messages = messageRepository.findAll();
        }

        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);

        return "main";
    }

    @PostMapping
    public String add(@RequestParam String text,
                      @RequestParam String tag,
                      Model model) {
        Message message = new Message(text, tag);
        messageRepository.save(message);

        Iterable<Message> messages = messageRepository.findAll();

        model.addAttribute("messages", messages);

        return "main";
    }

//    @PostMapping("/filter")
//    public String filter(@RequestParam String filter, Model model) {
//        Iterable<Message> messages;
//
//        if (filter != null && !filter.isEmpty()) {
//            messages = messageRepository.findByTag(filter);
//        } else {
//            messages = messageRepository.findAll();
//        }
//        model.addAttribute("messages", messages);
//
//        return "main";
//    }


}
