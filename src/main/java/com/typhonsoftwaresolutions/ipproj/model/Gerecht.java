package com.typhonsoftwaresolutions.ipproj.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Objects;

@SuppressWarnings("WeakerAccess")
@Entity
public class Gerecht {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty(message = "{meal.description.notempty}")
    @Size(min = 4, max = 50, message = "{meal.description.size}")
    @Column(unique = true)
    private String beschrijving;

    @NotNull(message = "{meal.price.notnull}")
    @DecimalMin(value = "0.1", message = "{meal.price.min}")
    @DecimalMax(value = "10", message = "{meal.price.max}")
    private Double prijs;

    @Pattern(regexp = "DAGSCHOTEL|SOEP|VEGGIE", message = "{meal.type.isinvalid}")
    private String type;

    public Gerecht() {
    }

    public Gerecht(String beschrijving, Double prijs, String type) {
        this.beschrijving = beschrijving;
        this.prijs = prijs;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public Double getPrijs() {
        return prijs;
    }

    public void setPrijs(Double prijs) {
        this.prijs = prijs;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gerecht gerecht = (Gerecht) o;
        return beschrijving.equals(gerecht.beschrijving) &&
                prijs.equals(gerecht.prijs) &&
                type.equals(gerecht.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(beschrijving, prijs, type);
    }

    public static class GerechtBuilder {
        private final String beschrijving;
        private Double prijs;
        private String type;

        public GerechtBuilder(String beschrijving) {
            this.beschrijving = beschrijving;
        }

        public GerechtBuilder withPrijs(Double prijs) {
            this.prijs = prijs;
            return this;
        }

        public GerechtBuilder withType(String type) {
            this.type = type;
            return this;
        }

        public Gerecht build() {
            return new Gerecht(beschrijving, prijs, type);
        }
    }
}
