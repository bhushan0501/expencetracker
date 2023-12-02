package com.expensetracker.expencetrack.Controller;

import com.expensetracker.expencetrack.Entity.Expense;
import com.expensetracker.expencetrack.Service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;
    // Method for listing down the new expense
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Expense> createExpense(@RequestBody Expense expense) {
        //  log.info("Received POST request with expense: {}", expense);
        Expense createdExpense = expenseService.createExpense(expense);
        return new ResponseEntity<>(createdExpense, HttpStatus.CREATED);
    }
    // Method for updating an expense
    @PutMapping("/update/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable String id, @RequestBody Expense expense) {
        Expense updatedExpense = expenseService.updateExpense(id, expense);
        if (updatedExpense != null) {
            return new ResponseEntity<>(updatedExpense, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    // Method for deleting specific expense using id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable String id) {
        boolean deleted = expenseService.deleteExpense(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }

    }
    // Method for getting all the expense
    @GetMapping("/getall")
    public ResponseEntity<List<Expense>> getAllExpenses() {
        List<Expense> expenses = expenseService.getAllExpenses();
        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }

    // Method for getting the single expense
    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable String id) {
        Expense expense = expenseService.getExpenseById(id);
        if (expense != null) {
            return new ResponseEntity<>(expense, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
   //Method for getting all the expenses
    @GetMapping("/total-amount")
    public ResponseEntity<BigDecimal> getTotalAmount() {
        BigDecimal totalAmount = expenseService.getTotalAmount();
        return new ResponseEntity<>(totalAmount, HttpStatus.OK);
    }




}
