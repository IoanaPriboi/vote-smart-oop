package Tema1;

public class Votant extends Persoana {
    private Circumscriptie circumscriptie;
    private String neindemanatic;
    private boolean votat;          // true -> candidatul a votat; false -> candidatul nu a votat


    // Constructori
    public Votant() {
    }

    public Votant(Circumscriptie circumscriptie, String cnp, int varsta, String neindemanatic, String nume) {
        super(cnp, varsta, nume);
        this.circumscriptie = circumscriptie;
        this.neindemanatic = neindemanatic;
        this.votat = false;
    }

    public Circumscriptie getCircumscriptie() {
        return circumscriptie;
    }

    public void setCircumscriptie(Circumscriptie circumscriptie) {
        this.circumscriptie = circumscriptie;
    }

    public String getNeindemanatic() {
        return neindemanatic;
    }

    public void setNeindemanatic(String neindemanatic) {
        this.neindemanatic = neindemanatic;
    }

    public boolean aVotat() {
        return votat;
    }

    public void setVotat(boolean votat) {
        this.votat = votat;
    }

    /* Verifica daca canditatul are varsta valida
     * daca varsta > 18 -> true
     * in caz contrar -> false si scrie eroarea corespunzatoare */
    public boolean varstaValida() {
        if (this.getVarsta() > 18) {
            return true;
        }
        System.out.println("EROARE: Varsta invalida");
        return false;
    }

    /* Verifica daca canditatul este valid, adica daca
     * are CNP valid si are varsta mai mare de 35 de ani */
    public boolean eValid() {
        return cnpValid() && varstaValida();
    }

}
