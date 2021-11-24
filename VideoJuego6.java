package VideoJuegoxd;

import java.util.ArrayList;
import java.util.Scanner;

public class VideoJuego6 {
	public static void main(String [] args){
		
		
		Scanner sc = new Scanner(System.in);

		ArrayList<Soldado> ejercito1 = new ArrayList<Soldado>();
		ArrayList<Soldado> ejercito2 = new ArrayList<Soldado>();
		Soldado [][] campoBatalla= new Soldado [10][10];
		
		int puntosVida=0, fila=0, columna=0, nivelAtaque=0, nivelDefensa=0;
		

		for (;;) {

			autogenerarSoldados(ejercito1,1,campoBatalla);
			autogenerarSoldados(ejercito2,2,campoBatalla);
			
			System.out.println("*************************************"
					+ "**********************************************");
			imprimirTablero(campoBatalla);
			System.out.print("\n");

			
			imprimirEjercito(ejercito1,1);
			imprimirEjercito(ejercito2,2);

			imprimirSoldadoFuerte(ejercito1,ejercito2);
			
			ordenamientoBurbuja(ejercito1,1);
			ordenamientoSeleccion(ejercito2,2);
			
			ejercitoGanador(ejercito1,ejercito2);
			System.out.println("\n*************************************"
					+ "**********************************************");
			
			System.out.println("¿Desea volver a Jugar? (Si=1, No=0)");
			int jugar=sc.nextInt();
			if (jugar==1) {
				
			}
			else {
				break;
			}
		}
		
	}
	
	// autogenerar. Puntos de vida 5, fila10, columna10, nivel de ataque5 y defensa5
	
	public static void autogenerarSoldados(ArrayList<Soldado> ejercito,int i,Soldado [][]campo) {
		
		String nombre;
		int vida=0,fila=0,columna=0,ataque=0,defensa=0;
		for (int j=0;j<10;j++) {
			nombre="Soldado"+j+"x"+i;
			
			Soldado soldadoini = new Soldado();
			
			for (;;) {
				
				vida = (int)(Math.random()*5+1);
				fila = (int)(Math.random()*10);
				columna = (int)(Math.random()*10);
				ataque = (int)(Math.random()*5+1);
				defensa = (int)(Math.random()*5+1);	
				
				soldadoini.setNombre(nombre);
				soldadoini.setVidaActual(vida);
				soldadoini.setFila(fila);
				soldadoini.setColumna(columna);
				soldadoini.setNivelAtaque(ataque);
				soldadoini.setNivelDefensa(defensa);		

				if (comprobarPosicionSoldado(campo,fila,columna)) {
					break;
				}
			}	
			ejercito.add(soldadoini);
			campo[fila][columna]=soldadoini;
		}
	}
	

	public static boolean comprobarPosicionSoldado(Soldado [][]campo,int fila,int columna) {
		if (campo[fila][columna]==null) {
			return true; //Esta vacío?
		}
		return false;
	}

	
	public static void imprimirTablero(Soldado [][]tablero) {
		System.out.println("Campo de Batalla:");
		for (int i=0;i<tablero.length;i++) {
			System.out.println();
			for (int j=0;j<tablero[i].length;j++) {
				if (tablero[i][j]!=null) {
					if (tablero[i][j].getNombre().substring(9,10).equals("1")) {
						System.out.print("(E1-"+tablero[i][j].getVidaActual()+")\t");
					}
					else {
						System.out.print("(E2-"+tablero[i][j].getVidaActual()+")\t");
					}
				}
				else {
					System.out.print("(----)\t");
				}
			}
		}
	}
	
	
	public static void imprimirEjercito(ArrayList<Soldado>ejercito,int a) {

		System.out.println("_____________________________________________"
				+ "____________________\n\nEjercito "+a+":\n");
		for (int i=0;i<ejercito.size();i++) {
			System.out.println("Nombre: "+ejercito.get(i).getNombre()+"\t-\t"+"Columna: "+
			(ejercito.get(i).getColumna()+1)+" \tFila: "+(ejercito.get(i).getFila()+1)+" \tVida"+
			ejercito.get(i).getVidaActual()+" Ataque: "+ejercito.get(i).getNivelAtaque()+" Defensa: "+
			ejercito.get(i).getNivelDefensa());
		}
		
	}
	
