package com.example.demo.controller;

import com.example.demo.domain.Message;
import com.example.demo.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Slf4j
public class GreetingController {

    private final MessageRepository messageRepository;

    @GetMapping("/")
    public String greeting(Model model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter,  Model model) {
        log.info("GGGGGGGGGGEEEEEEEEEEEEETTTTTTTTTTTTTTTTTTT______________________");
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

    @PostMapping("/main")
    public String add(@RequestParam String text,
                      @RequestParam String tag,
                      Model model) {
        log.info("PPPPPPPPPPPPPPPPPPPPPPPPOOOOOOOOOOOOOOOOOSSSSSSSSSSSSTTTTTTTTTTTTT--------");
        Message message = new Message(text, tag);
        messageRepository.save(message);

        Iterable<Message> messages = messageRepository.findAll();

        model.addAttribute("messages", messages);

        return "main";
    }

//    @GetMapping("/login")
//    public String loginPage(@RequestParam String username,
//                            @RequestParam String password) {
//        log.info("Have username = " + username + ", password = " + password);
//        return "main";
//    }

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
