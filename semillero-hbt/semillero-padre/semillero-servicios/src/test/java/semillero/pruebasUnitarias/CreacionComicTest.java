package semillero.pruebasUnitarias;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.enums.EstadoEnum;
import com.hbt.semillero.enums.TematicaEnum;

import junit.framework.Assert;

/**
 * 
 * <b>Descripción:<b> Clase que determina las pruebas unitarias para la creacion de comics
 * <b>Caso de Uso:<b>  crearComics
 * @author Personal
 * @version 1.0
 */
public class CreacionComicTest {
	
	/**
	 * Constante que contendra el log de la clase CreacionComicTest
	 */
	private final static Logger log = Logger.getLogger(CreacionComicTest.class);
	
	//Lista en la que se agregan los 10 comics tanto activos como inactivos
	private List<ComicDTO> listaComics = new ArrayList<>();
	
	@BeforeTest
	public void inicializar() {
		BasicConfigurator.configure(); // Inicializa el logger con una configuracion basica
		log.info(":::::::::::::::::::::::::::: INICIAN PRUEBAS UNITARIAS :::::::::::::::::::::::::::: ");
	}
	
	
	
	/**
	 * Metodo encargado de crear los 10 comics antes de las pruebas unitarias y agregarlos a la 
	 * lista de comics
	 * <b>Caso de Uso</b>
	 * @author Personal
	 * 
	 */
	@BeforeTest 
	public void crearComics() {
		
		//Se crean los comics variando sus estados
		ComicDTO comic = new ComicDTO(1L, "Comic 1","Editorial 1", TematicaEnum.BELICO,"Colección 1",
				Integer.valueOf("10"),BigDecimal.valueOf(100),"Autor 1",false,LocalDate.now(),
				EstadoEnum.ACTIVO,Long.valueOf(100) );
		listaComics.add(comic);
		comic = new ComicDTO(2L, "Comic 2","Editorial 2", TematicaEnum.BELICO,"Colección 2",
				Integer.valueOf("10"),BigDecimal.valueOf(100),"Autor 2",true,LocalDate.now(),
				EstadoEnum.INACTIVO,Long.valueOf(100) );
		
		listaComics.add(comic);
		comic = new ComicDTO(3L, "Comic 3","Editorial 3", TematicaEnum.BELICO,"Colección 3",
				Integer.valueOf("10"),BigDecimal.valueOf(100),"Autor 3",false,LocalDate.now(),
				EstadoEnum.ACTIVO,Long.valueOf(100) );
		
		listaComics.add(comic);
		comic = new ComicDTO(4L, "Comic 4","Editorial 4", TematicaEnum.BELICO,"Colección 4",
				Integer.valueOf("10"),BigDecimal.valueOf(100),"Autor 4",true,LocalDate.now(),
				EstadoEnum.INACTIVO,Long.valueOf(100) );
		
		listaComics.add(comic);
		comic = new ComicDTO(5L, "Comic 5","Editorial 5", TematicaEnum.BELICO,"Colección 5",
				Integer.valueOf("10"),BigDecimal.valueOf(100),"Autor 5",false,LocalDate.now(),
				EstadoEnum.ACTIVO,Long.valueOf(100) );
		
		listaComics.add(comic);
		comic = new ComicDTO(6L,"Comic 6","Editorial 6", TematicaEnum.BELICO,"Colección 6",
				Integer.valueOf("10"),BigDecimal.valueOf(100),"Autor 6",true,LocalDate.now(),
				EstadoEnum.INACTIVO,Long.valueOf(100) );
		
		listaComics.add(comic);
		comic = new ComicDTO(7L,"Comic 7","Editorial 7", TematicaEnum.BELICO,"Colección 7",
				Integer.valueOf("10"),BigDecimal.valueOf(100),"Autor 7",false,LocalDate.now(),
				EstadoEnum.ACTIVO,Long.valueOf(100) );
		
		listaComics.add(comic);
		comic = new ComicDTO(8L,"Comic 8","Editorial 8", TematicaEnum.BELICO,"Colección 8",
				Integer.valueOf("10"),BigDecimal.valueOf(100),"Autor 8",true,LocalDate.now(),
				EstadoEnum.INACTIVO,Long.valueOf(100) );
		
		listaComics.add(comic);
		comic = new ComicDTO(9L,"Comic 9","Editorial 9", TematicaEnum.BELICO,"Colección 9",
				Integer.valueOf("10"),BigDecimal.valueOf(100),"Autor 9",false,LocalDate.now(),
				EstadoEnum.ACTIVO,Long.valueOf(100) );
		
		listaComics.add(comic);
		comic = new ComicDTO(10L,"Comic 10","Editorial 10", TematicaEnum.BELICO,"Colección 10",
				Integer.valueOf("10"),BigDecimal.valueOf(100),"Autor 10",true,LocalDate.now(),
				EstadoEnum.INACTIVO,Long.valueOf(100) );
		listaComics.add(comic);
		
		
	}
	
	
	
