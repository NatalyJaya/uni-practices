#include <stdio.h>
#include <string.h>

#define N 100

// ORDENACI� DE L'ARRAY
void ordenacio_array(int v[], int n, FILE *fitxer){
	int temp;
	for(int i = 0; i < n; i++){
		for(int j = 0; j < n - 1; j++){
			fprintf(fitxer, "%d %p\n", 2, (void*)&v[j]); // Lectura
			fprintf(fitxer, "%d %p\n", 2, (void*)&v[j+1]); // Lectura
			if (v[j] < v[j + 1]){
				temp = v[j];
				fprintf(fitxer, "%d %p\n", 3, (void*)&v[j]); // Escriptura
				v[j] = v[j + 1];
				fprintf(fitxer, "%d %p\n", 3, (void*)&v[j+1]); // Escriptura
				v[j + 1] = temp;
			}
		}
	}
}

//ARRAY AMB NOMBRES PARELLS
void parells(int v[], int p[], int n, FILE *fitxer) {
	int j = 0;
	for(int i = 0; i < n; i++){
		fprintf(fitxer, "%d %p\n", 2, (void*)&v[i]); // Lectura
		if(v[i] % 2 == 0){
			p[j] = v[i];
			fprintf(fitxer, "%d %p\n", 3, (void*)&p[j]); // Escriptura
			j++;
		}
	}
}

int obtenir_nombre_parells(int v[], int n, FILE *fitxer){
	int numParells = 0;
	for (int i = 0; i < n; i++){
		fprintf(fitxer, "%d %p\n", 2, (void*)&v[i]); // Lectura
		if (v[i] % 2 == 0 ){
			numParells++;
		}
	}
	return numParells;
}

// ARRAY AMB NOMBRES SENARS
void senars(int v[], int p[], int n, FILE *fitxer) {
	int j = 0;
	for(int i = 0; i < n; i++){
		fprintf(fitxer, "%d %p\n", 2, (void*)&v[i]); // Lectura
		if(v[i] % 2 == 1){
			p[j] = v[i];
			fprintf(fitxer, "%d %p\n", 3, (void*)&p[j]); // Escriptura
			j++;
		}
	}
}

int obtenir_nombre_senars(int v[], int n, FILE *fitxer){
	int numSenars = 0;
	for (int i = 0; i < n; i++){
		fprintf(fitxer, "%d %p\n", 2, (void*)&v[i]); // Lectura
		if (v[i] % 2 == 1 ){
			numSenars++;
		}
	}
	return numSenars;
}

int main (){
	// Obrir el fitxer de tra�a amb el nom correcte
	FILE *fitxer = fopen("tr_calcul_array.prg", "w");
	if (fitxer == NULL) {
		printf("Error obrint el fitxer de tra�a.\n");
		return 1;
	}

	int valors[N] = {
        23, 12, 33, 93, 126, 45, 125, 669, 12, 4, 
        9, 25, 7, 89, 112, 23, 269, 59, 458, 47, 
        15, 35, 48, 126, 240, 125, 230, 120, 111, 2, 
        55, 598, 88, 44, 69, 78, 15, 124, 128, 450, 
        460, 5, 8, 97, 5, 12, 11, 33, 66, 125, 
        658, 458, 98, 69, 548, 59, 69, 230, 220, 365, 
        5, 6, 8, 40, 10, 69, 58, 45, 89, 2, 
        6, 9, 8, 45, 55, 66, 87, 1200, 125, 69, 
        69, 25, 83, 84, 59, 66, 98, 2, 9, 7, 
        89, 56, 6, 45, 69, 8, 100, 25, 200, 23
    };
    
    int p[N]; 
    int s[N];

    // ORDENACI� ARRAY
    ordenacio_array(valors, N, fitxer);

    // ARRAY NOMBRES PARELLS
    int numParells = obtenir_nombre_parells(valors, N, fitxer);
    parells(valors, p, N, fitxer);

    // ARRAY NOMBRES SENARS
    int numSenars = obtenir_nombre_senars(valors, N, fitxer);
    senars(valors, s, N, fitxer);

    // Tanquem el fitxer
    fclose(fitxer);
    printf("Tra�a generada correctament a tr_calcul_array.prg\n");

    return 0;
}

