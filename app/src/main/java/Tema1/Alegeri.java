package Tema1;

import java.util.*;

public class Alegeri {
    private String id;
    private String nume;
    private String stagiu;      // "NEINCEPUT", "IN_CURS" sau "TERMINAT"
    private ArrayList<Circumscriptie> circumscriptii = new ArrayList<>();
    private ArrayList<Candidat> candidati = new ArrayList<>();
    private ArrayList<Frauda> fraude = new ArrayList<>();
    private ManagerCandidati managerCandidati = new ManagerCandidati(candidati);

    // Constructori
    public Alegeri() {
    }

    public Alegeri(String id, String nume, String stagiu) {
        this.id = id;
        this.nume = nume;
        this.stagiu = stagiu;
    }

    public Alegeri(String id, String nume) {
        this.id = id;
        this.nume = nume;
    }

    // Gettere si settere
    public String getId() {
        return id;
    }

    public String getNume() {
        return nume;
    }

    public String getStagiu() {
        return stagiu;
    }

    public void setStagiu(String stagiu) {
        this.stagiu = stagiu;
    }

    public ArrayList<Candidat> getCandidati() {
        return candidati;
    }

    public ManagerCandidati getManagerCandidati() {
        return managerCandidati;
    }

    public ArrayList<Frauda> getFraude() {
        return fraude;
    }

    // Verifica daca este perioada de votare
    public boolean estePerioadaDeVotare() {
        if (stagiu.equals("IN_CURS")) {
            return true;
        }

        // Nu este perioada de votare => eroare
        System.out.println("EROARE: Nu este perioada de votare");
        return false;
    }

    // Porneste alegerile daca ele nu au inceput deja
    public void pornesteAlegeri() {
        // Verific daca alegerile nu au inceput
        if (!stagiu.equals("NEINCEPUT")) {
            System.out.println("EROARE: Alegerile deja au inceput");
            return;
        }

        // Pornesc alegerile
        setStagiu("IN_CURS");
        System.out.println("Au pornit alegerile " + nume);
    }

    // Cauta in lista de circumscriptii si returneaza circumscriptia cu numele dat
    public Circumscriptie cautaCircumscriptie(String numeCircumscriptie) {
        // Caut circumscriptia in lista
        for (Circumscriptie c : circumscriptii) {
            if (c.getNume().equals(numeCircumscriptie)) {
                return c;       // am gasit circumscriptia
            }
        }

        // Nu am gasit circumscriptia => eroare
        System.out.println("EROARE: Nu exista o circumscriptie cu numele " + numeCircumscriptie);
        return null;
    }

    // Adauga circumscriptia c la sesiunea de alegeri
    public void adaugaCircumscriptie(String numeCircumscriptie, String regiune) {
        // Verific daca este perioada de votare
        if (!estePerioadaDeVotare()) return;

        // Caut si verific daca exista deja o circumscriptie cu acelasi nume
        for (Circumscriptie c : circumscriptii) {
            if (c.getNume().equals(numeCircumscriptie)) {
                System.out.println("EROARE: Deja exista o circumscriptie cu numele " + numeCircumscriptie);
                return;
            }
        }

        // Adaug circumscriptia in alegeri
        circumscriptii.add(new Circumscriptie(numeCircumscriptie, regiune));
        System.out.println("S-a adaugat circumscriptia " + numeCircumscriptie);
    }

    // Elimina circumscriptia cu numele dat din sesiunea de alegeri
    public void eliminaCircumscriptie(String numeCircumscriptie) {
        // Verific daca este perioada de votare
        if (!estePerioadaDeVotare()) return;

        // Verific daca exista circumsriptia in alegeri
        Circumscriptie c = cautaCircumscriptie(numeCircumscriptie);
        if (c == null) return;

        // Sterg circumscriptia din sesiunea de alegeri
        circumscriptii.remove(c);
        System.out.println("S-a sters circumscriptia " + numeCircumscriptie);
    }

    // Cauta candidatul in lista de candidati dupa CNP si il returneaza
    public Candidat cautaCandidat(String cnp) {
        return managerCandidati.cautaCandidat(cnp);
    }

