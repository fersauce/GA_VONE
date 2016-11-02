/**
 * 
 */
package py.una.pol.vone.ga;

import java.util.ArrayList;
import java.util.List;

import edu.ufl.cise.bsmock.graph.Graph;
import edu.ufl.cise.bsmock.graph.ksp.Yen;
import edu.ufl.cise.bsmock.graph.util.Path;
import py.una.pol.vone.ga.model.Camino;

/**
 * Clase principal que ejecutara las pruebas necesarias para el analisis respectivo del problema VONE con un 
 * enfoque de algoritmo genetico.
 * @author fernando
 *
 */
public class Principal {

	/**
	 * Metodo main, donde se inicia la ejecucion de todo el algoritmo.
	 * @param args
	 */
	public static void main(String[] args) {
		String graphFileName;
		int K;
		
		graphFileName = "src/py/una/pol/vone/ga/USNet.txt";
		K = 6;
		//
		List<Camino> todosLosCaminos = hallarKCaminos(graphFileName, K);
		//TODO Hacer modelos de Sustrate Network y Virtual Network
		//TODO Hallar los K caminos entre cada par de nodos
		//TODO Esquematizar la red sustrato con todos los FS (4000 para 50 THz), representacion del vone de manera 
		//computacional
		//TODO Algortimo de generacion de redes virtuales
		//TODO Algoritmo Genetico debe ir aqui.
		//TODO Mostrar resultados, hacer comparativas y recibirnos de unos malditos ingenieros.
	}
	
	/**
	 * Metodo que utilizaremos para hallar los K caminos entre el par de nodos seleccionado de una red, que en este 
	 * caso sera montado como un grafo.
	 * @param graphFileName direccion del archivo que contiene los datos del grafo a montar.
	 * @param cantCaminos cantidad de caminos a hallar entre nodos.
	 * @return Lista con los K caminos mas cortos entre todos los nodos del grafo
	 */
	public static List<Camino> hallarKCaminos(String graphFileName, int cantCaminos){
		System.out.println("Leyendo los datos de la red a montar");
		Graph grafo = new Graph(graphFileName);
		System.out.println("Completado.");
		int cantidadNodos = grafo.numNodes();
		List<Camino> todosLosCaminos = new ArrayList<Camino>();
		
		long tiempoInicial = System.currentTimeMillis();
		for(int origen = 0; origen < cantidadNodos; origen++){
			for(int destino = origen+1;destino < cantidadNodos; destino++){
				//Aqui se halla el camino entre origen y destino.
				List<Path> caminos;
				Yen algoritmoYen = new Yen();
				
				caminos = algoritmoYen.ksp(grafo, String.valueOf(origen), Integer.toString(destino), cantCaminos);
				//Se crea el camino entre ambos 
				Camino caminoNuevo = new Camino();
				caminoNuevo.setOrigen(origen);
				caminoNuevo.setDestino(destino);
				caminoNuevo.setCaminos(caminos);
				//Se agrega los K caminos encontrados al listado.
				todosLosCaminos.add(caminoNuevo);
			}
		}
		long tiempoFinal = System.currentTimeMillis();
		System.out.println("Completado.");
		System.out.println("La busqueda se ha realizado en "+(tiempoFinal-tiempoInicial)/1000.00+" segundos.");
		for (Camino camino:todosLosCaminos){
			System.out.println("A continuacion mostramos los caminos hallados");
			System.out.println("k) costo: [camino]");
			int n = 0;
			for(Path p:camino.getCaminos()){
				System.out.println(++n +") " + p);
			}
		}
		return todosLosCaminos;
	}
}
