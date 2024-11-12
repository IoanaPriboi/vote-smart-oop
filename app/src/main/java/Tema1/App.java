package Tema1;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.text.*;

public class App {
    private Scanner scanner;

    public App(InputStream input) {
        this.scanner = new Scanner(input);
    }

    public void run() {
        // Implementați aici cerințele din enunț
        // Pentru citirea datelor de la tastatura se folosește câmpul scanner.

        //  Comenzi:
        ArrayList<String> comenzi = new ArrayList<String>();
        comenzi.add("Creare alegeri");
        comenzi.add("Pornire alegeri");
        comenzi.add("Adaugare circumscriptie");
        comenzi.add("Eliminare circumscriptie");
        comenzi.add("Adaugare candidat in alegeri");
        comenzi.add("Eliminare candidat in alegeri");
        comenzi.add("Adaugare votant in circumscriptie");
        comenzi.add("Listarea candidatilor din alegeri");
        comenzi.add("Listarea votant dintr-o circumscriptie");
        comenzi.add("Votare");
        comenzi.add("Oprire alegeri");
        comenzi.add("Creeaza raport voturi per circumscriptie");
        comenzi.add("Creeaza raport voturi pe plan national");
        comenzi.add("Analiza detaliata per circumscriptie");
        comenzi.add("Analiza detaliata pe plan national");
        comenzi.add("Raportare fraude");
        comenzi.add("Sterge alegeri");
        comenzi.add("Listarea alegeri");
        comenzi.add("Iesire");

        // Afisare meniu de comezi:
        System.out.println("Va multumim ca sunteti un cetatean implicat! Aveti mai jos meniul de comenzi:");
        for (int i = 0; i < comenzi.size(); i++) {
            System.out.println(i + ". " + comenzi.get(i));
        }
        System.out.println();

        ArrayList<Alegeri> alegeri = new ArrayList<Alegeri>();
        ArrayList<Circumscriptie> circumscriptii = new ArrayList<Circumscriptie>();

        // Input-ul user-ului:
        while (true) {
            System.out.println("Va rugam introduceti comanda dorita (numarul corespunzator):");
            int comanda = scanner.nextInt();
            scanner.nextLine();
            if (comanda >= 0 && comanda <= 18) {
                System.out.println("Comanda selectata: " + comanda + ". " + comenzi.get(comanda));
            }

            switch (comanda) {
                case 0:
                    System.out.println("Introduceti id-ul si numele alegerilor:");
                    String id0 = scanner.next();
                    String nume = scanner.nextLine().trim();
                    Alegeri alegere = new Alegeri(id0, nume);
                    alegere.creeazaAlegere(alegeri);
                    break;
                    
                case 1:
                    System.out.println("Introduceti id-ul alegerilor:");
                    String id1 = scanner.nextLine();
                    boolean existaId = false;
                    for(Alegeri a: alegeri) {
                        if(a.getId().equals(id1)) {
                            if(a.getStatus().equals("NEINCEPUT")) {
                                a.setStatus("IN_CURS");
                                existaId = true;
                                System.out.println("Au pornit alegerile " + a.getNume());
                            } else {
                                existaId = true;
                                System.out.println("EROARE: Alegerile deja au inceput");
                            }
                        }
                    }
                    if(!existaId) {
                        System.out.println("EROARE: Nu exista alegeri cu acest id");
                    }
                    break;

                case 2:
                    System.out.println("Introduceti id-ul alegerilor, numele circumscriptiei si regiunea:");
                    String id2 = scanner.next();
                    String nume2 = scanner.next().trim();
                    String regiune = scanner.nextLine().trim();
                    Circumscriptie circumscriptie = new Circumscriptie(nume2, regiune);
                    boolean ok = true;
                    for(Circumscriptie c: circumscriptii) {
                        if(c.getNume().equals(nume2)) {
                            ok = false;
                            System.out.println("EROARE: Exista deja o circumscriptie cu numele " + nume2);
                        }
                    }
                    if(!ok) {
                        break;
                    }
                    for(Alegeri a: alegeri) {
                        if(a.getId().equals(id2)) {
                            if(a.getStatus() != "IN_CURS") {
                                ok = true;
                                System.out.println("EROARE: Nu este perioada de votare");
                            } else {
                                circumscriptie.adaugaAlegeri(a);
                                circumscriptii.add(circumscriptie);
                                ok = true;
                                System.out.println("S-a adaugat circumscriptia " + nume2);
                            }
                        }
                    }
                    if(ok) {
                        System.out.println("EROARE: Nu exista alegeri cu acest id");
                    }
                    break;
                case 3:
                    System.out.println("Introduceti id-ul alegerilor si numele circumscriptiei:");
                    break;
                case 4:
                    System.out.println("Introduceti id-ul alegerilor, CNP-ul, varsta si numele candidatului:");
                    break;
                case 5:
                    System.out.println("Introduceti id-ul alegerilor si CNP-ul candidatului:");
                    break;
                case 6:
                    System.out.println("Introduceti id-ul alegerilor, numele circumscriptiei, CNP-ul, varsta, neindemanarea (da/nu) si numele votantului:");
                    break;
                case 7:
                    System.out.println("Introduceti id-ul alegerilor:");
                    break;
                case 8:
                    System.out.println("Introduceti id-ul alegerilor, numele circumscriptiei:");
                    break;
                case 9:
                    System.out.println("Indroduceti id-ul alegerilor, numele circumscriptiei, CNP-ul votantului si CNP-ul candidatului:");
                    break;
                case 10:
                    System.out.println("Introduceti id-ul alegerilor:");
                    break;
                case 11:
                    System.out.println("Introduceti id-ul alegerilor si numele circumscriptiei:");
                    break;
                case 12:
                    System.out.println("Introduceti id-ul alegerilor:");
                    break;
                case 13:
                    System.out.println("Introduceti id-ul alegerilor si numele circumscriptiei:");
                    break;
                case 14:
                    System.out.println("Introduceti id-ul alegerilor:");
                    break;
                case 15:
                    System.out.println("Introduceti id-ul alegerilor:");
                    break;
                case 16:
                    System.out.println("Introduceti id-ul alegerilor:");
                    break;
                case 17:
                    break;
                case 18:
                    System.out.println("Programul s-a incheiat. O zi buna!");
                    alegeri.clear();
                    scanner.close();
                    return;
                default:
                    System.out.println("Introduceti o comanda valida.");
                    break;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        App app = new App(System.in);
        app.run();
    }
}