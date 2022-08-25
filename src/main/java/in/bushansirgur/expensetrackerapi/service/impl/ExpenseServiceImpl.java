package in.bushansirgur.expensetrackerapi.service.impl;

import in.bushansirgur.expensetrackerapi.entity.Expense;
import in.bushansirgur.expensetrackerapi.exceptions.ResourceNotFoundException;
import in.bushansirgur.expensetrackerapi.repository.ExpenseRepository;
import in.bushansirgur.expensetrackerapi.repository.UserRepository;
import in.bushansirgur.expensetrackerapi.service.ExpenseService;
import in.bushansirgur.expensetrackerapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserService userService;

    @Override
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    @Override
    public Page<Expense> getAllExpenses(Pageable page) {
        return this.expenseRepository.findByUserId(this.userService.getLoggedInUser().getId(), page);
    }

    @Override
    public List<Expense> findByCategory(String category, Pageable page) {
        return this.expenseRepository.findByUserIdAndCategory(this.userService.getLoggedInUser().getId(), category, page).toList();
    }

    @Override
    public List<Expense> findByNameContaining(String keyword, Pageable page) {
        return this.expenseRepository.findByUserIdAndNameContaining(this.userService.getLoggedInUser().getId(), keyword, page).toList();
    }

    @Override
    public List<Expense> findByDateBetween(Date startDate, Date endDate, Pageable page) {
        if (startDate ==  null) {
            startDate = new Date(0);
        }
        if (endDate ==  null) {
            endDate = new Date(System.currentTimeMillis());
        }

        return this.expenseRepository.findByUserIdAndDateBetween(this.userService.getLoggedInUser().getId(), startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), page).toList();
    }

    public Expense findById(Integer id) {
        Long a = this.userService.getLoggedInUser().getId();
        Optional<Expense> expense = this.expenseRepository.findByUserIdAndId(this.userService.getLoggedInUser().getId(), id);
        if(expense.isPresent()) {
            return expense.get();
        }
        throw new ResourceNotFoundException("Expense is not found for the id " + id);
    }

    public void removeById(Integer id) {
        Expense expense = this.findById(id);
        this.expenseRepository.deleteById(expense.getId());
    }

    @Override
    public Expense  saveExpenseDetails(Expense expense) {
        expense.setUser(this.userService.getLoggedInUser());
        return this.expenseRepository.save(expense);
    }

    @Override
    public Expense updateExpenseDetails(Integer id, Expense expense) {
        Expense existingExp = this.findById(id);
        existingExp.setName(expense.getName() != null ? expense.getName(): existingExp.getName());
        existingExp.setDescription(expense.getDescription() != null ? expense.getDescription(): existingExp.getDescription());
        existingExp.setCategory(expense.getCategory() != null ? expense.getCategory(): existingExp.getCategory());
        existingExp.setAmount(expense.getAmount() != null ? expense.getAmount(): existingExp.getAmount());
        existingExp.setDate(expense.getDate() != null ? expense.getDate(): existingExp.getDate());
        return this.expenseRepository.save(existingExp);
    }

}
