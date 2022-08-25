package in.bushansirgur.expensetrackerapi.controller;

import in.bushansirgur.expensetrackerapi.entity.Expense;
import in.bushansirgur.expensetrackerapi.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    // http://localhost:8080/api/expenses
    //@ResponseStatus(value = HttpStatus.OK)
    //@GetMapping("/expenses")
    //public List<Expense> getALLExpenses() {
    //return this.expenseService.getAllExpenses();
    //}

    // http://localhost:8080/api/expenses
    // http://{{host}}:{{port}}/api/expenses?size=2&page=2
    // http://{{host}}:{{port}}/api/expenses?size=10&page=0&sort=amount,desc
    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("")
    public List<Expense> getALLExpensesPage(Pageable page) {
        List<Expense> expensePage = this.expenseService.getAllExpenses(page).toList();
        return expensePage;
    }

    // http://{{host}}:{{port}}/api/expenses/date?startDate=2021-10-13&endDate=2021-10-15
    @GetMapping("/date")
    public List<Expense> getAllExpensesByDate(
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            Pageable page) {

        return expenseService.findByDateBetween(startDate, endDate, page);
    }

    // Passing a parameter using path variable
    // http://localhost:8080/api/expenses/2
    // @PathVariable is used to get the query string from URL
    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/{id}")
    public Expense getALLExpenses(@PathVariable("id") Integer id) {
        return this.expenseService.findById(id);
    }

    // Passing a parameter using query string
    // http://localhost:8080/api/expenses?id=2
    // @RequestParam is used to get the query String from URL
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("")
    public void removeById(@RequestParam("id") Integer id) {
        this.expenseService.removeById(id);
    }

    // Map the HTTP request to a Java Object
    // http://{{host}}:{{port}}/api/expenses
    // Body ->{
    //    "name": "Water bill",
    //    "description": "water bill",
    //    "expenseAmount": 600.0,
    //    "category": "Bills",
    //    "date": "2021-10-14"
    //}
    // @RequestBody annotation Map the HTTP Request Body to a Java Object\
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("")
    public Expense saveExpenseDetails(@Valid @RequestBody Expense expense) {
        return this.expenseService.saveExpenseDetails(expense);
    }

    // http://localhost:8080/api/expenses/8
    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping("/{id}")
    public Expense saveExpenseDetails(@RequestBody Expense expense, @PathVariable Integer id) {
        return this.expenseService.updateExpenseDetails(id, expense);
    }

    @GetMapping("/category")
    public List<Expense> getExpensesByCategory(@RequestParam String category, Pageable page) {
        return this.expenseService.findByCategory(category, page);
    }

    // http://{{host}}:{{port}}/api/expenses/name?name=ec
    @GetMapping("/name")
    public List<Expense> getAllExpensesByName(@RequestParam String name, Pageable page) {
        return this.expenseService.findByNameContaining(name, page);
    }


}
