package com.typhonsoftwaresolutions.ipproj.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class DagMenu {

    @Id
    @NotNull
    private LocalDate datum;

    private String dag;

    @NotNull
    @ManyToOne
    private Gerecht soep;

    @NotNull
    @ManyToOne
    private Gerecht dagschotel;

    @NotNull
    @ManyToOne
    private Gerecht veggie;

    public DagMenu() {
    }

    public DagMenu(LocalDate datum, Gerecht soep, Gerecht dagschotel, Gerecht veggie) {
        this.datum = datum;
        this.soep = soep;
        this.dagschotel = dagschotel;
        this.veggie = veggie;
    }

    public String getDag() {
        switch (datum.getDayOfWeek()) {
            case MONDAY:
                return "MAANDAG";
            case TUESDAY:
                return "DINSDAG";
            case WEDNESDAY:
                return "WOENSDAG";
            case THURSDAY:
                return "DONDERDAG";
            case FRIDAY:
                return "VRIJDAG";
            case SATURDAY:
                return "ZATERDAG";
            case SUNDAY:
                return "ZONDAG";
            default:
                return "ERROR";
        }
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public Gerecht getSoep() {
        return soep;
    }

    public void setSoep(Gerecht soep) {
        this.soep = soep;
    }

    public Gerecht getDagschotel() {
        return dagschotel;
    }

    public void setDagschotel(Gerecht dagschotel) {
        this.dagschotel = dagschotel;
    }

    public Gerecht getVeggie() {
        return veggie;
    }

    public void setVeggie(Gerecht veggie) {
        this.veggie = veggie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DagMenu dagMenu = (DagMenu) o;
        return datum.equals(dagMenu.datum) &&
                soep.equals(dagMenu.soep) &&
                dagschotel.equals(dagMenu.dagschotel) &&
                veggie.equals(dagMenu.veggie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(datum, soep, dagschotel, veggie);
    }

    public static class DagMenuBuilder {
        private final LocalDate datum;
        private Gerecht soep;
        private Gerecht dagschotel;
        private Gerecht veggie;

        public DagMenuBuilder(LocalDate datum) {
            this.datum = datum;
        }

        public DagMenuBuilder withSoep(Gerecht soep) {
            this.soep = soep;
            return this;
        }

        public DagMenuBuilder withDagschotel(Gerecht dagschotel) {
            this.dagschotel = dagschotel;
            return this;
        }

        public DagMenuBuilder withVeggie(Gerecht veggie) {
            this.veggie = veggie;
            return this;
        }

        public DagMenu build() {
            return new DagMenu(datum, soep, dagschotel, veggie);
        }
    }
}
