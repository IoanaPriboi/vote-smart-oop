package Tema1;

abstract public class Persoana {
    protected String nume;
    protected String CNP;
    protected int varsta;

    // Constructori
    public Persoana() {}
    public Persoana(String CNP, int varsta, String nume) {
        this.nume = nume;
        this.CNP = CNP;
        this.varsta = varsta;
    }

    // Gettere si settere
    public String getNume() {
        return nume;
    }
    public void setNume(String nume) {
        this.nume = nume;
    }
    public String getCNP() {
        return CNP;
    }
    public void setcCNP(String CNP) {
        this.CNP = CNP;
    }
    public int getVarsta() {
        return varsta;
    }
    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    public String toString() {
        return nume + " " + CNP + " " + varsta;
    }
}
