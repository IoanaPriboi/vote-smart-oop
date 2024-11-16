package Tema1;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

public class ManagerAlegeri {
    private ArrayList<Alegeri> listaAlegeri = new ArrayList<Alegeri>();

    // Constructori
    public ManagerAlegeri() {}
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

    // Cauta alegerile cu id-ul dat ca parametru
    public Alegeri cautaAlegeri(String id) {
        for(Alegeri a : listaAlegeri) {
            if(a.getId().equals(id)) {
                return a;
            }
        }

        // Alegerile nu au fost gasite in lista
        return null;
    }

    // Adauga alegerile in lista de alegeri
    public void adaugaAlegeri(Alegeri alegeri) {
        listaAlegeri.add(alegeri);
    }

    // Creeaza o sesiune de alegeri
    public void creeazaAlegeri(String id, String nume) {
        Alegeri a = cautaAlegeri(id);
        if(a != null) {
            System.out.println("EROARE: Deja exista alegeri cu id " + id);
            return;
        }
        a = new Alegeri(id, nume, "NEINCEPUT");
        adaugaAlegeri(a);
        System.out.println("S-au creat alegerile " + nume);
    }

    // Porneste alegerile
    public void pornesteAlegeri(String id) {
        Alegeri a = cautaAlegeri(id);

        // Alegerile nu au fost gasite
        if(a == null) {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
            return;
        }

        if(a.getStatus().equals("NEINCEPUT")) {
            a.setStatus("IN_CURS");
            System.out.println("Au pornit alegerile " + a.getNume());
        } else {
            System.out.println("EROARE: Alegerile deja au inceput");
        }
    }

    public void adaugaCircumscriptie(String id, String nume, String regiune) {
        Alegeri a = cautaAlegeri(id);

        // Alegerile nu au fost gasite
        if(a == null) {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
            return;
        }

        a.adaugaCircumscriptie(nume, regiune);
    }

    public void eliminaCircumscriptie(String id, String nume) {
        Alegeri a = cautaAlegeri(id);

        // Alegerile nu au fost gasite
        if(a == null) {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
            return;
        }
        a.eliminaCircumscriptie(nume);
    }

    public void adaugaCandidat(String id, String CNP, int varsta, String nume) {
        Alegeri a = cautaAlegeri(id);

        // Alegerile nu au fost gasite
        if(a == null) {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
            return;
        }
        a.adaugaCandidat(CNP, varsta, nume);
    }

    public void eliminaCandidat(String id, String CNP) {
        Alegeri a = cautaAlegeri(id);

        // Alegerile nu au fost gasite
        if(a == null) {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
            return;
        }

        a.eliminaCandidat(CNP);
    }

    public void adaugaVotant(String id, String numeCircumscriptie, String CNP, int varsta, String neindemanatic, String nume) {
        Alegeri a = cautaAlegeri(id);

        // Alegerile nu au fost gasite
        if(a == null) {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
            return;
        }

        a.adaugaVotant(numeCircumscriptie, CNP, varsta, neindemanatic, nume);
    }

    public void listareCandidati(String id) {
        Alegeri a = cautaAlegeri(id);

        // Alegerile nu au fost gasite
        if(a == null) {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
            return;
        }

        a.listareCandidati();
    }

    public void listareVotanti(String id, String numeCircumscriptie) {
        Alegeri a = cautaAlegeri(id);

        // Alegerile nu au fost gasite
        if(a == null) {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
            return;
        }

        a.listareVotanti(numeCircumscriptie);
    }
    public void votare(String id, String numeCircumscriptie, String cnpVotant, String cnpCandidat) {
        Alegeri a = cautaAlegeri(id);

        // Alegerile nu au fost gasite
        if(a == null) {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
            return;
        }

        a.votare(numeCircumscriptie, cnpVotant, cnpCandidat);
    }

    public void oprireAlegeri(String id) {
        Alegeri a = cautaAlegeri(id);

        // Alegerile nu au fost gasite
        if(a == null) {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
            return;
        }

        a.oprireAlegeri();
    }

    public void listareAlegeri() {
        if(listaAlegeri.isEmpty()) {
            System.out.println("GOL: Nu sunt alegeri");
            return;
        }
        System.out.println("Alegeri:");
        for(Alegeri a : listaAlegeri) {
            System.out.println(a);
        }
    }

    public void raportCircumscriptie(String id, String numeCircumscriptie) {
        Alegeri a = cautaAlegeri(id);

        // Alegerile nu au fost gasite
        if(a == null) {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
            return;
        }

        Circumscriptie c = a.cautaCircumscriptie(numeCircumscriptie);
        if(c == null) {
            System.out.println("EROARE: Nu exista o circumscriptie cu numele " + numeCircumscriptie);
            return;
        }

        if(!a.getStatus().equals("TERMINAT")) {
            System.out.println("EROARE: Inca nu s-a terminat votarea");
            return;
        }
        c.raportVoturi();
    }

    public void raportNational(String id) {
        Alegeri a = cautaAlegeri(id);

        // Alegerile nu au fost gasite
        if(a == null) {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
            return;
        }
        if(!a.getStatus().equals("TERMINAT")) {
            System.out.println("EROARE: Inca nu s-a terminat votarea");
            return;
        }

        ArrayList<Vot> voturi = a.getVoturi();
        if(voturi.isEmpty()) {
            System.out.println("GOL: Lumea nu isi exercita dreptul de vot in Romania");
            return;
        }

        ArrayList<Candidat> candidati = a.getCandidati();

        // Sortare crescator dupa voturi, apoi descrescator dupa CNP
        candidati.sort((c1, c2) -> {
            if (c1.getNumarVoturi() != c2.getNumarVoturi()) {
                return Integer.compare(c1.getNumarVoturi(), c2.getNumarVoturi());
            }
            return c2.getCNP().compareTo(c1.getCNP());
        });

        System.out.println("Raport voturi Romania:");
        for(Candidat c : candidati) {
            System.out.println(c.getNume() + " " + c.getCNP() + " - " + c.getNumarVoturi());
        }
        candidati.clear();
    }

    public void analizaDetaliataCircumscriptie(String id, String numeCircumscriptie) {
        Alegeri a = cautaAlegeri(id);

        // Alegerile nu au fost gasite
        if(a == null) {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
            return;
        }
        Circumscriptie c = a.cautaCircumscriptie(numeCircumscriptie);
        if(c == null) {
            System.out.println("EROARE: Nu exista o circumscriptie cu numele " + numeCircumscriptie);
            return;
        }
        if(!a.getStatus().equals("TERMINAT")) {
            System.out.println("EROARE: Inca nu s-a terminat votarea");
            return;
        }
        c.analizaDetaliata(a.getNumarVoturi());
    }

    public void analizaDetaliataNational(String id) {
        Alegeri a = cautaAlegeri(id);

        // Alegerile nu au fost gasite
        if(a == null) {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
            return;
        }

        a.analizaDetaliataNational();
    }

    public void rapoarteFraude(String id) {
        Alegeri a = cautaAlegeri(id);

        // Alegerile nu au fost gasite
        if(a == null) {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
            return;
        }

        a.rapoarteFraude();
    }

    public void stergeAlegeri(String id) {
        Alegeri a = cautaAlegeri(id);

        // Alegerile nu au fost gasite
        if(a == null) {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
            return;
        }

        listaAlegeri.remove(a);
        System.out.println("S-au sters alegerile " + a.getNume());
    }

}
