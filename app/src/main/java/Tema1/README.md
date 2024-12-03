### Tema 1 POO - Priboi Ioana-Mădălina

# Vote Smart - Platforma de votare online

## Idee generală
**Vote smart**  este o platforma simpla de votare online, care gestioneaza procesul electoral
intr-un mod organizat si interactiv.

In cadrul aplicatiei, prin intermediul unui meniu sugestiv de comenzi,
utilizatorul poate crea alegeri, adauga circumscriptii, votanti si candidati, 
poate inregistra voturi. Dupa incheierea alegerilor, se pot genera rapoarte pentru voturi 
si analize detaliate, atat per circumscriptie, cat si pe plan national. 
De asemenea, platforma contine si functii pentru validarea datelor si gestionarea tentativelor de frauda,
pentru a asigura corectitudinea procesului electoral.

## Funtionalitati

### 1. Interactiune user-friendly
* Aplicatie usor de utilizat, implementand un sistem bazat pe comenzi tip text, in consola.
* Meniu de comenzi clar si detaliat, iar fiecare optiune fiind explicata. 
* Mesaje de eroare intuitive, oferind utilizatorului informatii clare despre actiunile care nu au fost efectuate corect.

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

Pentru rezolvarea temei, am incercat sa gandesc o structura cat mai fireasca (fiind vorba de programare orientata pe obiecte). 
Astfel, voi oferi detalii despre fiecare clasa implementata (pentru cele mai importante => mai multe detalii, pentru cele mai putin importante => mai putine detalii :))

###### Observatie: Toate atributele sunt private (pentru a respecta principiul incapsularii), iar toate metodele sunt publice pentru a permite interactiunea cu obiectele intr-un mod controlat.
###### Am folosit ArrayLists pentru implementarea tablourilor unidimensionale, prin urmare am utilizat metodele predefinite pentru adaugare, stergere, sortare etc. (`add`, `remove` etc.).

### App
**Clasa App** reprezinta punctul de interactiune principal dintre utilizator si aplicatie. 
Aceasta contine structura de baza a programului, inclusiv meniul cu comenzile posibile, 
un `Scanner` pentru citirea inputului de la tastatura si logica de rulare a aplicatiei. 
Am personalizat afisarea in consola pentru a face rularea programului cat mai clara si intuitiva.

#### <ins> Atribute:
* `private Scanner scanner`-> se foloseste pentru citirea de la tastatura (face parte din schelet)

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
Clasa **ManagerAlegeri** este o clasa auxiliara care gestioneaza lista completa de alegeri si executa 
comenzile introduse de utilizator intr-un mod organizat si clar (poate fi privita ca un manager pentru comenzi).
De obicei, fiecare metoda identifica sesiunea de alegeri corespunzatoare pe baza id-ului, daca se afla in stagiul corespunzator si dupa care apeleaza functia principala care implementeaza comanda dorita.
In anumite situatii, atunci cand este necesar sa interactionez cu clasa Circumscriptie, verific si daca circumscriptia exista in sesiunea electorala.

###### Obesrvatie: Fiind destul de repetitivi pasii si logica metodelor din aceasta clasa, voi detalia doar acolo unde cred ca este necesar (a se vedea si comentariile din cod)

#### <ins> Atribute:
* `private ArrayList<Alegeri> listaAlegeri` -> lista cu toate alegerile
* `private Analiza analiza = new Analiza()` -> ma ajuta sa apelez metodele de analiza
* `private Raport raport = new Raport() ` -> ma ajuta sa apelez metodele de rapoarte

#### <ins> Metode (functionalitati principale):
* `public Alegeri cautaAlegeri(String id)` -> cauta si returneaza alegerile cu id-ul dat ca parametru
  * intoarce obiectul de tip Alegeri daca a gasit alegerile in lista
  * intoarce null si afiseaza eroarea corespunzatoare daca nu a gasit alegerile in lista
* `public void creeazaAlegeri(String id, String nume)` -> creeaza alegerile cu id-ul dat
  * verific daca exista deja alegeri cu acelasi id
    * exista => afisez eroarea corespunzatoatre si opresc executia functiei
    * nu exista => creez o sesiune noua de alegeri, le adaug in lista si afisez mesajul de succes
