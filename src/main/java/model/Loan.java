package model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Loans")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int loanID;

    @ManyToOne
    @NotNull
    private Client client;

    @ManyToOne
    @NotNull
    private Movie movie;

    @Temporal(TemporalType.DATE)
    @NotNull
    private Date outDate;

    @Temporal(TemporalType.DATE)
    @NotNull
    private Date dueDate;

    public Loan(Client client, Movie movie, Date outDate, Date dueDate) {
        this.client = client;
        this.movie = movie;
        this.outDate = outDate;
        this.dueDate = dueDate;
    }
}
