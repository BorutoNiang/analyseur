# Analyseur Syntaxique d'Expressions Arithmétiques

Mini-Projet POO — DIC1 Génie Informatique  
UCAD / École Supérieure Polytechnique — 2025-2026  
Encadrant : Pr I. Fall

---

## Structure du projet

```
src/
  version1/       → Analyseur de base (booléens, sans gestion débordement)
  version2/       → Gestion du débordement ajoutée
  interpreteur/   → Calcule et affiche le résultat des expressions
  compilateur/    → Génère du pseudo-code pile (PUSH / ADD / MUL)
docs/
  javadoc/        → Documentation API générée (ouvrir index.html)
sujet/
  Mini Projet POO DIC1.pdf
  Mini_Projet_POO_DIC1.md
```

---

## Grammaire

```
E -> S ;
S -> P | P + S
P -> T | T * P
T -> C | ( S )
C -> 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9
```

Chaque symbole non-terminal correspond à une méthode dans `Analyseur` :
`expression()`, `somme()`, `produit()`, `terme()`, `chiffre()`.

---

## Prérequis

- Java JDK 11 ou supérieur
- Compiler et exécuter depuis le dossier de chaque version

---

## Version 1 — Analyseur de base

Analyse syntaxique avec retour booléen. Aucune gestion du débordement :
si le point-virgule est absent, une `StringIndexOutOfBoundsException` est levée.

```bash
cd src/version1
javac Source.java Analyseur.java Principale.java
java Principale
```

Résultats attendus :
```
Test "2+3;"      -> Analyse réussie : expression valide.
Test "2*3+4;"    -> Analyse réussie : expression valide.
Test "(2+3)*4;"  -> Analyse réussie : expression valide.
Test "2+;"       -> Erreur de syntaxe : expression invalide.
Test "2+3"       -> StringIndexOutOfBoundsException  (comportement attendu v1)
```

---

## Version 2 — Gestion du débordement

`Source.premier()` retourne `'\0'` et `Source.suivant()` retourne `false`
en fin de chaîne. Plus aucune exception non gérée.

```bash
cd src/version2
javac Source.java Analyseur.java Principale.java
java Principale
```

Résultats attendus :
```
Test "2+3;"      -> Analyse réussie : expression valide.
Test "2*3+4;"    -> Analyse réussie : expression valide.
Test "(2+3)*4;"  -> Analyse réussie : expression valide.
Test "2+3$4;"    -> Erreur de syntaxe : expression invalide.
Test "2+3"       -> Erreur de syntaxe : expression invalide.
Test "2+;"       -> Erreur de syntaxe : expression invalide.
```

---

## Interpréteur

Évalue les expressions valides grâce à une pile d'entiers (`Pile`).
Chaque méthode empile le résultat qu'elle calcule.

```bash
cd src/interpreteur
javac Pile.java Source.java Analyseur.java Principale.java
java Principale
```

Résultats attendus :
```
Test "2+3;"      -> Résultat = 5
Test "2*3+4;"    -> Résultat = 10
Test "(2+3)*4;"  -> Résultat = 20
Test "3*3+4*2;"  -> Résultat = 17
Test "2+3$4;"    -> Erreur de syntaxe : expression invalide.
Test "2+3"       -> Erreur de syntaxe : expression invalide.
```

---

## Compilateur

Génère des instructions en pseudo-code pile au lieu d'exécuter les calculs.
Chaque opération sur la pile devient un `println()`.

| Instruction | Signification                          |
|-------------|----------------------------------------|
| `PUSH n`    | Empile le chiffre `n`                  |
| `ADD`       | Dépile deux valeurs, empile leur somme |
| `MUL`       | Dépile deux valeurs, empile leur produit |

```bash
cd src/compilateur
javac Source.java Analyseur.java Principale.java
java Principale
```

Résultats attendus :
```
=== Compilation de "2+3;" ===
PUSH 2
PUSH 3
ADD
// fin du programme

=== Compilation de "2*3+4;" ===
PUSH 2
PUSH 3
MUL
PUSH 4
ADD
// fin du programme

=== Compilation de "(2+3)*4;" ===
PUSH 2
PUSH 3
ADD
PUSH 4
MUL
// fin du programme
```

---

## Documentation API

La documentation Javadoc est disponible dans `docs/javadoc/`.  
Ouvrir `docs/javadoc/index.html` dans un navigateur.

Pour la régénérer :

```bash
javadoc -d docs/javadoc -encoding UTF-8 -charset UTF-8 \
  src/version1/Source.java src/version1/Analyseur.java src/version1/Principale.java \
  src/version2/Source.java src/version2/Analyseur.java src/version2/Principale.java \
  src/interpreteur/Pile.java src/interpreteur/Source.java src/interpreteur/Analyseur.java src/interpreteur/Principale.java \
  src/compilateur/Source.java src/compilateur/Analyseur.java src/compilateur/Principale.java
```
