package Tema1;

import java.util.*;

public class Circumscriptie {
    // Atribute
    private String nume;
    private String regiune;     // numele regiunii din care face parte
    private ArrayList<Votant> votanti = new ArrayList<>();
    private ArrayList<Vot> voturi = new ArrayList<>();
    private ManagerVotanti managerVotanti = new ManagerVotanti(this);

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

    public ArrayList<Votant> getVotanti() {
        return votanti;
    }

    public ArrayList<Vot> getVoturi() {
        return voturi;
    }

    public int getNumarVoturi() {
        return voturi.size();
    }

    // Adauga votant in circumscriptie
    public void adaugaVotant(String cnp, int varsta, String neindemanatic, String nume) {
        managerVotanti.adaugaVotant(cnp, varsta, neindemanatic, nume);
    }

    // Listeaza votantii din circumscriptie
    public void listareVotanti() {
        managerVotanti.listareVotanti();
    }

    // Adauga un vot in lista de voturi a circumscriptiei
    public void adaugaVot(Vot vot) {
        voturi.add(vot);
    }

    // Verifica daca votantul a incercat sa comita o frauda
    public boolean incercareFrauda(String cnpVotant, Alegeri alegeri) {
        Votant votant = managerVotanti.cautaVotant(cnpVotant);
        if (votant == null || votant.aVotat()) {
            // Adaug frauda in lista de fraude din alegeri si afisez eroarea
            alegeri.adaugaFrauda(new Frauda(votant));
            System.out.println("FRAUDA: Votantul cu CNP-ul " + cnpVotant +
                    " a incercat sa comita o frauda. Votul a fost anulat");
            return true;
        }

        // Votantul a fost cinstit
        return false;
    }

    // Inregistrez votul votantului pentru candidat
    public void inregistrareVot(Votant votant, Candidat candidat) {
        votant.setVotat(true);
        Vot vot = new Vot(votant, candidat);

        // Verific daca votul este valid
        if (vot.getValid()) {
            candidat.adaugaVot();   // adaug votul candidatului
            adaugaVot(vot);         // adaug votul in lista de voturi
        }

        // Afisez mesajul pentru succes
        System.out.println(votant.getNume() + " a votat pentru " + candidat.getNume());
    }

    // Metoda prin care se voteaza in circumscriptie
    public void votare(String cnpVotant, String cnpCandidat, Alegeri alegeri) {
        // Verific daca exista candidatul
        Candidat candidat = alegeri.cautaCandidat(cnpCandidat);
        if (candidat == null) return;

        // Verific daca votantul a incercat sa comita o frauda
        if (incercareFrauda(cnpVotant, alegeri)) return;

        // Votantul a fost cinstit, deci inregistrez votul
        Votant votant = managerVotanti.cautaVotant(cnpVotant);
        inregistrareVot(votant, candidat);
    }

    // Returneaza o lista cu canidatii votati in aceasta circumscriptie
    public ArrayList<Candidat> candidatiVotati() {
        return new ManagerCandidati().candidatiVotati(voturi);
    }

    // Returneaza cel mai votat candidat din circumscriptie
    public Candidat celMaiVotatCandidat() {
        return new ManagerCandidati(candidatiVotati()).celMaiVotatCandidat();
    }

}