package Tema1;

import java.util.ArrayList;
import java.util.Comparator;

public class Regiune implements Comparable<Regiune> {
    private String nume;
    private ArrayList<Circumscriptie> circumscriptii = new ArrayList<>();

    // Constructori
    public Regiune() {
    }

    public Regiune(String nume) {
        this.nume = nume;
    }

    // Gettere si settere
    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void adaugaCircumscriptie(Circumscriptie c) {
        circumscriptii.add(c);
    }

    // Returneaza numarul de voturi din regiune
    public int getNumarVoturi() {
        int numarVoturi = 0;
        for (Circumscriptie c : circumscriptii) {
            numarVoturi += c.getNumarVoturi();
        }
        return numarVoturi;
    }

    // Returneza candidatii votati in regiune cu numarul corespunzator de voturi
    public ArrayList<Candidat> getCandidatiVotati() {
        AnalizaCandidati analiza = new AnalizaCandidati();
        return analiza.getCandidatiVotati(getVoturi());
    }

    // Afiseaza candidatii votati din regiune
    public void afisareCandidatiVotati() {
        ArrayList<Candidat> candidatiVotati = getCandidatiVotati();
        for (Candidat c : candidatiVotati) {
            System.out.println(c + " " + c.getNumarVoturi());
        }
    }

    // Returneaza cel mai votat candidat din regiune
    public Candidat celMaiVotatCandidat() {
        AnalizaCandidati analiza = new AnalizaCandidati();
        return analiza.getCelMaiVotatCandidat(getCandidatiVotati());
    }

    // Returneaza o lista cu toate voturile din regiune
    public ArrayList<Vot> getVoturi() {
        ArrayList<Vot> voturi = new ArrayList<>();
        for (Circumscriptie c : circumscriptii) {
            voturi.addAll(c.getVoturi());
        }
        return voturi;
    }

    // Suprascriu metoda compateTo a.i. refiunile sa se ordoneze descrescator dupa nume
    @Override
    public int compareTo(Regiune r) {
        return r.getNume().compareTo(this.getNume());
    }
}