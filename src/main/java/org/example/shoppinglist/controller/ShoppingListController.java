package org.example.shoppinglist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ShoppingListController {
    @RequestMapping("/")
    public String index() {
        return "main";
    }

    @RequestMapping("/add")
    public String add() {
        return "add";
    }
}