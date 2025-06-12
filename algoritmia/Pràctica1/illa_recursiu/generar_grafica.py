import csv
import matplotlib.pyplot as plt
import re

noms = []
temps = []
colors = []

with open("resultats.csv", newline='') as csvfile:
    reader = csv.DictReader(csvfile, delimiter=';')
    for row in reader:
        nom_fitxer = row["Test"]
        temps_ms = int(row["Temps_ms"])
        correcte = row["Correcte"] == "1"

        # Extreu el nombre de nois (després de "illa-" i abans del següent "-")
        match = re.search(r"illa-(\d+)", nom_fitxer)
        if match:
            n = int(match.group(1))
            noms.append(n)
            temps.append(temps_ms)
            colors.append("green" if correcte else "red")

# Genera la gràfica
plt.figure(figsize=(10, 6))
plt.scatter(noms, temps, c=colors, s=60)

plt.title("Temps d'execució per nombre de nois")
plt.xlabel("Nombre de nois (n)")
plt.ylabel("Temps (ms)")
plt.grid(True)
plt.tight_layout()

# Llegenda
from matplotlib.lines import Line2D
plt.legend(handles=[
    Line2D([0], [0], marker='o', color='w', label='Correcte', markerfacecolor='green', markersize=8),
    Line2D([0], [0], marker='o', color='w', label='Incorrecte', markerfacecolor='red', markersize=8)
])

# Desa la gràfica
plt.savefig("grafica_resultats.png")
plt.show()
