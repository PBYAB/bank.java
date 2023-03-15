import wyjatki.WrongKwotaException;
import wyjatki.WrongLenghtException;
import wyjatki.WrongNazwaException;

import javax.lang.model.element.VariableElement;
import java.io.FileNotFoundException;
import java.util.EmptyStackException;
import java.util.Scanner;

public class Main {
    private static void ObslugaZadan(Centrum centrum) throws NullPointerException,IndexOutOfBoundsException//obsługa żądań autoryzacji (kwota, data, dane karty) - decyzja zależna od rodzaju karty
    {   if(centrum == null)
        throw new NullPointerException("Nie ma centrum");
        try{
        if (centrum.getListaKlientow().size() != 0) {
            System.out.println(centrum.toStringKlientCentrum());
            Scanner scan = new Scanner(System.in);
            while(true){
                try{
                    System.out.println("Podaj index firmy: ");
                    int index = scan.nextInt();
                    if(index>centrum.getListaKlientow().size()-1||index<0)
                        throw new IndexOutOfBoundsException("Index firmy z poza zakresu");
                    System.out.println("Podaj numer karty: ");
                    String  nrKarty = scan.next();
                    System.out.println("Podaj kwote transakcji:");
                    double kwota = scan.nextDouble();
                    if (centrum.Autoryzacja(kwota, nrKarty, centrum.getKlientaCentrum(index)))
                        System.out.println("Transakcja zakoczona powodzeniem!");
                    else System.out.println("Transakcja zakoczona niepowodzeniem!");
                }catch (WrongLenghtException wrongLenghtException){
                    System.out.println("Podaj jeszcze raz");
                    System.out.println("------------------------------");
                }catch (WrongKwotaException wrongKwotaException){
                    System.out.println("Podaj kwote jeszcze raz");
                    System.out.println("------------------------------");
                }catch (IndexOutOfBoundsException indexOutOfBoundsException){
                    System.out.println("Podaj index firmy jeszcze raz");
                    System.out.println("------------------------------");
                }finally {
                    break;
                }
            }
        } else System.out.println("Brak firm w bazie centrum");
    }catch (NullPointerException nullPointerException){
            System.out.println("Brak firmy!");
            System.out.println("------------------------------");
        }

    }

