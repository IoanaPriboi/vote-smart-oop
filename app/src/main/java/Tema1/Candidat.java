package Tema1;

import java.util.Comparator;

public class Candidat extends Persoana {
    private int numarVoturi = 0;

    // Constructori
    public Candidat() {
    }

    public Candidat(String cnp, int varsta, String nume) {
        super(cnp, varsta, nume);
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

    /* Verifica daca canditatul are varsta valida
     * daca varsta > 35 -> true
     * in caz contrar -> false si scrie eroarea corespunzatoare */
    public boolean varstaValida() {
        if (this.getVarsta() > 35) {
            return true;
        }
        System.out.println("EROARE: Varsta invalida");
        return false;
    }

    /* Verifica daca canditatul este valid, adica daca
     * are si CNP-ul si varsta valide  */
    public boolean eValid() {
        return cnpValid() && varstaValida();
    }
}

// Comparator care ma ajuta sa sortez descrescator dupa nr. de voturi, apoi descrescator dupa CNP
class ComparatorVoturiCnp implements Comparator<Candidat> {
    @Override
    public int compare(Candidat c1, Candidat c2) {
        if (c1.getNumarVoturi() == c2.getNumarVoturi()) {
            return c2.getCnp().compareTo(c1.getCnp());
        }
        return c2.getNumarVoturi() - c1.getNumarVoturi();
    }
}