* `public void pornireAlegeri(String id)` -> porneste alegerile cu id-ul dat
  * verific daca exista alegerile in lista cu ajutorul functiei `cautaAlegeri`
    * nu exista => opresc executia programului (eroarea se va afisa automat in functia `cautaAlegeri`)
    * exista => verific daca alegerile nu au inceput deja si apelez metoda `pornireAlegeri` pentru alegerile gasite
* `public void oprireAlegeri(String id)` -> opreste alegerile cu id-ul dat
* `public void listareAlegeri()` -> listeaza toate alegerile in ordinea in care au fost create
  * verific daca exista alegeri in lista:
    * lista goala => afisez eroarea corespunzatoare si opresc executia functiei
    * lista contine alegeri => iterez prin lista cu ajutorul unui `for-each loop` si afisez fiecare sesiune de alegeri
* `public void stergeAlegeri(String id)` -> sterge sesiunea de alegeri cu id-ul dat din lista
  * verific daca exista alegerile cu id-ul dat
    * nu exista => afisez eroarea corespunzatoare si opresc executia functiei
    * exista => o elimin din lista si afisez mesajul de succes

### Alegeri
Clasa **Alegeri** reprezinta o sesiune de alegeri si are rolul de a gestiona circumscriptiile, candidatii si procesul de votare. 
Aici sunt centralizate toate informatiile si actiunile legate de o sesiune de alegeri, fiind cea mai importanta clasa din program.

#### <ins> Atribute:
* `private String id` -> id-ul alegerilor
* `private String nume` -> numele alegerilor
* `private String stagiu` -> stagiul alegerilor
  * _"NEINCEPUT"_ => alegerile nu au inceput inca
  * _"IN_CURS"_ => alegerile sunt in curs de desfasurare
  * _"TERMINAT"_ => Alegerile s-au incheiat
* `private ArrayList<Circumscriptie> circumscriptii` -> lista cu toate circumscriptiile din sesiunea de alegeri
* `private ArrayList<Candidat> candidati` -> lista cu toti candidatii din alegeri
* `private ArrayList<Frauda> fraude` -> lista cu toate fraudele comise in timpul alegerilor
* `private ManagerCandidati managerCandidati` -> atribut folosit pentru gestionarea candidatilor din alegeri

#### <ins> Metode (functionalitati principale):

##### 1. Gestionarea sesiunii curente de alegeri
* `public void pornireAlegeri(String id)` -> porneste sesiunea de alegeri
  * setez stagiul ca fiind "IN_CURS" si afisez mesajul de succes

* `public void oprireAlegeri()` -> opreste sesiunea de alegeri
  * setez stagiul ca "TERMINAT" si afisez mesajul de succes

##### 2. Gestionarea circumscriptiilor
* `public Circumscriptie cautaCircumscriptie(String numeCircumscriptie)` -> cauta circumscriptia in sesiunea de alegeri
  * iterez si caut prin lista de circumscriptii cu ajutorul unui `for-each loop`, pana gasesc circumscriptia cu numele dat
  * daca am gasit-o => o returnez
  * daca nu am gasit-o => afisez eroarea si returnez `null`
* `public void adaugaCircumscriptie(String numeCircumscriptie, String regiune)` -> adauga circumscriptia in sesiunea de alegeri
  * verific daca exista deja circumscriptia in alegeri
    * daca exista => afisez eroarea si ies din functie
    * daca nu => creez circumscriptia, o adaug in alegeri si afisez mesajul de succes
* `public void eliminaCircumscriptie(String numeCircumscriptie)` -> elimina circumscriptia din sesiunea de alegeri
  * verific daca exista circumscriptia in alegeri
    * daca nu => eroare si ies din functie
    * daca da => anulez toate voturile din acea circumscriptie, sterg circumscriptia din alegeri si afisez mesajul de succes

