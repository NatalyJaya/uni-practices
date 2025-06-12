# Compila el programa
illa: illait.hs
	ghc -o illa illait.hs

# Limpia archivos generados
clean:
	rm -f illa sortida.txt

# Genera instàncies de test si hi ha un script o eina preparada
instances:
	@echo "Generant instàncies de prova..."
	@./scripts/generar_instancies.sh || echo "No s'ha trobat el generador."

# Executa els tests si hi ha scripts preparats
test:
	@echo "Executant tests..."
	@./scripts/test.sh || echo "No s'ha trobat el script de test."

# Execució manual per comprovar que funciona
run:
	./illa entrada.txt sortida.txt
	@cat sortida.txt
