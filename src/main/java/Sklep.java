/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import wyjatki.WrongLenghtException;
import wyjatki.WrongNazwaException;

public class Sklep extends KlientCentrum {
    public Sklep(String Nazwa, String KRS) throws WrongNazwaException, WrongLenghtException {
        super(Nazwa, KRS);
    }
}
