package com.hbt.semillero.ejb;


import javax.ejb.Local;

import com.hbt.semillero.dto.ComicDTO;

/**
 * <b>Descripción:<b> Interfaz para la implementacion de metodos en el bean
 * <b>Caso de Uso:<b> semillero2021
 * @author Personal
 * @version 
 */
@Local
public interface IGestionarCompraComicLocal {
	
	//Método a implementar para la compra de comics
	public ComicDTO comprarComic(ComicDTO comicDTO)  throws Exception;
}
