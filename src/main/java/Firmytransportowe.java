import wyjatki.WrongLenghtException;
import wyjatki.WrongNazwaException;

public class Firmytransportowe extends KlientCentrum {
    public Firmytransportowe(String Nazwa, String KRS) throws WrongNazwaException, WrongLenghtException {
        super(Nazwa, KRS);
    }
}
