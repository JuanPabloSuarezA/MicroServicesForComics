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
public class ConsultaComicEstadoEnumDTO extends ResultadoDTO{
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * Atributo que determina los nombres mayores a una longitud
	 */
	private List<ComicDTO> listaComicsActivos;
	/**
	 * Atributo que determina los nombres menores a una longitud
	 */
	private List<ComicDTO> listaComicsInactivos;
	
	public ConsultaComicEstadoEnumDTO() {
		listaComicsActivos = new ArrayList<>();
		listaComicsInactivos = new ArrayList<>();
	}
	
	/**
	 * Constructor de la clase.
	 * @param nombresMayor
	 * @param nombresMenor
	 */
	public ConsultaComicEstadoEnumDTO(List<ComicDTO> comicsActivos,
			List<ComicDTO> comicsInactivos) {
		this.listaComicsActivos = comicsActivos;
		this.listaComicsInactivos = comicsInactivos;
	}

	/**
	 * Metodo encargado de retornar el valor del atributo comicsActivos
	 * @return El comicsActivos asociado a la clase
	 */
	public List<ComicDTO> getComicsActivos() {
		return listaComicsActivos;
	}

	/**
	 * Metodo encargado de modificar el valor del atributo comicsActivos
	 * @param comicsActivos El nuevo comicsActivos a modificar.
	 */
	public void setComicsActivos(List<ComicDTO> comicsActivos) {
		this.listaComicsActivos = comicsActivos;
	}

	/**
	 * Metodo encargado de retornar el valor del atributo comicsInactivos
	 * @return El comicsInactivos asociado a la clase
	 */
	public List<ComicDTO> getComicsInactivos() {
		return listaComicsInactivos;
	}

	/**
	 * Metodo encargado de modificar el valor del atributo comicsInactivos
	 * @param comicsInactivos El nuevo comicsInactivos a modificar.
	 */
	public void setComicsInactivos(List<ComicDTO> comicsInactivos) {
		this.listaComicsInactivos = comicsInactivos;
	}
	


}
