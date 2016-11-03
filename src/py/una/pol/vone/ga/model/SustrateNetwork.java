/**
 * 
 */
package py.una.pol.vone.ga.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fersauce
 *Modelo que representa a la red sustrato.
 */
public class SustrateNetwork {
	private List<SustrateNode> nodosFisicos;
	private List<SustrateEdge> enlacesFisicos;
	/**
	 * 
	 */
	public SustrateNetwork() {
		// TODO Auto-generated constructor stub
		super();
		this.nodosFisicos = new ArrayList<SustrateNode>();
		this.enlacesFisicos = new ArrayList<SustrateEdge>();
	}
	public List<SustrateNode> getNodosFisicos() {
		return nodosFisicos;
	}
	public void setNodosFisicos(List<SustrateNode> nodosFisicos) {
		this.nodosFisicos = nodosFisicos;
	}
	public List<SustrateEdge> getEnlacesFisicos() {
		return enlacesFisicos;
	}
	public void setEnlacesFisicos(List<SustrateEdge> enlacesFisicos) {
		this.enlacesFisicos = enlacesFisicos;
	}
	public void agregarNodoFisico(SustrateNode nodo){
		this.nodosFisicos.add(nodo);
	}
	public void agregarEnlaceFisico(SustrateEdge enlace){
		this.enlacesFisicos.add(enlace);
	}
}
