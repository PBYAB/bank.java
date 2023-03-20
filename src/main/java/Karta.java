
public abstract class Karta {
    protected String numer_karty, data_wygasniecia;

    public Karta(String przypisz_Numer, String przypisz_date) {
        numer_karty = przypisz_Numer;
        data_wygasniecia = przypisz_date;
    }

    public String getNrKarty() {
        return numer_karty;
    }

    public String get_data_wygasnieca() {
        return data_wygasniecia;
    }

    @Override
    public String toString() {
        return "Numer Kart:" + numer_karty + " Data wygasniecia: " + data_wygasniecia;
    }

}
