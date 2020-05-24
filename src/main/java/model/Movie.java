package model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "Movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int movieID;

    @NonNull
    @NotNull
    private String title;

    @NonNull
    @NotNull
    private String director;

    @NonNull
    @NotNull
    private String script;

    @NonNull
    @NotNull
    private String description;

    @NonNull
    @NotNull
    private int unitsInStock;

    @NonNull
    @NotNull
    private double pricePerUnit;
}
