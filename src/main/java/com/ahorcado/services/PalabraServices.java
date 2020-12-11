package com.ahorcado.services;

import org.springframework.stereotype.Service;

import com.ahorcado.entity.Palabra;
import com.sun.tools.javac.code.Attribute.Array;

@Service
public class PalabraServices implements PalabraServicesInterface{
	Palabra p = new Palabra();
	int intento = 7;
	
	@Override
	public String palabraOcultado() {
		StringBuilder str = new StringBuilder();
		
		for (int i = 0; i < p.getPalabra().length(); i++) {
			str.append("_ ");
		}
		
		return str.toString();
	}

	@Override
	public int comprobar(String s) {
		int num = -1;
		
		if (s.length() > 1) {
			num = p.getPalabra().indexOf(s);
		} else if (s.length() == p.getPalabra().length()) {
			num = p.getPalabra().compareTo(s);
		}
		
		return num;
	}

	@Override
	public String sacarPalabraConCaracter(String pOculto, String s) {
		int[] num;
		StringBuilder str = new StringBuilder();
		
		for (int i = 0; i < p.getPalabra().length(); i++) {
			if (s.equalsIgnoreCase(Character.toString(p.getPalabra().charAt(i)))) {
				str.append(s);
			} else  {
				str.append("_");
			}
		}
		
		return str.toString();
	}

	@Override
	public String noExiste() {
		return null;
	}

	@Override
	public String introducidoMinuscula(String s) {
		return s.toLowerCase();
	}	
}
