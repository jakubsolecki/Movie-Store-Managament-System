package model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Embeddable
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

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
}
