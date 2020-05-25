package model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class LoanHist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int loanHistID;

    @ManyToOne
    @NonNull
    @NotNull
    private Client client;

    @ManyToOne
    @NonNull
    @NotNull
    private Movie movie;

    @NonNull
    @NotNull
    private LocalDate dueDate;

    @NonNull
    @NotNull
    private LocalDate inDate;

    private double fine = 0;
    private String remarks;


}
