package Tema1;

import java.util.*;

public class Alegeri {
    private String id;
    private String nume;
    private String status;


    // Constructori
    public Alegeri() {}
    public Alegeri(String id, String nume, String status) {
        this.id = id;
        this.nume = nume;
        this.status = status;
    }
    public Alegeri(String id, String nume) {
        this.id = id;
        this.nume = nume;
    }

    // Getter si setter
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNume() {
        return nume;
    }
    public void setNume(String nume) {
        this.nume = nume;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    // Functia creeaza alegeri (daca acestea sunt valide)
    public boolean creeazaAlegere(ArrayList<Alegeri> alegeri) {
        for(Alegeri a : alegeri) {
            if(a.id.equals(this.id)) {
                System.out.println("EROARE: Deja exista alegeri cu id " + a.id);
                return false;
            }
        }
        this.status = "NEINCEPUT";
        alegeri.add(this);
        System.out.println("S-au creat alegerile " + this.nume);
        return true;
    }

//    public boolean pornireAlegeri(ArrayList<Alegeri> alegeri, String id) {
//        for(Alegeri a: alegeri) {
//            if(a.id.equals(id)) {
//                if(a.status.equals("NEINCEPUT")) {
//                    a.setStatus("IN_CURS");
//                    System.out.println("Au pornit alegerile " + a.nume);
//                    return true;
//                } else {
//                    System.out.println("EROARE: Alegerile deja au inceput");
//                    return false;
//                }
//            }
//        }
//        System.out.println("EROARE: Nu exista alegeri acest id");
//        return false;
//    }

}
