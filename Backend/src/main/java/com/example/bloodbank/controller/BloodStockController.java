package com.example.bloodbank.controller;

import com.example.bloodbank.model.BloodStock;
import com.example.bloodbank.model.Role;
import com.example.bloodbank.model.User;
import com.example.bloodbank.service.BloodStockService;
import com.example.bloodbank.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blood")
@CrossOrigin("*")
public class BloodStockController {

    private final BloodStockService bloodService;
    private final UserService userService;

    public BloodStockController(BloodStockService bloodService,
                                UserService userService) {
        this.bloodService = bloodService;
        this.userService = userService;
    }

    // HOSPITAL ONLY
    @PostMapping("/add/{userId}")
    public String addBlood(@PathVariable Long userId,
                           @RequestBody BloodStock stock) {

        User user = userService.getUserById(userId);

        if (user != null && user.getRole() == Role.HOSPITAL) {
            stock.setHospitalId(userId);   // ✅ VERY IMPORTANT
            bloodService.addStock(stock);
            return "Blood stock added successfully";
        }
        return "Access denied (only hospital)";
    }


    // USER & HOSPITAL
    @GetMapping("/search/{group}")
    public List<BloodStock> search(@PathVariable String group) {
        return bloodService.searchByGroup(group);
    }

    // HOSPITAL ONLY
    @DeleteMapping("/delete/{userId}/{stockId}")
    public String delete(@PathVariable Long userId,
                         @PathVariable Long stockId) {

        User user = userService.getUserById(userId);

        if (user != null && user.getRole() == Role.HOSPITAL) {
            bloodService.deleteStock(stockId);
            return "Blood stock deleted";
        }
        return "Access denied";
    }
}
