	package awto.test.log.rest;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import awto.test.log.dto.AwLogHashTagDTO;
import awto.test.log.dto.AwLogLoggerDTO;
import awto.test.log.service.AwLogLoggerService;
import javassist.NotFoundException;

/**
 * RESTCONTROLLER para API de logs Awto
 * 
 * @author DIEGO OLAVE
 *
 */
@RestController
public class AwLogControllerREST {
	protected final Logger logger = LogManager.getLogger(getClass().getName());

	@Autowired 
	AwLogLoggerService awLogLoggerService;
	
	/*
	 * Inserta un nuevo log
	 */
	@PostMapping(value = "/logs")
	public ResponseEntity<String> postLogs(@RequestBody AwLogLoggerDTO awLogHashTagREQ) {
		
		try {
			AwLogLoggerDTO result=this.awLogLoggerService.addLog(awLogHashTagREQ);
			logger.info("Log Creado correctamente. ID: "+result.getId());
			return new ResponseEntity<>("Log Creado correctamente. ID: "+result.getId(), HttpStatus.CREATED);
		}catch (IllegalArgumentException e) {
			logger.error("los parametros de entrada son invalidos, e: "+e.getMessage());
			return new ResponseEntity<>("Parametros de entrada invalidos",HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			logger.error("Ha ocurrido un error al insertar el log, e: "+e.getMessage());
			return new ResponseEntity<>("Ha ocurrido un error",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	/*
	 * Obtiene todos los logs
	 */
	@GetMapping(value = "/logs")
	public ResponseEntity<List<AwLogLoggerDTO>> getLogs() {
		try {
			List<AwLogLoggerDTO> result=this.awLogLoggerService.getAllLogs();
			return new ResponseEntity<>(result, HttpStatus.OK);
		}catch (Exception e) {
			logger.error("Ha ocurrido un error al insertar el log, e: "+e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	/*
	 * Obtiene todos los logs según su hashtag.
	 */
	@GetMapping(value = "/logs/{hashtag}")
	public ResponseEntity<List<AwLogLoggerDTO>> getLogsByHashTag(@PathVariable String hashtag) {
		try {
			List<AwLogLoggerDTO> result=this.awLogLoggerService.getLogsByHashTag(hashtag);
			return new ResponseEntity<>(result, HttpStatus.OK);
		}catch (NotFoundException e) {
			logger.error("No se ha encontrado los log segun el hashtag ,e: "+e.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}catch (Exception e) {
			logger.error("Ha ocurrido un error al recuperar los logs , e: "+e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	/*
	 * Obtiene el log según su id.
	 */
	@GetMapping(value = "/log/{logId}")
	public ResponseEntity<AwLogLoggerDTO> getLogsById(@PathVariable Integer logId) {
		try {
			AwLogLoggerDTO result=this.awLogLoggerService.getLogById(logId);
			return new ResponseEntity<>(result, HttpStatus.OK);
		}catch (NotFoundException e) {
			logger.error("No se ha encontrado el log,e: "+e.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}catch (Exception e) {
			logger.error("Ha ocurrido un error al recuperar el log, e: "+e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	/*
	 * Cambia el nombre de un hashtag en particular a través de su id.
	 */
	@PutMapping(value = "/logs")
	public ResponseEntity<String> updHashTag(@RequestBody AwLogHashTagDTO awLogHashTagDTO) {
		
		try {
			this.awLogLoggerService.updHashTag(awLogHashTagDTO);
			return new ResponseEntity<>("Hashtag actualizado correctamente", HttpStatus.OK);
		}catch (IllegalArgumentException e) {
			logger.error("La descripcion del hashtag no puede estar vacia, e: "+e.getMessage());
			return new ResponseEntity<>("La descripcion del hashtag no puede estar vacia",HttpStatus.BAD_REQUEST);
		}catch (IllegalAccessException e) {
			logger.error("No se puede repetir la descripcion de los hashtag, e: "+e.getMessage());
			return new ResponseEntity<>("La descripcion del hashtag ya existe en otro hashtag",HttpStatus.BAD_REQUEST);
		}catch (NotFoundException e) {
			logger.error("No se ha encontrado el hashtag, e: "+e.getMessage());
			return new ResponseEntity<>("El Hashtag ID "+awLogHashTagDTO.getId()+" no existe",HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			logger.error("Ha ocurrido un error al insertar el log, e: "+e.getMessage());
			return new ResponseEntity<>("Ha ocurrido un error",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
}
