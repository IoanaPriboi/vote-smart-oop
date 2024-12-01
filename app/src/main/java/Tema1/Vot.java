package Tema1;

public class Vot {
    private Votant votant;
    private Candidat candidat;
    private boolean valid;

    // Constructori
    public Vot() {
    }

    public Vot(Votant votant, Candidat candidat) {
        this.votant = votant;
        this.candidat = candidat;
        this.valid = this.votant.getNeindemanatic().equals("nu");
    }

    // Gettere si settere
    public Votant getVotant() {
        return votant;
    }

    public Candidat getCandidat() {
        return candidat;
    }

    public boolean getValid() {
        return valid;
    }
}