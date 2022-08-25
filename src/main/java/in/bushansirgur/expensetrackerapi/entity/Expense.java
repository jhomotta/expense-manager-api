package in.bushansirgur.expensetrackerapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.time.LocalDate;

@Data
@AllArgsConstructor()
@NoArgsConstructor()
@Entity
@Table(name = "tbl_expenses")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "expense_name", nullable = false)
    @Size(min= 3, message = "Expense name must be at least 3 characters.")
    @NotBlank(message = "Expense name must not be null")
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "expense_amount", nullable = false)
    private Double amount;

    @Column(name = "category", nullable = false)
    @NotBlank(message = "Expense category should not be null")
    private String category;

    @Column(name = "date", nullable = false)
    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "update_at", updatable = true)
    @UpdateTimestamp
    private Timestamp updateAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;


}