package com.ahorcado.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahorcado.model.entity.Jugador;
import com.ahorcado.model.repository.JugadorRepository;

@Service
public class JugadorServices {

	/**	Repositorio del jugador **/
	@Autowired
	private JugadorRepository jugadorRepository;

	/**
	 * Constructor del servicio de jugador para los test unitarios
	 * @param jugadorRepository
	 */
	public JugadorServices(JugadorRepository jugadorRepository) {
		this.jugadorRepository = jugadorRepository;
	}
	
	/**
	 * Crear Jugador
	 * @param jugador
	 * @return
	 */
	public Jugador postJugador(Jugador jugador) {
		Jugador existJugadorEnBase = jugadorRepository.findJugadorByNombre(jugador.getNombre());
		
		if (existJugadorEnBase == null) {
			jugadorRepository.save(jugador);
			existJugadorEnBase = jugador;
		}
		
		return existJugadorEnBase;
	}
	
	/**
	 * Obtener jugador por id
	 * @param id
	 * @return
	 */
	public Jugador getJugadorById(Integer id) {
		return jugadorRepository.findJugadorById(id);
	}
	
	/**
	 * Obtener jugador por nombre
	 * @param nombre
	 * @return
	 */
	public Jugador getJugadorByNombre(String nombre) {
		return jugadorRepository.findJugadorByNombre(nombre);
	}
}
