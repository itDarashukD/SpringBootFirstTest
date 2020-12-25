package com.example.webContent.controller;


import com.example.webContent.accesDB.Message;
import com.example.webContent.accesDB.User;
import com.example.webContent.repository.IMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private IMessageRepository iMessageRepository;


    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Message> messages = iMessageRepository.findAll();

        if (filter != null && !filter.isEmpty()) {
            messages = iMessageRepository.findByTag(filter);
        } else {
            messages = iMessageRepository.findAll();
        }

        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);

        return "main";
    }

    @PostMapping("/main")
    public String add(@AuthenticationPrincipal User user,
                      @RequestParam String text,
                      @RequestParam String tag, Map<String, Object> model) {
        Message message = new Message(text, tag, user);

        iMessageRepository.save(message);

        Iterable<Message> messages = iMessageRepository.findAll();

        model.put("messages", messages);
        model.put("filter", "");

        return "redirect:/main";
    }


    @PostMapping("id")
    public String getById(@RequestParam int id, Map<String, Integer> model) {

        model.put("id", id);

        return "main";

    }

}