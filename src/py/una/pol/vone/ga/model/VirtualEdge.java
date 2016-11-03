/**
 * 
 */
package py.una.pol.vone.ga.model;

/**
 * @author fernandosaucedo
 * Modelo que representa al enlace virtual entre dos nodos
 */
public class VirtualEdge {
	private VirtualNode nodoUno;
	private VirtualNode nodoDos;
	private int cantidadFSNecesarios;
	private boolean asignado;
	/**
	 * 
	 */
	public VirtualEdge() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param nodoUno primer nodo que conforma el enlace
	 * @param nodoDos segundo nodo que conforma el enlace
	 * @param cantidadFSNecesarios numero de FS a ser utilizados por este enlace
	 * @param asignado booleano para indicar si fue asignado 
	 */
	public VirtualEdge(VirtualNode nodoUno, VirtualNode nodoDos, 
			int cantidadFSNecesarios) {
		super();
		this.nodoUno = nodoUno;
		this.nodoDos = nodoDos;
		this.cantidadFSNecesarios = cantidadFSNecesarios;
		this.asignado = false;
	}
	/**
	 * @return the nodoUno
	 */
	public VirtualNode getNodoUno() {
		return nodoUno;
	}
	/**
	 * @param nodoUno the nodoUno to set
	 */
	public void setNodoUno(VirtualNode nodoUno) {
		this.nodoUno = nodoUno;
	}
	/**
	 * @return the nodoDos
	 */
	public VirtualNode getNodoDos() {
		return nodoDos;
	}
	/**
	 * @param nodoDos the nodoDos to set
	 */
	public void setNodoDos(VirtualNode nodoDos) {
		this.nodoDos = nodoDos;
	}
	/**
	 * @return the cantidadFSNecesarios
	 */
	public int getCantidadFSNecesarios() {
		return cantidadFSNecesarios;
	}
	/**
	 * @param cantidadFSNecesarios the cantidadFSNecesarios to set
	 */
	public void setCantidadFSNecesarios(int cantidadFSNecesarios) {
		this.cantidadFSNecesarios = cantidadFSNecesarios;
	}
	/**
	 * @return the asignado
	 */
	public boolean isAsignado() {
		return asignado;
	}
	/**
	 * @param asignado the asignado to set
	 */
	public void setAsignado(boolean asignado) {
		this.asignado = asignado;
	}
	
}
