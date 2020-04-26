import javax.persistence.*;
import java.util.Date;

@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int loanID;
    @ManyToOne
    private int memberID;
    @ManyToOne
    private int movieID;
    @Temporal(TemporalType.DATE)
    private Date outDate;
    @Temporal(TemporalType.DATE)
    private Date dueDate;
}