	/**
	 * Metodo encargado de crear una lista con los comics activos de la lista de comics
	 * <b>Caso de Uso</b>
	 * @author Personal
	 * 
	 * @return
	 */
	private List<ComicDTO> genComicsActivos(){
		//Se inicializa una nueva lista
		List<ComicDTO> comicsActivos = new ArrayList<>();
		
		for (int i = 0; i < listaComics.size(); i++) {
			//Se agregan los comics cuyo estado sea inactivo
			if(listaComics.get(i).getEstadoEnum() == EstadoEnum.ACTIVO) {
				comicsActivos.add(listaComics.get(i));
			} 
		}
		
		
		return comicsActivos;
	}
	
	/**
	 * Metodo encargado de crear una lista con los comics inactivos de la lista de comics
	 * <b>Caso de Uso</b>
	 * @author Personal
	 * 
	 * @return
	 */
	
	private List<ComicDTO> genComicsInactivos(){
		
		//Se inicializa una nueva lista
		List<ComicDTO> comicsInactivos = new ArrayList<>();
		
		for (int i = 0; i < listaComics.size(); i++) {
			//Se agregan los comics cuyo estado sea inactivo
			if(listaComics.get(i).getEstadoEnum() == EstadoEnum.INACTIVO) {
				comicsInactivos.add(listaComics.get(i));
			} 
		}
		
		
		return comicsInactivos;
	}
	
