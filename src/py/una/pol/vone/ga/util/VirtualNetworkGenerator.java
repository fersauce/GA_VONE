/**
 * 
 */
package py.una.pol.vone.ga.util;

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
 * @author fersauce
 * Clase utilizada para generar las topologias de redes virtuales.
 */
public class VirtualNetworkGenerator {
	
	static Graph<Object, DefaultEdge> grafoGenerado;

	/**
	 * Metodo que genera una topologia conexa y la almacena en el atributo de clase grafoGenerado
	 * @param cantidadNodos numero de nodos que contendra la topologia
	 * @param cantidadEnlaces numero de enlaces que contendra la topologia
	 */
	public static void generarTopologia(int cantidadNodos, int cantidadEnlaces){
        grafoGenerado = null;
        while(!generarTopologiaPriv(cantidadNodos, cantidadEnlaces)){
        }
	}
	
	
	/**
	 * Metodo privado que genera una topologia y retorna si el mismo es conexo o no
	 * @param cantidadNodos numero de nodos que contendra la topologia
	 * @param cantidadEnlaces numero de enlaces que contendra la topologia
	 * @return boolean que indica si se genero o no una topologia conexa
	 */
	private static boolean generarTopologiaPriv(int cantidadNodos, int cantidadEnlaces){
		grafoGenerado = new SimpleGraph<>(DefaultEdge.class);
		// Create the CompleteGraphGenerator object
        //CompleteGraphGenerator<Object, DefaultEdge> completeGenerator =
         //   new CompleteGraphGenerator<>(size);
        GnmRandomGraphGenerator<Object, DefaultEdge> completeGenerator = 
        		new GnmRandomGraphGenerator<>(cantidadNodos, cantidadEnlaces);

        // Create the VertexFactory so the generator can create vertices
        VertexFactory<Object> vFactory = new ClassBasedVertexFactory<>(Object.class);
    	// Use the CompleteGraphGenerator object to make completeGraph a
        // complete graph with [size] number of vertices
        completeGenerator.generateGraph(grafoGenerado, vFactory, null);

        // Now, replace all the vertices with sequential numbers so we can ID
        // them
        Set<Object> vertices = new HashSet<>();
        vertices.addAll(grafoGenerado.vertexSet());
        int counter = 1;
        for (Object vertex : vertices) {
            reemplazarNodos(vertex, counter++);
        }

        /* Print out the graph to be sure it's really complete
        Iterator<Object> iter = grafoGenerado.vertexSet().iterator();// new DepthFirstIterator<>(completeGraph);
        Object vertex;
        while (iter.hasNext()) {
            vertex = iter.next();
            System.out.println(
                "Vertex " + vertex.toString() + " is connected to: "
                    + grafoGenerado.edgesOf(vertex).toString());
            Iterator<DefaultEdge> hola = grafoGenerado.edgesOf(vertex).iterator();
            DefaultEdge edge;
            System.out.println(grafoGenerado.edgesOf(vertex).size());
            while (hola.hasNext()){
            	edge = hola.next();
            	System.out.println(grafoGenerado.getEdgeSource(edge)+grafoGenerado.getEdgeTarget(edge).toString());
            }
        }*/
        ArrayList<Object> visitados = new ArrayList<>();
        recorrerEnProfundidad(grafoGenerado.vertexSet().iterator().next(), visitados);
        System.out.println(visitados.size());
        if(visitados.size()!=cantidadNodos){
        	System.out.println("No es conexo");
        	System.out.println(visitados.toString());
        	grafoGenerado = null;
        	return false;
        }
		return true;
	}
	
	/**
	 * Metodo privado que realiza un recorrido en profundidad del grafo 
	 * @param vertice
	 * @param visitados
	 */
	private static void recorrerEnProfundidad(Object vertice, ArrayList<Object> visitados){
		visitados.add(vertice);
		Iterator<DefaultEdge> enlaces = grafoGenerado.edgesOf(vertice).iterator();
		DefaultEdge arista;
		while(enlaces.hasNext()){
			arista = enlaces.next();
			if(grafoGenerado.getEdgeSource(arista)==vertice){
				if(!visitados.contains(grafoGenerado.getEdgeTarget(arista))){
					recorrerEnProfundidad(grafoGenerado.getEdgeTarget(arista), visitados);
				}
			}else{
				if(!visitados.contains(grafoGenerado.getEdgeSource(arista))){
					recorrerEnProfundidad(grafoGenerado.getEdgeSource(arista), visitados);
				}
			}
		}
	}
	
	/**
	 * @param oldVertex
	 * @param newVertex
	 * @return
	 */
	private static void reemplazarNodos(Object oldVertex, Object newVertex)
    {
        Set<DefaultEdge> relatedEdges = grafoGenerado.edgesOf(oldVertex);
        grafoGenerado.addVertex(newVertex);

        Object sourceVertex;
        Object targetVertex;
        for (DefaultEdge e : relatedEdges) {
            sourceVertex = grafoGenerado.getEdgeSource(e);
            targetVertex = grafoGenerado.getEdgeTarget(e);
            if (sourceVertex.equals(oldVertex) && targetVertex.equals(oldVertex)) {
            	grafoGenerado.addEdge(newVertex, newVertex);
            } else {
                if (sourceVertex.equals(oldVertex)) {
                	grafoGenerado.addEdge(newVertex, targetVertex);
                } else {
                	grafoGenerado.addEdge(sourceVertex, newVertex);
                }
            }
        }
        grafoGenerado.removeVertex(oldVertex);
    }
	
	public static void main(String[] args)
    {
		Random rand = new Random();
		int numNodos, numEnlaces, numMaxEnlaces, numMinEnlaces;
		for(int i = 1;i <= 10; i++){
			numNodos = rand.nextInt(5)+3;
			numMinEnlaces = numNodos - 1;
			numMaxEnlaces = (numNodos*(numNodos-1))/2;//Numero de enlaces de un grafo conexo con N nodos.
			numEnlaces = rand.nextInt(numMaxEnlaces - numMinEnlaces + 1) + numMinEnlaces;
			System.out.println("Topologia "+i+" de "+numNodos+" nodos y "+numEnlaces+" enlaces");
			generarTopologia(numNodos, numEnlaces);
		}
    }
}