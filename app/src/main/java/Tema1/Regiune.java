package Tema1;

import java.util.ArrayList;


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

    // Adauga o circumsriptie in regiune
    public void adaugaCircumscriptie(Circumscriptie c) {
        circumscriptii.add(c);
    }

    // Returneaza numarul de voturi din regiune
    public int numarVoturi() {
        int numarVoturi = 0;
        for (Circumscriptie c : circumscriptii) {
            numarVoturi += c.getNumarVoturi();
        }
        return numarVoturi;
    }

    // Returneza candidatii votati in regiune, cu numarul corespunzator de voturi
    public ArrayList<Candidat> candidatiVotati() {
        ManagerCandidati managerCandidati = new ManagerCandidati();
        return managerCandidati.candidatiVotati(voturi());
    }

    // Returneaza cel mai votat candidat din regiune
    public Candidat celMaiVotatCandidat() {
        ManagerCandidati managerCandidati = new ManagerCandidati(candidatiVotati());
        return managerCandidati.celMaiVotatCandidat();
    }

    // Returneaza o lista cu toate voturile din regiune
    public ArrayList<Vot> voturi() {
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