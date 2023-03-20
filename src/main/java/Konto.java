import java.util.ArrayList;

public class Konto {
    private String numerKonta;
    private double saldo;
    public ArrayList<Karta> listaKart = new ArrayList<>();

    public Konto(String nrKonta) {
        saldo = 0;
        numerKonta = nrKonta;
    }

    public Konto(Konto k) {
        numerKonta = k.getNumerKonta();
        saldo = k.getSaldo();
        listaKart = k.getKarty();
    }

    public double getSaldo() {
        return saldo;
    }

    public String getNumerKonta() {
        return numerKonta;
    }

    /**
     * @author Marcin
     */
    public void przypiszKarte(Karta karta) {
        boolean zmiannaPom = false;
        for (Karta k : listaKart) {
            if (karta.getNrKarty().equals(k.getNrKarty())) {
                zmiannaPom = true;
                break;
            } else zmiannaPom = false;
        }
        if (zmiannaPom == false)
            listaKart.add(karta);
    }

    public void wplac(double kwota) {
        saldo += kwota;
    }

    public boolean wyplacKredytowa(double kwota) {
        if (kwota >= 0) {
            saldo -= kwota;
            return true;
        }
        return false;
    }

    public boolean wyplacDebetowa(double kwota) {
        if (kwota >= 0 && saldo >= kwota) {
            saldo -= kwota;
            return true;
        }
        return false;
    }

    public ArrayList<Karta> getKarty() {
        return listaKart;
    }

    public String toString() {
        int i = 0;
        String string = "";
        if (listaKart.size() == 0)
            return "Brak Kart";
        else
            for (Karta karta : listaKart)
                string += "[" + (i++) + "]" + " Numer: " + karta.getNrKarty() + " Data: " + karta.get_data_wygasnieca() + "\n";
        return string;
    }
}
