package com.example.ssfassessment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.ssfassessment.model.Order;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Controller
public class PizzaController {

@GetMapping(path="/")
    public String contactForm(Model model){
        model.addAttribute("order", new Order());
        return "index";
    }

    @GetMapping("/pizza")
    public String pizzaSubmit(@RequestParam String pizza, @RequestParam String Size,@RequestParam String Quantity, Order order,
                                BindingResult result, Model model, HttpServletResponse response){
        System.out.println("pizza page");
        if(result.hasErrors()){
            return "index";
        }

        return "pizza";
    
    }

    @PostMapping("/order")
    public String orderSubmit(@Valid Order order, BindingResult result, Model model, HttpServletResponse response) {
        System.out.println("order page");
        model.addAttribute("order", order);
    return "order";
    }
    // public String saveContact(@Valid Order order, BindingResult result, Model model, HttpServletResponse response){
    //     if(result.hasErrors()){
    //         return "contact";
    //     }
    //     // ctcRedisSvc.save(contact); //IF GOT REDISSERVICE THEN PUT
    //     response.setStatus(HttpServletResponse.SC_CREATED);   //this line is to change the status to 201 created
    //     return "pizza";
    // }
    
}
