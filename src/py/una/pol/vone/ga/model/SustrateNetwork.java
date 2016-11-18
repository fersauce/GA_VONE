/**
 * 
 */
package py.una.pol.vone.ga.model;

import java.util.ArrayList;

/**
 * Modelo que representa a la red sustrato.
 * @author fersauce
 */
public class SustrateNetwork {
	private ArrayList<SustrateNode> nodosFisicos;
	private ArrayList<SustrateEdge> enlacesFisicos;
	private int totalCPU;
	/**
	 * @return the totalCPU
	 */
	public int getTotalCPU() {
		return totalCPU;
	}
	/**
	 * @param totalCPU the totalCPU to set
	 */
	public void setTotalCPU(int totalCPU) {
		this.totalCPU = totalCPU;
	}
	/**
	 * Constructor de clase.
	 */
	public SustrateNetwork() {
		super();
		this.nodosFisicos = new ArrayList<SustrateNode>();
		this.enlacesFisicos = new ArrayList<SustrateEdge>();
	}
	public ArrayList<SustrateNode> getNodosFisicos() {
		return nodosFisicos;
	}
	public void setNodosFisicos(ArrayList<SustrateNode> nodosFisicos) {
		this.nodosFisicos = nodosFisicos;
	}
	public ArrayList<SustrateEdge> getEnlacesFisicos() {
		return enlacesFisicos;
	}
	public void setEnlacesFisicos(ArrayList<SustrateEdge> enlacesFisicos) {
		this.enlacesFisicos = enlacesFisicos;
	}
	public void agregarNodoFisico(SustrateNode nodo){
		this.nodosFisicos.add(nodo);
	}
	public void agregarEnlaceFisico(SustrateEdge enlace){
		this.enlacesFisicos.add(enlace);
	}
	@Override
	public String toString() {
		String retorno = new String();
		retorno = retorno.concat("Se imprime primero los nodos\n");
		for(SustrateNode nodo: this.getNodosFisicos()){
			retorno = retorno.concat("Nodo "+nodo.getNombre()+"\nEnlazado a el/los nodo/s: ");
			for(SustrateEdge enlace: nodo.getAdyacentes()){
				if(enlace.getNodoDestino()==nodo){
					retorno = retorno.concat(enlace.getNodoOrigen().getNombre()+", ");
				} else {
					retorno = retorno.concat(enlace.getNodoDestino().getNombre()+", ");
				}
			}
			retorno = retorno.concat("\n");
		}
		retorno = retorno.concat("Ahora se imprimen los enlaces entre estos nodos\n");
		for(SustrateEdge enlace: this.getEnlacesFisicos()){
			retorno = retorno.concat("Enlace "+enlace.getNodoOrigen().getNombre()+"<->"+
						enlace.getNodoDestino().getNombre()+"\n");
		}
		return retorno;
	}
	
}
