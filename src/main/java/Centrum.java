import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

public class Centrum {
    private ArrayList<KlientCentrum> listaKlientowCentrum = new ArrayList<>();// lista z klientami centrum
    private ArrayList<Transakcja> listaTransakcji = new ArrayList<>(); //lista z transakcjami
    private Bank bank;

    public void addBank(Bank b) {
        bank = b;
    }

    public void dodajKlientaCentrum(KlientCentrum klient) {
        boolean zmiannaPom = false;
        for (KlientCentrum klientCentrum : listaKlientowCentrum) {
            if (klientCentrum.getKrs().equals(klient.getKrs())) {
                zmiannaPom = true;
                break;
            } else zmiannaPom = false;
        }
        if (zmiannaPom == false)
            listaKlientowCentrum.add(klient);
    }
    public KlientCentrum getKlientaCentrum(int index) {
        return listaKlientowCentrum.get(index);
    }

    public ArrayList getListaKlientow() {
        return listaKlientowCentrum;
    }

    public void zapisDoArchiwum() throws FileNotFoundException {
        File plik = new File("nazwa_pliku.txt");
        if (plik == null)
            System.out.println("Brak pliku!");
        PrintWriter zapis = new PrintWriter("Archiwum.txt");
        for (Transakcja transakcja : listaTransakcji) {
            zapis.println(transakcja.toString());
        }
        zapis.close();
    }

    public void odczytZArchiwum() throws FileNotFoundException {
        File plik = new File("Archiwum.txt");
        Scanner in = new Scanner(plik);
        if (!plik.exists())
            System.out.println("Brak pliku!");
        String linia;
        while (in.hasNext()) {
            linia = in.nextLine();
            String[] czesci = linia.split(" ");
            listaTransakcji.add(new Transakcja(czesci[0], czesci[1], czesci[2], czesci[3], czesci[4], czesci[5], Double.parseDouble(czesci[6])));
            dodajKlientaCentrum(new KlientCentrum(czesci[0], czesci[1]));
            bank.dodajKlienta(new KlientBanku(czesci[3], czesci[4], czesci[5]));
            switch (czesci[2].toCharArray()[0]) {
                case '1':
                    bank.getKontoPoPeselu(czesci[5]).przypiszKarte(new Karta_Bankomatowa(czesci[2], "10.10.2028"));
                    break;
                case '2':
                    bank.getKontoPoPeselu(czesci[5]).przypiszKarte(new KartaDebetowa(czesci[2], "10.10.2028"));
                    break;
                case '3':
                    bank.getKontoPoPeselu(czesci[5]).przypiszKarte(new KartaKredytowa(czesci[2], "10.10.2028"));
                    break;
            }
        }
    }

    public boolean Autoryzacja(double Kwota, String NrKarty, KlientCentrum firma) {
        System.out.println(bank.getListeKlientow().size());
        if (listaKlientowCentrum.contains(firma)) {
            for (KlientBanku klient : bank.getListeKlientow())
                for (Karta karta : klient.getKonto().getKarty())
                    if (karta.getNrKarty().equals(NrKarty)) {
                        if (bank.decyzja(Kwota, NrKarty)) {
                            addTransakcja(firma.Nazwa, firma.KRS, NrKarty, klient.getImie(), klient.getNazwisko(), klient.getPesel(), Kwota);
                            return true;
                        }
                    }
        }
        return false;
    }

    public void addTransakcja(String nazwaFirmy, String krs, String nrKarty, String imie, String nazwisko, String pesel, double kwota) {
        listaTransakcji.add(new Transakcja(nazwaFirmy, krs, nrKarty, imie, nazwisko, pesel, kwota));
    }


    public String toStringKlientCentrum() {
        String string = "";
        int i = 0;
        if (listaKlientowCentrum.size() == 0)
            return "Nie ma firm!";
        else {
            for (Firma klientCentrum : listaKlientowCentrum) {
                string += (i++) + " " + klientCentrum.getNazwa() + " " + klientCentrum.getKRS() + "\n";
            }
            return string;
        }
    }

    public ArrayList<Transakcja> getListaTransakcji() {//metoda zwracajaca liste transakcji
        return listaTransakcji;
    }
}
