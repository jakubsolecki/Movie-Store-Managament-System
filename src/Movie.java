import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Set;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int movieID;
    private String title;
    private String director;
    private String script;
    private String description;
    private int unitsInStock;
    private double pricePerUnit;
//    private Set<Loan> loans;
//    private Set<LoanHist> loansHist;

    public Movie() {
    }
}
