package Tema1;

public class Votant extends Persoana {
    private Circumscriptie circumscriptie;
    private String neindemanatic;
    private boolean votat;          // true -> candidatul a votat; false -> candidatul nu a votat
    private boolean votValid;       // votantul are un vot valid daca nu este neindemanatic


    // Constructori
    public Votant() {}

    public Votant(Circumscriptie circumscriptie, String CNP, int varsta, String neindemanatic, String nume) {
        super(CNP, varsta, nume);
        this.circumscriptie = circumscriptie;
        this.neindemanatic = neindemanatic;
        this.votat = false;
        this.votValid = neindemanatic.equals("da");
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
    public boolean getVotat() {
        return votat;
    }
    public void setVotat(boolean votat) {
        this.votat = votat;
    }

    public boolean getVotValid() {
        return votValid;
    }

}
