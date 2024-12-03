package Tema1;

import org.checkerframework.checker.units.qual.A;

import java.util.*;

public class Circumscriptie {
    // Atribute
    private String nume;
    private String regiune;     // numele regiunii din care face parte
    private ArrayList<Votant> votanti = new ArrayList<>();
    private ArrayList<Vot> voturi = new ArrayList<>();

    // Constructori
    public Circumscriptie() {
    }

    public Circumscriptie(String nume, String regiune) {
        this.nume = nume;
        this.regiune = regiune;
    }

    // Gettere si settere
    public String getNume() {
        return nume;
    }

    public String getRegiune() {
        return regiune;
    }

    public ArrayList<Vot> getVoturi() {
        return voturi;
    }

    public int getNumarVoturi() {
        return voturi.size();
    }

    // Sorteaza votantii descrescator dupa CNP
    public void sortareVotantiCnp() {
        Collections.sort(votanti);
    }

    // Adauga votant in circumscriptie
    public void adaugaVotant(String cnp, int varsta, String neindemanatic, String numeVotant) {
        // Caut si verific daca in circumscriptie exista deja un votant cu acelasi CNP
        Votant v = cautaVotant(cnp);

        // Exista deja un votant cu acelasi CNP => eroare
        if (v != null) {
            System.out.println("EROARE: Votantul " + v.getNume() + " are deja acelasi CNP");
            return;
        }

        // Nu exista votantul => il creez
        v = new Votant(this, cnp, varsta, neindemanatic, numeVotant);

        // Verific daca votantul e valid si, in caz afirmativ, il adaug in circumscriptie
        if (v.esteValid()) {
            votanti.add(v);
            System.out.println("S-a adaugat votantul " + numeVotant);
        }
    }

    // Afiseaza votantii din circumscriptie
    public void afisareVotanti() {
        System.out.println("Votantii din " + this.nume + ":");
        for (Votant v : votanti) {
            System.out.println(v);
        }
    }

    // Listeaza votantii din circumscriptie
    public void listareVotanti() {
        // Verific daca exista votanti
        if (votanti.isEmpty()) {
            System.out.println("GOL: Nu sunt votanti in " + this.nume);
        }

        // Sortez votantii in ordine descrescatoare dupa CNP
        sortareVotantiCnp();

        // Afisez votantii ordonati
        afisareVotanti();
    }

    // Adauga vot in circumscriptie
    public void adaugaVot(Vot vot) {
        voturi.add(vot);
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

    // Sterge voturile pentru candidatul dat ca parametru
    public void anuleazaVoturiCandidat(String cnpCandidat) {
        // Parcurg toate voturile si le sterg pe cele care sunt in favoarea candidatului dat ca parametru
        for (Vot v : voturi) {
            if (v.getCandidat().getCnp().equals(cnpCandidat)) {
                v.getCandidat().anuleazaVot();
                voturi.remove(v);
            }
        }
    }

    // Returneaza o lista cu canidatii votati in aceasta circumscriptie
    public ArrayList<Candidat> candidatiVotati() {
        return new ManagerCandidati().candidatiVotati(voturi);
    }

    // Returneaza cel mai votat candidat din circumscriptie
    public Candidat celMaiVotatCandidat() {
        return new ManagerCandidati(candidatiVotati()).celMaiVotatCandidat();
    }

    // Returneaza numarul de voturi din circumscriptie pentru candidatul dat
    public int numarVoturiCandidat(String cnpCandidat) {
        int numarVoturi = 0;
        for (Vot v : voturi) {
            if (v.getCandidat().getCnp().equals(cnpCandidat)) {
                numarVoturi++;
            }
        }
        return numarVoturi;
    }
}
