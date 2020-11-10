	package awto.test.log.rest;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import awto.test.log.request.AwLogHashTagREQ;
import awto.test.log.service.AwLogLoggerService;


@RestController
public class AwLogController {
	protected final Logger logger = LogManager.getLogger(getClass().getName());

	@Autowired 
	AwLogLoggerService awLogLoggerService;
	
	
	@PostMapping(value = "/logs")
	public ResponseEntity<AwLogHashTagREQ> postLogs(@RequestBody AwLogHashTagREQ awLogHashTagREQ) {
		logger.info("Se agrega log, "+awLogHashTagREQ);
		
		try {
			AwLogHashTagREQ result=this.awLogLoggerService.addLog(awLogHashTagREQ);
			return new ResponseEntity<>(result, HttpStatus.CREATED);
		}catch (Exception e) {
			System.out.println("fail agregar");
			logger.error("Ha ocurrido un error al insertar el log, e: "+e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping(value = "/logs")
	public ResponseEntity<List<AwLogHashTagREQ>> getLogs() {
		logger.info("Se listan log, ");
		
		try {
			List<AwLogHashTagREQ> result=this.awLogLoggerService.getAllLogs();
			return new ResponseEntity<>(result, HttpStatus.CREATED);
		}catch (Exception e) {
			System.out.println("fail agregar");
			logger.error("Ha ocurrido un error al insertar el log, e: "+e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
}
