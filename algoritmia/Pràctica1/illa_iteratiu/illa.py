import sys
from collections import defaultdict

def read_relations(file): #Time Complexity: O(N)
    """Llegeix les relacions des d'un fitxer o entrada estàndard"""
    relations = []
    nodes = set()
    for line in file:
        line = line.strip()
        if not line:
            continue
        parts = line.split()
        if len(parts) != 3:
            return None, None
        a, b, c = parts
        if a == b or a == c or b == c:
            return None, None
        relations.append((a, b, c))
        nodes.update([a, b, c])
    return list(nodes), relations

def write_output(output, path=None): #Time Complexity: O(N)
    """Escriu el resultat a fitxer o sortida estàndard"""
    if path:
        with open(path, 'w') as f:
            f.write(output + "\n")
    else:
        print(output)

def is_valid_solution(order, relations): #Time Complexity: O(N)
    pos = {node: idx for idx, node in enumerate(order)}
    for a, b, c in relations:
        if pos[a] < pos[b] and not (pos[a] < pos[c] < pos[b]):
            return False
    return True

def find_valid_order(nodes, relations): #Time Complexity: O(N^2)
    freq = defaultdict(int)
    for a, b, c in relations: 
        freq[a] += 2
        freq[b] += 1
        freq[c] += 1
    
    current = sorted(nodes, key=lambda x: -freq[x]) 
    
    for _ in range(len(current)):
        violations = find_violations(current, relations)
        if not violations:
            return current
        
        for i, j in violations:
            for k in [i, j]:
                temp = current.copy()
                val = temp.pop(k)
                for pos in range(len(temp)+1):
                    new_order = temp[:pos] + [val] + temp[pos:]
                    if is_valid_solution(new_order, relations):
                        return new_order
    return None

def find_violations(order, relations): #Time Complexity: O(N)
    violations = set()
    pos = {val: idx for idx, val in enumerate(order)}
    for a, b, c in relations:
        if pos[a] > pos[b]:
            violations.add((min(pos[a], pos[b]), max(pos[a], pos[b])))
        if pos[a] > pos[c]:
            violations.add((min(pos[a], pos[c]), max(pos[a], pos[c])))
    return list(violations)

# Mètode alternatiu de lala.py
def fallback_method(relations): #Time Complexity: O(N)
    enemics = {}
    amics = {}
    nois = set()
    for x, e, a in relations:
        enemics[x] = e
        amics[x] = a
        nois.update([x, e, a])
    return trobar_fila(list(nois), enemics, amics)

def trobar_fila(nois, enemics, amics): #Time Complexity: O(N^2)
    fila = []
    for noi in nois:
        inserted = False
        for i in range(len(fila) + 1):
            fila_temp = fila[:i] + [noi] + fila[i:]
            if validar_fila(fila_temp, enemics, amics):
                fila = fila_temp
                inserted = True
                break
        if not inserted:
            return None
    return fila

def validar_fila(fila, enemics, amics): #Time Complexity: O(N)
    pos = {noi: idx for idx, noi in enumerate(fila)}
    for noi in fila:
        if noi in enemics:
            e = enemics[noi]
            a = amics[noi]
            if pos[noi] < pos.get(e, -1) and not (pos[noi] < pos.get(a, -1) < pos[e]):
                return False
    return True

def handle_fallback_conditions(relations): #Time Complexity: O(N)
    return fallback_method(relations)

def main(): # Time Complexity: O(N^2)
    if len(sys.argv) > 3:
        print("Ús: ./illa [fitxer_entrada] [fitxer_sortida]")
        sys.exit(1)
    
    # Gestió d'entrada
    if len(sys.argv) >= 2:
        try:
            with open(sys.argv[1], 'r') as f:
                nodes, relations = read_relations(f)
        except FileNotFoundError:
            write_output("impossible")
            sys.exit(1)
    else:
        nodes, relations = read_relations(sys.stdin)
    
    # Gestió d'errors en lectura
    if nodes is None or relations is None:
        write_output("impossible", sys.argv[2] if len(sys.argv)>=3 else None)
        return

    # Execució principal
    order = find_valid_order(nodes, relations)
    
    # Fallback si és necessari
    if not order:
        fallback = handle_fallback_conditions(relations)
        order = fallback if fallback else None

    # Gestió de sortida
    output_file = sys.argv[2] if len(sys.argv)>=3 else None
    write_output(" ".join(order) if order else "impossible", output_file)

if __name__ == "__main__":
    main()
