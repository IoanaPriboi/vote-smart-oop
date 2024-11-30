package Tema1;

import java.util.*;

public class Alegeri {
    private String id;
    private String nume;
    private String stagiu;      // "NEINCEPUT", "IN_CURS" sau "TERMINAT"
    private ArrayList<Circumscriptie> circumscriptii = new ArrayList<>();
    private ArrayList<Candidat> candidati = new ArrayList<>();
    private ArrayList<Frauda> fraude = new ArrayList<>();

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

    public void setId(String id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getStagiu() {
        return stagiu;
    }

    public void setStagiu(String stagiu) {
        this.stagiu = stagiu;
    }

    public ArrayList<Circumscriptie> getCircumscriptii() {
        return circumscriptii;
    }

    public ArrayList<Candidat> getCandidati() {
        return candidati;
    }

    public ArrayList<Frauda> getFraude() {
        return fraude;
    }

    // Construieste si returneaza o lista cu regiunile din alegeri
    public ArrayList<Regiune> getRegiuni() {
        // Parcurg circumscriptiile si formez lista de regiuni
        ArrayList<Regiune> regiuni = new ArrayList<>();
        for(Circumscriptie c : circumscriptii) {
            String numeRegiune = c.getRegiune();

            // Verific daca regiunea exista deja in lista de regiuni
            boolean exista = false;
            for(Regiune r : regiuni) {
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

    // Verifica daca este perioada de votare
    public boolean estePerioadaDeVotare() {
        if(stagiu.equals("IN_CURS")) {
            return true;
        }

        // Nu este perioada de votare => eroare
        System.out.println("EROARE: Nu este perioada de votare");
        return false;
    }

    // Porneste alegerile daca ele nu au inceput deja
    public void pornesteAlegeri() {
        // Verific daca alegerile nu au inceput
        if(!stagiu.equals("NEINCEPUT")) {
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
        if(c == null) return;

        // Sterg circumscriptia din sesiunea de alegeri
        circumscriptii.remove(c);
        System.out.println("S-a sters circumscriptia " + numeCircumscriptie);
    }

    // Cauta candidatul in lista de candidati dupa CNP si il returneaza
    public Candidat cautaCandidat(String cnpCandidat) {
        // Caut candidatul in lista
        for (Candidat candidat : candidati) {
            if (candidat.getCnp().equals(cnpCandidat)) {
                return candidat;   // am gasit candidatul
            }
        }
        // Candidatul nu a fost gasit => eroare
        System.out.println("EROARE: Nu exista un candidat cu CNP-ul " + cnpCandidat);
        return null;
    }

    // Adauga un candidat in alegeri
    public void adaugaCandidat(String cnp, int varsta, String nume) {
        // Verific daca este perioada de votare
        if (!estePerioadaDeVotare()) return;

        // Verific daca mai exista in alegeri un candidat cu acelasi CNP
        // Am gasit un candidat cu acelasi CNP => eroare
        for (Candidat c : candidati) {
            // Am gasit un candidat cu acelasi CNP => eroare
            if (c.getCnp().equals(cnp)) {
                System.out.println("EROARE: Candidatul " + c.getNume() + " are deja acelasi CNP");
                return;
            }
        }

        // Nu am gasit un candiat cu acelasi CNP => creez un nou candidat
        Candidat candidat = new Candidat(cnp, varsta, nume);

        // Verific daca candidatul este valid si il adaug in alegeri
        if (candidat.esteValid()) {
            candidati.add(candidat);
            System.out.println("S-a adaugat candidatul " + nume);
        }
    }

    // Elimina un candidat din alegeri
    public void eliminaCandidat(String cnp) {
        // Verific daca este perioada de votare
        if (!estePerioadaDeVotare()) return;

        // Verific daca exista candidatul in lista
        Candidat c = cautaCandidat(cnp);

        // Nu am gasit candidatul => erorare
        if (c == null) {
            System.out.println("EROARE: Nu exista un candidat cu CNP-ul " + cnp);
            return;
        }

        // Am gasit candidatul si il elimin din alegeri
        candidati.remove(c);
        System.out.println("S-a sters candidatul " + c.getNume());
    }

    // Sorteaza candidatii descrescator dupa CNP
    public void sortareCandidatiCnp() {
        Collections.sort(candidati);
    }

    // Sorteaza candidatii descrescator dupa nr. de voturi, apoi dupa CNP
    public void sortareCandidatiVoturiCnp() {
        candidati.sort(new ComparatorVoturiCnp());
    }

    // Afiseaza toti candidatii din sesiunea de alegeri
    public void afisareCandidati() {
        System.out.println("Candidatii:");
        for (Candidat c : candidati) {
            System.out.println(c);
        }
    }

    // Listeaza candidatii din sesiunea de alegeri ordonati descrescator dupa CNP
    public void listareCandidati() {
        // Verific daca au inceput alegerile
        if (!(getStagiu().equals("IN_CURS") || getStagiu().equals("TERMINAT"))) {
            System.out.println("EROARE: Inca nu au inceput alegerile");
            return;
        }

        // Verific daca exista candidati
        if (candidati.isEmpty()) {
            System.out.println("GOL: Nu sunt candidati");
        }

        // Sortez candidatii descrescator dupa CNP
        sortareCandidatiCnp();

        // Afisez candidatii sortati
        afisareCandidati();
    }

    // Opreste alegerile
    public void oprireAlegeri() {
        // Verific daca este perioada de votare
        if (!estePerioadaDeVotare()) return;

        // Marchez alegerile ca terminate
        setStagiu("TERMINAT");
        System.out.println("S-au terminat alegerile " + nume);
    }

    // Returneaza o lista cu toate voturile nationale (din toate circumscriptiile)
    public ArrayList<Vot> getVoturiNational() {
        ArrayList<Vot> voturiNatioanle = new ArrayList<>();
        for (Circumscriptie c : circumscriptii) {
            voturiNatioanle.addAll(c.getVoturi());
        }
        return voturiNatioanle;
    }

    // Returneaza numarul de voturi pe plan national (din toate circumscriptiile)
    public int getNumarVoturiNational() {
        int numarVoturiNatioanal = 0;
        for (Circumscriptie c : circumscriptii) {
            numarVoturiNatioanal += c.getNumarVoturi();
        }
        return numarVoturiNatioanal;
    }

    // Creeaza raportul national de voturi (pentru toate circumscriptiile)
    public void raportNational() {
        // Verific daca s-a terminat sesiunea de alegeri
        if (!stagiu.equals("TERMINAT")) {
            System.out.println("EROARE: Inca nu s-a terminat votarea");
            return;
        }

        // Verific daca lumea isi exercita dreptul de vot
        ArrayList<Vot> voturiNationale = getVoturiNational();
        if (voturiNationale.isEmpty()) {
            System.out.println("GOL: Lumea nu isi exercita dreptul de vot in Romania");
            return;
        }

        // Sortez crescator dupa voturi, apoi descrescator dupa CNP
        sortareCandidatiVoturiCnp();

        // Afisez raportul de voturi
        System.out.println("Raport voturi Romania:");
        for (Candidat c : candidati) {
            System.out.println(c.getNume() + " " + c.getCnp() + " - " + c.getNumarVoturi());
        }
    }

    // Adauga fraduda in alegeri
    public void adaugaFrauda(Frauda frauda) {
        fraude.add(frauda);
    }

    // Afiseaza toate fraudele din sesiunea de alegeri
    public void rapoarteFraude() {
        // Verific daca s-a terminat sesiunea de alegeri
        if (!stagiu.equals("TERMINAT")) {
            System.out.println("EROARE: Inca nu s-a terminat votarea");
            return;
        }

        // Verific daca nu s-a comis nicio frauda
        if (fraude.isEmpty()) {
            System.out.println("GOL: Romanii sunt cinstiti");
            return;
        }

        // Afisez fraudele comise
        System.out.println("Fraude comise:");
        for (int i = fraude.size() - 1; i >= 0; i--) {
            System.out.println(fraude.get(i));
        }
    }

    // Suprascriu metoda toString pentru listarea alegerilor conform cerintei
    @Override
    public String toString() {
        return id + " " + nume;
    }

}