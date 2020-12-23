package com.example.webContent;


import com.example.webContent.accesDB.Message;
import com.example.webContent.repository.IMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class GreetingController {

    @Autowired
    private IMessageRepository iMessageRepository;


    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model ) {
        Iterable<Message> messages = iMessageRepository.findAll();

        model.put("messages", messages);

        return "main";
    }

    @PostMapping("/main")
    public String add(@RequestParam String text, @RequestParam String tag, Map<String, Object> model) {
        Message message = new Message(text, tag);

        iMessageRepository.save(message);

        Iterable<Message> messages = iMessageRepository.findAll();

        model.put("messages", messages);

        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        Iterable<Message> messages;

        if (filter != null && !filter.isEmpty()) {
            messages = iMessageRepository.findByTag(filter);
        } else {
            messages = iMessageRepository.findAll();
        }

        model.put("messages", messages);

        return "main";
    }

    @PostMapping("id")
    public String getById(@RequestParam int id,Map<String ,Integer> model){

        model.put("id",id);

        return "main";

    }

}