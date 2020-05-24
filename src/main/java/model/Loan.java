package model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "Loans")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int loanID;

    @ManyToOne
    @NonNull
    @NotNull
    private Client client;

    @ManyToOne
    @NonNull
    @NotNull
    private Movie movie;

    @Basic
    @NonNull
    @NotNull
    private LocalDate outDate;

    @Basic
    @NonNull
    @NotNull
    private LocalDate dueDate;
}
