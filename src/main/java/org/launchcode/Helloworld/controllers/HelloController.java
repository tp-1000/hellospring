package org.launchcode.Helloworld.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Tom
 */
@Controller //tells Springboot that there are methods in the class that handle the controller.
@RequestMapping(value = "base", method = {RequestMethod.GET})// if not setup then root path is used
//@RequestMapping("hello");
public class HelloController {

    //Handles request at path... /hello (said request handler lives at...)
    @GetMapping("hello") // handle get requests -- one for every type of HTTP request
    @ResponseBody // we wil only use this to "return plain text" no fancy stuff.
    public String Hello() {
        return "Hello, Spring";
    };

    //Handles request at path... /goodbye (said request handler lives at...)
    @GetMapping("goodbye") // handle get requests -- one for every type of HTTP request
    @ResponseBody // we wil only use this to "return plain text" no fancy stuff. -- default is to return template
    public String Goodbye() {
        return "Goodbye, Spring";
    };

    // create handler that handles requests of the form /hello?name=Launchcode ---- matches query param name and method param =
    @RequestMapping(value = "hello2", method = {RequestMethod.GET, RequestMethod.POST}) //  more general set up but can take multipul
    //@GetMapping("hello2")
    @ResponseBody
    public String helloWithQueryParam(@RequestParam String name, @RequestParam String lang) {
        return "<body style='background:#a7b6be'>" +
                "<p style='padding-top:60px; font-size:60pt; color:#da0b0b; text-align:center' >"+greetInLang(lang)+ " " + name + "!</p>" +
                "</body>";
    }

    // handles requests of the form /hello/Launchcode   ---- called a path parameter
    @GetMapping("hello/{name}")
    @ResponseBody
    public String helloWithPathParameter(@PathVariable String name) {
        return "hello " + name + "!!";
    }

    // handles requests by redirecting them of the form /hello/Launchcode   ---- called a path parameter (see below)
    @GetMapping("hello2/{name}")
    public String helloWithRedirectPathParameter(@PathVariable String name) {
        return "redirect:/base/" + name;
    }

    @GetMapping("/form")
    @ResponseBody
    public String helloForm() {
        return "<html>" +
                    "<body>" +
                        "<form action='hello2' method='POST'>" + //submit a request to /hello2
                            "<lable for='lang-select'>Choose a language:</lable>" +
                            "<select name='lang' id='lang-select'>" +
                                "<option value='en'>-- Please choose an option --</option>" +
                                "<option value='fr'>French</option>" +
                                "<option value='en'>English</option>" +
                                "<option value='gr'>German</option>" +
                                "<option value='ar'>Arabic</option>" +
                                "<option value='ch'>Chinese</option>" +
                            "</select>" +
                            "<input type='text' name='name' placeholder='your name'>" +
                            "<input type='submit' value='Greet me!'>" +
                        "</form>" +
                    "</body>" +
                "</html>";
    }
    public static String greetInLang(String lang) {
        String languageSpecificGreeting = "Hello";
        if(lang.equals("fr")){
            languageSpecificGreeting = "Bonjour";
        }
        if(lang.equals("gr")){
            languageSpecificGreeting = "Hallo";
        }
        if(lang.equals("ar")){
            languageSpecificGreeting = "مرحبا";
        }
        if(lang.equals("ch")){
            languageSpecificGreeting = "你好";
        }

        return languageSpecificGreeting;
    }
}
