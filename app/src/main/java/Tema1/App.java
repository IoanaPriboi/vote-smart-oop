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

        //  Creez meniul de comenzi:
        ArrayList<String> comenzi = getMeniuComenzi();

        // Afisez meniul de comezi:
        System.out.println("Va multumim ca sunteti un cetatean implicat! Aveti mai jos meniul de comenzi:");
        for (int i = 0; i < comenzi.size(); i++) {
            System.out.println(i + ". " + comenzi.get(i));
        }
        System.out.println();

        /* Pentru claritatea si simplitatea codului folosesc managerAlegeri pentru a gestiona
         * comenzile introduse de user si a apela functiile corespunzatoare */
        ManagerAlegeri managerAlegeri = new ManagerAlegeri();

        // Declar variabilele pe care le voi folosi pentru citire
        // Alegeri:
        String id;
        String numeAlegeri;

        // Circumscriptie
        String numeCircumscriptie;
        String regiune;

        // Candidat
        String numeCandidat;
        String cnpCandidat;
        int varstaCandidat;

        // Votant
        String numeVotant;
        String cnpVotant;
        int varstaVotant;
        String neindemanatic;

        // Input-ul user-ului:
        while (true) {
            System.out.println("Va rugam introduceti comanda dorita (numarul corespunzator):");
            int comanda = scanner.nextInt();
            scanner.nextLine();
            if (comanda >= 0 && comanda <= 18) {
                System.out.println("Comanda selectata: " + comanda + ". " + comenzi.get(comanda));
            } else {
                System.out.println("Comanda invalida.");
            }

            // Incep sa gestionez comenzile si sa apelez metodele corespunzatoare
            switch (comanda) {
                case 0:     // 0. Creare alegeri
                    System.out.println("Introduceti id-ul si numele alegerilor:");
                    id = scanner.next();
                    numeAlegeri = scanner.nextLine().trim();
                    System.out.println("Ati introdus: " + id + " " + numeAlegeri);
                    managerAlegeri.creeazaAlegeri(id, numeAlegeri);
                    break;

                case 1:     // 1. Pornire Alegeri
                    System.out.println("Introduceti id-ul alegerilor:");
                    id = scanner.nextLine();
                    System.out.println("Ati introdus: " + id);
                    managerAlegeri.pornesteAlegeri(id);
                    break;

                case 2:     // 2. Adaugare circumscriptie
                    System.out.println("Introduceti id-ul alegerilor, numele circumscriptiei si regiunea:");
                    id = scanner.next();
                    numeCircumscriptie = scanner.next().trim();
                    regiune = scanner.nextLine().trim();
                    System.out.println("Ati introdus: " + id + " " + numeCircumscriptie + " " + regiune);
                    managerAlegeri.adaugaCircumscriptie(id, numeCircumscriptie, regiune);
                    break;

                case 3:     // 3. Eliminare circumscriptie
                    System.out.println("Introduceti id-ul alegerilor si numele circumscriptiei:");
                    id = scanner.next();
                    numeCircumscriptie = scanner.nextLine().trim();
                    System.out.println("Ati introdus: " + id + " " + numeCircumscriptie);
                    managerAlegeri.eliminaCircumscriptie(id, numeCircumscriptie);
                    break;

                case 4:     // 4. Adaugare candidat in alegeri
                    System.out.println("Introduceti id-ul alegerilor, CNP-ul, varsta si numele candidatului:");
                    id = scanner.next();
                    cnpCandidat = scanner.next().trim();
                    varstaCandidat = scanner.nextInt();
                    numeCandidat = scanner.nextLine().trim();
                    System.out.println("Ati introdus: " + id + " " + cnpCandidat + " " + varstaCandidat + " " + numeCandidat);
                    managerAlegeri.adaugaCandidat(id, cnpCandidat, varstaCandidat, numeCandidat);
                    break;

                case 5:     // 5. Eliminare candidat din alegeri
                    System.out.println("Introduceti id-ul alegerilor si CNP-ul candidatului:");
                    id = scanner.next();
                    cnpCandidat = scanner.nextLine().trim();
                    System.out.println("Ati introdus: " + id + " " + cnpCandidat);
                    managerAlegeri.eliminaCandidat(id, cnpCandidat);
                    break;

                case 6:       // 6. Adaugare votant in circumscriptie
                    System.out.println("Introduceti id-ul alegerilor, numele circumscriptiei, " +
                            "CNP-ul, varsta, neindemanarea (da/nu) si numele votantului:");
                    id = scanner.next();
                    numeCircumscriptie = scanner.next().trim();
                    cnpVotant = scanner.next().trim();
                    varstaVotant = scanner.nextInt();
                    neindemanatic = scanner.next().trim();
                    numeVotant = scanner.nextLine().trim();
                    System.out.println("Ati introdus: " + id + " " + numeCircumscriptie + " " +
                            cnpVotant + " " + varstaVotant + " " + neindemanatic + " " + numeVotant);
                    managerAlegeri.adaugaVotant(id, numeCircumscriptie, cnpVotant, varstaVotant, neindemanatic, numeVotant);
                    break;

                case 7:     // 7. Listarea candidatilor din alegeri
                    System.out.println("Introduceti id-ul alegerilor:");
                    id = scanner.nextLine().trim();
                    System.out.println("Ati introdus: " + id);
                    managerAlegeri.listareCandidati(id);
                    break;

                case 8:       // 8. Listarea votantilor dintr-o circumscriptie
                    System.out.println("Introduceti id-ul alegerilor, numele circumscriptiei:");
                    id = scanner.next().trim();
                    numeCircumscriptie = scanner.next().trim();
                    System.out.println("Ati introdus: " + id + " " + numeCircumscriptie);
                    managerAlegeri.listareVotanti(id, numeCircumscriptie);
                    break;

                case 9:       // 9. Votare
                    System.out.println("Indroduceti id-ul alegerilor, numele circumscriptiei, " +
                            "CNP-ul votantului si CNP-ul candidatului:");
                    id = scanner.next();
                    numeCircumscriptie = scanner.next().trim();
                    cnpVotant = scanner.next().trim();
                    cnpCandidat = scanner.nextLine().trim();
                    System.out.println("Ati introdus: " + id + " " + numeCircumscriptie + " " +
                            cnpVotant + " " + cnpCandidat);
                    managerAlegeri.votare(id, numeCircumscriptie, cnpVotant, cnpCandidat);
                    break;

                case 10:    // 10. Oprire alegeri
                    System.out.println("Introduceti id-ul alegerilor:");
                    id = scanner.nextLine().trim();
                    System.out.println("Ati introdus: " + id);
                    managerAlegeri.oprireAlegeri(id);
                    break;

                case 11:    // 11. Creeaza raport voturi per circumscriptie
                    System.out.println("Introduceti id-ul alegerilor si numele circumscriptiei:");
                    id = scanner.next().trim();
                    numeCircumscriptie = scanner.nextLine().trim();
                    System.out.println("Ati introdus: " + id + " " + numeCircumscriptie);
                    managerAlegeri.raportCircumscriptie(id, numeCircumscriptie);
                    break;

                case 12:    // 12. Creeaza raport voturi national (pentru toate circumscriptiile)
                    System.out.println("Introduceti id-ul alegerilor:");
                    id = scanner.nextLine().trim();
                    System.out.println("Ati introdus: " + id);
                    managerAlegeri.raportNational(id);
                    break;

                case 13:    // 13. Analiza detaliata per circumscriptie
                    System.out.println("Introduceti id-ul alegerilor si numele circumscriptiei:");
                    id = scanner.next().trim();
                    numeCircumscriptie = scanner.nextLine().trim();
                    System.out.println("Ati introdus: " + id + " " + numeCircumscriptie);
                    managerAlegeri.analizaDetaliataCircumscriptie(id, numeCircumscriptie);
                    break;

                case 14:    // 14. Analiza detaliata pe plan national
                    System.out.println("Introduceti id-ul alegerilor:");
                    id = scanner.nextLine().trim();
                    System.out.println("Ati introdus: " + id);
                    managerAlegeri.analizaDetaliataNational(id);
                    break;

                case 15:    // 15. Rapoarte fraudale
                    System.out.println("Introduceti id-ul alegerilor:");
                    id = scanner.nextLine().trim();
                    System.out.println("Ati introdus: " + id);
                    managerAlegeri.rapoarteFraude(id);
                    break;

                case 16:    // 16. Sterge alegeri
                    System.out.println("Introduceti id-ul alegerilor:");
                    id = scanner.nextLine().trim();
                    System.out.println("Ati introdus: " + id);
                    managerAlegeri.stergeAlegeri(id);
                    break;

                case 17:    // 17. Listare alegeri
                    managerAlegeri.listareAlegeri();
                    break;

                case 18:    // 18. Iesire
                    System.out.println("Programul s-a incheiat. O zi buna!");
                    scanner.close();
                    return;

                default:    // Comanda invalida
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

    // Construieste meniul cu toate comenzile posibile
    public ArrayList<String> getMeniuComenzi() {
        ArrayList<String> comenzi = new ArrayList<>();
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

        return comenzi;
    }
}