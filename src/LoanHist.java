import javax.persistence.*;
import java.util.Date;

@Entity
public class LoanHist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int loanHistID;
    @ManyToOne
    private int memberID;
    @ManyToOne
    private int movieID;
    @Temporal(TemporalType.DATE)
    private Date dueDate;
    @Temporal(TemporalType.DATE)
    private Date inDate;
    private double fine;
    private String remarks;

}
