import javax.persistence.*;
import java.util.Set;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int clientID;
    private String ClientName;
    private String Country;
    private String City;
    private String Street;
    private String Zip;
//    private Set<Loan> loans;
//    private Set<LoanHist> loansHist;

    public Client() {
    }

    public Client(String name) {
        this.ClientName = name;
    }
}
