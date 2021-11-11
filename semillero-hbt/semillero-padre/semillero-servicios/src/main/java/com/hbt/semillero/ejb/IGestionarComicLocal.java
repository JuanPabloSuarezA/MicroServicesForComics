package com.hbt.semillero.ejb;

import java.util.List;

import javax.ejb.Local;

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.dto.ConsultaComicEstadoEnumDTO;
import com.hbt.semillero.dto.ConsultaNombrePrecioComicDTO;
import com.hbt.semillero.dto.ListaComicsDTO;
import com.hbt.semillero.dto.ConsultaComicTamanioNombreDTO;
import com.hbt.semillero.dto.ResultadoDTO;
import com.hbt.semillero.enums.EstadoEnum;

@Local
public interface IGestionarComicLocal {
	
	public ConsultaNombrePrecioComicDTO consultarNombrePrecioComic(Long idComic);
	public ConsultaComicTamanioNombreDTO  consultarComicTamanioNombre (Short lengthComic);
	
	public ConsultaComicEstadoEnumDTO  consultarComicEstadoEnumDTO (EstadoEnum estadoEnum);

	public ComicDTO crearComic(ComicDTO comicDTO) throws Exception;
	
	public ComicDTO actualizarComic(ComicDTO comicDTO) throws Exception;
	
	public ResultadoDTO eliminarComic(Long idComic);
	
	public ListaComicsDTO consultarComics();
	public ComicDTO consultarUnComic(Long idComic);
}
