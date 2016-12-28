/**
 * 
 */
package py.una.pol.vone.simulator.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Modelo que representa al VNR
 * 
 * @author fersauce
 */
public class VirtualNetwork {
	private List<VirtualNode> nodosVirtuales;
	private List<VirtualEdge> enlacesVirtuales;
	private int totalCPU;
	private boolean mapeado;

	/**
	 * Constructor de la clase.
	 */
	public VirtualNetwork() {
		super();
		this.nodosVirtuales = new ArrayList<VirtualNode>();
		this.enlacesVirtuales = new ArrayList<VirtualEdge>();
		this.mapeado = false;
	}

	/**
	 * Constructor de la clase.
	 * 
	 * @param nodosVirtuales
	 *            ArrayList con todos los nodos de la red virtual.
	 * @param enlacesVirtuales
	 *            ArrayList con todos los enlaces entre los nodos de la red
	 *            virtual.
	 */
	public VirtualNetwork(ArrayList<VirtualNode> nodosVirtuales, ArrayList<VirtualEdge> enlacesVirtuales) {
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
	 * @param nodosVirtuales
	 *            the nodosVirtuales to set
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
	 * @param enlacesVirtuales
	 *            the enlacesVirtuales to set
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
	 * @param mapeado
	 *            the mapeado to set
	 */
	public void setMapeado(boolean mapeado) {
		this.mapeado = mapeado;
	}

	/**
	 * @return the totalCPU
	 */
	public int getTotalCPU() {
		return totalCPU;
	}

	/**
	 * @param totalCPU
	 *            the totalCPU to set
	 */
	public void setTotalCPU(int totalCPU) {
		this.totalCPU = totalCPU;
	}

	/**
	 * 
	 */
	public void addNodoVirtual(VirtualNode nodoVirtual) {
		this.nodosVirtuales.add(nodoVirtual);
	}

	/**
	 * 
	 */
	public void addEnlaceVirtual(VirtualEdge enlaceVirtual) {
		this.enlacesVirtuales.add(enlaceVirtual);
	}

	@Override
	public String toString() {
		String retorno = new String();
		retorno = retorno.concat("Se imprime primero los nodos\n");
		for (VirtualNode nodo : this.getNodosVirtuales()) {
			retorno = retorno.concat("Nodo " + nodo.getNombre() + "\nEnlazado a el/los nodo/s: ");
			for (VirtualEdge enlace : nodo.getAdyacentes()) {
				if (enlace.getNodoDos() == nodo) {
					retorno = retorno.concat(enlace.getNodoUno().getNombre() + ", ");
				} else {
					retorno = retorno.concat(enlace.getNodoDos().getNombre() + ", ");
				}
			}
			retorno = retorno.concat("\n");
		}
		retorno = retorno.concat("Ahora se imprimen los enlaces entre estos nodos\n");
		for (VirtualEdge enlace : this.getEnlacesVirtuales()) {
			retorno = retorno.concat(
					"Enlace " + enlace.getNodoUno().getNombre() + "<->" + enlace.getNodoDos().getNombre() + "\n");
		}
		retorno = retorno.concat("Total de CPU de la red: " + this.getTotalCPU());
		return retorno;
	}

}
