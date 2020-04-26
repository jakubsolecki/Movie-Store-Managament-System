import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Set;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int memberID;
    private String name;
    private String country;
    private String city;
    private String street;
    private String zip;
    private Set<Loan> loans;
    private Set<LoanHist> loansHist;
}