	/**
	 * Metodo encargado de comprobar que se estén listando solo los comics activos 
	 * <b>Caso de Uso</b>
	 * @author Personal
	 * 
	 */
	@Test 
	public void validarComicsActivos() {
		log.info("Inicia ejecucion del metodo validarComicsActivos()");
		
		//Se obtiene una lista de los comics activos 
		List<ComicDTO> comicsActivos = genComicsActivos();
		
		
//		Assert.assertFalse(condition);
//		Assert.assertTrue(condition);
//		Assert.assertEquals(expected, actual);
//		Assert.assertNotNull(object);
//		Assert.assertNull(object);
		
		
		/*Se comprueba que solo se contengan comics activos comparando 
		 * una lista donde se agregaron los comics uno por uno con la lista
		 * generada con el condicional comparativo para comics activos
		 */
		Assert.assertEquals(agregarManualmenteComicsActivos(), comicsActivos);
		
		
		//Se imprimen en consola todos los comics activos 
		for (int i = 0; i < comicsActivos.size(); i++) {
			System.out.println(comicsActivos.get(i));
		}
		
		log.info("Finaliza la ejecucion del metodo validarComicsActivos()");
	}
	
	
	/**
	 * Metodo encargado de agregar cada comic activo manualmente, según los mismos de la lista
	 * de comics principal con el fin de generar un resultado esperado con el cual comparar
	 * <b>Caso de Uso</b>
	 * @author Personal
	 * 
	 * @return
	 */
	private List<ComicDTO> agregarManualmenteComicsActivos() {
		List<ComicDTO> comicsActivos = new ArrayList<>();
		ComicDTO comic = new ComicDTO(1L, "Comic 1","Editorial 1", TematicaEnum.BELICO,"Colección 1",
				Integer.valueOf("10"),BigDecimal.valueOf(100),"Autor 1",false,LocalDate.now(),
				EstadoEnum.ACTIVO,Long.valueOf(100) );
		comicsActivos.add(comic);
		comic = new ComicDTO(3L, "Comic 3","Editorial 3", TematicaEnum.BELICO,"Colección 3",
				Integer.valueOf("10"),BigDecimal.valueOf(100),"Autor 3",false,LocalDate.now(),
				EstadoEnum.ACTIVO,Long.valueOf(100) );
		
		comicsActivos.add(comic);

		comic = new ComicDTO(5L, "Comic 5","Editorial 5", TematicaEnum.BELICO,"Colección 5",
				Integer.valueOf("10"),BigDecimal.valueOf(100),"Autor 5",false,LocalDate.now(),
				EstadoEnum.ACTIVO,Long.valueOf(100) );
		
		comicsActivos.add(comic);

		comic = new ComicDTO(7L,"Comic 7","Editorial 7", TematicaEnum.BELICO,"Colección 7",
				Integer.valueOf("10"),BigDecimal.valueOf(100),"Autor 7",false,LocalDate.now(),
				EstadoEnum.ACTIVO,Long.valueOf(100) );
		
		comicsActivos.add(comic);

		comic = new ComicDTO(9L,"Comic 9","Editorial 9", TematicaEnum.BELICO,"Colección 9",
				Integer.valueOf("10"),BigDecimal.valueOf(100),"Autor 9",false,LocalDate.now(),
				EstadoEnum.ACTIVO,Long.valueOf(100) );
		
		comicsActivos.add(comic);
		return comicsActivos;
	}
	
	
	/**
	 * Metodo encargado de agregar cada comic inactivo manualmente, según los mismos de la lista
	 * de comics principal con el fin de generar un resultado esperado con el cual comparar
	 * <b>Caso de Uso</b>
	 * @author Personal
	 * 
	 * @return
	 */
	private List<ComicDTO> agregarManualmenteComicsInactivos() {
		List<ComicDTO> comicsInactivos = new ArrayList<>();
		ComicDTO comic = new ComicDTO(2L, "Comic 2","Editorial 2", TematicaEnum.BELICO,"Colección 2",
				Integer.valueOf("10"),BigDecimal.valueOf(100),"Autor 2",true,LocalDate.now(),
				EstadoEnum.INACTIVO,Long.valueOf(100) );
		
		comicsInactivos.add(comic);
	
		comic = new ComicDTO(4L, "Comic 4","Editorial 4", TematicaEnum.BELICO,"Colección 4",
				Integer.valueOf("10"),BigDecimal.valueOf(100),"Autor 4",true,LocalDate.now(),
				EstadoEnum.INACTIVO,Long.valueOf(100) );
		
		comicsInactivos.add(comic);
		
		comic = new ComicDTO(6L,"Comic 6","Editorial 6", TematicaEnum.BELICO,"Colección 6",
				Integer.valueOf("10"),BigDecimal.valueOf(100),"Autor 6",true,LocalDate.now(),
				EstadoEnum.INACTIVO,Long.valueOf(100) );
		
		comicsInactivos.add(comic);

		comic = new ComicDTO(8L,"Comic 8","Editorial 8", TematicaEnum.BELICO,"Colección 8",
				Integer.valueOf("10"),BigDecimal.valueOf(100),"Autor 8",true,LocalDate.now(),
				EstadoEnum.INACTIVO,Long.valueOf(100) );
		
		comicsInactivos.add(comic);

		comic = new ComicDTO(10L,"Comic 10","Editorial 10", TematicaEnum.BELICO,"Colección 10",
				Integer.valueOf("10"),BigDecimal.valueOf(100),"Autor 10",true,LocalDate.now(),
				EstadoEnum.INACTIVO,Long.valueOf(100) );
		comicsInactivos.add(comic);
		return comicsInactivos;
	}
	
