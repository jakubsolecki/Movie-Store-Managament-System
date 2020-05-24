package model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
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

    @Temporal(TemporalType.DATE)
    @NonNull
    @NotNull
    private Date dueDate;

    @Temporal(TemporalType.DATE)
    @NonNull
    @NotNull
    private Date inDate;

    private double fine = 0;
    private String remarks;
}
