import lombok.Getter;

@Getter
public class Transakcja {
    private String nazwaFirmy, krs, nrKarty, imie, nazwisko, pesel;
    private double kwota;

    public Transakcja(String nazwaFirmy, String krs, String nrKarty, String imie, String nazwisko, String pesel, double kwota) {
        this.nazwaFirmy = nazwaFirmy;
        this.krs = krs;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.nrKarty = nrKarty;
        this.pesel = pesel;
        this.kwota = kwota;
    }

    @Override
    public String toString() {// metoda zwrazajaca string w formacie zapisu do archiwum
        return nazwaFirmy + " " + krs + " " + nrKarty + " " + imie + " " + nazwisko + " " + pesel + " " + kwota;
    }
}
