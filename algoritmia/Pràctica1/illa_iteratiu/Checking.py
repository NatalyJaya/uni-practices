import os
import subprocess
import time

matplotlib_installed = False
try:
    import matplotlib.pyplot as plt
    matplotlib_installed = True
except:
    print("Please install matplotlib")

def chequejar_exemples(executable, inici=3, fi=10, num_exemples=20, max_retries=50):
    directory = os.getcwd()
    input_directory = os.path.join(directory, 'exemples')
    output_directory = os.path.join(directory, 'solucions')
    os.makedirs(output_directory, exist_ok=True)

    if executable.endswith(".py"):
        command_line = ['python3', executable]
    else:
        command_line = [executable]

    times = []
    for n in range(inici, fi):
        print(f"Chequejant exemples amb {n} nois")
        point_time = 0
        for m in range(1, num_exemples + 1):
            input_filename = os.path.join(input_directory, f"illa-{n}-{m}.txt")
            output_filename = os.path.join(output_directory, f"solucio-{n}-{m}.txt")
            if not os.path.exists(input_filename):
                print(f"Arxiu no trobat: {input_filename}")
                raise FileNotFoundError

            retries = 0
            while retries < max_retries:
                initial_time = time.time()
                process = subprocess.run(
                    command_line + [input_filename],
                    stdout=subprocess.PIPE,
                    stderr=subprocess.PIPE,
                )
                final_time = time.time()
                output_data = process.stdout.decode("utf-8")
                error_data = process.stderr.decode("utf-8")
                solution = output_data.split()
                point_time += final_time - initial_time

                if not os.path.exists(output_filename):
                    print(f"Arxiu no trobat: {output_filename}")
                    raise FileNotFoundError

                solucions = []
                with open(output_filename, 'r') as f:
                    for line in f:
                        solucions.append(line.split())

                if "impossible" in output_data or "error" in error_data:
                    retries += 1
                    if retries >= max_retries:
                        print(f"Maximum retries reached for {input_filename}. Skipping this example.")
                        break
                elif solution not in solucions:
                    print(f"Error en el cas {input_filename}")
                    raise ValueError
                else:
                    break

        times.append((n, point_time))
    return times

def print_times(inici, fi, times, executable):
    if matplotlib_installed:
        ns, ts = zip(*times)
        plt.figure(figsize=(8, 5))
        plt.plot(ns, ts, marker='o', label="Tiempo total")
        plt.xticks(range(inici, fi + 1))
        plt.title(f"Tiempo de resolución vs número de nois\n({os.path.basename(executable)})")
        plt.xlabel("Número de nois (n)")
        plt.ylabel("Tiempo total (s)")
        plt.grid(True)
        try:
            import numpy as np
            z = np.polyfit(ns, ts, 2)
            p = np.poly1d(z)
            plt.plot(ns, p(ns), "--", color="red", label="Ajuste cuadrático")
        except ImportError:
            pass
        plt.legend()
        plt.tight_layout()
        plt.savefig(f"time_complexity_{os.path.basename(executable)}.png")
        plt.show()
    else:
        print(times)

if __name__ == '__main__':
    import sys
    times = []
    inici, fi = 3, 10
    executable = sys.argv[1]
    if len(sys.argv) > 2:
        inici = int(sys.argv[2])
        fi = int(sys.argv[3])
    times = chequejar_exemples(executable, inici, fi + 1)
    print_times(inici, fi, times, executable)