    // Adauga un candidat in alegeri
    public void adaugaCandidat(String cnp, int varsta, String nume) {
        // Verific daca este perioada de votare
        if (!estePerioadaDeVotare()) return;

        // Adaug candidatul in alegeri
        managerCandidati.adaugaCandidat(cnp, varsta, nume);
    }

    // Elimina un candidat din alegeri
    public void eliminaCandidat(String cnp) {
        // Verific daca este perioada de votare
        if (!estePerioadaDeVotare()) return;

        // Elimin candidatul din alegeri
        managerCandidati.eliminaCandidat(cnp);
    }

    // Listeaza candidatii din sesiunea de alegeri ordonati descrescator dupa CNP
    public void listareCandidati() {
        // Verific daca au inceput alegerile
        if (!(stagiu.equals("IN_CURS") || stagiu.equals("TERMINAT"))) {
            System.out.println("EROARE: Inca nu au inceput alegerile");
            return;
        }

        // Listez candidatii
        managerCandidati.listareCandidati();
    }

    // Metoda prin care se voteaza in circumscriptie
    public void votare(String numeCircumscriptie, String cnpVotant, String cnpCandidat) {
        // Verific daca este perioada de votare
        if (!estePerioadaDeVotare()) return;

        // Verific daca exista circumscriptia
        Circumscriptie circumscriptie = cautaCircumscriptie(numeCircumscriptie);
        if(circumscriptie == null) return;

        // Verific daca exista candidatul
        Candidat candidat = cautaCandidat(cnpCandidat);
        if (candidat == null) return;

        // Verific daca votantul a incercat sa comita o frauda
        Votant votant = circumscriptie.cautaVotant(cnpVotant);
        if (votant == null || votant.getVotat()) {
            // Adaug frauda in lista de fraude din alegeri si afisez eroarea
            fraude.add(new Frauda(votant));
            System.out.println("FRAUDA: Votantul cu CNP-ul " + cnpVotant +
                    " a incercat sa comita o frauda. Votul a fost anulat");
            return;
        }

        // Votantul a fost cinctit, deci inregistrez votul
        Vot vot = new Vot(votant, candidat);
        votant.setVotat(true);

        // Verific daca votul este valid si il adaug in circumcriptie si candidatului
        if(vot.getValid()) {
            circumscriptie.adaugaVot(vot);
            candidat.adaugaVot();
        }

        // Afisez mesajul de succes
        System.out.println(votant.getNume() + " a votat pentru " + candidat.getNume());
    }

    // Opreste alegerile
    public void oprireAlegeri() {
        // Verific daca este perioada de votare
        if (!estePerioadaDeVotare()) return;

        // Marchez alegerile ca terminate
        setStagiu("TERMINAT");
        System.out.println("S-au terminat alegerile " + nume);
    }

    // Construieste si returneaza o lista cu regiunile din alegeri
    public ArrayList<Regiune> regiuni() {
        // Parcurg circumscriptiile si formez lista de regiuni
        ArrayList<Regiune> regiuni = new ArrayList<>();
        for (Circumscriptie c : circumscriptii) {
            String numeRegiune = c.getRegiune();

            // Verific daca regiunea exista deja in lista de regiuni
            boolean exista = false;
            for (Regiune r : regiuni) {
                if (r.getNume().equals(numeRegiune)) {
                    r.adaugaCircumscriptie(c);  // daca exista doar ii adaug circumscriptia
                    exista = true;
                    break;
                }
            }

            // Daca nu exista o adaug in lista si ii adaug circumscriptia
            if (!exista) {
                Regiune r = new Regiune(numeRegiune);
                r.adaugaCircumscriptie(c);
                regiuni.add(r);
            }
        }

        // Returnez lista rezultata
        return regiuni;
    }

    // Returneaza numarul de voturi pe plan national (din toate circumscriptiile)
    public int numarVoturiNational() {
        int numarVoturiNatioanal = 0;
        for (Circumscriptie c : circumscriptii) {
            numarVoturiNatioanal += c.getNumarVoturi();
        }
        return numarVoturiNatioanal;
    }

    // Suprascriu metoda toString pentru listarea alegerilor conform cerintei
    @Override
    public String toString() {
        return id + " " + nume;
    }

}