package Tema1;

import java.util.ArrayList;

public class Circumscriptie {
    private String nume;
    private String regiune;
    private ArrayList<Alegeri> alegeri;

    // Constructori
    public Circumscriptie() {

    }
    public Circumscriptie(String nume, String regiune) {
        this.nume = nume;
        this.regiune = regiune;
    }

    public void adaugaAlegeri(Alegeri a) {
        this.alegeri.add(a);
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


}
