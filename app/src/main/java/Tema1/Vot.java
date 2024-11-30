package Tema1;

import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;

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

    public void setVotant(Votant votant) {
        this.votant = votant;
    }

    public Candidat getCandidat() {
        return candidat;
    }

    public void setCandidat(Candidat candidat) {
        this.candidat = candidat;
    }

    public boolean getValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
