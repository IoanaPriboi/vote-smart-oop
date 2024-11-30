package Tema1;

import java.util.ArrayList;
import java.util.Collections;

public class ManagerVotanti {
    private ArrayList<Votant> votanti = new ArrayList<>();
    private Circumscriptie circumscriptie;

    // Constructori
    public ManagerVotanti() {}
    public ManagerVotanti(ArrayList<Votant> votanti) {
        this.votanti = votanti;
    }
    public ManagerVotanti(Circumscriptie circumscriptie) {
        this.circumscriptie = circumscriptie;
        this.votanti = circumscriptie.getVotanti();
    }


    // Cauta si returneaza votantul cu CNP-ul dat ca parametru
    public Votant cautaVotant(String cnp) {
        // Caut votantul in lista
        for (Votant v : votanti) {
            if (v.getCnp().equals(cnp)) {
                return v;   // am gasit votantul
            }
        }

        return null;    // nu am gasit votantul
    }

    // Adauga votant in circumscriptie
    public void adaugaVotant(String cnp, int varsta, String neindemanatic, String nume) {
        // Caut si verific daca in circumscriptie exista deja un votant cu acelasi CNP
        Votant v = cautaVotant(cnp);

        // Exista deja un votant cu acelasi CNP => eroare
        if (v != null) {
            System.out.println("EROARE: Votantul " + v.getNume() + " are deja acelasi CNP");
            return;
        }

        // Nu exista votantul => il creez
        v = new Votant(circumscriptie, cnp, varsta, neindemanatic, nume);

        // Verific daca votantul e valid si, in caz afirmativ, il adaug in circumscriptie
        if (v.esteValid()) {
            votanti.add(v);
            System.out.println("S-a adaugat votantul " + nume);
        }
    }

    // Sorteaza votantii descrescator dupa CNP
    public void sortareVotantiCnp() {
        Collections.sort(votanti);
    }

    // Afiseaza votantii din circumscriptie
    public void afisareVotanti() {
        System.out.println("Votantii din " + circumscriptie.getNume() + ":");
        for (Votant v : votanti) {
            System.out.println(v);
        }
    }

    // Listeaza votantii din circumscriptie
    public void listareVotanti() {
        // Verific daca exista votanti
        if (votanti.isEmpty()) {
            System.out.println("GOL: Nu sunt votanti in " + circumscriptie.getNume());
        }

        // Sortez votantii in ordine descrescatoare dupa CNP
        sortareVotantiCnp();

        // Afisez votantii ordonati
        afisareVotanti();
    }

}
