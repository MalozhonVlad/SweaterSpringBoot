package com.example.demo.controller;

import com.example.demo.domain.Message;
import com.example.demo.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final MessageRepository messageRepository;

    @GetMapping("/")
    public String greeting(Model model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Model model) {
        Iterable<Message> messages = messageRepository.findAll();

        model.addAttribute("messages", messages);
        return "main";
    }

    @PostMapping("/main")
    public String add(@RequestParam String text,
                      @RequestParam String tag,
                      Model model) {
        Message message = new Message(text, tag);
        messageRepository.save(message);

        Iterable<Message> messages = messageRepository.findAll();

        model.addAttribute("messages", messages);

        return "main";
    }

    @PostMapping("/filter")
    public String filter(@RequestParam String filter, Model model) {
        Iterable<Message> messages;

        if (filter != null && !filter.isEmpty()) {
            messages = messageRepository.findByTag(filter);
        } else {
            messages = messageRepository.findAll();
        }
        model.addAttribute("messages", messages);

        return "main";
    }


}
