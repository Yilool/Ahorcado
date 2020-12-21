package com.ahorcado.services;

import static org.mockito.Mockito.mock;
import java.util.ArrayList;
import java.util.List;

//import org.hibernate.query.criteria.internal.expression.SearchedCaseExpression.WhenClause;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.ahorcado.model.entity.Partida;

class PartidaServicesTest {
	//Class Under Test
	private PartidaServices psCut;
	
	//Dependencias
	private PartidaServices mockPS;
	private List<Partida> mockListPartidas;
	private StringBuilder mockStr;
	private Partida mockPartida;
	
	@BeforeEach
	private void init() {
		psCut = new PartidaServices();
		mockPS = mock(PartidaServices.class);
	}
	
	@Test
	public void getPartidasTest() {
		mockListPartidas = new ArrayList<Partida>();
		
		Mockito.when(mockPS.getPartidas()).thenReturn(mockListPartidas);
		
		assert(mockPS.getPartidas().equals(mockListPartidas));
	}
	
	@Test
	public void setPartidasTest() {
		mockListPartidas = new ArrayList<Partida>();
		mockPS.setPartidas(mockListPartidas);
		
		Mockito.when(mockPS.getPartidas()).thenReturn(mockListPartidas);
		
		assert(mockPS.getPartidas().equals(mockListPartidas));
	}

	@Test
	public void ocultarPalabraTest() {
		mockStr = new StringBuilder("____");
		Mockito.when(mockPS.ocultarPalabra("Hola")).thenReturn(mockStr.toString());
		
		assert(mockPS.ocultarPalabra("Hola").equals(mockStr.toString()));
	}

	@Test
	public void crearPartidaTest() {
		String mockPalabraAdivinar = "Hola";
		String mockPalabraOculta = "____";
		mockPartida = mock(Partida.class);
		mockListPartidas = new ArrayList<Partida>();
		
		Mockito.when(mockPartida.getPalabraAdivinar()).thenReturn(mockPalabraAdivinar);
		Mockito.when(mockPS.ocultarPalabra(mockPalabraAdivinar)).thenReturn(mockPalabraOculta);
		
		Mockito.when(mockListPartidas.add(mockPartida)).thenReturn(Boolean.TRUE);
		Mockito.when(psCut.crearPartida()).thenReturn(mockPartida);
		
		assert(psCut.crearPartida().equals(mockPartida));
	}
//	public Partida crearPartida() {
//		Partida partida = new Partida();
//		partida.setPalabraOculta(ocultarPalabra(partida.getPalabraAdivinar()));
//
//		this.partidas.add(partida);
//
//		return partida;
//	}
//
//	public Partida existeLetra(String adivinando, Integer idPartida) {
//		StringBuilder str = new StringBuilder();
//
//		Partida p = this.partidas.stream().filter(m -> m.getId() == idPartida).findFirst().orElse(null);
//
//		if (!p.isFinish()) {
//			if (p.getPalabraAdivinar().indexOf(adivinando) > -1) {
//				for (int i = 0; i < p.getPalabraAdivinar().length(); i++) {
//					if (String.valueOf(p.getPalabraAdivinar().charAt(i)).equalsIgnoreCase(adivinando)) {
//						str.append(adivinando);
//					} else {
//						str.append("_");
//					}
//				}
//				p.setPalabraOculta(str.toString());
//
//				if (p.getPalabraOculta().equalsIgnoreCase(p.getPalabraAdivinar())) {
//					p.setFinish(Boolean.TRUE);
//				}
//			} else {
//				p.setIntento(p.getIntento() - 1);
//			}
//		}
//
//		return p;
//	}
}
