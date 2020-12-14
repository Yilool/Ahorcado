package com.ahorcado.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ahorcado.services.PartidaServices;

@RestController
@RequestMapping(path = "partida")
public class PartidaController {
	@Autowired
	PartidaServices ps;

	@PostMapping(path = "")
	public ResponseEntity<?> postPartida() {
		return ResponseEntity.status(HttpStatus.OK).body(ps.crearPartida());
	}

	@PutMapping(path = "{idPartida}")
	public ResponseEntity<?> putPartida(@PathVariable int idPartida,@RequestParam String adivinando) {
		ResponseEntity<?> res;
		
		if (adivinando.length() > 1) {
			res = ResponseEntity.status(HttpStatus.OK).body("Solo de uno en uno");
		} else {
			res = ResponseEntity.status(HttpStatus.OK).body(ps.existeLetra(adivinando, idPartida));
		}
		
		return res;
	}
}
