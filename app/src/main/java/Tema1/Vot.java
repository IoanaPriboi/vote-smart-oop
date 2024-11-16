package Tema1;

import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;

public class Vot {
    private Alegeri alegeri;
    private Circumscriptie circumscriptie;
    private Votant votant;
    private Candidat candidat;

    // Constructori
    public Vot() {}
    public Vot(Alegeri alegeri, Circumscriptie circumscriptie, Votant votant, Candidat candidat) {
        this.alegeri = alegeri;
        this.circumscriptie = circumscriptie;
        this.votant = votant;
        this.candidat = candidat;
    }

    public Vot(Circumscriptie circumscriptie, Votant votant, Candidat candidat) {
        this.circumscriptie = circumscriptie;
        this.votant = votant;
        this.candidat = candidat;
    }

    // Gettere si settere
    public Alegeri getAlegeri() {
        return alegeri;
    }
    public void setAlegeri(Alegeri alegeri) {
        this.alegeri = alegeri;
    }
    public Circumscriptie getCircumscriptie() {
        return circumscriptie;
    }
    public void setCircumscriptie(Circumscriptie circumscriptie) {
        this.circumscriptie = circumscriptie;
    }
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


}
