import os
import subprocess
import time

matplotlib_installed = False
try:
    import matplotlib.pyplot as plt
    matplotlib_installed = True
except:
    print("Matplotlib no està instal·lat (opcional per a gràfiques)")

debug = False

def chequejar_exemples(haskell_file, inici=4, fi=13, num_exemples=20):
    directory = os.getcwd()
    input_directory = os.path.join(directory, 'exemples')
    output_directory = os.path.join(directory, 'solucions')
    os.makedirs(output_directory, exist_ok=True)

    command_line = ['runhaskell', haskell_file]
    times = []

    for n in range(inici, fi):
        print(f"Chequejant exemples amb {n} nois")
        point_time = 0
        for m in range(1, num_exemples + 1):
            input_filename = os.path.join(input_directory, f"batalla-{n}-{m}.txt")
            solutions_filename = os.path.join(output_directory, f"solucio-{n}-{m}.txt")
            output_filename = "output.txt"

            if not os.path.exists(input_filename):
                print(f"Arxiu no trobat: {input_filename}")
                raise FileNotFoundError

            if debug:
                print(f"Executant: {' '.join(command_line)} {input_filename} {output_filename}")

            # Executa el programa Haskell amb runhaskell
            start_time = time.time()
            process = subprocess.run(
                command_line + [input_filename, output_filename],
                stdout=subprocess.PIPE,
                stderr=subprocess.PIPE
            )
            end_time = time.time()

            if not os.path.exists(output_filename):
                print(f"No s'ha generat {output_filename}")
                raise FileNotFoundError

            try:
                with open(output_filename, "r") as f:
                    distancia = f.readline().strip()
                    solution = f.readline().split()
            except Exception as e:
                print(f"Error llegint {output_filename}: {e}")
                raise

            point_time += end_time - start_time

            # Llegir solucions vàlides
            expected_solutions = []
            with open(solutions_filename, 'r') as f:
                _ = f.readline()  # ignora la distància
                for line in f:
                    expected_solutions.append(line.strip().split())

            if solution not in expected_solutions:
                print(f"❌ Error en el cas {input_filename}")
                print(f"Obtinguda: {solution}")
                print(f"Esperades: {expected_solutions}")
                raise ValueError

        times.append((n, point_time))

    return times


def print_times(inici, fi, times, executable):
    if matplotlib_installed:
        plt.xticks(range(inici, fi + 1))
        plt.title("Temps d'execució: " + executable)
        plt.xlabel("Nombre de nois")
        plt.ylabel("Temps (s)")
        plt.plot(*zip(*times))
        plt.savefig("time.png")
        plt.show()
    else:
        for n, t in times:
            print(f"{n} nois: {t:.3f} s")


if __name__ == '__main__':
    import sys

    if len(sys.argv) < 2:
        print("Ús: python3 Checker.py battle.hs [<inici> <fi>]")
        sys.exit(1)

    executable = sys.argv[1]
    inici = int(sys.argv[2]) if len(sys.argv) > 2 else 4
    fi = int(sys.argv[3]) if len(sys.argv) > 3 else 13

    times = chequejar_exemples(executable, inici, fi + 1)
    print_times(inici, fi, times, executable)
