package Tema1;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.Collections;

// Gestioneaza si salveaza toate alegerile din program
public class ManagerAlegeri {
    private ArrayList<Alegeri> listaAlegeri = new ArrayList<>();    // lista cu toate alegerile
    private Analiza analiza = new Analiza();
    private Raport raport = new Raport();

    // Constructori
    public ManagerAlegeri() {
    }

    public ManagerAlegeri(ArrayList<Alegeri> listaAlegeri) {
        this.listaAlegeri = listaAlegeri;
    }

    // Gettere si settere
    public ArrayList<Alegeri> getListaAlegeri() {
        return listaAlegeri;
    }

    public void setListaAlegeri(ArrayList<Alegeri> listaAlegeri) {
        this.listaAlegeri = listaAlegeri;
    }

    // Cauta si returneaza alegerile cu id-ul dat ca parametru
    public Alegeri cautaAlegeri(String id) {
        for (Alegeri a : listaAlegeri) {
            if (a.getId().equals(id)) {
                return a;   // alegerile au fost gasite
            }
        }

        // Alegerile nu au fost gasite in lista
        System.out.println("EROARE: Nu exista alegeri cu acest id");
        return null;
    }

    // Creeaza o sesiune de alegeri
    public void creeazaAlegeri(String id, String nume) {
        // Caut si verific daca exista deja alegeri cu acelasi id
        for (Alegeri a : listaAlegeri) {
            if (a.getId().equals(id)) {
                System.out.println("EROARE: Deja exista alegeri cu id " + id);
                return;
            }
        }

        // Nu am gasit alegerile => creez o sesiune noua de alegeri si o adaug in lista
        listaAlegeri.add(new Alegeri(id, nume, "NEINCEPUT"));
        System.out.println("S-au creat alegerile " + nume);
    }

    // Porneste alegerile
    public void pornesteAlegeri(String id) {
        // Verific daca exista alegerile cu id-ul dat
        Alegeri a = cautaAlegeri(id);
        if (a == null) return;

        // Pornesc alegerile
        a.pornesteAlegeri();
    }

    // Adauga circumscriptia in alegeri
    public void adaugaCircumscriptie(String id, String nume, String regiune) {
        // Verific daca exista alegerile cu id-ul dat
        Alegeri a = cautaAlegeri(id);
        if (a == null) return;

        // Adaug circumscriptia in alegeri
        a.adaugaCircumscriptie(nume, regiune);
    }

    // Elimina circumscriptia din alegeri
    public void eliminaCircumscriptie(String id, String nume) {
        // Verific daca exista alegerile cu id-ul dat
        Alegeri a = cautaAlegeri(id);
        if (a == null) return;

        // Elimin circumscriptia
        a.eliminaCircumscriptie(nume);
    }

    // Adauga candidat in alegeri
    public void adaugaCandidat(String id, String cnp, int varsta, String nume) {
        // Verific daca exista alegerile cu id-ul dat
        Alegeri a = cautaAlegeri(id);
        if (a == null) return;

        // Adaug candidatul in alegeri
        a.adaugaCandidat(cnp, varsta, nume);
    }

    // Elimina candidat din alegeri
    public void eliminaCandidat(String id, String cnp) {
        Alegeri a = cautaAlegeri(id);
        if (a == null) return;

        a.eliminaCandidat(cnp);
    }

    // Adauga votant in circumscriptie
    public void adaugaVotant(String id, String numeCircumscriptie, String cnp, int varsta, String neindemanatic, String numeVotant) {
        // Verific daca exista alegerile cu id-ul dat
        Alegeri a = cautaAlegeri(id);
        if (a == null) return;

        // Verific daca este perioada de votare
        if (!a.estePerioadaDeVotare()) return;

        // Verific daca exista circumsriptia in alegeri
        Circumscriptie c = a.cautaCircumscriptie(numeCircumscriptie);
        if (c == null) return;

        // Adaug votantul in circumscriptie
        c.adaugaVotant(cnp, varsta, neindemanatic, numeVotant);
    }

    // Listeaza caditatii dintr-o sesiune de alegeri
    public void listareCandidati(String id) {
        // Verific daca exista alegerile cu id-ul dat
        Alegeri a = cautaAlegeri(id);
        if (a == null) return;

        // Listez candidatii din alegeri
        a.listareCandidati();
    }

