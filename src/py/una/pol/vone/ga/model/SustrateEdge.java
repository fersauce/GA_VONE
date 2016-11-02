/**
 * 
 */
package py.una.pol.vone.ga.model;

/**
 * @author fersauce
 * Modelo que representa a los enlaces fisicos.
 */
public class SustrateEdge {
	private SustrateNode nodoOrigen;
	private SustrateNode nodoDestino;
	private int distancia;
	private String[] frequencySlot;
	
	/**
	 * 
	 */
	public SustrateEdge() {
		// TODO Auto-generated constructor stub
	}
	
	public SustrateEdge(SustrateNode origen, SustrateNode destino, int distancia){
		this.nodoOrigen = origen;
		this.nodoDestino = destino;
		this.distancia = distancia;
		this.frequencySlot = new String[200];
	}

	public SustrateNode getNodoOrigen() {
		return nodoOrigen;
	}

	public void setNodoOrigen(SustrateNode nodoOrigen) {
		this.nodoOrigen = nodoOrigen;
	}

	public SustrateNode getNodoDestino() {
		return nodoDestino;
	}

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
