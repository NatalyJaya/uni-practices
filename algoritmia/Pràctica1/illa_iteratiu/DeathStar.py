
import sys
import random 
import string

def death_star(n_cycle = 2):
    """
    That's no moon! It's a space station!
    """
    noms_nois = random.sample(string.ascii_uppercase, n_cycle*2)
    
    cycle = noms_nois[:n_cycle]
    pendants = noms_nois[n_cycle:]

    for i, noi in enumerate( cycle ):
        enemic = cycle[(i+1)%n_cycle]
        amic = pendants[i]
        print(f"{noi} {enemic} {amic}")
    
    for i, noi in enumerate( pendants ):
        enemic = cycle[(i+1)%n_cycle]
        amic = cycle[i]
        print(f"{noi} {enemic} {amic}")

if __name__ == "__main__":
    if len(sys.argv) > 1:
        death_star(int(sys.argv[1]))
    else:
        death_star()