##### 3. Gestionarea candidatilor
Pentru gestionarea candidatilor am ales sa folosesc o clasa ajutatoare, numita `ManagerCandidati`, 
pentru a evita duplicarea codului (gestionez candidati si in circumscriptii si regiuni).
Astfel, metodele din Alegeri cu aceasta functionalitate vor verifica conditiile
de desfasurare a sesiunii si alegeri si vor apela metodele specializate din 
clasa ManagerCandidati, prin intermediul campului managerCandidati.
* `public Candidat cautaCandidat(String cnp)` -> cauta si returneaza candidatul
* `public void adaugaCandidat(String cnp, int varsta, String nume)` -> adauga candidatul in alegeri
* `public void eliminaCandidat(String cnp)` -> elimina candidatul din sesiunea de alegeri si anuleaza toate voturile primite de acesta in circumscriptii
* `public void listareCandidati()` -> listeaza candidatii daca au inceput alegerile

##### 4. Procesul de votare
Se realizeaza prin intermediul metodei `votare`, aceasta fiind cea mai complexa metoda din program:
* `public void votare(String numeCircumscriptie, String cnpVotant, String cnpCandidat)`
  * verific daca exista circumscriptia in alegeri
  * verific daca exista candidatul in alegeri
  * verific daca votantul a incercat sa comita o fraduda, adica daca nu este inregistrat in circumscriptia in care a incercat sa voteze sau daca a votat deja
    * a incercat sa comita o frauda => adaug frauda in lista, afisez eroarea corespunzatoare si opresc executia functiei
    * a fost cinstit:
      * marchez ca a votat si afisez mesajul de succes
      * verific daca votul sau este unul valid (votantul nu este neindemanatic)
        * daca da => adaug votul sau in circumscriptie si canditatului votat

##### 5. Informatii suplimentare
* `public boolean verificaPerioadaDeVotare()` -> verifica daca este perioada de votare (afiseaza eroare in caz contrar)
* `public boolean verificaTerminareAlegeri()` -> verifica daca s-au terminat alegerile (afiseaza eroare in caz contrar)
* `public boolean verificaIncepereAlegeri()` -> verifica daca au inceput alegerile (afiseaza eroare in caz contrar)
* `public int numarVoturiNational()` -> returneaza numarul total de voturi exprimate
  * calculez suma voturilor din toate circumscriptiile
  * returnez rezultatul

* `public ArrayList<Regiune> regiuni() `-> construieste si returneaza lista de regiuni din alegeri
  * iterez prin circumscriptiile din lista
  * pentru fiecare circumscriptie, adaug regiunea corespunzatoare in lista (daca nu exista deja)
  * returnez lista de regiuni

### Persoana

Clasa **Persoana** este o clasa abstracta de baza, avand atribute comune pentru toate persoanele implicate in procesul electoral.
Aceasta este folosita ca parinte pentru clasele Candidat si Votant.

Contine atribute si metode de baza: 

#### <ins> Atribute:

* `private String nume` -> numele persoanei
* `private String cnp` -> CNP-ul, folsit pentru identificare
* `private int varsta` -> varsta persoanei (folosita ulterior pentru validarea candidatilor si votantilor)

#### <ins> Metode (functionalitati principale):

* `public boolean cnpValid()` -> verifica daca persoana are CNP-ul valid (de lungime 13)
  * daca da => returneaza `true`
  * daca nu => afiseaza eroarea corespunsatoare si intoarce `false`
* `public int compareTo(Persoana p)` -> suprascrie metoda pentru ordonarea descrescatoare dupa CNP
* `public String toString()` -> suprascrie metoda pentru afisarea persoanelor conform cerintei

### Candidat

Clasa **Candidat** extinde clasa abstracta **Persoana** si reprezinta un participant in alegeri. Aceasta contine atribute
specifice unui candidat (`numarVoturi`), gestioneaza numarul de voturi primite si are metode de validare a candidatului.
Comparatorul `ComparatorVoturiCnp` ma ajuta sa sortez candidatii descrescator dupa numarul de voturi, 
apoi descrescator dupa CNP.

### Votant
Clasa **Votant** extinde clasa abstracta **Persoana** si reprezinta o persoana care isi exercita dreptul de vot intr-o circumscriptie. 
Aceasta contine atribute specifice unui votant, cum ar fi circumscriptia in care voteaza, daca a votat sau nu si daca este neindemanatic. 
De asemenea, clasa implementeaza functionalitati pentru validarea votantului.

### ManagerCandidati

