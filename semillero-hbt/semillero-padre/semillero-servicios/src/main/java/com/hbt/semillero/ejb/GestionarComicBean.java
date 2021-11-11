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

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class GestionarComicBean implements IGestionarComicLocal {

	@PersistenceContext
	public EntityManager em;

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	@Override
	public ConsultaNombrePrecioComicDTO consultarNombrePrecioComic(Long idComic) {
		String consulta = "SELECT new com.hbt.semillero.dto.ConsultaNombrePrecioComicDTO(c.nombre, c.precio)  "
						+ " FROM Comic c WHERE c.id = :idComic";
		ConsultaNombrePrecioComicDTO consultaNombrePrecioDTO = new ConsultaNombrePrecioComicDTO();
		try {
			Query consultaNativa = em.createQuery(consulta);
			consultaNativa.setParameter("idComic", idComic);
			consultaNombrePrecioDTO = (ConsultaNombrePrecioComicDTO) consultaNativa.getSingleResult();
			consultaNombrePrecioDTO.setExitoso(true);
			consultaNombrePrecioDTO.setMensajeEjecucion("Se ejecuto exitosamente la consulta");	
		} catch (Exception e) {
			consultaNombrePrecioDTO.setExitoso(false);
			consultaNombrePrecioDTO.setMensajeEjecucion("Se ha presentado un error tecnico al consultar el comic");
		}

		return consultaNombrePrecioDTO;
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	@Override
	public ComicDTO consultarUnComic(Long idComic) {
		String consulta = "SELECT c  FROM Comic c WHERE c.id = :idComic";
		ComicDTO comicDTO = new ComicDTO();
		Comic comic; 
		try {
			Query queryConsultarComic = em.createQuery(consulta);
			queryConsultarComic.setParameter("idComic", idComic);
			comic = (Comic) queryConsultarComic.getSingleResult();
			comicDTO = this.convertirComicToComicDTO(comic);
			comicDTO.setExitoso(true);
			comicDTO.setMensajeEjecucion("Se ejecuto exitosamente la consulta");
		} catch (Exception e) {
			comicDTO.setExitoso(false);
			comicDTO.setMensajeEjecucion("Se ha presentado un error tecnico al consultar el comic");
		}

		return comicDTO;
	};
	
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	@Override
	public ConsultaComicEstadoEnumDTO   consultarComicEstadoEnumDTO (EstadoEnum estadoEnum) {
		//Se elabora la consulta
		String findAllComic = " SELECT c FROM Comic c WHERE c.estadoEnum = :estadoEnum";
		//Se genera el objeto a retornar 
		ConsultaComicEstadoEnumDTO consultaComicEstadoEnumDTO = new ConsultaComicEstadoEnumDTO();
		try {
			//Se ejecuta la consulta
			Query queryFindAllComic = em.createQuery(findAllComic);
			queryFindAllComic.setParameter("estadoEnum", estadoEnum);
			//Se obtienen todos los comics
			@SuppressWarnings("unchecked")
			List<Comic> listaComics = queryFindAllComic.getResultList();
			List<ComicDTO> listaComicsDTO = new ArrayList<>();
			Comic comic = null;
			//Se guardan los comics ya sean activos o inactivos
			for (int i = 0; i < listaComics.size(); i++) {
				comic = listaComics.get(i);
				listaComicsDTO.add(this.convertirComicToComicDTO(comic));
			}
			consultaComicEstadoEnumDTO.setComicsActivos(listaComicsDTO);
			
			consultaComicEstadoEnumDTO.setExitoso(true);
			consultaComicEstadoEnumDTO.setMensajeEjecucion("Comics procesados exitosamente");	
		} catch (Exception e) {
			consultaComicEstadoEnumDTO.setExitoso(false);
			consultaComicEstadoEnumDTO.setMensajeEjecucion("Se ha presentado un error tecnico");
		}

		return consultaComicEstadoEnumDTO;
	}
	
	
	/**
	 * Metodo encargado de consultar comics y clasificarlos segun tamaño de nombre
	 * @author Personal
	 * 
	 * @param nombre
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	@Override
	public ConsultaComicTamanioNombreDTO consultarComicTamanioNombre(Short lengthComic) {
		//Se elabora la consulta
		String findAllComic = " SELECT cm.nombre FROM Comic cm ";
		//Se genera el objeto a retornar 
		ConsultaComicTamanioNombreDTO consultaComicTamanioNombreDTO = new ConsultaComicTamanioNombreDTO();
		try {
			//Se ejecuta la consulta
			Query queryFindAllComic = em.createQuery(findAllComic);
			if (lengthComic > 100) {
				throw new Exception("La longitud máxima permitida es de 100 caracteres");
			}
			//Se obtienen todos los comics
			@SuppressWarnings("unchecked")
			List<String> listaComics = queryFindAllComic.getResultList();
			//Se clasifican los nombres comparando su longitud con el parametro lengthComic
			for (int i = 0; i < listaComics.size(); i++) {
				if (listaComics.get(i).length() >= lengthComic) {
					consultaComicTamanioNombreDTO.setNombreMayorIgual(listaComics.get(i));
				}
				else {
					consultaComicTamanioNombreDTO.setNombreMenor(listaComics.get(i));
				}
				
			}
			
			consultaComicTamanioNombreDTO.setExitoso(true);
			consultaComicTamanioNombreDTO.setMensajeEjecucion("Comics procesados exitosamente");	
		} catch (Exception e) {
			consultaComicTamanioNombreDTO.setExitoso(false);
			consultaComicTamanioNombreDTO.setMensajeEjecucion("Se ha presentado un error tecnico");
		}

		return consultaComicTamanioNombreDTO;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public ComicDTO crearComic(ComicDTO comicDTO) throws Exception {
		
		if (comicDTO.getId() != null) {
			throw new Exception("El comic ya existe");
		}
		
		if(comicDTO.getNombre() == null) {
			throw new Exception("El campo nombre es requerido");
		}
		 
		ComicDTO comicDTOResult = null;
		comicDTO.setFechaVenta(LocalDate.now());
		Comic comic = this.convertirComicDTOToComic(comicDTO);
		em.persist(comic);
		comicDTOResult = this.convertirComicToComicDTO(comic);
		comicDTOResult.setExitoso(true);
		comicDTOResult.setMensajeEjecucion("El comic ha sido creado exitosamente");
		return comicDTOResult;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public ComicDTO actualizarComic(ComicDTO comicDTO) throws Exception{
		// TODO Auto-generated method stub
		if(comicDTO.getNombre() == null) {
			throw new Exception("El campo nombre es requerido");
		}
		
		ComicDTO comicDTOResult = new ComicDTO();
		
		try {
			Comic comic = this.convertirComicDTOToComic(comicDTO);
			
			if (comic.getId() == null) {
				em.persist(comic);
			}
			else {
				em.merge(comic);
			}
	
			comicDTOResult = this.convertirComicToComicDTO(comic);
			comicDTOResult.setExitoso(true);
			comicDTOResult.setMensajeEjecucion("El comic ha sido modificado exitosamente");
			
		} catch (Exception e) {
			comicDTOResult.setExitoso(false);
			comicDTOResult.setMensajeEjecucion("Se ha presentado un error tecnico");
		}
		
		return comicDTOResult;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public ResultadoDTO eliminarComic(Long idComic) {
		ResultadoDTO  resultadoDTO = new ResultadoDTO();
		String eliminarComicVarios = " DELETE FROM Comic WHERE id = :idComic";
		try {
			Query queryEliminarVarios = em.createQuery(eliminarComicVarios);
			queryEliminarVarios.setParameter("idComic", idComic);
			queryEliminarVarios.executeUpdate();
			
			resultadoDTO.setExitoso(true);
			resultadoDTO.setMensajeEjecucion("El comic ha sido eliminado exitosamente");
			
		}
		catch (Exception e) {
			resultadoDTO.setExitoso(false);
			resultadoDTO.setMensajeEjecucion("Se ha presentado un error tecnico al eliminar el comic");
		}

		return resultadoDTO;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ListaComicsDTO consultarComics() {
		ListaComicsDTO listaComicsDTO = new ListaComicsDTO();
		List<Comic> listaComics;
		List<ComicDTO> listaComicsDTOSub  = new ArrayList<>();
		try {
			String findAllComic = " SELECT cm FROM Comic cm ";
			Query queryFindAllComic = em.createQuery(findAllComic);
			listaComics = queryFindAllComic.getResultList();
			ComicDTO comicDTO;
			for (int i = 0; i < listaComics.size(); i++) {
				comicDTO = convertirComicToComicDTO( listaComics.get(i));
				comicDTO.setExitoso(true);
				comicDTO.setMensajeEjecucion("Se ejecutó exitosamente la consulta");
				listaComicsDTOSub.add(comicDTO);
			}
			listaComicsDTO.setListaComics(listaComicsDTOSub);
			listaComicsDTO.setExitoso(true);
			listaComicsDTO.setMensajeEjecucion("Lista obtenida exitosamente");
			
		}
		catch (Exception e) {
			listaComicsDTO.setExitoso(false);
			listaComicsDTO.setMensajeEjecucion("Se ha presentado un error tecnico al obtener la lista");
		}
		
		
		
		return listaComicsDTO;
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
