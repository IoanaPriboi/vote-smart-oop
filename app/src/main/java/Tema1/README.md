### Tema 1 POO - Priboi Ioana-Mădălina

# Vote Smart - Platforma de votare online

## Idee generală
**Vote smart**  este o platforma simpla de votare online, care gestioneaza procesul electoral
intr-un mod organizat si interactiv.

In cadrul aplicatiei, prin intermediul unui meniu sugestiv de comenzi,
utilizatorul poate crea alegeri, adauga circumscriptii, votanti si candidati, 
poate inregistra voturi. Dupa incheierea alegerilor, se pot genera rapoarte pentru voturi 
si analize detaliate, atat per circumscriptie, cat si pe plan national. 
De asemenea, exista si functii pentru validarea datelor si gestionarea tentativelor de frauda,
pentru a asigura corectitudinea procesului electoral.

## Funtionalitati

### 1. Interactiune user-friendly
* Aplicatia este usor de utilizat, implementand un sistem bazat pe comenzi textuale, in consola.
* Utilizatorul are acces la un meniu de comenzi clar si detaliat, iar fiecare optiune este explicata pas cu pas. 
* Mesajele de eroare sunt intuitive, oferind utilizatorului informatii clare despre actiunile care nu au fost efectuate corect.

### 2. Gestioarea procesului electoral
* Crearea sesiunilor de alegeri, cu optiuni de pornire, oprire, stergere.
* Adaugarea si eliminarea circumscriptiilor, candidatilor si votantilor, cu verificari pentru datele introduse.
* Listarea alegerilor, candidatilor din alegeri si a votantilor din circumscriptii.
* Inregistrarea voturilor valide si gestionarea tentativelor de frauda.

### 3. Vizualizarea rapoartelor si alanlizelor detaliate
* Afisarea rapoartelor voturilor atat per circumscriptie, cat si pe plan national.
* Afisarea analizelor detaliate (cu procentaje si statistici) atat per circumscriptie, cat si pe plan national
* Afisarea rapoartelor cu toate fraudele comise in sesiunea de alegeri.

## Structura programului

Pentru rezolvarea temei, am incercat sa gandesc o structura cat 
mai fireasca (fiind vorba de programare orientata pe obiecte). 
Astfel, voi scrie si explica mai jos fiecare clasa implementata, 
impreuna cu atributele si functionalitatile principale (a se vedea 
si comentariile din cod pentru detalii suplimentare)

###### Observatie: Toate atributele sunt private (pentru a respecta principiul incapsularii), iar toate metodele sunt publice pentru a permite interactiunea cu obiectele intr-un mod controlat.


### App
**Clasa App** reprezinta punctul de interactiune principal dintre utilizator si aplicatie. 
Aceasta contine structura de baza a programului, inclusiv meniul cu comenzile posibile, 
un `Scanner` pentru citirea inputului de la tastatura si logica de rulare a aplicatiei. 
Am personalizat afisarea in consola pentru a face rularea programului cat mai clara si intuitiva.

#### <ins> Atribute:
* `private Scanner scanner`-> se folosește pentru citirea de la tastatură (face parte din schelet)

#### <ins> Metode (functionalitati generale):
* `public App(InputStream input)` -> ajuta la citirea inputului (face parte din schelet)
* `public ArrayList<String> getMeniuComenzi()` -> creeaza si returneza meniul cu toate comezile posibile (o lista de string-uri ce contine numele comenzilor din enunt)
* `public static void main(String[] args)` -> metoda main a programului (face parte din schelet)
* `public void run()` -> contine implementarea cerintelor din enunt
  * creez meniul de comenzi cu ajutorul functiei `getMeniuComezi`
  * afisez meniul de comenzi, pentru a face programul cat mai user-friendly
  * cu ajutorul unui bloc de `switch` identific comanda introdusa de user si apelez metoda corespunzatoare folosind variabila locala `managerAlegeri`
    
###### Observatie: Introducerea unei comenzi invalide (un numar < 0 sau > 18) va afisa un mesaj sugestiv, dar nu va duce la oprirea programului, user-ul putand sa introduca ulterior o alta comanda. Programul se va opri doar cand se introduce numarul 18 (Iesire).

### ManagerAlegeri
Este o clasa auxiliara care ma ajuta sa gestionez intreaga lista de alegeri si sa execut comenzile introduse de user intr-un mod cat mai clar.
In general, in fiecare metoda din aceasta clasa verific daca exista alegerile cu id-ul dat, apoi apelez mai departe functia principala care implementeaza functionalitatea comenzii.
In unele cazuri, atunci cand e nevoie sa apelez o metoda din clasa Circumscriptie, verific si daca este indeplinita condita de desfasurare a alegerilor si daca circumscriptia data se afla in alegeri.

###### Obesrvatie: Fiind destul de repetitivi pasii si logica metodelor din aceasta clasa, voi detalia doar acolo unde cred ca este necesar (a se vedea si comentariile din cod)

#### <ins> Atribute:
* `private ArrayList<Alegeri> listaAlegeri` -> lista cu toate alegerile
* `private Analiza analiza = new Analiza()` -> ma ajuta sa apelez metodele de analiza
* `private Raport raport = new Raport() ` -> ma ajuta sa apelez metodele de rapoarte

#### <ins> Constructori:
* `public ManagerAlegeri()` -> folosesc doar "no-args constructor", intru-cat initializez toate atributele cand le declar 

