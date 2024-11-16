package Tema1;

public class Candidat extends Persoana{
    private int numarVoturi = 0;

    // Constructori
    public Candidat() {
        super();
    }
    public Candidat(String CNP, int varsta, String nume) {
        super(CNP, varsta, nume);
    }

    // Gettere si settere
    public int getNumarVoturi() {
        return numarVoturi;
    }
    public void setNumarVoturi(int numarVoturi) {
        this.numarVoturi = numarVoturi;
    }

    // Sterge voturile cadidatului
    public void anuleazaVoturi() {
        this.numarVoturi = 0;
    }

    // Adauga un vot candidatului
    public void adaugaVot() {
        this.numarVoturi++;
    }

}
