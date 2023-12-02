package com.expensetracker.expencetrack.Repository;

import com.expensetracker.expencetrack.Entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, String> {
}
