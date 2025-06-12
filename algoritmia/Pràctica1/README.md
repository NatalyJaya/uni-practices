# Pràctica 1 - Algorísmia i Complexitat  
## L’illa de les hormones

### Descripció

Aquesta pràctica planteja un problema de posicionament dins d’una fila índia, protagonitzat per un conjunt de nois que tenen relacions d’enemistat i amistat. Cada noi ha de ser col·locat en una posició tal que, si veu al seu enemic darrere seu, també pugui veure primer el seu millor amic entre ells. A més, les relacions compleixen una jerarquia de fatxenderia, on un noi només pot tenir enemistat amb algú més fatxenda que ell (excepte el primer).

L’objectiu és trobar un ordre vàlid segons aquestes condicions, de forma eficient, evitant l’enfocament de força bruta O(n!) i optant per solucions O(n^2) o millors.

---

### Format d’entrada

L’entrada consisteix en un fitxer de text on cada línia conté tres noms: A B C

Aquest format representa que el noi A considera enemic a B i amic a C. Els nois estan ordenats per grau de fatxenderia, de més a menys.

---

### Format de sortida

La sortida ha de ser una sola línia amb els noms ordenats en una fila que compleixi totes les condicions donades. Si no és possible trobar una fila vàlida, la sortida ha de ser: impossible


---

### Instruccions d’ús

#### Python

Requereix Python 3.  
Executar:

```bash
python3 illa.py <fitxer_entrada> [fitxer_sortida]

- Si no s’indica el fitxer de sortida, la sortida s’imprimirà per pantalla.

#### Haskell

Compilar i executar:
ghc illa.hs -o illa ./illa <fitxer_entrada> <fitxer_sortida>


---

### Contingut del projecte

- `illa_iteratiu` — Part en Python de la pràctica, amb mètode principal eficient i mètode alternatiu per casos no coberts.
- `illa_recursiu` — Part en Haskell de la pràctica, utilitzant tècniques de backtracking per generar permutacions vàlides.
- `README.md` — Aquest document explicatiu.
- `Pràctica1.pdf` — Informe teòric opcional (si es genera amb pandoc a partir del README).

---

### Algorismes implementats

#### Python

- Mètode principal `find_valid_order`: busca un ordre vàlid a partir de la freqüència i resol violations amb una estratègia iterativa (O(n^2)).
- Mètode alternatiu `fallback_method`: construeix la fila inserint progressivament nois en posicions vàlides (O(n^2)).
- Control d’entrada/sortida via `sys.argv` o estàndard.

#### Haskell

- `inserir`: construcció recursiva amb backtracking per trobar ordres vàlids.
- `comprovarRegla`: funció que valida si una fila compleix una regla concreta.
- `resoldre`: retorna la primera solució vàlida o "impossible".
- Control de validesa i detecció de casos impossibles.

---

### Exemple

**Entrada:**
A D B 
B A D
C B D 
D B A

**Sortida esperada:**
A B C D

**Entrada sense solució:**
A A C 
B A D 
C B A 
D A B

**Sortida:**
impossible

---

### Cost teòric

- Python (mètode principal): O(n^2)
- Python (alternativa): O(n^2) en el pitjor cas
- Haskell (backtracking): O(n!) però eficient per a n petits
- No s’utilitza força bruta completa a Python

---

### Execució i test

Per compilar i testar tot el projecte:
make 
make test

No s’han de produir errors ni advertiments.

---

### Requisits tècnics

- Decorador i generador a Python si s’inclouen funcions addicionals
- Functor a Haskell per gestionar estructures de dades, si s’amplia
- Codi net i comentat, seguint bones pràctiques (pylint, tipatge, modularitat)

---

### Autors
Yhislaine Nataly, Jaya Salazar
Valeria Adriana, Escalera Flores

---

