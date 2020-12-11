package com.ahorcado.services;

public interface PalabraServicesInterface {
	public String palabraOcultado();
	
	public String introducidoMinuscula(String s);
	
	public int comprobar(String s);
	
	public String sacarPalabraConCaracter(String pOculto, String s);
	
	public String noExiste();
}
