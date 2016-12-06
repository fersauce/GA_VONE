/**
 * 
 */
package py.una.pol.vone.ga.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.VertexFactory;
import org.jgrapht.generate.GnmRandomGraphGenerator;
import org.jgrapht.graph.ClassBasedVertexFactory;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

/**
 * Clase dedicada a la generacion de las topologias de redes virtuales que
 * podran ser utilizadas en el simulador.
 * 
 * @author Fernando Saucedo
 * @version 1.0
 * @since 2016-12-03
 */
public class TopologyGenerator {

	/**
	 * Constructor de la clase.
	 */
	public TopologyGenerator() {

	}

	/**
	 * 
	 */
	public static ArrayList<Graph<Object, DefaultEdge>> generarRedes(int numeroDeRedes) {
		Graph<Object, DefaultEdge> grafo;
		Random genNumAleatorio = new Random();
		int numeroNodos, numeroEnlaces, numeroMinEnlaces, numeroMaxEnlaces;
		ArrayList<Graph<Object, DefaultEdge>> listaDeGrafo = new ArrayList<Graph<Object, DefaultEdge>>();
		for (int numeroTopologia = 1; numeroTopologia <= numeroDeRedes; numeroTopologia++) {
			grafo = null;
			while (grafo == null) {
				grafo = new SimpleGraph<>(DefaultEdge.class);
				/*
				 * La cantidad de nodos a tener cada VNR es de 3<=numeroNodos<=7
				 */
				numeroNodos = genNumAleatorio.nextInt(7 - 3 + 1) + 3;
				/*
				 * La cantidad de enlaces, se halla en base a la cantidad de
				 * nodos que va a tener la VNR, que el minimo numero de enlaces
				 * a tener es de la cantidad de nodos menos uno y la cantidad
				 * maxima que puede tener se encuentra representado por el
				 * numero que corresponde a la mitad del producto entre la
				 * cantidad de nodos por su antecesor.
				 */
				numeroMinEnlaces = numeroNodos - 1;
				numeroMaxEnlaces = (numeroNodos * (numeroNodos - 1)) / 2;
				numeroEnlaces = genNumAleatorio.nextInt(numeroMaxEnlaces - numeroMinEnlaces + 1) + numeroMinEnlaces;
				GnmRandomGraphGenerator<Object, DefaultEdge> generadorGrafo = new GnmRandomGraphGenerator<>(numeroNodos,
						numeroEnlaces);
				/*
				 * Se crea el conjunto de fabrica de vertices, para poder
				 * generar los vertices o nodos
				 */
				VertexFactory<Object> vFactory = new ClassBasedVertexFactory<>(Object.class);
				/* Creamos el grafo */
				generadorGrafo.generateGraph(grafo, vFactory, null);
				/*
				 * Aqui se realiza el cambio del contenido de los vertices con
				 * objetos de tipo Integer
				 */
				Set<Object> vertices = new HashSet<>();
				vertices.addAll(grafo.vertexSet());
				int counter = 1;
				for (Object vertex : vertices) {
					reemplazarNodos(grafo, vertex, counter++);
				}
				/*
				 * Esta ultima parte realiza la comprobacion de si el grafo
				 * generado contiene un camino desde cada vertice a los demas
				 * vertices (no necesariamente tiene que ser camino directo)
				 */
				ArrayList<Object> visitados = new ArrayList<>();
				recorrerEnProfundidad(grafo, grafo.vertexSet().iterator().next(), visitados);
				if (visitados.size() != numeroNodos) {
					grafo = null;
				}
			}
			/*
			 * Se agrega el grafo generado a la lista de grafos a ser cargado al
			 * archivo
			 */
			listaDeGrafo.add(grafo);
			grafo = null;
		}
		return listaDeGrafo;
	}

	/**
	 * Metodo privado que realiza un recorrido en profundidad del grafo
	 * 
	 * @param vertice
	 *            objeto que tiene los datos del vertice de los cuales se
	 *            buscara si hay hijos y si ya se visitaron.
	 * @param visitados
	 *            lista que tiene los vertices que ya fueron visitados.
	 */
	private static void recorrerEnProfundidad(Graph<Object, DefaultEdge> grafoGenerado, Object vertice,
			ArrayList<Object> visitados) {
		visitados.add(vertice);
		Iterator<DefaultEdge> enlaces = grafoGenerado.edgesOf(vertice).iterator();
		DefaultEdge arista;
		while (enlaces.hasNext()) {
			arista = enlaces.next();
			if (grafoGenerado.getEdgeSource(arista) == vertice) {
				if (!visitados.contains(grafoGenerado.getEdgeTarget(arista))) {
					recorrerEnProfundidad(grafoGenerado, grafoGenerado.getEdgeTarget(arista), visitados);
				}
			} else {
				if (!visitados.contains(grafoGenerado.getEdgeSource(arista))) {
					recorrerEnProfundidad(grafoGenerado, grafoGenerado.getEdgeSource(arista), visitados);
				}
			}
		}
	}

	/**
	 * Metodo que reemplaza el valor del vertice obtenido (Object) en otro
	 * objeto pasado como parametro
	 * 
	 * @param nodoViejo
	 *            objeto viejo a reemplazar
	 * @param nodoNuevo
	 *            objeto nuevo a colocar en el lugar del viejo
	 */
	private static void reemplazarNodos(Graph<Object, DefaultEdge> grafoGenerado, Object nodoViejo, Object nodoNuevo) {
		Set<DefaultEdge> relatedEdges = grafoGenerado.edgesOf(nodoViejo);
		grafoGenerado.addVertex(nodoNuevo);

		Object sourceVertex;
		Object targetVertex;
		for (DefaultEdge e : relatedEdges) {
			sourceVertex = grafoGenerado.getEdgeSource(e);
			targetVertex = grafoGenerado.getEdgeTarget(e);
			if (sourceVertex.equals(nodoViejo) && targetVertex.equals(nodoViejo)) {
				grafoGenerado.addEdge(nodoNuevo, nodoNuevo);
			} else {
				if (sourceVertex.equals(nodoViejo)) {
					grafoGenerado.addEdge(nodoNuevo, targetVertex);
				} else {
					grafoGenerado.addEdge(sourceVertex, nodoNuevo);
				}
			}
		}
		grafoGenerado.removeVertex(nodoViejo);
	}

	/**
	 * Metodo main para generar los archivos con las VNRs con la estructura de
	 * la cual se va a guardar y el simulador pueda reconocer posteriormente al
	 * momento de utilizarlo.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int cantidadRedes = 10;
		ArrayList<Graph<Object, DefaultEdge>> listaDeGrafos;
		listaDeGrafos = generarRedes(cantidadRedes);
		for (Graph<Object, DefaultEdge> grafoAux : listaDeGrafos) {
			System.out.println(grafoAux);
		}
		/* Primero se crea el directorio donde se almacenara */
		File miDir = new File("");
		try {
			System.out.println(miDir.getAbsolutePath());
		} catch (Exception e) {

		}
		
		for(int cantidad=1; cantidad<=5;cantidad++){
			/*Instancio y creo el directorio multiplicando por 10 y agregando un cero a la izq. */
			for(int archivo=1; archivo<=20;archivo++){
				/*Instancio y creo el fichero .txt con info de la red y una red por archivo*/
			}
		}
	}

}
