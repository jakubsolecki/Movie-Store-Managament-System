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
public class LoanHist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int loanHistID;

    @ManyToOne
    @NotNull
    private Client client;

    @ManyToOne
    @NotNull
    private Movie movie;

    @Temporal(TemporalType.DATE)
    @NotNull
    private Date dueDate;

    @Temporal(TemporalType.DATE)
    @NotNull
    private Date inDate;

    private double fine;
    private String remarks;

    public LoanHist(Client client, Movie movie, Date dueDate, Date inDate, double fine, String remarks) {
        this.client = client;
        this.movie = movie;
        this.dueDate = dueDate;
        this.inDate = inDate;
        this.fine = fine;
        this.remarks = remarks;
    }
}
