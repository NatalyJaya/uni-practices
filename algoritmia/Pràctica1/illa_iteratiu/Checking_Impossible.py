import subprocess
import os
import time

def run_checker():
    carpeta = "impossible"
    script = "illa.py"

    archivos = sorted(f for f in os.listdir(carpeta) if f.startswith("impossible") and f.endswith(".txt"))
    total = len(archivos)
    correctos = 0

    for archivo in archivos:
        path = os.path.join(carpeta, archivo)
        try:
            resultado = subprocess.check_output(["python3", script, path], text=True).strip()
            if resultado == "impossible":
                print(f"[OK] {archivo}")
                correctos += 1
            else:
                print(f"[FAIL] {archivo} → Devolvió: {resultado}")
        except subprocess.CalledProcessError as e:
            print(f"[ERROR] {archivo} → Fallo al ejecutar illa.py\n{e}")
        except Exception as e:
            print(f"[EXCEPTION] {archivo} → {e}")

    print(f"\nTests pasados: {correctos}/{total}")

if __name__ == "__main__":
    run_checker()
    import matplotlib.pyplot as plt

    tiempos = []

    for archivo in archivos:
        path = os.path.join(carpeta, archivo)
        try:
            inicio = time.time()
            subprocess.check_output(["python3", script, path], text=True).strip()
            fin = time.time()
            tiempos.append(fin - inicio)
        except subprocess.CalledProcessError:
            tiempos.append(None)
        except Exception:
            tiempos.append(None)

    # Filtrar tiempos válidos
    tiempos_validos = [t for t in tiempos if t is not None]
    indices_validos = [i for i, t in enumerate(tiempos) if t is not None]

    # Generar gráfico
    plt.figure(figsize=(10, 6))
    plt.plot(indices_validos, tiempos_validos, marker='o', label="Tiempo de ejecución")
    plt.title("Complejidad temporal")
    plt.xlabel("Índice del archivo")
    plt.ylabel("Tiempo (segundos)")
    plt.grid(True)
    plt.legend()
    plt.show()