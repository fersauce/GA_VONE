/**
 * 
 */
package py.una.pol.vone.ga.util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.VertexFactory;
import org.jgrapht.generate.GnmRandomGraphGenerator;
import org.jgrapht.graph.ClassBasedVertexFactory;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.DepthFirstIterator;

/**
 * @author fersauce
 * Clase utilizada para generar las topologias de redes virtuales.
 */
public class VirtualNetworkGenerator {

	
	static Graph<Object, DefaultEdge> completeGraph;
	static int size = 4;

	public static void main(String[] args)
    {
        // Create the graph object; it is null at this point
        completeGraph = new SimpleGraph<>(DefaultEdge.class);

        // Create the CompleteGraphGenerator object
        //CompleteGraphGenerator<Object, DefaultEdge> completeGenerator =
         //   new CompleteGraphGenerator<>(size);
        GnmRandomGraphGenerator<Object, DefaultEdge> completeGenerator = new GnmRandomGraphGenerator<>(size, 3);

        // Create the VertexFactory so the generator can create vertices
        VertexFactory<Object> vFactory = new ClassBasedVertexFactory<>(Object.class);

        // Use the CompleteGraphGenerator object to make completeGraph a
        // complete graph with [size] number of vertices
        completeGenerator.generateGraph(completeGraph, vFactory, null);

        // Now, replace all the vertices with sequential numbers so we can ID
        // them
        Set<Object> vertices = new HashSet<>();
        vertices.addAll(completeGraph.vertexSet());
        Integer counter = 0;
        for (Object vertex : vertices) {
            replaceVertex(vertex, counter++);
        }

        // Print out the graph to be sure it's really complete
        @SuppressWarnings("unchecked")
		Iterator<Object> iter = new DepthFirstIterator<>(completeGraph);
        Object vertex;
        while (iter.hasNext()) {
            vertex = iter.next();
            System.out.println(
                "Vertex " + vertex.toString() + " is connected to: "
                    + completeGraph.edgesOf(vertex).toString());
            Iterator<DefaultEdge> hola = completeGraph.edgesOf(vertex).iterator();
            DefaultEdge edge;
            while (hola.hasNext()){
            	edge = hola.next();
            	System.out.println(completeGraph.getEdgeSource(edge)+completeGraph.getEdgeTarget(edge).toString());
            }
        }
    }

    private static boolean replaceVertex(Object oldVertex, Object newVertex)
    {
        if ((oldVertex == null) || (newVertex == null)) {
            return false;
        }
        Set<DefaultEdge> relatedEdges = completeGraph.edgesOf(oldVertex);
        completeGraph.addVertex(newVertex);

        Object sourceVertex;
        Object targetVertex;
        for (DefaultEdge e : relatedEdges) {
            sourceVertex = completeGraph.getEdgeSource(e);
            targetVertex = completeGraph.getEdgeTarget(e);
            if (sourceVertex.equals(oldVertex) && targetVertex.equals(oldVertex)) {
                completeGraph.addEdge(newVertex, newVertex);
            } else {
                if (sourceVertex.equals(oldVertex)) {
                    completeGraph.addEdge(newVertex, targetVertex);
                } else {
                    completeGraph.addEdge(sourceVertex, newVertex);
                }
            }
        }
        completeGraph.removeVertex(oldVertex);
        return true;
    }
}