# 🏝️ La illa de les hormones

## 📋 Descripció del projecte

Aquest projecte forma part de l’assignatura **Algoritmia i Complexitat** del Grau en Enginyeria Informàtica (EPS - Universitat de Lleida). L’objectiu és resoldre un problema d’optimització relacionat amb la divisió d’un conjunt de nois en dos equips, maximitzant la separació entre membres d’equips oposats.

> **Problema:** Donats $2n$ nois amb coordenades $(x_i, y_i)$, es vol dividir-los en dos equips de $n$ membres (equip roig i equip blau) de forma que la **distància mínima** entre qualsevol parell de nois d’equips oposats sigui **la màxima possible**.

---

## 📁 Contingut del repositori

- `input.txt` — Exemple de fitxer d’entrada.
- `output.txt` — Resultat de la solució.
- `illa-hormones-2.pdf` — Documentació formal del projecte (amb anàlisi, disseny i justificació teòrica).
- `backtracking.py` — Codi implementat amb Backtracking
- `dynamic.py` — Codi implementat amb Programació Dinàmica
- `battle.py` — Codi implementat de dymanic en haskell
- `batalla.py``batalla.hs` — Codi proporcionat pel professorat
- `hsChecker.py` — Script de verificació per al codi en Haskell.
- `README.md` — Aquest fitxer.

---
# Sistema d'Avaluació Automàtica

Aquest repositori utilitza scripts en Python per a avaluar automàticament solucions escrites en Haskell i Python.

---

## 🧪 Avaluació d'Arxius Haskell

Per executar un arxiu Haskell (`.hs`), utilitza la següent comanda:

```bash
python3 hsChecker.py battle.hs

```
## 🧪 Avaluació d'Arxius Python

Per executar un arxiu Haskell (`.py`), utilitza la següent comanda:

```bash
python3 Checker.py backtracking.py
python3 Checker.py dynamic.py
