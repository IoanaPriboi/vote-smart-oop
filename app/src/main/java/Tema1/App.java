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

        ManagerAlegeri managerAlegeri = new ManagerAlegeri();

        // Lista cu toate circumscriptiile
        ArrayList<Circumscriptie> circumscriptii = new ArrayList<Circumscriptie>();

        // Declar variabilele pe care le voi folosi pentru citire
        String id;
        String nume;
        String regiune;
        String CNP;
        String cnpVotant, cnpCandidat;
        String numeCircumscriptie;
        String neindemanatic;
        int varsta;
        Alegeri alegere;

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
                    id = scanner.next();
                    nume = scanner.nextLine().trim();
                    managerAlegeri.creeazaAlegeri(id, nume);
                    break;
                    
                case 1:
                    System.out.println("Introduceti id-ul alegerilor:");
                    id = scanner.nextLine();
                    managerAlegeri.pornesteAlegeri(id);
                    break;

                case 2:
                    System.out.println("Introduceti id-ul alegerilor, numele circumscriptiei si regiunea:");
                    id = scanner.next();
                    nume = scanner.next().trim();
                    regiune = scanner.nextLine().trim();
                    managerAlegeri.adaugaCircumscriptie(id, nume, regiune);
                    break;

                case 3:
                    System.out.println("Introduceti id-ul alegerilor si numele circumscriptiei:");
                    id = scanner.next();
                    nume = scanner.nextLine().trim();
                    managerAlegeri.eliminaCircumscriptie(id, nume);
                    break;

                case 4:
                    System.out.println("Introduceti id-ul alegerilor, CNP-ul, varsta si numele candidatului:");
                    id = scanner.next();
                    CNP = scanner.next().trim();
                    varsta = scanner.nextInt();
                    nume = scanner.nextLine().trim();
                    managerAlegeri.adaugaCandidat(id, CNP, varsta, nume);
                    break;

                case 5:
                    System.out.println("Introduceti id-ul alegerilor si CNP-ul candidatului:");
                    id = scanner.next();
                    CNP = scanner.nextLine().trim();
                    managerAlegeri.eliminaCandidat(id, CNP);
                    break;

                case 6:
                    System.out.println("Introduceti id-ul alegerilor, numele circumscriptiei, CNP-ul, varsta, neindemanarea (da/nu) si numele votantului:");
                    id = scanner.next();
                    numeCircumscriptie = scanner.next().trim();
                    CNP = scanner.next().trim();
                    varsta = scanner.nextInt();
                    neindemanatic = scanner.next().trim();
                    nume = scanner.nextLine().trim();
                    managerAlegeri.adaugaVotant(id, numeCircumscriptie, CNP, varsta, neindemanatic, nume);
                    break;

                case 7:
                    System.out.println("Introduceti id-ul alegerilor:");
                    id = scanner.nextLine().trim();
                    managerAlegeri.listareCandidati(id);
                    break;

                case 8:
                    System.out.println("Introduceti id-ul alegerilor, numele circumscriptiei:");
                    id = scanner.next().trim();
                    numeCircumscriptie = scanner.next().trim();
                    managerAlegeri.listareVotanti(id, numeCircumscriptie);
                    break;

                case 9:
                    System.out.println("Indroduceti id-ul alegerilor, numele circumscriptiei, CNP-ul votantului si CNP-ul candidatului:");
                    id = scanner.next();
                    numeCircumscriptie = scanner.next().trim();
                    cnpVotant = scanner.next().trim();
                    cnpCandidat = scanner.nextLine().trim();
                    managerAlegeri.votare(id, numeCircumscriptie, cnpVotant, cnpCandidat);
                    break;

                case 10:
                    System.out.println("Introduceti id-ul alegerilor:");
                    id = scanner.nextLine().trim();
                    managerAlegeri.oprireAlegeri(id);
                    break;

                case 11:
                    System.out.println("Introduceti id-ul alegerilor si numele circumscriptiei:");
                    id = scanner.next().trim();
                    numeCircumscriptie = scanner.nextLine().trim();
                    managerAlegeri.raportCircumscriptie(id, numeCircumscriptie);
                    break;
                case 12:
                    System.out.println("Introduceti id-ul alegerilor:");
                    id = scanner.nextLine().trim();
                    managerAlegeri.raportNational(id);
                    break;

                case 13:
                    System.out.println("Introduceti id-ul alegerilor si numele circumscriptiei:");
                    id = scanner.next().trim();
                    numeCircumscriptie = scanner.nextLine().trim();
                    managerAlegeri.analizaDetaliataCircumscriptie(id, numeCircumscriptie);
                    break;

                case 14:
                    System.out.println("Introduceti id-ul alegerilor:");
                    id = scanner.nextLine().trim();
                    managerAlegeri.analizaDetaliataNational(id);
                    break;

                case 15:
                    System.out.println("Introduceti id-ul alegerilor:");
                    id = scanner.nextLine().trim();
                    managerAlegeri.rapoarteFraude(id);
                    break;

                case 16:
                    System.out.println("Introduceti id-ul alegerilor:");
                    id = scanner.nextLine().trim();
                    managerAlegeri.stergeAlegeri(id);
                    break;
                case 17:
                    managerAlegeri.listareAlegeri();
                    break;

                case 18:
                    System.out.println("Programul s-a incheiat. O zi buna!");
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