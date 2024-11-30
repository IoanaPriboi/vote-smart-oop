package Tema1;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Circumscriptie {
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

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getRegiune() {
        return regiune;
    }

    public void setRegiune(String regiune) {
        this.regiune = regiune;
    }

    public ArrayList<Votant> getVotanti() {
        return votanti;
    }

    public void setVotanti(ArrayList<Votant> votanti) {
        this.votanti = votanti;
    }

    public ArrayList<Vot> getVoturi() {
        return voturi;
    }

    public void setVoturi(ArrayList<Vot> voturi) {
        this.voturi = voturi;
    }

    public int getNumarVoturi() {
        return voturi.size();
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
        v = new Votant(this, cnp, varsta, neindemanatic, nume);

        // Verific daca votantul e valid si, in caz afirmativ, il adaug in circumscriptie
        if (v.eValid()) {
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
        System.out.println("Votantii din " + nume + ":");
        for (Votant v : votanti) {
            System.out.println(v);
        }
    }

    // Listeaza votantii din circumscriptie
    public void listareVotanti() {
        // Verific daca exista votanti
        if (votanti.isEmpty()) {
            System.out.println("GOL: Nu sunt votanti in " + nume);
        }

        // Sortez votantii in ordine descrescatoare dupa CNP
        sortareVotantiCnp();

        // Afisez votantii ordonati
        afisareVotanti();
    }

    // Adauga un vot in lista de voturi
    public void adaugaVot(Vot vot) {
        voturi.add(vot);
    }

    /* Returneaza o lista cu canidatii votati in aceasta circumscriptie,
     * fiecare cadidat avand doar numarul de voturi din aceasta circumscriptie
     * Sortarea este descrescatoare dupa voturi, apoi descrescatoare dupa CNP */
    public ArrayList<Candidat> getCandidatiVotati() {
        AnalizaCandidati analiza = new AnalizaCandidati();
        return analiza.getCandidatiVotati(voturi);
    }

    // Returneaza cel mai votat candidat din circumscriptie
    public Candidat celMaiVotatCandidat() {
        AnalizaCandidati analiza = new AnalizaCandidati();
        return analiza.getCelMaiVotatCandidat(getCandidatiVotati());
    }

    // Afiseaza raportul de voturi pentru circumscriptie
    public void raportVoturi() {
        // Verific daca exista voturi in circumscriptie
        if (voturi.isEmpty()) {
            System.out.println("GOL: Lumea nu isi exercita dreptul de vot in " + nume);
            return;
        }

        // Aflu candidatii votati si nr. de voturi corespunzator fiecarui candidat din circumscriptie
        ArrayList<Candidat> candidatiVotati = getCandidatiVotati();

        // Sortez candidatii descrescator dupa voturi, apoi descrescator dupa CNP
        candidatiVotati.sort(new ComparatorVoturiCnp());

        // Afisez raportul de voturi in ordinea corespunzatoare
        System.out.println("Raport voturi " + nume + ":");
        for (Candidat c : candidatiVotati) {
            System.out.println(c.getNume() + " " + c.getCnp() + " - " + c.getNumarVoturi());
        }
    }
}