Clasa **ManagerCandidati** este folosita pentru gestionarea candidatilor (intr-o sesiune de alegeri, intr-o circumscriptie sau intr-o regiune). 
Aceasta implementeaza functionalitati pentru adaugarea, eliminarea, sortarea si listarea candidatilor. 
De asemenea, permite identificarea candidatilor votati dintr-o lista de voturi si determinarea celui mai votat candidat dintr-o lista de candidati.

### Vot

Clasa **Vot** reprezinta un vot exprimat intr-o sesiune de alegeri. 
Aceasta este caracterizat prin votant, candidatul ales si validitatea votului. 
Validitatea este determinata de indemanarea votantului (atributul `neindemanatic`).

### Frauda

Clasa **Frauda** reprezinta o tentativa de frauda in procesul electoral. 
Aceasta este caracterizata prin votantul care a comis frauda si circumscriptia in care a fost comisa frauda. 
Am suprascris metoda `toString` pentru a ma ajuta sa afisez raportul de fraude conform cerintei.

### Circumscriptie

Clasa **Circumscriptie** reprezinta o subdiviziune teritoriala dintr-o sesiune de alegeri.
Aceasta gestioneaza votantii, voturile si candidatii la nivel de circumscriptie.
Include functionalitati pentru adaugarea votantilor si voturilor, precum si pentru identificarea candidatilor votati si a celui mai votat candidat.

### Regiune

Clasa **Regiune** reprezinta un grup de circumscriptii dintr-o anumita zona geografica. 
Aceasta contine metode pentru calcularea numarului total de voturi din regiune, identificarea 
candidatilor votati si determinarea celui mai votat candidat din regiune.
In aceasta clasa, suprascriu metoda `compareTo`, pentru a face afisarea descrescator, dupa nume.

### Analiza

Clasa **Analiza** este responsabila pentru afisarea analizelor detaliate ale rezultatelor alegerilor. 
Aceasta include metode pentru analiza detaliata la nivel de circumscriptie si pe plan national, 
calculand statistici precum procentaje celor mai votati candiati si distribuirea voturilor.

#### <ins> Atribute:

* Nu are atribute, intrucat e folosita doar pentru prelucrarea datelor din clasele Alegeri si Circumscriptie si afisarea analizelor rezultate

#### <ins> Metode (functionalitati principale):
* `public void analizaDetaliataPerCircumscriptie(Alegeri alegeri, Circumscriptie circumscriptie)` -> afiseaza analiza detaliata pentru circumscriptia data
  * verific daca a votat macar un cetatean in acea circumscriptie
    * daca nu => afisez eroarea si ies din functie
  * aflu numarul de voturi pe plan national, numarul de voturi din circumscriptie, precum si cel mai votat candidat din circumscriptie
  * calculez procentajele corespunzatoare: 
    * procentajul voturilor din circumscriptie raportat la voturile nationale (`procentajCircmscriptie`)
    * procentajul voturilor celui mai votat candidat raportat la totalul voturilor din circumscriptie (`procentajCandidat`)
  * afisez analiza respectand formatul impus


* `public void analizaDetaliataNational(Alegeri alegeri)` -> afiseaza analiza detaliata pe plan national (pentru toate regiunile din sesiunea de alegeri)
  * procedez exact ca mai sus, doar ca pentru fiecare regiune din sesiunea de alegeri (folosesc un for-each loop pentru a trece prin toate reginunile ordonate descrescator dupa nume)

### Raport

Clasa **Raport** este responsabila de generarea rapoartelor cu rezultatele alegerilor. 
Aceasta include metode ce afiseaza rapoarte pentru voturile dintr-o circumscriptie, pentru rezultatele 
pe plan national si pentru tentativele de frauda inregistrate in timpul alegerilor.

#### <ins> Atribute:

* Nu are atribute, intrucat e folosita doar pentru prelucrarea datelor din clasele Alegeri si Circumscriptie si afisarea rapoartelor rezultate

#### <ins> Metode (functionalitati principale):
* `public void raportCircumscriptie(Circumscriptie circumscriptie)` -> afiseaza raportul de voturi pentru circumscriptia data
  * verific daca a votat macar o persoana in circumscriptie
  * determin lista candidatilor votati in circumscriptie
  * sortez candidatii descrescator dupa voturi si descrescator dupa CNP
  * afisez raportul

