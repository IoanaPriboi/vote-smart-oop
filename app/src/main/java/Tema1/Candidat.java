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

    // Adauga un vot candidatului
    public void adaugaVot() {
        this.numarVoturi++;
    }

    // Verifica daca canditatul are varsta valida
    public boolean varstaValida() {
        if (this.getVarsta() > 35) {
            return true;
        }

        // Varsta invalida => eroare
        System.out.println("EROARE: Varsta invalida");
        return false;
    }

    // Verifica daca candidatul este valid (are varsta si CNP-ul valide)
    public boolean esteValid() {
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