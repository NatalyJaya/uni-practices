#!/bin/bash

# Directorios de entrada y salida
EXEMPLES_DIR="scripts/exemples"
SOLUCIONS_DIR="scripts/solucions"

# Crear los directorios si no existen
mkdir -p "$EXEMPLES_DIR"
mkdir -p "$SOLUCIONS_DIR"

# Generar ejemplos de entrada (instancias)
echo "Generando archivos de entrada..."

# Ejemplo 1: Illa-5-3.txt
echo -e "entrada1\nentrada2\nentrada3" > "$EXEMPLES_DIR/illa-5-3.txt"
echo "Instancia illa-5-3.txt generada."

# Ejemplo 2: Illa-5-4.txt
echo -e "entradaA\nentradaB\nentradaC" > "$EXEMPLES_DIR/illa-5-4.txt"
echo "Instancia illa-5-4.txt generada."

# Ejemplo 3: Illa-5-5.txt
echo -e "entradaX\nentradaY\nentradaZ" > "$EXEMPLES_DIR/illa-5-5.txt"
echo "Instancia illa-5-5.txt generada."

# Generar soluciones correspondientes (opcional)
echo "Generando soluciones..."

# Solución para illa-5-3.txt
echo "solucion_5_3" > "$SOLUCIONS_DIR/illa-5-3.txt"
echo "Solución para illa-5-3.txt generada."

# Solución para illa-5-4.txt
echo "solucion_5_4" > "$SOLUCIONS_DIR/illa-5-4.txt"
echo "Solución para illa-5-4.txt generada."

# Solución para illa-5-5.txt
echo "solucion_5_5" > "$SOLUCIONS_DIR/illa-5-5.txt"
echo "Solución para illa-5-5.txt generada."

# Aquí puedes seguir generando más soluciones si es necesario.

echo "Generación de instancias y soluciones completada."