    // Listeaza votantii dintr-o circumscriptie
    public void listareVotanti(String id, String numeCircumscriptie) {
        // Verific daca exista alegerile cu id-ul dat
        Alegeri a = cautaAlegeri(id);
        if (a == null) return;

        // Verific daca au inceput alegerile
        if (!(a.getStagiu().equals("IN_CURS") || a.getStagiu().equals("TERMINAT"))) {
            return;
        }

        // Verific daca exista circumsriptia in alegeri
        Circumscriptie c = a.cautaCircumscriptie(numeCircumscriptie);
        if (c == null) return;

        // Afisez votantii din circumscriptie
        c.listareVotanti();
    }

    // Inregistreaza un vot
    public void votare(String id, String numeCircumscriptie, String cnpVotant, String cnpCandidat) {
        // Verific daca exista alegerile cu id-ul dat
        Alegeri a = cautaAlegeri(id);
        if (a == null) return;

        // Verific daca este perioada de votare
        if (!a.estePerioadaDeVotare()) return;

        // Verific daca exista circumscriptia
        Circumscriptie c = a.cautaCircumscriptie(numeCircumscriptie);
        if(c == null) return;

        // Se realizeaza votarea
        c.votare(cnpVotant, cnpCandidat, a);
    }

    // Opreste alegerile
    public void oprireAlegeri(String id) {
        // Verific daca exista alegerile cu id-ul dat
        Alegeri a = cautaAlegeri(id);
        if (a == null) return;

        a.oprireAlegeri();
    }

    // Listeaza toate alegerile
    public void listareAlegeri() {
        // Verific daca exista alegeri
        if (listaAlegeri.isEmpty()) {
            System.out.println("GOL: Nu sunt alegeri");
            return;
        }

        // Afisez alegerile (in ordinea in care au fost bagate in lista)
        System.out.println("Alegeri:");
        for (Alegeri a : listaAlegeri) {
            System.out.println(a);
        }
    }

    // Afiseaza raportul voturilor pentru circumscriptia data
    public void raportCircumscriptie(String id, String numeCircumscriptie) {
        // Verific daca exista alegerile cu id-ul dat
        Alegeri a = cautaAlegeri(id);
        if (a == null) return;


        // Verific daca s-au terminat alegerile
        if (!a.getStagiu().equals("TERMINAT")) {
            System.out.println("EROARE: Inca nu s-a terminat votarea");
            return;
        }

        // Verific daca exista circumsriptia in alegeri
        Circumscriptie c = a.cautaCircumscriptie(numeCircumscriptie);
        if (c == null) return;

        // Afisez raportul pentru circumscriptie
        raport.raportCircumscriptie(c);
    }

    // Afiseaza raportul de voturi pe plan national
    public void raportNational(String id) {
        // Verific daca exista alegerile cu id-ul dat
        Alegeri a = cautaAlegeri(id);
        if (a == null) return;

        // Afisez raportul de voturi pe plan national
        raport.raportNational(a);
    }

    public void analizaDetaliataCircumscriptie(String id, String numeCircumscriptie) {
        // Verific daca exista alegerile cu id-ul dat
        Alegeri a = cautaAlegeri(id);
        if (a == null) return;

        // Verific daca exista circumscriptia in alegeri
        Circumscriptie c = a.cautaCircumscriptie(numeCircumscriptie);
        if (c == null) return;

        // Afisez analiza detaliata pentru circumscriptia data
        analiza.analizaDetaliataPerCircumscriptie(a, c);
    }

    public void analizaDetaliataNational(String id) {
        // Verific daca exista alegerile cu id-ul dat
        Alegeri a = cautaAlegeri(id);
        if (a == null) return;

        // Afisez analiza detaliata pe plan national
        analiza.analizaDetaliataNational(a);
    }

    // Afiseaza toate fraudele din sesiunea de alegeri
    public void rapoarteFraude(String id) {
        // Verific daca exista alegerile cu id-ul dat
        Alegeri a = cautaAlegeri(id);
        if (a == null) return;

        // Afisez fraudele comise
        raport.raportFraude(a);
    }

    // Sterge alegerile cu id-ul dat din lista de alegeri
    public void stergeAlegeri(String id) {
        // Verific daca exista alegerile cu id-ul dat
        Alegeri a = cautaAlegeri(id);
        if (a == null) return;

        listaAlegeri.remove(a);
        System.out.println("S-au sters alegerile " + a.getNume());
    }
}