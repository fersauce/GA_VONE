/**
 * 
 */
package py.una.pol.vone.ga.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fernandosaucedo
 * Modelo que representa al VNR
 */
public class VirtualNetwork {
	private List<VirtualNode> nodosVirtuales;
	private List<VirtualEdge> enlacesVirtuales;
	private boolean mapeado;
	/**
	 * 
	 */
	public VirtualNetwork() {
		super();
		this.nodosVirtuales = new ArrayList<VirtualNode>();
		this.enlacesVirtuales = new ArrayList<VirtualEdge>();
		this.mapeado = false;
	}
	/**
	 * @param nodosVirtuales
	 * @param enlacesVirtuales
	 * @param mapeado
	 */
	public VirtualNetwork(ArrayList<VirtualNode> nodosVirtuales, 
			ArrayList<VirtualEdge> enlacesVirtuales) {
		super();
		this.nodosVirtuales = nodosVirtuales;
		this.enlacesVirtuales = enlacesVirtuales;
		this.mapeado = false;
	}
	/**
	 * @return the nodosVirtuales
	 */
	public List<VirtualNode> getNodosVirtuales() {
		return nodosVirtuales;
	}
	/**
	 * @param nodosVirtuales the nodosVirtuales to set
	 */
	public void setNodosVirtuales(List<VirtualNode> nodosVirtuales) {
		this.nodosVirtuales = nodosVirtuales;
	}
	/**
	 * @return the enlacesVirtuales
	 */
	public List<VirtualEdge> getEnlacesVirtuales() {
		return enlacesVirtuales;
	}
	/**
	 * @param enlacesVirtuales the enlacesVirtuales to set
	 */
	public void setEnlacesVirtuales(List<VirtualEdge> enlacesVirtuales) {
		this.enlacesVirtuales = enlacesVirtuales;
	}
	/**
	 * @return the mapeado
	 */
	public boolean isMapeado() {
		return mapeado;
	}
	/**
	 * @param mapeado the mapeado to set
	 */
	public void setMapeado(boolean mapeado) {
		this.mapeado = mapeado;
	}
	/**
	 * 
	 */
	public void addNodoVirtual(VirtualNode nodoVirtual){
		this.nodosVirtuales.add(nodoVirtual);
	}
	/**
	 * 
	 */
	public void addEnlaceVirtual(VirtualEdge enlaceVirtual){
		this.enlacesVirtuales.add(enlaceVirtual);
	}
}
