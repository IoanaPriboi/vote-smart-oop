package Tema1;

import java.util.*;

public class Alegeri {
    private String id;
    private String nume;
    private String status;
    private ArrayList<Regiune> regiuni =  new ArrayList<Regiune>();
    private ArrayList<Circumscriptie> circumscriptii = new ArrayList<Circumscriptie>();
    private ArrayList<Candidat> candidati = new ArrayList<Candidat>();
    private ArrayList<Votant> votanti = new ArrayList<Votant>();
    private ArrayList<Vot> voturi = new ArrayList<Vot>();
    private ArrayList<Frauda> fraude = new ArrayList<Frauda>();

    // Constructori
    public Alegeri() {
    }
    
    public Alegeri(String id, String nume, String status) {
        this.id = id;
        this.nume = nume;
        this.status = status;
    }
    public Alegeri(String id, String nume) {
        this.id = id;
        this.nume = nume;
    }

    // Getter si setter
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNume() {
        return nume;
    }
    public void setNume(String nume) {
        this.nume = nume;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public ArrayList<Circumscriptie> getCircumscriptii() {
        return circumscriptii;
    }
    public ArrayList<Candidat> getCandidati() {
        return candidati;
    }
    public ArrayList<Vot> getVoturi() {
        return voturi;
    }
    public int getNumarVoturi() {
        return voturi.size();
    }

    public boolean perioadaVotare() {
        if(!this.status.equals("IN_CURS")) {
            System.out.println("EROARE: Nu este perioada de votare");
            return false;
        }
        return true;
    }

    // Cauta circumscriptia cu numele nume in lista de circumscriptii
    public Circumscriptie cautaCircumscriptie(String nume) {

        // Caut circumscriptia in lista
        for(Circumscriptie c : circumscriptii) {
            if(c.getNume().equals(nume)) {
                return c;
            }
        }

        // Nu exista circumscriptia
        return null;
    }

    // Adauga circumscriptia c la sesiunea de alegeri
    public void adaugaCircumscriptie(String nume, String regiune) {
        // Verific daca alegerile sunt in desfasurare
        if(!this.perioadaVotare()) {
            return;
        }
        Circumscriptie c = cautaCircumscriptie(nume);
        if(c != null && !this.circumscriptii.isEmpty()) {
            System.out.println("EROARE: Deja exista o circumscriptie cu numele " + c.getNume());
            return;
        }

        c = new Circumscriptie(nume, regiune);
        this.circumscriptii.add(c);
        System.out.println("S-a adaugat circumscriptia " + c.getNume());
    }

    public void eliminaCircumscriptie(String nume) {
        // Verific daca alegerile sunt in desfasurare
        if(!this.perioadaVotare()) {
            return;
        }

        Circumscriptie c = cautaCircumscriptie(nume);
        if(c == null) {
            System.out.println("EROARE: Nu exista o circumscriptie cu numele " + nume);
            return;
        }
        this.circumscriptii.remove(c);
        System.out.println("S-a sters circumscriptia " + nume);
    }

    public Candidat cautaCandidati(String CNP) {
        // Caut candidatul in lista
        for(Candidat c : candidati) {
            if(c.getCNP().equals(CNP)) {
                return c;
            }
        }
        return null;
    }

    public boolean existaCandidat(String CNP) {
        Candidat c = cautaCandidati(CNP);
        if(c == null) {
            System.out.println("EROARE: Nu exista un candidat cu CNP-ul " + CNP);
            return false;
        }
        return true;
    }

    public void adaugaCandidat(String CNP, int varsta, String nume) {
        // Verific daca CNP-ul este valid
        if(CNP.length() != 13) {
            System.out.println("EROARE: CNP invalid");
            return;
        }

        // Verific daca varsta e valida
        if(varsta < 35) {
            System.out.println("EROARE: Varsta invalida");
            return;
        }

        // Verific daca alegerile sunt in desfasurare
        if(!perioadaVotare()) {
            return;
        }

        Candidat c = cautaCandidati(CNP);
        if(c != null) {
            System.out.println("EROARE: Candidatul " + c.getNume() + " are deja acelasi CNP");
            return;
        }

        c = new Candidat(CNP, varsta, nume);
        candidati.add(c);
        System.out.println("S-a adaugat candidatul " + c.getNume());
    }

    public void eliminaCandidat(String CNP) {
        // Verific daca alegerile sunt in desfasurare
        if(!perioadaVotare()) {
            return;
        }

        // Verific daca exista candidatul in lista
        Candidat c = cautaCandidati(CNP);
        if(c == null) {
            System.out.println("EROARE: Nu exista un candidat cu CNP-ul " + CNP);
            return;
        }
        candidati.remove(c);
        System.out.println("S-a sters candidatul " + c.getNume());
    }

    public Votant cautaVotant(String cnpVotant) {
        if(votanti.isEmpty()) {
            return null;
        }
        for(Votant v : votanti) {
            if(v.getCNP().equals(cnpVotant)) {
                return v;
            }
        }
        return null;
    }
    public void adaugaVotant(String numeCircumscriptie, String CNP, int varsta, String neindemanatic, String nume) {
        Circumscriptie c = cautaCircumscriptie(numeCircumscriptie);
        if(c == null) {
            System.out.println("EROARE: Nu exista o circumscriptie cu numele " + numeCircumscriptie);
            return;
        }

        // Verific daca CNP-ul este valid
        if(CNP.length() != 13) {
            System.out.println("EROARE: CNP invalid");
            return;
        }

        // Verific daca varsta e valida
        if(varsta < 18) {
            System.out.println("EROARE: Varsta invalida");
            return;
        }

        // Verific daca alegerile sunt in desfasurare
        if(!perioadaVotare()) {
            return;
        }
        Votant votant = new Votant(c, CNP, varsta, neindemanatic, nume);
        votanti.add(votant);
        c.adaugaVotant(CNP, varsta, neindemanatic, nume);
    }

    public void listareCandidati() {
        if(!(this.status.equals("IN_CURS") || this.status.equals("TERMINAT"))) {
            System.out.println("EROARE: Inca nu au inceput alegerile");
            return;
        }

        if(candidati.isEmpty()) {
            System.out.println("GOL: Nu sunt candidati");
        }

        // Sortare în ordine crescatoare dupa CNP
        candidati.sort((c1, c2) -> c1.getCNP().compareTo(c2.getCNP()));

        System.out.println("Candidatii:");
        for(Candidat c : candidati) {
            System.out.println(c);
        }
    }

    public boolean existaVotantInFraude(String cnpVotant) {
        if(fraude.isEmpty()) {
            return false;
        }
        for(Frauda f : fraude) {
            if(f.getVotant().getCNP().equals(cnpVotant)) {
                return true;
            }
        }
        return false;
    }
    public void listareVotanti(String numeCircumscriptie) {

        Circumscriptie c = cautaCircumscriptie(numeCircumscriptie);
        if(c == null) {
            System.out.println("EROARE: Nu exista o circumscriptie cu numele " + numeCircumscriptie);
            return;
        }

        if(!(this.status.equals("IN_CURS") || this.status.equals("TERMINAT"))) {
            System.out.println("EROARE: Inca nu au inceput alegerile");
            return;
        }

        ArrayList<Votant> votanti = c.getVotanti();
        if(votanti.isEmpty()) {
            System.out.println("GOL: Nu sunt votanti in " + numeCircumscriptie);
        }

        // Sortare în ordine crescatoare dupa CNP
        votanti.sort((v1, v2) -> v1.getCNP().compareTo(v2.getCNP()));


        System.out.println("Votantii din " + numeCircumscriptie + ":");
        for(Votant v : votanti) {
            System.out.println(v);
        }
    }

    public Regiune cautaRegiune(String numeRegiune) {
        if(regiuni.isEmpty()) {
            return null;
        }
        for(Regiune r : regiuni) {
            if(r.getNume().equals(numeRegiune)) {
                return r;
            }
        }
        return null;
    }

    public void votare(String numeCircumscriptie, String cnpVotant, String cnpCandidat) {
        if(!perioadaVotare()) {
            return;
        }

        Circumscriptie circumscriptie = cautaCircumscriptie(numeCircumscriptie);
        if(circumscriptie == null) {
            System.out.println("EROARE: Nu exista o circumscriptie cu numele " + numeCircumscriptie);
            return;
        }

        Candidat candidat = cautaCandidati(cnpCandidat);
        if(candidat == null) {
            System.out.println("EROARE: Nu exista un candidat cu CNP-ul " + cnpCandidat);
            return;
        }

        Votant votant = cautaVotant(cnpVotant);
        if(votant == null) {
            System.out.println("EROARE: Nu exista un votant cu CNP-ul " + cnpVotant);
            return;
        }

        if(circumscriptie.cautaVotant(cnpVotant) == null || votant.getVotat()) {
            if(!existaVotantInFraude(cnpVotant)) {
                fraude.add(new Frauda(votant, circumscriptie));
            }
            System.out.println("FRAUDA: Votantul cu CNP-ul " + cnpVotant + " a incercat sa comita o frauda. Votul a fost anulat");
            return;
        }

        votant.setVotat(true);
        Vot vot = new Vot(circumscriptie, votant, candidat);

        Regiune regiune = cautaRegiune(circumscriptie.getRegiune());
        if(regiune == null) {
            regiune = new Regiune(circumscriptie.getRegiune());
            regiuni.add(regiune);
        }

        if(votant.getVotValid()) {
            candidat.adaugaVot();
            circumscriptie.adaugaVot(vot);
            circumscriptie.adaugaCandidatVotat(candidat);
            regiune.adaugaVot(vot);
            voturi.add(vot);
            System.out.println("Candidatul " + candidat.getNume() + " are " + candidat.getNumarVoturi() + " voturi");
        }
        System.out.println(votant.getNume() + " a votat pentru " + candidat.getNume());
    }

    public void oprireAlegeri() {
        if(!perioadaVotare()) {
            return;
        }
        this.status = "TERMINAT";
        System.out.println("S-au terminat alegerile " + this.nume);
    }

    public void ordoneazaRegiuni() {
        regiuni.sort((r1, r2) -> {return r1.getNume().compareTo(r2.getNume());});
    }

    public void analizaDetaliataNational() {
        if(!this.status.equals("TERMINAT")) {
            System.out.println("EROARE: Inca nu s-a terminat votarea");
            return;
        }
        if(voturi.isEmpty()) {
            System.out.println("GOL: Lumea nu isi exercita dreptul de vot in Romania");
            return;
        }
        System.out.println("In Romania au fost " + this.getNumarVoturi() + " voturi.");
        ordoneazaRegiuni();
        for(Regiune r : regiuni) {
            int procentaRegiune = (r.getNrVoturi() * 100) / getNumarVoturi();
            int procentajCandidat = (r.celMaiVotatCandidat().getNumarVoturi() * 100) / r.getNrVoturi();
            System.out.println("In " + r.getNume() + " au fost " + r.getNrVoturi() + " voturi din " + getNumarVoturi() +
                    ". Adica " + procentaRegiune + "%. Cele mai multe voturi au fost stranse de " + r.celMaiVotatCandidat().getCNP() +
                    " " + r.celMaiVotatCandidat().getNume() + ". Acestea constituie " + procentajCandidat + "% din voturile regiunii.");
        }
    }

    public void rapoarteFraude() {
        if(!this.status.equals("TERMINAT")) {
            System.out.println("EROARE: Inca nu s-a terminat votarea");
            return;
        }

        if(fraude.isEmpty()) {
            System.out.println("GOL: Romanii sunt cinstiti");
        }

        System.out.println("Fraude comise:");
        for(int i = fraude.size() - 1; i >= 0; i--) {
            System.out.println(fraude.get(i));
        }
    }

    public String toString() {
        return this.id + " " + this.nume;
    }
}
