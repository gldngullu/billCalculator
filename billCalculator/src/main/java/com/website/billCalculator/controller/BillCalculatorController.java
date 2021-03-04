package com.website.billCalculator.controller;

import com.website.billCalculator.model.CalculatedBill;
import com.website.billCalculator.model.Bill;
import com.website.billCalculator.service.BillCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController()
@RequestMapping("/bill")
public class BillCalculatorController {

    @Autowired
    BillCalculatorService billCalculatorService;

    @PostMapping("/calculate")
    public CalculatedBill calculateBill(@RequestBody Bill billCalculation){
        return billCalculatorService.calculateBill(billCalculation);
    }

    @GetMapping("/")
    public ArrayList<Bill> getExamples(){
        return billCalculatorService.createExampleBills();
    }
}
