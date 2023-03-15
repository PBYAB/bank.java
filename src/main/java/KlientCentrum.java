/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import wyjatki.WrongLenghtException;
import wyjatki.WrongNazwaException;

public class KlientCentrum extends Firma {
    public KlientCentrum(String name, String krs) throws WrongNazwaException, WrongLenghtException {
        super(name, krs);
    }

    public String getKrs() {
        return KRS;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + ":\n" + "Nazwa firmy: " + Nazwa + "\n" + "Numer KRS: " + KRS + "\n" + super.toString() + "\n";
    }
}
