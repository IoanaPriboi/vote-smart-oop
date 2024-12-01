package Tema1;

public class Frauda {
    Votant votant;

    // Constructori
    public Frauda() {
    }

    public Frauda(Votant votant) {
        this.votant = votant;
    }

    // Gettere si settere
    public Votant getVotant() {
        return votant;
    }

    public void setVotant(Votant votant) {
        this.votant = votant;
    }

    // Suprascriu metoda toString pentru afisarea fraudelor conform cerintei
    @Override
    public String toString() {
        return "In " + votant.getCircumscriptie().getNume() +
                ": " + votant.getCnp() + " " + votant.getNume();
    }
}