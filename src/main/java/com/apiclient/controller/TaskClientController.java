package com.apiclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Controller
public class TaskClientController {

    @Value("${dashboard.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/")
    public String index(Model model) {
        // daily-dashboardのAPIからタスク一覧を取得
        List tasks = restTemplate.getForObject(apiUrl + "/api/tasks", List.class);
        model.addAttribute("tasks", tasks);
        return "index";
    }
}
