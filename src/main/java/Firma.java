/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * @author Konrad
 */

import java.util.List;
import java.util.ArrayList;

public class Firma {
    protected String Nazwa, KRS;//skladowe odpowiedzialne za nazwe i numer krs fimry
    private List<KlientBanku> Firmas;

    public Firma(String Nazwa, String KRS) {
        this.Nazwa = Nazwa;
        this.KRS = KRS;

    }

    public Firma() {
        Firmas = new ArrayList<>();
    }

    public String PrzejrzyjListeKlientowFirmy() {
        return "Lista klientow firmy: " + Firmas.toString();
    }

    public String getNazwa() {
        return Nazwa;
    }

    public String getKRS() {
        return KRS;
    }
}
