package Tema1;

import java.util.ArrayList;
import java.util.Collections;

public class Analiza {

    // Afiseaza analiza detaliata per circumscriptie
    public void analizaDetaliataPerCircumscriptie(Alegeri alegeri, Circumscriptie circumscriptie) {
        // Verific daca cetatenii au votat
        int numarVoturiNational = alegeri.numarVoturiNational();
        int numarVoturiCircumscriptie = circumscriptie.getNumarVoturi();
        if (numarVoturiCircumscriptie == 0) {
            System.out.println("GOL: Lumea nu isi exercita dreptul de vot in " + circumscriptie.getNume());
            return;
        }

        // Calculez procentajele
        Candidat celMaiVotatCandidat = circumscriptie.celMaiVotatCandidat();
        int procentajCircumscriptie = (numarVoturiCircumscriptie * 100) / numarVoturiNational;
        int procentajCandidat = (celMaiVotatCandidat.getNumarVoturi() * 100) / numarVoturiCircumscriptie;

        // Afisez textul
        System.out.println("In " + circumscriptie.getNume() + " au fost " + numarVoturiCircumscriptie + " voturi din " + numarVoturiNational +
                ". Adica " + procentajCircumscriptie + "%. Cele mai multe voturi au fost stranse de " + celMaiVotatCandidat.getCnp() + " " +
                celMaiVotatCandidat.getNume() + ". Acestea constituie " + procentajCandidat + "% din voturile circumscriptiei.");
    }

    // Afiseaza analiza detaliata pe plan national
    public void analizaDetaliataNational(Alegeri alegeri) {
        // Verific daca nu a votat nimeni
        int numarVoturiNational = alegeri.numarVoturiNational();
        if (numarVoturiNational == 0) {
            System.out.println("GOL: Lumea nu isi exercita dreptul de vot in Romania");
            return;
        }

        // Obtin regiunile din alegeri si le sortez descrescator, dupa nume
        ArrayList<Regiune> regiuni = alegeri.regiuni();
        Collections.sort(regiuni);

        // Afisez analiza pentru fiecare regiune (adica pe plan national)
        System.out.println("In Romania au fost " + numarVoturiNational + " voturi.");

        for (Regiune r : regiuni) {
            // Calculez procentajele
            int procentaRegiune = (r.numarVoturi() * 100) / numarVoturiNational;
            int procentajCandidat = (r.celMaiVotatCandidat().getNumarVoturi() * 100) / r.numarVoturi();

            // Afisez textul
            System.out.println("In " + r.getNume() + " au fost " + r.numarVoturi() + " voturi din " + numarVoturiNational +
                    ". Adica " + procentaRegiune + "%. Cele mai multe voturi au fost stranse de " + r.celMaiVotatCandidat().getCnp() +
                    " " + r.celMaiVotatCandidat().getNume() + ". Acestea constituie " + procentajCandidat + "% din voturile regiunii.");
        }
    }
}
