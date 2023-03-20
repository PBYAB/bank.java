import lombok.Getter;
@Getter
public abstract class Osoba {
    protected String imie, nazwisko, pesel;

    public Osoba() {
    }

    @Override
    public String toString() {
        return "Imie: " + imie + " Nazwisko: " + nazwisko + " PESEL: " + pesel;
    }
}
