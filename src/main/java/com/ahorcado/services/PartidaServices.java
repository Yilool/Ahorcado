package com.ahorcado.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ahorcado.entity.Partida;

@Service
public class PartidaServices {
	List<Partida> partidas = new ArrayList<Partida>();

	public List<Partida> getPartidas() {
		return partidas;
	}


	public void setPartidas(List<Partida> partidas) {
		this.partidas = partidas;
	}

	private String ocultarPalabra(String palabra) {
		StringBuilder str = new StringBuilder();

		for (int i = 0; i < palabra.length(); i++) {
			str.append("_");
		}

		return str.toString();
	}


	public Partida crearPartida() {
		Partida partida = new Partida();
		partida.setPalabraOculta(ocultarPalabra(partida.getPalabraAdivinar()));

		this.partidas.add(partida);

		return partida;
	}

	public Partida existeLetra(String adivinando, Integer idPartida) {
		StringBuilder str = new StringBuilder();

		Partida p = this.partidas.stream().filter(m -> m.getId() == idPartida).findFirst().orElse(null);

		if (!p.isFinish()) {
			if (p.getPalabraAdivinar().indexOf(adivinando) > -1) {
				for (int i = 0; i < p.getPalabraAdivinar().length(); i++) {
					if (String.valueOf(p.getPalabraAdivinar().charAt(i)).equalsIgnoreCase(adivinando)) {
						str.append(adivinando);
					} else {
						str.append("_");
					}
				}
				p.setPalabraOculta(str.toString());

				if (p.getPalabraOculta().equalsIgnoreCase(p.getPalabraAdivinar())) {
					p.setFinish(Boolean.TRUE);
				}
			} else {
				p.setIntento(p.getIntento() - 1);
			}
		}

		return p;
	}
}
