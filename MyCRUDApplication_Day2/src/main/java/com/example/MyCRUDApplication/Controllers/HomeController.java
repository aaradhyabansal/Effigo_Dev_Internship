package com.example.MyCRUDApplication.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping({"","/"})
    public String index()
    {
        return "index";
    }
    @GetMapping("/contact")
    public String contact()
    {
        return "Contact";
    }
    @GetMapping("/privacy")
    public String privacy()
    {
        return "Privacy";
    }
}
