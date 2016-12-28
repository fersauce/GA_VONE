/**
 * 
 */
package py.una.pol.vone.simulator.model;

/**
 * Modelo que representa a los enlaces fisicos.
 * @author fersauce
 */
public class SustrateEdge {
	private SustrateNode nodoOrigen;
	private SustrateNode nodoDestino;
	private int distancia;
	private String[] frequencySlot;
	
	/**
	 * Constructor de clase.
	 */
	public SustrateEdge() {
		
	}
	
	/**
	 * Constructor de clase.
	 * @param origen nodo origen o nodo uno del enlace
	 * @param destino nodo destino o nodo dos del enlace
	 * @param distancia es la distancia recorrida 
	 */
	public SustrateEdge(SustrateNode origen, SustrateNode destino, int distancia){
		super();
		this.nodoOrigen = origen;
		this.nodoDestino = destino;
		this.distancia = distancia;
		this.frequencySlot = new String[200];//es array es 200, en base a lo definido con el profe
	}

	/**
	 * Getter de uno de los nodos.
	 * @return nodo configurado como uno u origen.
	 */
	public SustrateNode getNodoOrigen() {
		return nodoOrigen;
	}

	/**
	 * Setter del nodo uno.
	 * @param nodoOrigen nodo a setear dentro del objeto.
	 */
	public void setNodoOrigen(SustrateNode nodoOrigen) {
		this.nodoOrigen = nodoOrigen;
	}

	/**
	 * Getter de uno de los nodos.
	 * @return nodo configurado como dos o destino.
	 */
	public SustrateNode getNodoDestino() {
		return nodoDestino;
	}

	/**
	 * Setter del nodo dos.
	 * @param nodoDestino nodo a setear dentro del objeto
	 */
	public void setNodoDestino(SustrateNode nodoDestino) {
		this.nodoDestino = nodoDestino;
	}

	public int getDistancia() {
		return distancia;
	}

	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}

	public String[] getFrequencySlot() {
		return frequencySlot;
	}

	public void setFrequencySlot(String[] frequencySlot) {
		this.frequencySlot = frequencySlot;
	}
}
