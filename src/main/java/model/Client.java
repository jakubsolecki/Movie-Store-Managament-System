package model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int clientID;

    @NonNull
    @NotNull
    private String firstName;

    @NonNull
    @NotNull
    private String lastName;

    @NonNull
    @NotNull
    private String phone;

    @NonNull
    @NotNull
    private String email;

    // TODO: move address to an embedded class

    @NonNull
    @NotNull
    private String country;

    @NonNull
    @NotNull
    private String city;

    @NonNull
    @NotNull
    private String street;

    @NonNull
    @NotNull
    private String zip;


    private String remarks;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Loan> loans = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY)
    private Set<LoanHist> loansHist = new HashSet<>();

    public Client(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void addLoan(Loan loan) {
        this.loans.add(loan);
    }

    public void addLoanHist(LoanHist loanHist) {
        this.loansHist.add(loanHist);
    }

    public void removeLoan(Loan loan) {
        this.loans.remove(loan);
    }
}
