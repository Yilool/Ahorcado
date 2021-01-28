package com.ahorcado.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ahorcado.model.entity.Jugador;

@Repository(value = "jugadorRepository")
public interface JugadorRepository extends JpaRepository<Jugador, Integer>{
	/**
	 * Buscar por id
	 * @param id
	 * @return
	 */
	public Jugador findJugadorById(Integer id);
	
	/**
	 * Buscar por nombre
	 * @param nombre
	 * @return
	 */
	public Jugador findJugadorByNombre(String nombre);
}
