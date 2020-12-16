package com.ahorcado.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class Partida {
	private AtomicInteger identificador = new AtomicInteger(0);
	private Integer id;
	private String palabraAdivinar;
	private String palabraOcultado;
	private Set<String> letrasUtilizado;
	private int intento;
	private boolean finish;
	private String msg;

	public Partida() {
		super();
		this.id = identificador.addAndGet(1);
		this.palabraAdivinar = "iesjacaranda";
		this.intento = 7;
		this.finish = Boolean.FALSE;
		this.letrasUtilizado = new HashSet<>();
		this.msg = "Introduzca una letra para adivinar la palabra!!";
	}

	public String getPalabraAdivinar() {
		return palabraAdivinar;
	}

	public void setPalabraAdivinar(String palabraAdivinar) {
		this.palabraAdivinar = palabraAdivinar;
	}

	public String getPalabraOculta() {
		return palabraOcultado;
	}

	public void setPalabraOculta(String pOculta) {
		this.palabraOcultado = pOculta;
	}

	public int getIntento() {
		return intento;
	}

	public void setIntento(int intento) {
		this.intento = intento;
	}

	public boolean isFinish() {
		return finish;
	}

	public void setFinish(boolean finish) {
		this.finish = finish;
	}

	public Set<String> getLetrasUtilizado() {
		return letrasUtilizado;
	}

	public void setLetrasUtilizado(Set<String> letrasUtilizado) {
		this.letrasUtilizado = letrasUtilizado;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + intento;
		result = prime * result + ((palabraOcultado == null) ? 0 : palabraOcultado.hashCode());
		result = prime * result + ((palabraAdivinar == null) ? 0 : palabraAdivinar.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Partida other = (Partida) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (intento != other.intento)
			return false;
		if (palabraOcultado == null) {
			if (other.palabraOcultado != null)
				return false;
		} else if (!palabraOcultado.equals(other.palabraOcultado))
			return false;
		if (palabraAdivinar == null) {
			if (other.palabraAdivinar != null)
				return false;
		} else if (!palabraAdivinar.equals(other.palabraAdivinar))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Partida "+id+"[Ocultado: " + palabraOcultado + " | Vidas: " + intento + "]";
	}
}