#### <ins> Metode:
* `public Alegeri cautaAlegeri(String id)` -> cauta si returneaza alegerile cu id-ul dat ca parametru
  * intoarce obiectul de tip Alegeri daca a gasit alegerile in lista
  * intoarce null si afiseaza eroarea corespunzatoare daca nu a gasit alegerile in lista
* `public void creeazaAlegeri(String id, String nume)` -> creeaza alegerile cu id-ul dat
  * verific daca exista deja alegeri cu acelasi id
    * daca exista: afisez eroarea corespunzatoatre si opresc executia functiei
    * daca nu exista: creez o sesiune noua de alegeri, le adaug in lista si afisez mesajul de succes
* `public void pornesteAlegeri(String id)` -> porneste alegerile cu id-ul dat
  * verific daca exista alegerile in lista cu ajutorul functiei `cautaAlegeri`
    * daca nu exista: opresc executia programului (eroarea se va afisa automat in functia `cautaAlegeri`)
    * daca exista: apelez metoda corespunzatoare pentru alegerile gasite
* `public void adaugaCircumscriptie(String id, String nume, String regiune)` -> adauga circumsriptia in alegeri
  * verific daca exista alegerile in lista cu ajutorul functiei `cautaAlegeri`
    * daca nu exista: opresc executia programului (eroarea se va afisa automat in functia `cautaAlegeri`)
    * daca exista: apelez metoda corespunzatoare pentru alegerile gasite
* `public void eliminaCircumscriptie(String id, String nume)` -> elimina circumscriptia din alegeri
* `public void adaugaCandidat(String id, String cnp, int varsta, String nume)` -> adauga candidatul in alegeri
* `public void eliminaCandidat(String id, String cnp)` -> elimina candidatul din alegeri
* `public void adaugaVotant(String id, String numeCircumscriptie, String cnp, int varsta, String neindemanatic, String numeVotant)` -> adauga votant in circumscriptie
  * verific daca exista alegerile in lista cu ajutorul functiei `cautaAlegeri`
    * daca nu exista: opresc executia functiei (eroarea se va afisa automat in functia `cautaAlegeri`)
    * daca exista: verific daca este perioada de votare, apoi daca circumsriptia exista in sesiunea de alegeri. Daca toate conditiile sunt indeplinite apelez metoda `adaugaVotant(cnp, varsta, neindemanatic, numeVotant)` pentru circumscriptia gasita
* `public void listareCandidati(String id)` -> listeaza candidatii din sesiunea de alegeri, in ordine _descrescatoare_ dupa CNP
* `public void listareVotanti(String id, String numeCircumscriptie)` -> listeaza votantii  din circumscriptie, in ordine _descrescatoare_ dupa CNP
* `public void votare(String id, String numeCircumscriptie, String cnpVotant, String cnpCandidat)` -> implementeaza comanda de votare, inregistrand un vot in circumscriptia cu numele `numeCircumscriptie`, de la votantul cu CNP-ul `cnpVotant` catre candidatul cu CNP-ul `cnpCandidat`
* `public void oprireAlegeri(String id)` -> opreste alegerile cu id-ul dat
* `public void raportCircumscriptie(String id, String numeCircumscriptie)` -> afiseaza raportul voturilor din circumscriptia cu numele dat
  * apeleaza metoda `raportCircumscriptie(c)`, prin intermediul campului `raport`
* `public void raportNational(String id)` -> afiseaza raportul de voturi pe plan national (pentru toate circumscriptiile)
  * apeleaza metoda `raportNational(a)`, prin intremediul campului `raport`
* `public void analizaDetaliataCircumscriptie(String id, String numeCircumscriptie)` -> afiseaza analiza detaliata pentru circumscriptia cu numele dat ca parametru
    * apeleaza metoda `analizaDetaliataPerCircumscriptie(a, c)`, prin intermediul campului `analiza`
* `public void analizaDetaliataNational(String id)` -> afiseza analiza detaliata pe plan national
  * apeleaza metoda `analizaDetaliataNational(a)`, prin intermediul campului analiza
* `public void rapoarteFraude(String id)` -> afiseaza raportul cu toate fraudele din alegeri, in ordinea inversa in care au fost comise (LIFO, ca intr-o stiva)
  * apeleaza metoda `raportFraude(a)`, prin intermediul campului `raport`
* `public void listareAlegeri()` -> listeaza toate alegerile in ordinea in care au fost create
  * verific daca exista alegeri in lista:
    * lista goala => afisez eroarea corespunzatoare si opresc executia functiei
    * lista contine alegeri => iterez prin lista cu ajutorul unui `for-each loop` si afisez fiecare sesiune de alegeri
* `public void stergeAlegeri(String id)` -> sterge sesiunea de alegeri cu id-ul dat din lista
  * verific daca exista alegerile cu id-ul dat
    * nu exista => afisez eroarea corespunzatoare si opresc executia functiei
    * exista => o elimin din lista si afisez mesajul de succes

### Alegeri

#### <ins> Atribute:
* `private String id` -> id-ul alegerilor
* `private String nume` -> numele alegerilor
* `private String stagiu` -> stagiul alegerilor
  * _"NEINCEPUT"_ => alegerile nu au inceput inca
  * _"IN_CURS"_ => alegerile sunt in curs de desfasurare
  * _"TERMINAT"_ => Alegerile s-au incheiat

#### <ins> Metode:
* Constructori cu parametrii si fara parametrii
* Gettere si settere pentru toate atributele
* 

### Persoana

### Candidat

### Votant

### Vot

### Frauda

### ManagerCandidati

### ManagerVotanti

### Circumscriptie

### Regiune