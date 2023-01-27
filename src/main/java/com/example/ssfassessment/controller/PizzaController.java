package com.example.ssfassessment.controller;

import java.util.function.ToDoubleFunction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.ssfassessment.model.Order;
import com.example.ssfassessment.service.PizzaService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Controller 
public class PizzaController {

@Autowired
private PizzaService pizzasvc;

@GetMapping(path="/")
    public String index(Model model){
        model.addAttribute("order", new Order());

        return "index";
    }

    @PostMapping("/pizza")
    public String pizzaSubmit(@Valid Order order, BindingResult result, Model model, HttpServletResponse response) {
        pizzasvc.save(order);
        System.out.println("order > " + order);

        Double pizzaprice = 0d;
        Double sizeprice = 0d;
        Double pizzasum = 0d;

        // Double pquantity = (Double) order.quantity;
     
        switch(order.pizza) {
            case "bella":
            case "marinana":
            case "spianatacalabrese":
                pizzaprice = 30.0;
            case "margherita":
                pizzaprice = 22.0;
            case "trioformaggio":
                pizzaprice = 25.0;
        }

        System.out.println(pizzaprice);
        switch(order.size) {
            case "sm":
                sizeprice = 1.0;
            case "md":
                sizeprice = 1.2;
            case "lg":
                sizeprice = 1.5;
        }

        pizzasum = pizzaprice * sizeprice;
        // pizzasum = pizzaprice * sizeprice * pquantity;
        System.out.println(pizzasum);
        model.addAttribute("order1", new Order());
        return "pizza";
        
    }

    @PostMapping("/order")
    public String orderSubmit(@Valid Order order, BindingResult result, Model model, HttpServletResponse response) {
        pizzasvc.save(order);
        System.out.println("Order > " + order);
    return "order";
    
    }
}
