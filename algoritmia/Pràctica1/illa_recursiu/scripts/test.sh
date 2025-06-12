#!/bin/bash

EJECUTABLE=./illa
EXEMPLES_DIR=scripts/exemples
SOLUCIONS_DIR=scripts/solucions
OUTFILE=temp_output.txt
CSV=resultats.csv

CORRECTES=0
TOTAL=0

echo "Executant tests..."
echo ""

# Inicialitza fitxer de resultats per gràfica
echo "Test;Temps_ms;Correcte" > "$CSV"

for entrada in "$EXEMPLES_DIR"/*.txt; do
    nom_arxiu=$(basename "$entrada")             
    sortida_esperada="$SOLUCIONS_DIR/$nom_arxiu"

    if [[ ! -f "$sortida_esperada" ]]; then
        echo "[SKIP] $nom_arxiu → No hi ha solució esperada"
        continue
    fi

    # ⏱️ Mesura temps abans d'executar
    start=$(date +%s%N)

    $EJECUTABLE "$entrada" "$OUTFILE" 2>/dev/null

    # ⏱️ Temps després d'executar
    end=$(date +%s%N)
    duration_ms=$(( (end - start) / 1000000 ))  # temps en mil·lisegons

    resultat=$(cat "$OUTFILE" | tr -d '\r')

    if grep -Fxq "$resultat" "$sortida_esperada"; then
        echo "[OK]   $nom_arxiu"
        CORRECTES=$((CORRECTES + 1))
        es_correcte="1"
    else
        echo "[FAIL] $nom_arxiu → Obtingut: '$resultat' no és una solució vàlida"
        es_correcte="0"
    fi

    echo "$nom_arxiu;$duration_ms;$es_correcte" >> "$CSV"
    TOTAL=$((TOTAL + 1))
done

echo ""
echo "Resultat: $CORRECTES de $TOTAL tests correctes."
echo "Temps d'execució guardats a: $CSV"

# Neteja
rm -f "$OUTFILE"
