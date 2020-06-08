package model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Embeddable
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Address {

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
