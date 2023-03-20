import lombok.Getter;

import java.util.ArrayList;
import java.util.Random;

@Getter
public class KlientBanku extends Osoba {
    private Konto konto;

    private static String makeNumeruKonta() {// metoda tworzaca numer konta
        Random generator = new Random();
        return Long.toString(generator.nextLong(100000000, 999999999)) + Long.toString(generator.nextLong(100000000, 999999999)) + Long.toString(generator.nextLong(10000000, 99999999));
    }

    public KlientBanku(String Imie, String Nazwisko, String PESEL) {
        imie = Imie;
        nazwisko = Nazwisko;
        pesel = PESEL;
        Random generator = new Random();
        konto = new Konto(makeNumeruKonta());
    }

}
