package Tema1;

abstract public class Persoana implements Comparable<Persoana> {
    private String nume;
    private String cnp;
    private int varsta;

    // Constructori
    public Persoana() {
    }

    public Persoana(String cnp, int varsta, String nume) {
        this.nume = nume;
        this.cnp = cnp;
        this.varsta = varsta;
    }

    // Gettere si settere
    public String getNume() {
        return nume;
    }

    public String getCnp() {
        return cnp;
    }

    public int getVarsta() {
        return varsta;
    }

    // Verifica daca persoana are CNP-ul valid (este format din 13 cifre)
    public boolean cnpValid() {
        if (cnp != null && cnp.length() == 13 && cnp.matches("\\d+")) {
            return true;
        }

        // CNP invalid => eroare
        System.out.println("EROARE: CNP invalid");
        return false;
    }

    // Suprascriu metoda compareTo pentru ordonarea descrescatoare dupa CNP
    @Override
    public int compareTo(Persoana p) {
        return p.cnp.compareTo(this.cnp);
    }

    // Suprascriu metoda toString pentru afisare
    @Override
    public String toString() {
        return nume + " " + cnp + " " + varsta;
    }
}