import wyjatki.WrongLenghtException;

import java.util.ArrayList;
import java.util.Random;

public class Bank {
    private ArrayList<KlientBanku> listaKlientowBanku = new ArrayList<>();//lista z klientami centrum

    public Bank() {
    }

    private String resztaNumeruKarty() {// metoda tworzaca numer karty
        Random generator = new Random();
        return Integer.toString(generator.nextInt(100000000, 999999999)) + Integer.toString(generator.nextInt(100000, 999999));
    }

    public void dodajKlienta(KlientBanku klient) {//metoda dodajaca firme do klientow centrum
        boolean zmiennaPom = false;
        for (KlientBanku klientBanku : listaKlientowBanku) {
            if (klientBanku.getPesel().equals(klient.getPesel())) {
                zmiennaPom = true;
                break;
            } else zmiennaPom = false;
        }
        if (zmiennaPom == false)
            listaKlientowBanku.add(klient);
    }

    public Karta wydajKarte(int rodzaj, KlientBanku klientBanku) {//metoda tworzaca karte o wybranym typie
        switch (rodzaj) {
            case 1:
                return new Karta_Bankomatowa("1" + resztaNumeruKarty(), "20.10.2028");
            case 2:
                return new KartaDebetowa("2" + resztaNumeruKarty(), "20.10.2028");
            case 3:
                return new KartaKredytowa("3" + resztaNumeruKarty(), "20.10.2028");
        }
        return null;
    }
    public boolean decyzja(double Kwota, String NrKarty) {//metoda zwracajaca true jesli karta i stan konta poozawalaja na transakcje

        for (KlientBanku obecny : listaKlientowBanku) {
            for (Karta karta : obecny.getKonto().listaKart) {
                if (karta.getNrKarty().equals(NrKarty)) {
                    if (karta instanceof Karta_Bankomatowa)
                        return false;
                    if (karta instanceof KartaDebetowa && obecny.getKonto().wyplacDebetowa(Kwota))
                        return true;
                    if (karta instanceof KartaKredytowa && obecny.getKonto().wyplacKredytowa(Kwota))
                        return true;
                }
            }
        }
        return false;
    }

    public ArrayList<KlientBanku> getListeKlientow() {//zwraca liste klientow centrum
        return listaKlientowBanku;
    }

    public KlientBanku getWlascicielPoNumerzeKarty(String numer)// metoda zwracajaca wlasciciela po podaniu numeru karty
    {
        for (KlientBanku klient : listaKlientowBanku) {
            for (Karta k : klient.getKonto().getKarty()) {
                if (k.getNrKarty().equals(numer))
                    return klient;
            }
        }
        return null;
    }

    public Konto getKontoPoNumerze(String numer) throws WrongLenghtException {//metoda zwracajaca honto po numerze konta
        if(numer.length()!=26)
            throw new WrongLenghtException("Zla dlugosc numeru konta");
        for (KlientBanku klientBanku : listaKlientowBanku) {
            if (klientBanku.getKonto().getNumerKonta().equals(numer))
                return klientBanku.getKonto();
        }
        return new Konto("00");
    }

    public Konto getKontoPoPeselu(String pesel) {
        for (KlientBanku klientBanku : listaKlientowBanku) {
            if (klientBanku.getPesel().equals(pesel))
                return klientBanku.getKonto();
        }
        return null;
    }
}
