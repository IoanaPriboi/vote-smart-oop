package Tema1;

public class Frauda {
    Votant votant;
    Circumscriptie circumscriptie;

    // Constructori
    public Frauda() {
    }

    public Frauda(Votant votant) {
        this.votant = votant;
    }

    public Frauda(Votant Votant, Circumscriptie circumscriptie) {
        this.votant = Votant;
        this.circumscriptie = circumscriptie;
    }

    // Gettere si settere
    public Votant getVotant() {
        return votant;
    }

    public void setVotant(Votant votant) {
        this.votant = votant;
    }

    public Circumscriptie getCircumscriptie() {
        return circumscriptie;
    }

    public void setCircumscriptie(Circumscriptie circumscriptie) {
        this.circumscriptie = circumscriptie;
    }

    // Suprascriu metoda toString pentru afisarea fraudelor conform cerintei
    @Override
    public String toString() {
        // Verific daca am sters circumscriptia sau votantul intre timp
        if (circumscriptie == null || votant == null) {
            return null;
        }
        return "In " + circumscriptie.getNume() +
                ": " + votant.getCnp() + " " + votant.getNume();
    }
}