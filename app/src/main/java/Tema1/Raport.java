package Tema1;

import java.util.ArrayList;

public class Raport {
    // Afiseaza raportul de voturi pentru circumscriptie
    public void raportCircumscriptie(Circumscriptie circumscriptie) {
        // Verific daca nu a votat nimeni in circumscriptie
        if (circumscriptie.getNumarVoturi() == 0) {
            System.out.println("GOL: Lumea nu isi exercita dreptul de vot in " + circumscriptie.getNume());
            return;
        }

        // Aflu candidatii votati si nr. de voturi corespunzator fiecarui candidat din circumscriptie
        ArrayList<Candidat> candidatiVotati = circumscriptie.candidatiVotati();

        // Sortez candidatii descrescator dupa voturi, apoi descrescator dupa CNP
        candidatiVotati.sort(new ComparatorVoturiCnp());

        // Afisez raportul de voturi in ordinea corespunzatoare
        System.out.println("Raport voturi " + circumscriptie.getNume() + ":");
        for (Candidat c : candidatiVotati) {
            System.out.println(c.getNume() + " " + c.getCnp() + " - " + c.getNumarVoturi());
        }
    }

    // Afiseaza raportul national de voturi pentru alegerile date ca parametru
    public void raportNational(Alegeri alegeri) {
        // Verific daca s-a terminat sesiunea de alegeri
        if (!alegeri.getStagiu().equals("TERMINAT")) {
            System.out.println("EROARE: Inca nu s-a terminat votarea");
            return;
        }

        // Verific daca lumea isi exercita dreptul de vot
        if (alegeri.numarVoturiNational() == 0) {
            System.out.println("GOL: Lumea nu isi exercita dreptul de vot in Romania");
            return;
        }

        // Sortez crescator dupa voturi, apoi descrescator dupa CNP
        alegeri.getManagerCandidati().sortareCandidatiVoturiCnp();

        // Afisez raportul de voturi
        System.out.println("Raport voturi Romania:");
        for (Candidat c : alegeri.getCandidati()) {
            System.out.println(c.getNume() + " " + c.getCnp() + " - " + c.getNumarVoturi());
        }
    }

    // Afiseaza toate fraudele din sesiunea de alegeri
    public void raportFraude(Alegeri alegeri) {
        // Verific daca s-a terminat sesiunea de alegeri
        if (!alegeri.getStagiu().equals("TERMINAT")) {
            System.out.println("EROARE: Inca nu s-a terminat votarea");
            return;
        }

        // Verific daca nu s-a comis nicio frauda
        ArrayList<Frauda> fraude = alegeri.getFraude();
        if (fraude.isEmpty()) {
            System.out.println("GOL: Romanii sunt cinstiti");
            return;
        }

        // Afisez fraudele comise
        System.out.println("Fraude comise:");
        for (int i = fraude.size() - 1; i >= 0; i--) {
            System.out.println(fraude.get(i));
        }
    }
}
