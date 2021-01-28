package com.ahorcado.model.entity;

import java.util.concurrent.atomic.AtomicInteger;

public class Usuario {
	private AtomicInteger identificador = new AtomicInteger(0);
	private Integer id;
	private String ip;
	
	public Usuario(String ip) {
		super();
		this.id = identificador.addAndGet(1);
		this.ip = ip;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Integer getId() {
		return id;
	}
}
