package Tema1;

public class Frauda {

    Votant votant;
    Circumscriptie circumscriptie;

    // Constructori
    public Frauda() {}
    public Frauda(Votant votant, Circumscriptie circumscriptie) {
        this.votant = votant;
        this.circumscriptie = circumscriptie;
    }

    //Gettere si settere
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

    @Override
    public String toString() {
        return  "In " + circumscriptie.getNume() +
                ": " + votant.getCNP() + " " + votant.getNume();
    }
}
