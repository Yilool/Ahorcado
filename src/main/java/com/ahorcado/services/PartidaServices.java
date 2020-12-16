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

	public String ocultarPalabra(String palabra) {
		StringBuilder str = new StringBuilder();

		for (int i = 0; i < palabra.length(); i++) {
			str.append("? ");
		}

		return str.toString();
	}

	private String obtenerLetraDeCadena(String cadena, int posicion) {
		return String.valueOf(cadena.charAt(posicion));
	}

	public Partida crearPartida() {
		Partida partida = new Partida();
		partida.setPalabraOculta(this.ocultarPalabra(partida.getPalabraAdivinar()));

		this.partidas.add(partida);

		return partida;
	}
	
	public Partida obtenerPartida(Integer id) {
		return this.partidas.stream().filter(m -> m.getId() == id).findFirst().orElse(null);
	}

	public Partida existeLetra(String adivinando, Integer idPartida) {
		StringBuilder str = new StringBuilder();

		Partida p = obtenerPartida(idPartida);

		if (!p.isFinish()) {
			if (p.getPalabraAdivinar().indexOf(adivinando) > -1) {
				for (int i = 0; i < p.getPalabraAdivinar().length(); i++) {
					if (obtenerLetraDeCadena(p.getPalabraAdivinar(), i).equalsIgnoreCase(adivinando)) {
						str.append(adivinando.toLowerCase() + " ");
					} else if (!obtenerLetraDeCadena(p.getPalabraOculta(), i).equalsIgnoreCase("*")) {
						str.append(String.valueOf(p.getPalabraOculta().charAt(i)) + " ");
					} else {
						str.append("? ");
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
		} else {
			p.setMsg("Partida ya terminada.");
		}

		return p;
	}
}
