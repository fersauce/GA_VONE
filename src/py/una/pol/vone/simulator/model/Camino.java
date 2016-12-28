/**
 * 
 */
package py.una.pol.vone.simulator.model;

import java.util.List;

import edu.ufl.cise.bsmock.graph.util.Path;

/**
 * @author FernandoRamón
 * Clase camino en donde se define un objeto para representar los caminos entre cada uno de los nodos de un grafo.
 */
public class Camino {

	/**
	 * 
	 */
	private int origen;
	private int destino;
	private List<Path> caminos;
	public Camino() {
		
	}
	
	public Camino(int origenExt, int destinoExt){
		this.origen = origenExt;
		this.destino = destinoExt;
	}

	public int getOrigen() {
		return origen;
	}

	public void setOrigen(int origen) {
		this.origen = origen;
	}

	public int getDestino() {
		return destino;
	}

	public void setDestino(int destino) {
		this.destino = destino;
	}

	public List<Path> getCaminos() {
		return caminos;
	}

	public void setCaminos(List<Path> caminos) {
		this.caminos = caminos;
	}
	
}