    private static void zapisOdczyt(Centrum centrum) throws IndexOutOfBoundsException {//zapis i odczyt stanu systemu na dysk (realizacja archiwum operacji w postaci odrębnego pliku)
        while (true){
            try{
                System.out.println("Podaj co chcesz zrobic: (1- zapisac stan na dysk, 2 - odczytac stan dysku, 0 - wyjsc)");
                Scanner scan = new Scanner(System.in);
                int wybor = scan.nextInt();
                if(wybor!=1&&wybor!=2&&wybor!=0)
                    throw new IndexOutOfBoundsException();
                switch (wybor) {
                    case 1:
                        centrum.zapisDoArchiwum();
                        break;
                    case 2:
                        centrum.odczytZArchiwum();
                        break;
                    case 0:
                        break;
                                }
                } catch (IndexOutOfBoundsException indexOutOfBoundsException){
                System.out.println("wybor z poza dostepnych!");
                System.out.println("------------------------------");
                }catch (FileNotFoundException | WrongNazwaException | WrongLenghtException fileNotFoundException){
                System.out.println("Sprobuj ponownie!");
                System.out.println("------------------------------");
            }finally {
                break;
            }
                }
            }
    private static void zarzazdanieFirmamicase3(Centrum centrum){
        Scanner scan = new Scanner(System.in);
        System.out.println(centrum.toStringKlientCentrum());
        System.out.println("Podaj index firmy ktora chcesz usunac:");
        int index = scan.nextInt();
        if(index>centrum.getListaKlientow().size()-1&&index<0)
            throw new IndexOutOfBoundsException("Index firmy z poza zakresu");
        centrum.getListaKlientow().remove(index);
        System.out.println("Klient usuniety!");
        System.out.println(centrum.toStringKlientCentrum());
    }
    private static void zarzazdanieFirmamicase2(Centrum centrum) throws WrongNazwaException, WrongLenghtException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Podaj nazwe firmy(bez spacji):");
        String nazwaFirmy = scan.next();
        System.out.println("Podaj KRS firmy:");
        String krsFirmy = scan.next();
        System.out.println("Podaj rodzaj firmy (1 - sklep, 2 - Zaklad uslugowy,3 - firma transportowa");
        int wybor2 = scan.nextInt();
        if (wybor2 == 1)
            centrum.getListaKlientow().add(new Sklep(nazwaFirmy, krsFirmy));
        if (wybor2 == 2)
            centrum.getListaKlientow().add(new ZakładyUsługowe(nazwaFirmy, krsFirmy));
        if (wybor2 == 3)
            centrum.getListaKlientow().add(new Firmytransportowe(nazwaFirmy, krsFirmy));
    }
    private static void zarzazdanieFirmami(Centrum centrum) throws NullPointerException{//zarządzenie firmami korzystającymi z centrum centrum i bankami (dodawanie, usuwanie, przegląd)
        if(centrum == null)
            throw new NullPointerException();
        while(true){
            try{
                System.out.println("Podaj co chcesz zrobic: (1- zobaczyc firmy, 2 - dodac firme,3 - usunac firme, 0 - wyjsc)");
                Scanner scan = new Scanner(System.in);
                int wybor = scan.nextInt();
                if(wybor!=1&&wybor!=2&&wybor!=3&&wybor!=0)
                    throw new IndexOutOfBoundsException();
                switch (wybor) {
                    case 1:
                        System.out.println(centrum.toStringKlientCentrum());
                        break;
                    case 2:
                        zarzazdanieFirmamicase2(centrum);
                        break;
                    case 3:
                        zarzazdanieFirmamicase3(centrum);
                        break;
                    case 0:
                        break;
                }
            }catch (NullPointerException nullPointerException){
                System.out.println("------------------------------");
                System.out.println("Centrum nie istnieje!");
            }catch (EmptyStackException emptyStackException){
                System.out.println("------------------------------");
                System.out.println("Lista klientow centrum jest pusta");
            }catch (IndexOutOfBoundsException indexOutOfBoundsException){
                System.out.println("------------------------------");
                System.out.println("Wybor z poza dostepnych!");
            } catch (WrongNazwaException e) {
                throw new RuntimeException(e);
            } catch (WrongLenghtException e) {
                throw new RuntimeException(e);
            }finally {
                break;
            }
        }
    }

    private static void zarzazdanieKartami(Centrum centrum, Bank bank, String nrKonta) throws IndexOutOfBoundsException,EmptyStackException,WrongLenghtException{//zarządzanie kartami (przypisana do klienta banku, wydana przez bank)
        Scanner scan = new Scanner(System.in);
        if (bank.getKontoPoNumerze(nrKonta).getKarty().size() == 0)
        throw new EmptyStackException();
        while(true){
            try{
                System.out.println("Wybierrz co chcesz zrobic(1 - zobaczyc swoje karty, 2 - dodace karte, 3 - usunac karte,4 - wplacic, 0 - przerwac)");
                int wybor = scan.nextInt();
                if(wybor!=1&&wybor!=2&&wybor!=3&&wybor!=4&&wybor!=0)
                    throw new IndexOutOfBoundsException();
                switch (wybor) {
                    case 1:
                        System.out.println(bank.getKontoPoNumerze(nrKonta).toString());
                        break;
                    case 2:
                        System.out.println("podaj jaka chcesz dodac karte: (1 - bankomatowa, 2 - debetowa, 3 - kredytowa)");
                        int wybor2 = scan.nextInt();
                        bank.getKontoPoNumerze(nrKonta).przypiszKarte(bank.wydajKarte(wybor2, bank.getWlascicielPoNumerzeKarty(nrKonta)));
                        break;
                    case 3:
                        System.out.println("Podaj index karty ktora chcesz usunac: ");
                        System.out.println(bank.getKontoPoNumerze(nrKonta).toString());
                        int index = scan.nextInt();
                        bank.getKontoPoNumerze(nrKonta).getKarty().remove(index);
                        break;
                    case 4:
                        System.out.println(bank.getKontoPoNumerze(nrKonta).toString());
                        System.out.println("Podaj ile chcesz wplacic");
                        double kwota = scan.nextDouble();
                        bank.getKontoPoNumerze(nrKonta).wplac(kwota);
                        break;
                    case 0:
                        break;}
            }catch (EmptyStackException emptyStackException){
                System.out.println("------------------------------");
                System.out.println("Konto nie posiada kart");
            }catch (IndexOutOfBoundsException indexOutOfBoundsException){
                System.out.println("------------------------------");
                System.out.println("Wybor z poza dsotepnych!");
            }finally {
                break;
            }
        }
    }

    private static void przeszukiwaniePo1(Centrum centrum) throws WrongLenghtException,WrongKwotaException{
        while (true){
            try{
                Scanner scan = new Scanner(System.in);
                System.out.println("Podaj po czym chcesz przeszukiwac");
                System.out.println("1 - krs firmy");
                System.out.println("2 - numer karty");
                System.out.println("3 - wlasciciel");
                System.out.println("4 - kwota");
                int wybor = scan.nextInt();
                switch (wybor) {
                    case 1:
                        System.out.println("Podaj KRS:");
                        String nrKrs = scan.next();
                        if(nrKrs.length()!=10)
                            throw new WrongLenghtException("Zla dlugosc KRS");
                        for (Transakcja transakcja : centrum.getListaTransakcji()) {
                            if (transakcja.getKrs().equals(nrKrs))
                                System.out.println(transakcja);
                        }
                        break;
                    case 2:
                        System.out.println("Podaj numer karty:");
                        String nrKarty = scan.next();
                        if(nrKarty.length()!=16)
                            throw new WrongLenghtException("Zla dlugosc numeru karty");
                        for (Transakcja transakcja : centrum.getListaTransakcji()) {
                            if (transakcja.getNrKarty().equals(nrKarty))
                                System.out.println(transakcja);
                        }
                        break;
                    case 3:
                        System.out.println("Podaj pesel:");
                        String pesel = scan.next();
                        if(pesel.length()!=11)
                            throw new WrongLenghtException("Zla dlugosc peselu");
                        for (Transakcja transakcja : centrum.getListaTransakcji()) {
                            if (transakcja.getPesel().equals(pesel))
                                System.out.println(transakcja);
                        }
                        break;
                    case 4:
                        System.out.println("Podaj kwote:");
                        double kwota = scan.nextDouble();
                        if(kwota<0)
                            throw new WrongKwotaException("Ujemna kwota!");
                        for (Transakcja transakcja : centrum.getListaTransakcji()) {
                            if (transakcja.getKwota()==kwota)
                                System.out.println(transakcja);
                        }
                        break;
                }
            }catch (WrongLenghtException wrongLenghtException){
                System.out.println("------------------------------");
                System.out.println("Sprobuj podac jescze raz!");
            }catch (WrongKwotaException wrongKwotaException){
                System.out.println("------------------------------");
                System.out.println("Sprobuj podac jescze raz!");
            }finally {
                break;
            }
        }
    }
    private static void przeszukiwaniePo2(Centrum centrum){
        Scanner scan = new Scanner(System.in);
        System.out.println("Podaj po czym chcesz przeszukiwac");
        System.out.println("1&2 - krs firmy i numer karty");
        System.out.println("1&3 - krs firmy i wlasciciel");
        System.out.println("1&4 - krs firmy i kwota");
        System.out.println("2&3 - numer karty  i wlasiciel");
        System.out.println("2&4 - numer karty i kwota");
        System.out.println("3&4 - wlasciciel  i kwota");
        String wybor = scan.next();

        switch (wybor){
            case "1&2":
                System.out.println("Podaj KRS:");
                String nrKrs = scan.next();
                System.out.println("Podaj numer karty:");
                String nrKarty = scan.next();
                for (Transakcja transakcja : centrum.getListaTransakcji()) {
                    if (transakcja.getKrs().equals(nrKrs)&&transakcja.getNrKarty().equals(nrKarty))
                        System.out.println(transakcja);
                }
                break;
            case "1&3":
                System.out.println("Podaj KRS:");
                nrKrs = scan.next();
                System.out.println("Podaj pesel:");
                String pesel = scan.next();
                for (Transakcja transakcja : centrum.getListaTransakcji()) {
                    if (transakcja.getKrs().equals(nrKrs)&&transakcja.getPesel().equals(pesel))
                        System.out.println(transakcja);
                }
                break;
            case "1&4":
                System.out.println("Podaj KRS:");
                nrKrs = scan.next();
                System.out.println("Podaj kwote:");
                double kwota = scan.nextInt();
                for (Transakcja transakcja : centrum.getListaTransakcji()) {
                    if (transakcja.getKrs().equals(nrKrs)&&transakcja.getKwota()==kwota)
                        System.out.println(transakcja);
                }
                break;
            case "2&3":
                System.out.println("Podaj numer karty:");
                nrKarty = scan.next();
                System.out.println("Podaj pesel:");
                pesel = scan.next();
                for (Transakcja transakcja : centrum.getListaTransakcji()) {
                    if (transakcja.getNrKarty().equals(nrKarty)&&transakcja.getPesel().equals(pesel))
                        System.out.println(transakcja);
                }
                break;
            case "2&4":
                System.out.println("Podaj numer karty:");
                nrKarty = scan.next();
                System.out.println("Podaj kwota:");
                kwota = scan.nextDouble();
                for (Transakcja transakcja : centrum.getListaTransakcji()) {
                    if (transakcja.getNrKarty().equals(nrKarty)&&transakcja.getKwota()==kwota)
                        System.out.println(transakcja);
                }
                break;
            case "3&4":
                System.out.println("Podaj pesel:");
                pesel = scan.next();
                System.out.println("Podaj kwota:");
                kwota = scan.nextDouble();
                for (Transakcja transakcja : centrum.getListaTransakcji()) {
                    if (transakcja.getPesel().equals(pesel)&&transakcja.getKwota()==kwota)
                        System.out.println(transakcja);
                }
                break;
        }
    }

    private static void przeszukiwaniePo2lub(Centrum centrum){
        Scanner scan = new Scanner(System.in);
        System.out.println("Podaj po czym chcesz przeszukiwac");
        System.out.println("1|2 - krs firmy lub numer karty");
        System.out.println("1|3 - krs firmy lub wlasciciel");
        System.out.println("1|4 - krs firmy lub kwota");
        System.out.println("2|3 - numer karty lub wlasiciel");
        System.out.println("2|4 - numer karty lub kwota");
        System.out.println("3|4 - wlasciciel lub kwota");
        String wybor = scan.next();

        switch (wybor){
            case "1|2":
                System.out.println("Podaj KRS:");
                String nrKrs = scan.next();
                System.out.println("Podaj numer karty:");
                String nrKarty = scan.next();
                for (Transakcja transakcja : centrum.getListaTransakcji()) {
                    if (transakcja.getKrs().equals(nrKrs)||transakcja.getNrKarty().equals(nrKarty))
                        System.out.println(transakcja);
                }
                break;
            case "1|3":
                System.out.println("Podaj KRS:");
                nrKrs = scan.next();
                System.out.println("Podaj pesel:");
                String pesel = scan.next();
                for (Transakcja transakcja : centrum.getListaTransakcji()) {
                    if (transakcja.getKrs().equals(nrKrs)||transakcja.getPesel().equals(pesel))
                        System.out.println(transakcja);
                }
                break;
            case "1|4":
                System.out.println("Podaj KRS:");
                nrKrs = scan.next();
                System.out.println("Podaj kwote:");
                double kwota = scan.nextInt();
                for (Transakcja transakcja : centrum.getListaTransakcji()) {
                    if (transakcja.getKrs().equals(nrKrs)||transakcja.getKwota()==kwota)
                        System.out.println(transakcja);
                }
                break;
            case "2|3":
                System.out.println("Podaj numer karty:");
                nrKarty = scan.next();
                System.out.println("Podaj pesel:");
                pesel = scan.next();
                for (Transakcja transakcja : centrum.getListaTransakcji()) {
                    if (transakcja.getNrKarty().equals(nrKarty)||transakcja.getPesel().equals(pesel))
                        System.out.println(transakcja);
                }
                break;
            case "2|4":
                System.out.println("Podaj numer karty:");
                nrKarty = scan.next();
                System.out.println("Podaj kwota:");
                kwota = scan.nextDouble();
                for (Transakcja transakcja : centrum.getListaTransakcji()) {
                    if (transakcja.getNrKarty().equals(nrKarty)||transakcja.getKwota()==kwota)
                        System.out.println(transakcja);
                }
                break;
            case "3|4":
                System.out.println("Podaj pesel:");
                pesel = scan.next();
                System.out.println("Podaj kwota:");
                kwota = scan.nextDouble();
                for (Transakcja transakcja : centrum.getListaTransakcji()) {
                    if (transakcja.getPesel().equals(pesel)||transakcja.getKwota()==kwota)
                        System.out.println(transakcja);
                }
                break;
        }
    }

    private static void przeszukiwanie(Centrum centrum) throws WrongKwotaException, WrongLenghtException {

      while (true) {
          try{
              Scanner scan = new Scanner(System.in);
              System.out.println("Podaj co chcesz zrobi: ( 1 - przeszukiwanie po 1 warunku, 2 - przeszukiwanie po 2 warunkach, 3 - przeszukiwenia po alternatywnych warunkach)");
              int wybor = scan.nextInt();
              if(wybor!=1&&wybor!=2&&wybor!=3)
                  throw new IndexOutOfBoundsException("Opdowiedz z poza dostepnyvh!");
              switch (wybor){
                  case 1:
                      przeszukiwaniePo1(centrum);break;

                  case 2:
                      przeszukiwaniePo2(centrum);break;
                  case 3:
                      przeszukiwaniePo2lub(centrum);break;
              }
          }catch (IndexOutOfBoundsException indexOutOfBoundsException){
              System.out.println("------------------------------");
              System.out.println("Sprobuj podac jescze raz!");
          }finally {
              break;
          }
      }
    }

    public static void main(String[] args) throws FileNotFoundException, WrongLenghtException, WrongKwotaException {
        Centrum centrum = new Centrum();
        Bank bank = new Bank();
        centrum.addBank(bank);
        int wybor = 1;
        System.out.println("Witaj!");
        System.out.println("1 - Zarzadzanie firmami korzystajacymi z centrum");
        System.out.println("2 - zarzadzanie swoimi  kartami");
        System.out.println("3 - transakcja");
        System.out.println("4 - Odczytac/zapisac stan dysku");
        System.out.println("5 - Przeszukiwanie archiwum");
        System.out.println("6 - legenda");
        System.out.println("0 - Koniec");
        while (wybor != 0) {

            Scanner scan = new Scanner(System.in);
            wybor = scan.nextInt();
            String numerKonta;
            switch (wybor) {
                case 0:
                    break;
                case 1:
                    zarzazdanieFirmami(centrum);
                    break;
                case 2:
                    for (KlientBanku klientBanku : bank.getListeKlientow())
                        System.out.println(klientBanku.getImie() + " " + klientBanku.getNazwisko() + " " + klientBanku.getKonto().getNumerKonta());
                    System.out.println("Podaj numer konta");
                    numerKonta = scan.next();
                    zarzazdanieKartami(centrum, bank, numerKonta);
                    break;
                case 3:
                    ObslugaZadan(centrum);
                    break;
                case 4:
                    zapisOdczyt(centrum);
                    break;
                case 5:
                    przeszukiwanie(centrum);
                    break;
                case 6:
                    System.out.println("1 - Zarzadzanie firmami korzystajacymi z centrum");
                    System.out.println("2 - zarzadzanie swoimi  kartami");
                    System.out.println("3 - transakcja");
                    System.out.println("4 - Odczytac/zapisac stan dysku");
                    System.out.println("5 - Przeszukiwanie archiwum");
                    System.out.println("6 - legenda");
                    System.out.println("0 - Koniec");
                    break;
            }
            System.out.println("----------------------------------------------------------menu-----------------------------------------------------------");
            System.out.print(" 1 - Zarzadzanie firmami ");
            System.out.print(" 2 - zarzadzanie kartami ");
            System.out.print(" 3 - transakcja ");
            System.out.print(" 4 - Odczytac/zapisac ");
            System.out.print(" 5 - Przeszukiwanie archiwum ");
            System.out.print(" 6 - legenda ");
            System.out.print(" 0 - Koniec\n");
        }
    }
}