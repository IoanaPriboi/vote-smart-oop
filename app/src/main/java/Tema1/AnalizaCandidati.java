package Tema1;

import java.util.ArrayList;

public class AnalizaCandidati {

    // Constructor
    public AnalizaCandidati() {}

    // Imi returneaza candidatii votati, acestia avand numarul de voturi corespunzator
    public ArrayList<Candidat> getCandidatiVotati(ArrayList<Vot> voturi) {
        ArrayList<Candidat> candidatiVotati = new ArrayList<>();
        for (Vot v : voturi) {
            // Verific daca candidatul exista deja in lista de candidati votati in circumscriptie
            Candidat candidatVotat = v.getCandidat();
            boolean exista = false;
            for (Candidat candidat : candidatiVotati) {
                if (candidat.getCnp().equals(candidatVotat.getCnp())) {
                    candidat.adaugaVot();
                    exista = true;
                    break;
                }
            }
            if (!exista) {
                Candidat candidatNou = new Candidat(candidatVotat.getCnp(),
                        candidatVotat.getVarsta(), candidatVotat.getNume());
                candidatNou.adaugaVot();
                candidatiVotati.add(candidatNou);
            }
        }
        return candidatiVotati;
    }

    // Returneaza cel mai votat candidat din lista de candidati
    public Candidat getCelMaiVotatCandidat(ArrayList<Candidat> candidati) {
        Candidat celMaiVotatCandidat = candidati.get(0);
        for (Candidat c : candidati) {
            if (c.getNumarVoturi() > celMaiVotatCandidat.getNumarVoturi()) {
                celMaiVotatCandidat = c;
            }
        }
        return celMaiVotatCandidat;
    }

}
