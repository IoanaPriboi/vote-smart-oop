package Tema1;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;

public class Circumscriptie {
    private String nume;
    private String regiune;
    private int numarVoturi;

    // private Regiune regiune;
    private ArrayList<Votant> votanti = new ArrayList<Votant>();
    private ArrayList<Vot> voturi = new ArrayList<Vot>();
    private ArrayList<Candidat> candidatiVotati = new ArrayList<Candidat>();
    // Constructori
    public Circumscriptie() {}
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

    public int getNumarVoturi() {
        return this.voturi.size();
    }

    public Votant cautaVotant(String CNP) {
        // Caut votantul in lista
        for(Votant v : votanti) {
            if(v.getCNP().equals(CNP)) {
                return v;
            }
        }
        return null;
    }

    public void adaugaVotant(String CNP, int varsta, String neindemanatic, String nume) {
        Votant v = cautaVotant(CNP);
        if(v != null) {
            System.out.println("EROARE: Votantul " + v.getNume() + " are deja acelasi CNP");
            return;
        }
        v = new Votant(this, CNP, varsta, neindemanatic, nume);
        votanti.add(v);
        System.out.println("S-a adaugat votantul " + v.getNume());
    }

    public void adaugaVot(Vot vot) {
        voturi.add(vot);
    }
    public void adaugaCandidatVotat(Candidat c) {
        candidatiVotati.add(c);
    }

    public void raportVoturi() {
        if(voturi.isEmpty()) {
            System.out.println("GOL: Lumea nu isi exercita dreptul de vot in " + this.nume);
            return;
        }
        ArrayList<Candidat> candidati = new ArrayList<Candidat>();
        for(Vot v : voturi) {
            Candidat c = v.getCandidat();
            if(!candidati.contains(c)) {
                c.setNumarVoturi(1);
                candidati.add(c);
            } else {
                c.adaugaVot();
            }
        }
        // Sortare descrescator dupa voturi, apoi descrescator dupa CNP
        candidati.sort((c1, c2) -> {
            if (c1.getNumarVoturi() != c2.getNumarVoturi()) {
                return Integer.compare(c2.getNumarVoturi(), c1.getNumarVoturi());
            }
            return c2.getCNP().compareTo(c1.getCNP());
        });

        System.out.println("Raport voturi " + this.nume + ":");
        for(Candidat c : candidati) {
            System.out.println(c.getNume() + " " + c.getCNP() + " - " + c.getNumarVoturi());
        }
        candidati.clear();
    }


    public Candidat celMaiVotatCandidat() {
        ArrayList<Candidat> candidati = new ArrayList<Candidat>();
        for(Vot v : voturi) {
            Candidat c = v.getCandidat();
            if(!candidati.contains(c)) {
                c.setNumarVoturi(1);
                candidati.add(c);
            } else {
                c.adaugaVot();
            }
        }
        // Sortare descrescator dupa voturi, apoi descrescator dupa CNP
        candidati.sort((c1, c2) -> {
            if (c1.getNumarVoturi() != c2.getNumarVoturi()) {
                return Integer.compare(c2.getNumarVoturi(), c1.getNumarVoturi());
            }
            return c2.getCNP().compareTo(c1.getCNP());
        });

        Candidat celMaiVotatCandidat = candidati.get(0);
        candidati.clear();
        return celMaiVotatCandidat;
    }
    public void analizaDetaliata(int numarVoturiNational) {
        if(voturi.isEmpty()) {
            System.out.println("GOL: Lumea nu isi exercita dreptul de vot in " + this.nume);
            return;
        }
        Candidat candidat = celMaiVotatCandidat();
        int procentajCircumscriptie = (getNumarVoturi() * 100) / numarVoturiNational;
        int procentajCandidat = (celMaiVotatCandidat().getNumarVoturi() * 100) / getNumarVoturi();
        System.out.println( "In " + nume + " au fost " + getNumarVoturi() + " voturi din " + numarVoturiNational +
                ". Adica " + procentajCircumscriptie + "%. Cele mai multe voturi au fost stranse de " + celMaiVotatCandidat().getCNP() + " " +
                celMaiVotatCandidat().getNume() + ". Acestea constituie " + procentajCandidat + "% din voturile circumscriptiei.");
    }


}
