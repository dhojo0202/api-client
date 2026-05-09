package com.apiclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TaskClientController {

    @Value("${dashboard.api.url}")
    private String apiUrl;

    // PATCHに対応したRestTemplate
    private final RestTemplate restTemplate;

    public TaskClientController() {
        this.restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
    }

    // タスク一覧表示
    @GetMapping("/")
    public String index(Model model) {
        List<?> tasks = restTemplate.getForObject(apiUrl + "/api/tasks", List.class);
        model.addAttribute("tasks", tasks);
        return "index";
    }

    // タスク追加
    @PostMapping("/tasks")
    public String create(@RequestParam String title,
                         @RequestParam(required = false) String dueDate) {
        Map<String, String> body = new HashMap<>();
        body.put("title", title);
        if (dueDate != null && !dueDate.isBlank()) {
            body.put("dueDate", dueDate);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);

        restTemplate.postForObject(apiUrl + "/api/tasks", request, String.class);
        return "redirect:/";
    }

    // 完了切り替え
    @PostMapping("/tasks/{id}/complete")
    public String toggleComplete(@PathVariable Long id) {
        restTemplate.exchange(apiUrl + "/api/tasks/" + id + "/complete", HttpMethod.PATCH, null, String.class);
        return "redirect:/";
    }

    // 削除
    @PostMapping("/tasks/{id}/delete")
    public String delete(@PathVariable Long id) {
        restTemplate.delete(apiUrl + "/api/tasks/" + id);
        return "redirect:/";
    }
}
