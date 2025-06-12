import networkx as nx
import matplotlib.pyplot as plt

def plot_directed_graph(graph_dict, output_filename="directed_graph.png"):
    G = nx.DiGraph(graph_dict)
    pos = nx.spring_layout(G)
    nx.draw(G, pos, with_labels=True, node_color='lightgreen', node_size=1000, arrowsize=20, connectionstyle='arc3,rad=0.2')
    plt.savefig(output_filename)
    plt.show()

def parse_relationships(filepath):
    relationships = {} 
    try:
        with open(filepath, 'r') as file:
            for line in file:
                line = line.strip()
                if line:
                    parts = line.split()
                    if len(parts) == 3:
                        boy, enemy, _ = parts
                        relationships[boy] = enemy
                    else:
                        print(f"Warning: Invalid line format: {line}")
    except FileNotFoundError:
        print(f"Error: File not found at {filepath}")
    except Exception as e:
        print(f"An error occurred: {e}")
    return relationships

if __name__ == "__main__":
    import sys
    import os
    if len(sys.argv) != 2:
        print("Usage: python imprimir_graph.py <filepath>")
        sys.exit(1)
    directed_graph_data = parse_relationships(sys.argv[1])
    print( directed_graph_data)
    plot_directed_graph( directed_graph_data, output_filename=os.path.splitext(os.path.basename(sys.argv[1]))[0])
