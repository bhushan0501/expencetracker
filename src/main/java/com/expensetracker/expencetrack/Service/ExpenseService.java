package com.expensetracker.expencetrack.Service;

import com.expensetracker.expencetrack.Entity.Expense;
import com.expensetracker.expencetrack.Repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;
    //Logic for getting all expense
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }
    //Logic for getting expense by id
    public Expense getExpenseById(String id) {
        return expenseRepository.findById(id).orElse(null);
    }
    //Logic for Creating the expense
    public Expense createExpense(Expense expense) {
        return expenseRepository.save(expense);
    }
    //Logic for updating the expense
    public Expense updateExpense(String id, Expense updatedExpense) {
        Expense existingExpense = expenseRepository.findById(id).orElse(null);
        if (existingExpense != null) {
            // Update fields of existingExpense with values from updatedExpense
            existingExpense.setDescription(updatedExpense.getDescription());
            existingExpense.setAmount(updatedExpense.getAmount());
            // Update other fields as needed
            return expenseRepository.save(existingExpense);
        } else {
            return null;
        }
    }
    //Logic for deleting the expense
    public boolean deleteExpense(String id) {
        if (expenseRepository.existsById(id)) {
            expenseRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
    //logic for getting the total expense amount
    public BigDecimal getTotalAmount() {
        List<Expense> allExpenses = expenseRepository.findAll();
        return allExpenses.stream()
                .map(Expense::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
