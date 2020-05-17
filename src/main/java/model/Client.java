package model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int clientID;

    @NotNull
    private String clientName;

    @NotNull
    private String country;

    @NotNull
    private String city;

    @NotNull
    private String street;

    @NotNull
    private String zip;

    @OneToMany
    private Set<Loan> loans = new HashSet<>();

    @OneToMany
    private Set<LoanHist> loansHist = new HashSet<>();

    public Client(String clientName, String country, String city, String street, String zip) {
        this.clientName = clientName;
        this.country = country;
        this.city = city;
        this.street = street;
        this.zip = zip;
    }

    public Client(String clientName) {
        this.clientName = clientName;
    }

}
