package com.hbt.semillero.ejb;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.dto.ConsultaComicEstadoEnumDTO;
import com.hbt.semillero.dto.ConsultaNombrePrecioComicDTO;
import com.hbt.semillero.dto.ListaComicsDTO;
import com.hbt.semillero.dto.ConsultaComicTamanioNombreDTO;
import com.hbt.semillero.dto.ResultadoDTO;
import com.hbt.semillero.entidad.Comic;
import com.hbt.semillero.enums.EstadoEnum;

/**
 * <b>Descripción:<b> Clase que determina la lógica de transacciones para comprar un comic
 * <b>Caso de Uso:semillero2021
 * @author Personal
 * @version 
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class GestionarCompraComicBean implements IGestionarCompraComicLocal {

	@PersistenceContext
	public EntityManager em;

	
	/** 
	 * método para comprar un comic que recibe el parámetro comicDTO
	 * para actualizar la cantidad que se vaya a comprar y el estado
	 * del comic
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public ComicDTO comprarComic(ComicDTO comicDTO) throws Exception{
		
		//Se genera una consulta para encontrar el comic con el id especificado
		String consulta = "SELECT c  FROM Comic c WHERE c.id = :idComic";
		
		//Se declaran variables para almacenar resultados de consulta y de retorno
		ComicDTO comicDTOConsulta = new ComicDTO();
		ComicDTO comicDTOResult = new ComicDTO();
		Comic comic, comicResult; 
		try {
			//Se crea la query del comic 
			Query queryConsultarComic = em.createQuery(consulta);
			//Se establece el parámetro de Idcomid del DTO
			queryConsultarComic.setParameter("idComic", comicDTO.getId());
			//Se ejecuta la consulta para obtener el comic según el ID
			comic = (Comic) queryConsultarComic.getSingleResult();
			//Se convierte a comicDTO el comic para las operaciones
			comicDTOConsulta = this.convertirComicToComicDTO(comic);
			//Se obtiene un nombrecomic para enviar en el mensaje de ejecucion
			String nombreComic = comicDTOConsulta.getNombre();
			
			//Se rechazan compras para comics inactivos
			if (comicDTOConsulta.getEstadoEnum() == EstadoEnum.INACTIVO) {
				throw new Exception("El comic seleccionado no cuenta con stock en bodega");
			}
			
			//Se rechazan compras para cantidades superiores a la disponible de
			//la cantidad del comic 
			
			if (comicDTO.getCantidad() >  comicDTOConsulta.getCantidad()) {	
				Long numeroComics = comicDTOConsulta.getCantidad();
				throw new Exception("La cantidad existente del comic es:" +   numeroComics  
						+ " y es inferior la ingresada");
			}
			
			//Si la cantidad es menor o igual se puede efectuar la compra
			if (comicDTO.getCantidad() <  comicDTOConsulta.getCantidad()) {	
				//Se actualiza la fecha de venta 
				comicDTO.setFechaVenta(LocalDate.now());
				//Se descuenta la cantidad ingresada
				comicDTO.setCantidad(comicDTOConsulta.getCantidad() - comicDTO.getCantidad());

			}
			else if (comicDTO.getCantidad() == comicDTOConsulta.getCantidad()) {
				
				//Se actualiza la fecha de venta 
				comicDTO.setFechaVenta(LocalDate.now());
				//Se descuenta la cantidad ingresada
				comicDTO.setCantidad(0L);
				
				//Se actualiza el estado a inactivo
				comicDTO.setEstadoEnum(EstadoEnum.INACTIVO);
			}
			
			//Se convierte a comic el comic actualizado para la operacion de actualización
			comicResult = convertirComicDTOToComic(comicDTO);
			//Se actualiza el comic
			em.merge(comicResult);
			
			//Se convierte el comic para retornar
			comicDTOResult = this.convertirComicToComicDTO(comicResult);
			
			//Se establecen los mensajes pertinentes
			comicDTOResult.setExitoso(true);
			comicDTOResult.setMensajeEjecucion("La compra del comic " + nombreComic 
					+" fue exitosa");
			
		} catch (Exception e) {
			comicDTOResult.setExitoso(false);
			comicDTOResult.setMensajeEjecucion(e.getMessage());
		}
		
		return comicDTOResult;
	}

	
	
	/**
	 * 
	 * Metodo encargado de transformar un comic a un comicDTO
	 * 
	 * @param comic
	 * @return
	 */
	private ComicDTO convertirComicToComicDTO(Comic comic) {
		ComicDTO comicDTO = new ComicDTO();
		comicDTO.setId(comic.getId());
		comicDTO.setNombre(comic.getNombre());
		comicDTO.setEditorial(comic.getEditorial());
		comicDTO.setTematicaEnum(comic.getTematicaEnum());
		comicDTO.setColeccion(comic.getColeccion());
		comicDTO.setNumeroPaginas(comic.getNumeroPaginas());
		comicDTO.setPrecio(comic.getPrecio());
		comicDTO.setAutores(comic.getAutores());
		comicDTO.setColor(comic.getColor());
		comicDTO.setFechaVenta(comic.getFechaVenta());
		comicDTO.setEstadoEnum(comic.getEstadoEnum());
		comicDTO.setCantidad(comic.getCantidad());
		return comicDTO;
	}

	/**
	 * 
	 * Metodo encargado de transformar un comicDTO a un comic
	 * 
	 * @param comic
	 * @return
	 */
	private Comic convertirComicDTOToComic(ComicDTO comicDTO) {
		Comic comic = new Comic();
		comic.setId(comicDTO.getId());
		comic.setNombre(comicDTO.getNombre());
		comic.setEditorial(comicDTO.getEditorial());
		comic.setTematicaEnum(comicDTO.getTematicaEnum());
		comic.setColeccion(comicDTO.getColeccion());
		comic.setNumeroPaginas(comicDTO.getNumeroPaginas());
		comic.setPrecio(comicDTO.getPrecio());
		comic.setAutores(comicDTO.getAutores());
		comic.setColor(comicDTO.getColor());
		comic.setFechaVenta(comicDTO.getFechaVenta());
		comic.setEstadoEnum(comicDTO.getEstadoEnum());
		comic.setCantidad(comicDTO.getCantidad());
		return comic;
	}
}





//comic.setAutores(comicDTO.getAutores());
//comic.setCantidad(comicDTO.getCantidad());
//comic.setColeccion(comicDTO.getColeccion());
//comic.setColor(comicDTO.getColor());
//comic.setEditorial(comicDTO.getEditorial());
//comic.setEstadoEnum(comicDTO.getEstadoEnum());
//comic.setFechaVenta(comicDTO.getFechaVenta());
//comic.setNombre(comicDTO.getNombre());
//comic.setNumeroPaginas(comicDTO.getNumeroPaginas());
//comic.setPrecio(comicDTO.getPrecio());
//comic.setTematicaEnum(comicDTO.getTematicaEnum());
