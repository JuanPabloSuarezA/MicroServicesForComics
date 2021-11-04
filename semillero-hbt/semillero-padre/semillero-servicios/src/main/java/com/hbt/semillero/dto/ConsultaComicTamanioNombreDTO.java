/**
 * ConsultarComicTamanioNombreDTO.java
 */
package com.hbt.semillero.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * <b>Descripción:<b> Clase que determina el dto a usar para consultar nombres 
 * y clasificarlos segun la longitud
 * 
 * @author Personal
 */
public class ConsultaComicTamanioNombreDTO extends ResultadoDTO{
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * Atributo que determina los nombres mayores a una longitud
	 */
	private List<String> nombresMayorIgual = new ArrayList<>();
	/**
	 * Atributo que determina los nombres menores a una longitud
	 */
	private List<String> nombresMenor = new ArrayList<>();
	
	public ConsultaComicTamanioNombreDTO() {
		//Constructor vacio
	}
	
	/**
	 * Constructor de la clase.
	 * @param nombresMayor
	 * @param nombresMenor
	 */
	public ConsultaComicTamanioNombreDTO(List<String> nombresMayor,List<String> nombresMenor) {
		this.nombresMayorIgual = nombresMayor;
		this.nombresMenor = nombresMenor;
	}
	
	public List<String> getNombresMayorIgual() {
		return nombresMayorIgual;
	} 	
	
	public void setNombreMayorIgual(String nombre) {
		this.nombresMayorIgual.add(nombre);
	}
	public List<String> getNombresMenor() {
		return nombresMenor;
	} 	
	public void setNombreMenor(String nombre) {
		this.nombresMenor.add(nombre);
	}

}
