package in.bushansirgur.expensetrackerapi.repository;

import in.bushansirgur.expensetrackerapi.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Integer> {

    // SELECT * FROM tbl_expenses WHERE category='Other'
    Page<Expense> findByUserIdAndCategory(Integer userId,String category, Pageable page);

    // SELECT * FROM tbl_expenses WHERE expense_name Like '%a%'
    Page<Expense> findByUserIdAndNameContaining(Integer userId,String keyword, Pageable page);

    // SELECT * FROM tbl_expenses WHERE date BETWEEN date('2021-10-13') AND date('2021-10-15')
    Page<Expense> findByUserIdAndDateBetween(Integer userId,LocalDate startDate, LocalDate endDate, Pageable page);

    Page<Expense> findByUserId(Integer userId, Pageable page);

    Optional<Expense> findByUserIdAndId(Integer userId, Integer expenseId);


}
