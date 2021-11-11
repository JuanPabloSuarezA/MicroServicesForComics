package com.hbt.semillero.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.dto.ConsultaComicEstadoEnumDTO;
import com.hbt.semillero.dto.ConsultaComicTamanioNombreDTO;
import com.hbt.semillero.dto.ConsultaNombrePrecioComicDTO;
import com.hbt.semillero.dto.ListaComicsDTO;
import com.hbt.semillero.dto.ResultadoDTO;
import com.hbt.semillero.ejb.IGestionarComicLocal;
import com.hbt.semillero.ejb.IGestionarCompraComicLocal;
import com.hbt.semillero.enums.EstadoEnum;

@Path("/gestionarCompraComic")
public class GestionarCompraComicRest {
	
	@EJB
	private IGestionarCompraComicLocal gestionarCompraComicLocal;

	
	//Se asigna la ruta compraComic para las solicitudes al servicio de comprar comic 
	
	@POST
	@Path("/comprarComic")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ComicDTO comprarComic(ComicDTO comicDTO) throws Exception {
		
		return this.gestionarCompraComicLocal.comprarComic(comicDTO);
	} 

}
