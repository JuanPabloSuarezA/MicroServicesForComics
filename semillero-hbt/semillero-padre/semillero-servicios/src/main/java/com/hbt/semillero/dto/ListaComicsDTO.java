/**
 * ListaComicsDTO.java
 */
package com.hbt.semillero.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <b>Descripción:<b> Clase que determina
 * <b>Caso de Uso:<b> 
 * @author Personal
 * @version 
 */
public class ListaComicsDTO extends ResultadoDTO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ComicDTO> listaComics;
	
	public ListaComicsDTO() {
		this.listaComics = new ArrayList<>();
	}

	/**
	 * Metodo encargado de retornar el valor del atributo listaComics
	 * @return El listaComics asociado a la clase
	 */
	public List<ComicDTO> getListaComics() {
		return listaComics;
	}

	/**
	 * Metodo encargado de modificar el valor del atributo listaComics
	 * @param listaComics El nuevo listaComics a modificar.
	 */
	public void setListaComics(List<ComicDTO> listaComics) {
		this.listaComics = listaComics;
	}
	
}
