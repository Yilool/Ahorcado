package com.ahorcado.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import com.ahorcado.model.entity.Jugador;
import com.ahorcado.model.entity.Partida;
import com.ahorcado.model.repository.JugadorRepository;
import com.ahorcado.model.repository.PartidaRepository;
=======
import com.ahorcado.model.entity.Partida;
>>>>>>> fa85ed68fc0987a009ee8923a59425a659c39d1f

@Service
public class PartidaServices {

	/** Repositorio de la Partida **/
	@Autowired
	private PartidaRepository partidaRepository;
	
	/** Repositorio del jugador **/
	@Autowired
	private JugadorRepository jugadorRepository;

	/** Caracter que sustituye a otra letra **/
	private final String ocultar = ".";
	
	/**
	 * Constructor del servicio de partidas para los text unitarios
	 * @param partidaRepository
	 * @param jugadorRepository
	 */
	public PartidaServices(PartidaRepository partidaRepository, JugadorRepository jugadorRepository) {
		this.partidaRepository = partidaRepository;
		this.jugadorRepository = jugadorRepository;
	}

	/**
	 * Ocultar una palabra
	 * @param palabra
	 * @return String
	 */
	public String ocultarPalabra(String palabra) {
		StringBuilder str = new StringBuilder();

		for (int i = 0; i < palabra.length(); i++) {
			str.append(ocultar);
		}

		return str.toString();
	}

	/**
	 * Crear la partida
	 * @return Partida
	 */
	public Partida crearPartida(Jugador jugador) {
		Partida partida = null;
		Jugador existeEnBase = jugadorRepository.findJugadorById(jugador.getId());
		
		
		if (existeEnBase != null) {
			partida = new Partida(existeEnBase);
			partida.setPalabraOculta(this.ocultarPalabra(partida.getPalabraAdivinar()));
			partidaRepository.save(partida);
		}

		return partida;
	}
	
	/**
	 * Obtener partida por id
	 * @param id
	 * @return Partida
	 */
	public Partida getPartidaById(Integer id) {
		return partidaRepository.findPartidaById(id);
	}

	public Partida existeLetra(Integer idPartida, String jugador, String ip, String letra) throws Exception{
		StringBuilder str = new StringBuilder();

		Partida p = partidaRepository.findPartidaById(idPartida);
		Jugador j = jugadorRepository.findJugadorByNombre(jugador);
		String l = letra.toLowerCase();
		
		excepciones(p, j, ip, l);
		
		if (poderSeguirJugandoPartida(p)) {
			if (p.getLetrasUtilizado().contains(adivinando.toLowerCase())) {
				p.setMsg("Letra ya utilizado.");
			} else {
				if (p.getPalabraAdivinar().indexOf(adivinando) > -1) {
					for (int i = 0; i < p.getPalabraAdivinar().length(); i++) {
						if (obtenerLetraDeCadena(p.getPalabraAdivinar(), i).equalsIgnoreCase(adivinando)) {
							str.append(adivinando.toLowerCase());
						} else if (!obtenerLetraDeCadena(p.getPalabraOculta(), i).equalsIgnoreCase("*")) {
							str.append(String.valueOf(p.getPalabraOculta().charAt(i)));
						} else {
							str.append("?");
						}
					}
					
					p.setPalabraOculta(str.toString());

					if (p.getPalabraOculta().equalsIgnoreCase(p.getPalabraAdivinar())) {
						p.setFinish(Boolean.TRUE);
						p.setMsg("Partida ganada.");
					}
				} else {
					p.setIntento(p.getIntento() - 1);
					
					if (p.getIntento() < 1) {
						p.setFinish(Boolean.TRUE);
						p.setMsg("Partida perdida.");
					}
				}
				p.getLetrasUtilizado().add(adivinando.toLowerCase());
			}
		} else {
			p.setMsg("Partida ya terminada.");
		}

		return p;
	}
	
	/**
	 * Comprueba el estado de la partida y la cantidad de intentos
	 * @param p
	 * @return Boolean
	 */
	protected Boolean poderSeguirJugandoPartida(Partida p) {
		boolean seguir = Boolean.FALSE;
		
		if (!p.isFinish() && p.getIntento() > 0) {
			seguir = Boolean.TRUE;
		}
		
		return seguir;
	}

	/**
	 * Buscar letra mediante la posicion
	 * @param cadena
	 * @param posicion
	 * @return String
	 */
	protected String obtenerLetraDeCadenaPorPosicion(String cadena, int posicion) {
		return String.valueOf(cadena.charAt(posicion));
	}
	
	/**
	 * Comprobaciones para excepciones
	 * @param partida
	 * @param jugador
	 * @param ip
	 * @param letra
	 * @throws Exception
	 */
	protected void excepciones(Partida partida, Jugador jugador, String ip, String letra) throws Exception{
		if (partida == null) {
			throw new Exception("ERROR_PARTIDA_1: Partida no existe");
		}
		if (jugador == null) {
			throw new Exception("ERROR_JUGADOR_1: Jugador no existe");
		}
		if (!jugador.getIp().equals(ip)) {
			throw new Exception("ERROR_JUGADOR_2: IP "+ ip +" no pertenece a jugador "+ jugador.getIp());
		}
		if (!partida.getJugador().equals(jugador)) {
			throw new Exception("ERROR_PARTIDA_2: Jugador "+ jugador.getNombre() +" no pertenece a la partida"+ partida.getId());
		}
		if (letra.length() > 1 || letra.length() < 1) {
			throw new Exception("ERROR_PARTIDA_3: La letra "+ letra +" no es una letra");
		}
		if (partida.getLetrasUtilizado().contains(letra)) {
			throw new Exception("ERROR_PARTIDA_4: La letra "+ letra +" ya ha sido utilizado");
		}
	}
}
