package model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int movieID;

    @NotNull
    private String title;

    @NotNull
    private String director;

    @NotNull
    private String script;

    @NotNull
    private String description;

    @NotNull
    private int unitsInStock;

    @NotNull
    private double pricePerUnit;

    public Movie(String title, String director, String script, String description, int unitsInStock, double pricePerUnit) {
        this.title = title;
        this.director = director;
        this.script = script;
        this.description = description;
        this.unitsInStock = unitsInStock;
        this.pricePerUnit = pricePerUnit;
    }
}
