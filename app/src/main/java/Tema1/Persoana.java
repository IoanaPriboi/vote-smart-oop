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

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getCnp() {
        return cnp;
    }

    public void setcCnp(String cnp) {
        this.cnp = cnp;
    }

    public int getVarsta() {
        return varsta;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    /* Verifica daca persoana are CNP-ul valid
     * daca CNP-ul are lungimea 13 -> true
     * in caz contrar -> false si scrie eroarea corespunzatoare
     */
    public boolean cnpValid() {
        // CNP valid
        if (cnp != null && cnp.length() == 13) {
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
