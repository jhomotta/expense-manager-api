package in.bushansirgur.expensetrackerapi.service;

import in.bushansirgur.expensetrackerapi.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ExpenseService {
    List<Expense> getAllExpenses();
    Expense findById(Integer id);
    void removeById(Integer id);
    Expense saveExpenseDetails(Expense expense);
    Expense updateExpenseDetails(Integer Id, Expense expense);
    Page<Expense> getAllExpenses(Pageable page);
    List<Expense> findByCategory(String category, Pageable page);
    List<Expense> findByNameContaining(String keyword, Pageable page);
    List<Expense> findByDateBetween(Date startDate, Date endDate, Pageable page);

}
