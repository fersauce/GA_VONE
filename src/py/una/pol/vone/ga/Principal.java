/**
 * 
 */
package py.una.pol.vone.ga;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import edu.ufl.cise.bsmock.graph.Graph;
import edu.ufl.cise.bsmock.graph.ksp.Yen;
import edu.ufl.cise.bsmock.graph.util.Path;
import py.una.pol.vone.ga.model.Camino;
import py.una.pol.vone.ga.model.SustrateNetwork;
import py.una.pol.vone.ga.model.VirtualNetwork;
import py.una.pol.vone.ga.util.NetworkGenerator;

/**
 * Clase principal que ejecutara el simulador para realizar el analisis
 * respectivo del problema VONE.
 * 
 * @author Fernando Saucedo
 * @version 1.0
 * @since 2016-09-12
 */
public class Principal {

	/**
	 * Metodo main, donde se inicia la ejecucion de todo el algoritmo.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		/* Aqui iniciamos escogiendo la red a utilizar */
		String graphFileName;
		int K, cantidadNodos;
		System.out.println(
				"Por favor seleccione la red sobre la cual se va montar las redes virtuales\n1. Red NSF\n2. Red US");
		Scanner scannerEntrada = new Scanner(System.in);
		String datos;
		datos = scannerEntrada.nextLine();
		if (datos.equals("1")) {
			graphFileName = "static/sustatrenets/NSFNet.txt";
			cantidadNodos = 14;
		} else {
			graphFileName = "static/sustatrenets/USNet.txt";
			cantidadNodos = 24;
		}
		K = 6;
		@SuppressWarnings("unused")
		List<Camino> todosLosCaminos = hallarKCaminos(graphFileName, K);
		NetworkGenerator generador = new NetworkGenerator();
		SustrateNetwork redFisica = new SustrateNetwork();
		ArrayList<VirtualNetwork> redesVirtuales = new ArrayList<VirtualNetwork>();
		/*
		 * Aqui se genera primero la red fisica, dependiendo del classpath se
		 * arma el tema.
		 */
		generador.generarRedFisica(redFisica, graphFileName, cantidadNodos);
		int cantidadRedesVirtuales = 5;
		/*
		 * Aqui se selecciona la cantidad de redes y el numero de la agrupacion
		 * de topologias a utilizar para crear las VNRs
		 */
		System.out.println("Escoja la cantidad de redes a utilizar en la ejecucion de la prueba");
		String datosCantidadRedes = scannerEntrada.nextLine();
		String direccionArchivoTopoligias = "static/vnrgroups";
		switch (Integer.parseInt(datosCantidadRedes)) {
		case 1:
			String datosNumeroDeRed = scannerEntrada.nextLine();
			System.out.println("Ingrese la topologia a utilizar");
			direccionArchivoTopoligias = direccionArchivoTopoligias.concat("/010");
			int numeroDeRed = Integer.parseInt(datosNumeroDeRed);
			if (numeroDeRed < 10) {
				direccionArchivoTopoligias = direccionArchivoTopoligias.concat("00" + datosNumeroDeRed);
			} else {
				direccionArchivoTopoligias = direccionArchivoTopoligias.concat("0" + datosNumeroDeRed);
			}
			break;
		default:
			break;
		}
		generador.generarRedesVirtuales(cantidadRedesVirtuales, redesVirtuales);
		/*
		 * Aqui se ordenan los requerimientos de redes virtuales de acuerdo al
		 * total de CPU
		 */
		Collections.sort(redesVirtuales, new Comparator<VirtualNetwork>() {
			@Override
			public int compare(VirtualNetwork redUno, VirtualNetwork redDos) {
				return new Integer(redDos.getTotalCPU()).compareTo(new Integer(redUno.getTotalCPU()));
			}
		});
	}

	/*
	 * private static String crearPathArchivoDeTopologia(int cantidad, int
	 * numeroDeRed){ String opcion; return opcion; }
	 */

	/**
	 * Metodo que utilizaremos para hallar los K caminos entre el par de nodos
	 * seleccionado de una red, que en este caso sera montado como un grafo.
	 * 
	 * @param graphFileName
	 *            direccion del archivo que contiene los datos del grafo a
	 *            montar.
	 * @param cantCaminos
	 *            cantidad de caminos a hallar entre nodos.
	 * @return Lista con los K caminos mas cortos entre todos los nodos del
	 *         grafo
	 */
	public static List<Camino> hallarKCaminos(String graphFileName, int cantCaminos) {
		// Aqui se lee los datos de la red a montar
		Graph grafo = new Graph(graphFileName);
		int cantidadNodos = grafo.numNodes();
		List<Camino> todosLosCaminos = new ArrayList<Camino>();

		@SuppressWarnings("unused")
		long tiempoInicial = System.currentTimeMillis();
		for (int origen = 0; origen < cantidadNodos; origen++) {
			for (int destino = origen + 1; destino < cantidadNodos; destino++) {
				// Aqui se halla el camino entre origen y destino.
				List<Path> caminos;
				Yen algoritmoYen = new Yen();

				caminos = algoritmoYen.ksp(grafo, String.valueOf(origen), Integer.toString(destino), cantCaminos);
				// Se crea el camino entre ambos
				Camino caminoNuevo = new Camino();
				caminoNuevo.setOrigen(origen);
				caminoNuevo.setDestino(destino);
				caminoNuevo.setCaminos(caminos);
				// Se agrega los K caminos encontrados al listado.
				todosLosCaminos.add(caminoNuevo);
			}
		}
		@SuppressWarnings("unused")
		long tiempoFinal = System.currentTimeMillis();
		/*
		 * System.out.println("La busqueda se ha realizado en "+(tiempoFinal-
		 * tiempoInicial)/1000.00+" segundos."); Este es para hacer que imprima
		 * el tiempo que tarda en hallar todos los k caminos
		 *
		 * for (Camino camino:todosLosCaminos){
		 * System.out.println("A continuacion mostramos los caminos hallados");
		 * System.out.println("k) costo: [camino]"); int n = 0; for(Path
		 * p:camino.getCaminos()){ System.out.println(++n +") " + p); } }
		 */
		return todosLosCaminos;
	}
}
