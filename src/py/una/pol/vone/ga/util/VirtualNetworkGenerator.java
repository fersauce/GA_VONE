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
	//static int size = 4;

	public static void generarTopologia(int cantidadNodos, int cantidadEnlaces){
		/*//Se crea el objeto (interface) Graph y se instancia un grafo simple.
		grafoGenerado = new SimpleGraph<>(DefaultEdge.class);
		GnmRandomGraphGenerator<Object, DefaultEdge> generadorDeGrafo = 
				new GnmRandomGraphGenerator<>(cantidadNodos, cantidadEnlaces);
		VertexFactory<Object> vFactory = new ClassBasedVertexFactory<>(Object.class);
		boolean grafoConexo = false;
		int contadorNoConexo = 0;
		while(!grafoConexo){
			//Con este metodo se genera un grafo sin bucles ni multiples enlaces de manera aleatoria
			generadorDeGrafo.generateGraph(grafoGenerado, vFactory, null);
			Set<Object> nodos = new HashSet<>();
			nodos.addAll(grafoGenerado.vertexSet());
			//Integer contador = 1;
			//for(Object nodo : nodos){
			//	reemplazarNodos(nodo, contador);
			//}
			Iterator<Object> nodosNuevos = grafoGenerado.vertexSet().iterator();//new DepthFirstIterator<>(grafoGenerado);
			Object nodoNuevo;
			while(nodosNuevos.hasNext()){
				nodoNuevo = nodosNuevos.next();
				System.out.println(grafoGenerado.edgesOf(nodoNuevo).isEmpty());
				if(!grafoGenerado.edgesOf(nodoNuevo).isEmpty()){
					System.out.println("Genero "+ ++contadorNoConexo + "topologias no conexas");
					grafoConexo = true;
				}
			}
		}*/
		// Create the graph object; it is null at this point
        //grafoGenerado = new SimpleGraph<>(DefaultEdge.class);

        
        grafoGenerado = null;
        while(!generarTopologiaPriv(cantidadNodos, cantidadEnlaces)){
        }
	}
	
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

        // Print out the graph to be sure it's really complete
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
            /*if(grafoGenerado.edgesOf(vertex).size()==0){
            	System.out.println("Hola, nuevo grafo");
            	counter = 0;
            	grafoGenerado = null;
            	return false;
            }*/
        }
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
	public static void main(String[] args)
    {
        /*/ Create the graph object; it is null at this point
        grafoGenerado = new SimpleGraph<>(DefaultEdge.class);

        // Create the CompleteGraphGenerator object
        //CompleteGraphGenerator<Object, DefaultEdge> completeGenerator =
         //   new CompleteGraphGenerator<>(size);
        GnmRandomGraphGenerator<Object, DefaultEdge> completeGenerator = new GnmRandomGraphGenerator<>(size, 3);

        // Create the VertexFactory so the generator can create vertices
        VertexFactory<Object> vFactory = new ClassBasedVertexFactory<>(Object.class);

        // Use the CompleteGraphGenerator object to make completeGraph a
        // complete graph with [size] number of vertices
        completeGenerator.generateGraph(grafoGenerado, vFactory, null);

        // Now, replace all the vertices with sequential numbers so we can ID
        // them
        Set<Object> vertices = new HashSet<>();
        vertices.addAll(grafoGenerado.vertexSet());
        Integer counter = 0;
        for (Object vertex : vertices) {
            reemplazarNodos(vertex, counter++);
        }

        // Print out the graph to be sure it's really complete
        @SuppressWarnings("unchecked")
		Iterator<Object> iter = grafoGenerado.vertexSet().iterator();// new DepthFirstIterator<>(completeGraph);
        Object vertex;
        while (iter.hasNext()) {
            vertex = iter.next();
            System.out.println(
                "Vertex " + vertex.toString() + " is connected to: "
                    + grafoGenerado.edgesOf(vertex).toString());
            Iterator<DefaultEdge> hola = grafoGenerado.edgesOf(vertex).iterator();
            DefaultEdge edge;
            while (hola.hasNext()){
            	edge = hola.next();
            	System.out.println(grafoGenerado.getEdgeSource(edge)+grafoGenerado.getEdgeTarget(edge).toString());
            }
        }*/
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

    private static boolean reemplazarNodos(Object oldVertex, Object newVertex)
    {
        if ((oldVertex == null) || (newVertex == null)) {
            return false;
        }
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
        return true;
    }
}