	/**
	 * Metodo encargado de contar solo los comics activos dada una lista de comics
	 * <b>Caso de Uso</b>
	 * @author Personal
	 * 
	 * @param listaComics
	 * @return
	 */
	private int contarComicsActivos(List<ComicDTO> listaComics) {
		int comicsActivos=0;
		for (int i = 0; i < listaComics.size(); i++) {
			
			//Se aumenta el contador por cada comic activo 
			if(listaComics.get(i).getEstadoEnum() == EstadoEnum.ACTIVO) {
				comicsActivos++;
			} 
		}
		
		return comicsActivos;
	}
	
	/**
	 * Metodo encargado de obtener los nombres de comics inactivos dada una lista de comics
	 * <b>Caso de Uso</b>
	 * @author Personal
	 * 
	 * @param listaComics
	 * @return
	 */
	
	private String genNombresComicsInactivos(List<ComicDTO> listaComics) {
		String nombresComicsInactivos="";
		for (int i = 0; i < listaComics.size(); i++) {
			//Se aumenta el contador por cada comic inactivo 
			if(listaComics.get(i).getEstadoEnum() == EstadoEnum.INACTIVO) {
				nombresComicsInactivos += listaComics.get(i).getNombre() + " ";
			} 
		}
		
		return nombresComicsInactivos;
	}
	
	


	/**
	 * Metodo encargado de comprobar que solo se listen los comics inactivos
	 * <b>Caso de Uso</b>
	 * @author Personal
	 * 
	 */
	@Test
	public void validarComicsInactivos() {
		log.info("Inicia ejecucion del metodo validarComicsInactivos()");
		
		//Se obtiene la lista de comics inactivos
		List<ComicDTO> comicsInactivos = genComicsInactivos();
		//Se obtiene el tamaño de la lista de comics inactivos
		int tamanioListaTotal = comicsInactivos.size();
		//Se cuentan los comics activos de la lista de inactivos (debería ser 0 esta cantidad)
		int numeroTotalActivos = contarComicsActivos(comicsInactivos);
		//Los comics inactivos serían los restantes de la cantidad total de comics
		int numeroTotalInactivos = tamanioListaTotal - numeroTotalActivos;
		
		//Se obtienen todos los nombres de solo los comics inactivos
		String nombresComicsInacivos = genNombresComicsInactivos(comicsInactivos); 
	
		try {
			//Se espera un resultado true
			Assert.assertTrue(agregarManualmenteComicsInactivos().equals(comicsInactivos));
			
			//La condicion no se asigna negada con el fin de mostrar el mensaje de excepcion 
			if(agregarManualmenteComicsInactivos().equals(comicsInactivos)) {
				log.info("Mensaje de excepción: ");
				throw new Exception("Se ha detectado que de " + tamanioListaTotal + 
						" comics se encontraron que " + numeroTotalActivos + " se encuentran activos y "
						+ numeroTotalInactivos + " inactivos. Los comics inactivos son: "
						+ nombresComicsInacivos);
			}
		} catch (Exception e) {
		System.out.println(e.getMessage());
		}
		
		log.info("Finaliza la ejecucion del metodo validarComicsInactivos()");
	}
	
	@AfterTest
	public void finalizaPruebasUnitarias() {
		log.info(":::::::::::::::::::::::::::: FINALIZAN PRUEBAS UNITARIAS :::::::::::::::::::::::::::: ");
	}
}
