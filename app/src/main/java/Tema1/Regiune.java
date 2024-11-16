package Tema1;

import java.util.ArrayList;

public class Regiune {
    private String nume;
    private double procentajNational;
    private double procentajCandidat;

    private ArrayList<Vot> voturi = new ArrayList<Vot>();

    public Regiune() {}
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
    public ArrayList<Vot> getVoturi() {
        return voturi;
    }
    public int getNrVoturi() {
        return voturi.size();
    }
    public void adaugaVot(Vot vot) {
        voturi.add(vot);
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

}
