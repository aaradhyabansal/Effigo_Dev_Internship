package com.aaradhya.rest.webservices.restful_web_services.helloworld;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
public class HelloWorldController {
    private MessageSource messageSource;

    public HelloWorldController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @RequestMapping(path="/hello-world",method = RequestMethod.GET)
    public String helloWorld() {
        return "Hello World!";
    }
    @GetMapping(path="hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World Bean!");
    }
    @GetMapping(path="hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
        return new HelloWorldBean("Hello World "+name);
    }
    @GetMapping(path="/hello-world-i18ed")
    public String helloWorldintl() {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message", null,"Default Messsaeg",locale);
//        return new HelloWorldBean("Hello World Bean!");
    }
}