	public static void imprimirSoldadoFuerte(ArrayList<Soldado>ejercito1,ArrayList<Soldado>ejercito2) {
		Soldado fuerte1 = new Soldado();
		Soldado fuerte2 = new Soldado();
		
		fuerte1.setVidaActual(0);
		fuerte2.setVidaActual(0);

		for (int i=0;i<ejercito1.size();i++) {
			if (ejercito1.get(i).getVidaActual()>fuerte1.getVidaActual()) {
				fuerte1=ejercito1.get(i);
			}
		}
			
		for (int i=0;i<ejercito2.size();i++) {
			if (ejercito2.get(i).getVidaActual()>fuerte1.getVidaActual()) {
				fuerte2=ejercito2.get(i);
			}
		}
			
		System.out.println("_____________________________________________"
				+ "____________________\n\nSoldados más fuertes:\n");
		
		System.out.println(fuerte1.getNombre()+"\t-\t"+"Columna: "+(fuerte1.getColumna()+1)
				+" \tFila: "+(fuerte1.getFila()+1)+" \tVida: "+fuerte1.getVidaActual());;		
		System.out.println(fuerte2.getNombre()+"\t-\t"+"Columna: "+(fuerte2.getColumna()+1)
				+" \tFila: "+(fuerte2.getFila()+1)+" \tVida: "+fuerte2.getVidaActual());;
				
		System.out.println("_____________________________________________"
				+ "____________________\n");
	}
	
	public static void ordenamientoBurbuja(ArrayList<Soldado>ejercito,int a) {
		
		ArrayList<Soldado>ejercitoTemp = new ArrayList<Soldado>();
		Soldado temp = new Soldado();
		
		System.out.println("Ordenamiento Burbuja: \n");

		for (int i=0;i<ejercito.size()-1;i++) {
			for (int j=0;j<ejercito.size()-1;j++) {
				String soldier = "Soldado"+j+"x"+a;
				
				if (ejercito.get(j).getVidaActual()<(ejercito.get(j+1).getVidaActual())) {
					temp=ejercito.get(j);
					ejercito.set(j, ejercito.get(j+1));
					ejercito.set(j+1, temp);
				}
			}
		}
		
		for (int i=0;i<ejercito.size();i++) {
			System.out.println("Nombre: "+ejercito.get(i).getNombre()+"\t-\t"+"Columna: "+
			(ejercito.get(i).getColumna()+1)+" \tFila: "+(ejercito.get(i).getFila()+1)+" \tVida"+
			ejercito.get(i).getVidaActual());
		}
	}
	
	public static void ordenamientoSeleccion(ArrayList<Soldado>ejercito,int a) {
		
		ArrayList<Soldado>ejercitoTemp = new ArrayList<Soldado>();
		int min;
		Soldado aux;
		for(int i=0;i<ejercito.size();i++) {
			min= i;
			
			for (int j=i+1;j<ejercito.size();j++) {
				if (ejercito.get(j).getVidaActual()>ejercito.get(i).getVidaActual()) {
					min= j;
				}
			}
			
			aux=ejercito.get(i);
			ejercito.set(i, ejercito.get(min));
			ejercito.set(min, aux);
		}
		
		System.out.println("\nOrdenamiento Seleccion: \n");

		for (int i=0;i<ejercito.size();i++) {
			System.out.println("Nombre: "+ejercito.get(i).getNombre()+"\t-\t"+"Columna: "+
			(ejercito.get(i).getColumna()+1)+" \tFila: "+(ejercito.get(i).getFila()+1)+" \tVida"+
			ejercito.get(i).getVidaActual());
		}
	}
	
	public static void ejercitoGanador(ArrayList<Soldado>ejercito1,ArrayList<Soldado>ejercito2) {
		int vidaEjercito1=0,vidaEjercito2=0;
		for (int i=0;i<ejercito1.size();i++) {
			vidaEjercito1+=ejercito2.get(i).getVidaActual();
		}
		for (int i=0;i<ejercito1.size();i++) {
			vidaEjercito2+=ejercito2.get(i).getVidaActual();
		}
			
		System.out.println("\n---------------------------------------------");
		if (vidaEjercito1>vidaEjercito2) {
			System.out.println("¡¡¡EL GANADOR ES EL EJERCITO 1!!!");
		}
		if (vidaEjercito1<vidaEjercito2) {
			System.out.println("¡¡¡EL GANADOR ES EL EJERCITO 2!!!");
		}
		if (vidaEjercito1==vidaEjercito2) {
			System.out.println("¡¡¿ES UN EMPATE?!!");
		}
		System.out.println("\nMetrica Usada: Vida total del ejercito");

	}
}
