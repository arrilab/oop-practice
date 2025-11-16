package org.example.s5_reflection.controller;

import org.example.s5_reflection.annotaion.Controller;
import org.example.s5_reflection.annotaion.RequestMapping;
import org.example.s5_reflection.annotaion.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HomeController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(HttpServletRequest req, HttpServletResponse rep) {
        return "home";
    }
}
