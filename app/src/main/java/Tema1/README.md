### Tema 1 POO - Priboi Ioana-Mădălina

## Ideea generală

## Structura programului

Pentru rezolvarea temei, am încercat să gândesc o structură cât 
mai firească (fiind vorba de programare orientată pe obiecte). 
Astfel, voi scrie si explica mai jos fiecare clasă implementată împreună 
cu toate atributele și metodele sale.

###### Observatie: Toate atributele sunt private si toate metodele sunt publice.


### App
Este clasa prin intermediul careia interactionez cu user-ul. 
Contine scheletul temei, meniul cu comenzile posibile

#### <ins> Atribute:
* `private Scanner scanner`-> se folosește pentru citirea de la tastatură (face parte din schelet)

#### <ins> Metode:
* `public App(InputStream input)` -> ajuta la citirea inputului (face parte din schelet)
* `public ArrayList<String> getMeniuComenzi()` -> creeaza si returneza meniul cu toate comezile posibile (o lista de string-uri ce contine numele comenzilor din enunt)
* `public void run()` -> contine implementarea cerintelor din enunt
  * creez meniul de comenzi cu ajutorul functiei `getMeniuComezi`
  * afisez meniul de comenzi, pentru a face programul cat mai user-friendly
  * cu ajutorul unui bloc de `switch` identific comanda introdusa de user si apelez metoda corespunzatoare folosind variabila locala `managerAlegeri`
    
###### Observatie: Introducerea unei comenzi invalide (un numar < 0 sau > 18) va afisa un mesaj sugestiv, dar nu va duce la oprirea programului, user-ul putand sa introduca ulterior o alta comanda. Programul se va opri doar cand se introduce numarul 18 (Iesire).

* `public static void main(String[] args)` -> metoda main a programului (face parte din schelet)

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
* Gettere si settere pentru fiecare atribut
* 

### ManagerAlegeri

#### <ins> Atribute:

#### <ins> Metode:


