package com.ahorcado.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ahorcado.model.entity.Partida;

@Repository(value = "jugadorRepository")
public interface PartidaRepository extends JpaRepository<Partida, Integer>{
	
	/**
	 * Buscar por id
	 * @param id
	 * @return
	 */
	public Partida findPartidaById(Integer id);
}
