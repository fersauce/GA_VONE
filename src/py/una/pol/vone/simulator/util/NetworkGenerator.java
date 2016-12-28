/**
 * 
 */
package py.una.pol.vone.simulator.util;

import java.io.BufferedReader;
import java.io.FileReader;
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

import py.una.pol.vone.simulator.model.SustrateEdge;
import py.una.pol.vone.simulator.model.SustrateNetwork;
import py.una.pol.vone.simulator.model.SustrateNode;
import py.una.pol.vone.simulator.model.VirtualEdge;
import py.una.pol.vone.simulator.model.VirtualNetwork;
import py.una.pol.vone.simulator.model.VirtualNode;

/**
 * Clase utilizada para generar las topologias de redes virtuales.
 * @author Fernando Saucedo
 * @version 1.0
 * @since 2016-11-16
 */
public class NetworkGenerator {
	
	private static Graph<Object, DefaultEdge> grafoGenerado;

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
        ArrayList<Object> visitados = new ArrayList<>();
        recorrerEnProfundidad(grafoGenerado.vertexSet().iterator().next(), visitados);
        if(visitados.size()!=cantidadNodos){
        	grafoGenerado = null;
        	return false;
        }
		return true;
	}
	
	/**
	 * Metodo privado que realiza un recorrido en profundidad del grafo 
	 * @param vertice objeto que tiene los datos del vertice de los cuales se buscara si hay hijos y si ya se visitaron.
	 * @param visitados lista que tiene los vertices que ya fueron visitados.
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
	 * Metodo que reemplaza el valor del vertice obtenido (Object) en otro objeto pasado como parametro
	 * @param nodoViejo objeto viejo a reemplazar
	 * @param nodoNuevo objeto nuevo a colocar en el lugar del viejo
	 */
	private static void reemplazarNodos(Object nodoViejo, Object nodoNuevo)
    {
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
	 * Metodo utilizado para generar las redes virtuales a utilizar.
	 * @param cantidadRedes numero de redes a tener la red.
	 * @param requerimientosVirtuales Listado en donde se van a almacenar las redes generadas.
	 */
	public void generarRedesVirtuales(int cantidadRedes, ArrayList<VirtualNetwork> requerimientosVirtuales)
    {
		Random rand = new Random();
		int numNodos, numEnlaces, numMaxEnlaces, numMinEnlaces;
		//ArrayList<VirtualNetwork> requerimientosVirtuales = new ArrayList<>();
		VirtualNetwork redVirtual;
		ArrayList<VirtualNode> nodosVirtuales;
		ArrayList<VirtualEdge> enlacesVirtuales;
		/*for(int numeroArchivoOrigenRed= 1; numeroArchivoOrigenRed <= param; numeroArchivo(
		 * */
		for(int i = 1;i <= cantidadRedes; i++){
			numNodos = rand.nextInt(5)+3;
			numMinEnlaces = numNodos - 1;
			numMaxEnlaces = (numNodos*(numNodos-1))/2;//Numero de enlaces de un grafo conexo con N nodos.
			numEnlaces = rand.nextInt(numMaxEnlaces - numMinEnlaces + 1) + numMinEnlaces;
			generarTopologia(numNodos, numEnlaces);
			//Se crea las listas para albergar los nodos virtuales y los enlaces virtuales
			nodosVirtuales = new ArrayList<VirtualNode>();
			enlacesVirtuales = new ArrayList<VirtualEdge>();
			//Aqui se agrega los nodos a la lista.
			VirtualNode nodo;
			int capacidadCPU, totalCPU = 0;
			for (int j = 1; j<= numNodos; j++){
				//El requerimiento de CPU es entre uno y cuatro unidades de CPU, por eso el rand entre esos valores.
				capacidadCPU = rand.nextInt(4)+1;
				nodo = new VirtualNode(j,"VNR"+String.valueOf(i)+"N"+String.valueOf(j), capacidadCPU);
				nodosVirtuales.add(nodo);
				nodo = null;
				totalCPU += capacidadCPU;
			}
			//Aqui se agrega los enlaces virtuales.
			Iterator<DefaultEdge> aristas = grafoGenerado.edgeSet().iterator();
			DefaultEdge arista;
			VirtualEdge enlace;
			VirtualNode nodoOrigen = null, nodoDestino = null;
			int cantidadFS;
			while(aristas.hasNext()){
				arista = aristas.next();
				enlace = new VirtualEdge();
				boolean origenEncontrado = false, destinoEncontrado = false;
				for(VirtualNode nod: nodosVirtuales){
					if(!origenEncontrado && grafoGenerado.getEdgeSource(arista).toString().
							equals(String.valueOf(nod.getIdentificador()))){
						nodoOrigen = nod;
						origenEncontrado = true;
					} else if(!destinoEncontrado && grafoGenerado.getEdgeTarget(arista).toString().
							equals(String.valueOf(nod.getIdentificador()))){
						nodoDestino = nod;
						destinoEncontrado = true;
					}
					if(origenEncontrado&&destinoEncontrado){
						break;
					}
				}
				//El requerimiento de FS es entre uno y cuatro unidades, por eso el rand entre esos valores.
				cantidadFS = rand.nextInt(4)+1;
				//Se crea el enlace virtual
				enlace = new VirtualEdge(nodoOrigen, nodoDestino, cantidadFS);
				//Se asocia a cada nodo virtual el enlace creado.
				ArrayList<VirtualEdge> adyacentesOrigen = (ArrayList<VirtualEdge>)nodoOrigen.getAdyacentes();
				adyacentesOrigen.add(enlace);
				nodoOrigen.setAdyacentes(adyacentesOrigen);
				ArrayList<VirtualEdge> adyacentesDestino = (ArrayList<VirtualEdge>)nodoDestino.getAdyacentes();
				adyacentesDestino.add(enlace);
				nodoDestino.setAdyacentes(adyacentesDestino);
				enlacesVirtuales.add(enlace);
				//Se libera todo.
				nodoOrigen = null;
				nodoDestino = null;
				enlace = null;
				arista = null;
			}
			redVirtual = new VirtualNetwork(nodosVirtuales,enlacesVirtuales);
			redVirtual.setTotalCPU(totalCPU);
			requerimientosVirtuales.add(redVirtual);
			redVirtual = null;
		}
    }
	
	/**
	 * Metodo para generar la red Fisica
	 * @param redFisica objeto que va a almacenar la red fisica.
	 * @param pathRed direccion de donde obtener los enlaces de la red fisica.
	 * @param cantidadNodos numero de nodos que va a contener la red fisica.
	 */
	public void generarRedFisica(SustrateNetwork redFisica, String pathRed, int cantidadNodos){
		ArrayList<SustrateNode> nodosFisicos = new ArrayList<SustrateNode>();
		ArrayList<SustrateEdge> enlacesFisicos = new ArrayList<SustrateEdge>();
		//Esta parte es para generar los nodos
		SustrateNode nodoNuevo;
		Random rnd = new Random();
		int capacidadCPU;
		
		for(int i = 1; i<=cantidadNodos; i++){
			//Se hace el random entre 10 y 20 unidades de CPU.
			capacidadCPU = rnd.nextInt(11)+1;
			nodoNuevo = new SustrateNode(i, "NodoSustrato "+String.valueOf(i), capacidadCPU);
			nodosFisicos.add(nodoNuevo);
			nodoNuevo = null;
		}
		
		//Metodo para generar los enlaces fisicos
		try{
			@SuppressWarnings("resource")
			BufferedReader bf = new BufferedReader(new FileReader(pathRed));
			String linea = bf.readLine();
			SustrateNode nodoOrigen = null, nodoDestino = null;
			SustrateEdge enlaceNuevo;
			boolean origenEncontrado = false, destinoEncontrado = false, enlaceRepetido = false;
			int distancia;
			while(linea != null){
				String [] descripcionEnlace = linea.split("\\s");
				if(descripcionEnlace.length == 3){
					for(SustrateNode nodo: nodosFisicos){
						if(!origenEncontrado && nodo.getID() == (Integer.parseInt(descripcionEnlace[0])+1)){
							nodoOrigen = nodo;
							origenEncontrado = true;
						} else if(!destinoEncontrado && nodo.getID() == (Integer.parseInt(descripcionEnlace[1])+1)){
							nodoDestino = nodo;
							destinoEncontrado = true;
						}
						if(origenEncontrado && destinoEncontrado){
							break;
						}
					}
					//La distancia es un valor que agregue, entre 1 y 5 km, por si los lleguemos a utilizar.
					distancia = rnd.nextInt(5)+1;
					enlaceNuevo = new SustrateEdge(nodoOrigen, nodoDestino, distancia);
					/*Las dos siguientes lineas es para verificar que no exista el enlace, ya que el archivo tiene 
					 * dos enlaces por el tema de convertir en no dirigido dicho grafo
					 */
					for(SustrateEdge enlace: enlacesFisicos){
						//Aqui se verifica si el enlace ya no existe, pero invertido
						if((enlace.getNodoOrigen().getID() == nodoOrigen.getID() && 
								enlace.getNodoDestino().getID() == nodoDestino.getID()) ||
								(enlace.getNodoOrigen().getID()==nodoDestino.getID() && 
								enlace.getNodoDestino().getID() == nodoOrigen.getID())){
							enlaceRepetido = true;
							break;
						}
					}					
					if(!enlaceRepetido){
						enlacesFisicos.add(enlaceNuevo);
						ArrayList<SustrateEdge> adyacentesOrigen = nodoOrigen.getAdyacentes();
						adyacentesOrigen.add(enlaceNuevo);
						nodoOrigen.setAdyacentes(adyacentesOrigen);
						ArrayList<SustrateEdge> adyacentesDestino = nodoDestino.getAdyacentes();
						adyacentesDestino.add(enlaceNuevo);
						nodoDestino.setAdyacentes(adyacentesDestino);
					}
				}
				linea = bf.readLine();
				nodoOrigen = null;
				nodoDestino = null;
				enlaceNuevo = null;
				origenEncontrado = false;
				destinoEncontrado = false;
				enlaceRepetido = false;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		redFisica.setEnlacesFisicos(enlacesFisicos);
		redFisica.setNodosFisicos(nodosFisicos);
	}
}