* `public void raportNational(Alegeri alegeri)` -> afiseaza raportul de voturi pe plan national
  * verific daca a votat macar o persoana in toata tara
  * sortez lista cu candidatii din alegeri
  * afisez raportul national

* `public void raportFraude(Alegeri alegeri)` -> afiseaza raportul cu fraudele din alegeri
  * verific daca exista tentative de frauda
  * afisez lista cu fraudele, respectand ordinea last in first out (LIFO)


## Bonus

### Alte cazuri limita

* CNP-ul nu este valid, pentru ca nu respecta forma standard `SAALLZZJJNNNC`. In programul meu, un CNP este 
considerat valid daca este format doar din cifre si are lungimea 13, ceea ce in realitate nu este suficient
pentru ca un CNP sa fie corect (in enuntul temei este specificat doar cazul "Lungime CNP invalida").
* Varsta introdusa nu corespunde cu cea dedusa din CNP. Pentru a evita acest caz, varsta unei persoane ar putea fi extrasa direct din CNP 
(asta in cazul in care se implementeaza o metoda de validare a CNP-ului mai complexa, precum cea descrisa mai sus).
* Numele persoanelor (chiar si cel al alegerilor, circumsriptiilor, regiunilor etc.) ar trebui sa respecte un anumit format care sa fie testat si validat 
(de exemplu, nu pot contine caractere speciale)
* Daca s-a comis o frauda intr-o circumscriptie care a fost stearsa ulterior, frauda ramane in lista cu toate fraudele din cadrul sesiunii de alegeri si va aparea in raportul final. 
In opinia mea, fraudele comise intr-o circumsriptie eliminata din alegeri ar trebui sterse, asa cum sunt sterse voturile si votantii din cadrul acelei circumscriptii.
Daca nu se sterg aceste fraude, atunci, la crearea raportului cu fraude, ar trebui afisata o eroare de genul: "EROARE: Circumscriptia nu mai exista".
* Romanii din diaspora nu prea pot sa voteze prin intermediul acestei aplicatii, poate ar fi fost utila introducerea unei astfel de optiuni, 
pentru ca procesul elecotral sa fie cat mai apropiat de realitate.


### Refactorizarea comenzilor si raspunsurilor

* Citirea input-ului cred ca putea fi mai clara. Poate ar fi fost utila o clasa separata care sa contina metode separate pentru citirea inputului si afisarea mesajelor pentru fiecare comanda. De exemplu:
  * Clasa `CitireInput` cu metode ce contin practic blocurile din structura `switch`, din metoda `run`. De exemplu, pentru comanda 0. Creare alegeri:
      * in clasa `CitireInput`:
         ```
        public String[] comanda0() {
              System.out.println("Introduceti id-ul si numele alegerilor:");
              String id = scanner.next();
              String numeAlegeri = scanner.nextLine().trim();
              System.out.println("Ati introdus: " + id + " " + numeAlegeri);
              return new String[] {id, numeAlegeri};
        } 
        ```
        * in metoda `run()` din clasa `App`:
          ```
          switch (comanda) {
             case 0: // Creare alegeri
               String[] input = citireInput.comanda0();
               managerAlegeri.creeazaAlegeri(input[0], input[1]);
               break;
          ```
          
* Introducerea unor blocuri try-catch pentru anumite exceptii si erori poate ar fi fost benefica. De exemplu, in structura `switch`:
  * ````
        try {
          switch (comanda) {
             case 0: // Creare alegeri
               String[] input = citireInput.comanda0();
               managerAlegeri.creeazaAlegeri(input[0], input[1]);
               break;
        } catch (Exception e){
              // un mesaj
        }
    ````
    
* Implementarea unei interfete cu metode de citire input, afisare mesaje si rulare comenzi ar putea face logica programului mai clara.
Interfata ar putea avea un schelet de genul:
  * ````
    public interface AplicatieInterface {
    // Citeste input-ului
    String citesteInput(String input);

    // Afiseaza un mesaj in consola
    void afiseazaMesaj(String mesaj);

    // Ruleaza o comanda
    void ruleazaComanda(int id);
    } 
    ````
          

  