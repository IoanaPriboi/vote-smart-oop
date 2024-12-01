package Tema1;

import java.util.ArrayList;
import java.util.Collections;

public class ManagerCandidati {
    private ArrayList<Candidat> candidati = new ArrayList<>();

    // Constructori
    public ManagerCandidati() {
    }

    public ManagerCandidati(ArrayList<Candidat> candidati) {
        this.candidati = candidati;
    }

    // Cauta candidatul in lista de candidati dupa CNP si il returneaza
    public Candidat cautaCandidat(String cnp) {
        // Caut candidatul in lista
        for (Candidat candidat : candidati) {
            if (candidat.getCnp().equals(cnp)) {
                return candidat;   // am gasit candidatul
            }
        }
        // Nu am gasit candidatul => eroare
        System.out.println("EROARE: Nu exista un candidat cu CNP-ul " + cnp);
        return null;
    }

    // Adauga un candidat in alegeri
    public void adaugaCandidat(String cnp, int varsta, String nume) {
        // Verific daca mai exista in alegeri un candidat cu acelasi CNP
        for (Candidat candidat : candidati) {
            if (candidat.getCnp().equals(cnp)) {
                System.out.println("EROARE: Candidatul " + candidat.getNume() + " are deja acelasi CNP");
                return;
            }
        }

        // Nu am gasit un candiat cu acelasi CNP => creez un nou candidat
        Candidat candiat = new Candidat(cnp, varsta, nume);

        // Verific daca candidatul este valid si il adaug in alegeri
        if (candiat.esteValid()) {
            candidati.add(candiat);
            System.out.println("S-a adaugat candidatul " + nume);
        }
    }

    // Elimina un candidat din alegeri
    public void eliminaCandidat(String cnp) {
        // Verific daca exista candidatul in lista
        Candidat c = cautaCandidat(cnp);
        if (c == null) return;

        // Am gasit candidatul si il elimin din alegeri
        candidati.remove(c);
        System.out.println("S-a sters candidatul " + c.getNume());
    }

    // Sorteaza candidatii descrescator dupa CNP
    public void sortareCandidatiCnp() {
        Collections.sort(candidati);
    }

    // Sorteaza candidatii descrescator dupa nr. de voturi, apoi dupa CNP
    public void sortareCandidatiVoturiCnp() {
        candidati.sort(new ComparatorVoturiCnp());
    }

    // Afiseaza toti candidatii din sesiunea de alegeri
    public void afisareCandidati() {
        System.out.println("Candidatii:");
        for (Candidat c : candidati) {
            System.out.println(c);
        }
    }

    // Listeaza candidatii din sesiunea de alegeri ordonati descrescator dupa CNP
    public void listareCandidati() {
        // Verific daca exista candidati
        if (candidati.isEmpty()) {
            System.out.println("GOL: Nu sunt candidati");
        }

        // Sortez candidatii descrescator dupa CNP
        sortareCandidatiCnp();

        // Afisez candidatii sortati
        afisareCandidati();
    }

    // Returneaza cel mai votat candidat
    public Candidat celMaiVotatCandidat() {
        Candidat celMaiVotatCandidat = candidati.get(0);
        for (Candidat c : candidati) {
            if (c.getNumarVoturi() > celMaiVotatCandidat.getNumarVoturi()) {
                celMaiVotatCandidat = c;
            }
        }
        return celMaiVotatCandidat;
    }

    // Returneaza candidatii votati, acestia avand numarul de voturi corespunzator
    public ArrayList<Candidat> candidatiVotati(ArrayList<Vot> voturi) {
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

            // Daca nu exista candidatul, il adaug in lista
            if (!exista) {
                Candidat candidatNou = new Candidat(candidatVotat.getCnp(),
                        candidatVotat.getVarsta(), candidatVotat.getNume());
                candidatNou.adaugaVot();
                candidatiVotati.add(candidatNou);
            }
        }

        // Returneza lista de candidati votati
        return candidatiVotati;
    }

}
