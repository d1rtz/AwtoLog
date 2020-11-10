package awto.test.log.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import awto.test.log.dto.AwLogHashTagDTO;
import awto.test.log.dto.AwLogLoggerDTO;
import awto.test.log.models.AwLogHashTag;
import awto.test.log.models.AwLogLogger;
import awto.test.log.repository.AwLogHashTagRepository;
import awto.test.log.repository.AwLogLoggerRepository;
import javassist.NotFoundException;

/**
 * Implementacion de servicios para AwtoLog
 * 
 * @author DIEGO OLAVE
 *
 */
@Service
public class AwLogLoggerServiceImpl implements AwLogLoggerService {

	protected final Logger logger = LogManager.getLogger(getClass().getName());

	@Autowired
	AwLogLoggerRepository awLogLoggerRepository;

	@Autowired
	AwLogHashTagRepository awLogHashTagRepository;

	/**
	 * Agrega nuevo log. Si no contiene hashtags, captura IllegalArgumentException
	 * 
	 * @param awLogHashTagDTO
	 * @return AwLogHashTagDTO
	 * @exception IllegalArgumentException
	 */
	@Transactional
	@Override
	public AwLogLoggerDTO addLog(AwLogLoggerDTO awLogHashTagDTO) {
		logger.info("Se agrega log, " + awLogHashTagDTO);

		// Se realizan validaciones
		if (null == awLogHashTagDTO) {
			throw new IllegalArgumentException("Parametro no puede ser nulo");
		}
		if (null == awLogHashTagDTO.getHashtags() || awLogHashTagDTO.getHashtags().isEmpty()) {
			throw new IllegalArgumentException("Los hashtags no pueden estar vacio en el log");
		}
		// Se recupera o crea los hashtags
		AwLogLogger awLogLogger = new AwLogLogger();
		mapDtoToEntity(awLogHashTagDTO, awLogLogger);

		// Se carga el log
		AwLogLogger savedAwLogLogger = awLogLoggerRepository.save(awLogLogger);
		return mapEntityToDto(savedAwLogLogger);

	}

	/**
	 * Recupera listado de todos los logs y sus hashtags
	 * 
	 * @param Nothing
	 * @return List<AwLogHashTagDTO>
	 */
	@Override
	public List<AwLogLoggerDTO> getAllLogs() {
		logger.debug("Se recuperan todos los logs");
		List<AwLogLoggerDTO> awLogHashTagDTOs = new ArrayList<>();
		List<AwLogLogger> awLogHashTags = awLogLoggerRepository.findAll();
		//Se itera los log encontrados segun el hashset para ser retornados con la estructura de AwLogLoggerHashTagDTO
		awLogHashTags.stream().forEach(awLogHashTag -> {
			AwLogLoggerDTO awLogHashTagDTO = mapEntityToDto(awLogHashTag);
			awLogHashTagDTOs.add(awLogHashTagDTO);
		});
		return awLogHashTagDTOs;
	}
	
	
	/**
	 * Recupera el log segun el id
	 * 
	 * @param logId
	 * @return AwLogHashTagDTO
	 * @throws NotFoundException 
	 */
	@Override
	public AwLogLoggerDTO getLogById(Integer logId) throws NotFoundException {
		logger.debug("Se recupera el log segun el ID "+logId);
		
		if(!awLogLoggerRepository.existsById(logId)) {
			throw new NotFoundException("No se ha encontrado el log segun el ID: "+logId);
		}
		AwLogLogger awLogHashTag = awLogLoggerRepository.getOne(logId);
		//construye el log para ser retornados con la estructura de AwLogLoggerHashTagDTO
		AwLogLoggerDTO awLogHashTagDTO = mapEntityToDto(awLogHashTag);
		return awLogHashTagDTO;
	}
	

	/**
	 * Recupera listado de logs segun el hashtag entrante
	 * 
	 * @param hashtag
	 * @return List<AwLogHashTagDTO>
	 * @throws NotFoundException 
	 */
	@Override
	public List<AwLogLoggerDTO> getLogsByHashTag(String hashtag) throws NotFoundException {
		logger.debug("Se recuperan todos los logs que contengan el hashtag: " + hashtag);
		List<AwLogLoggerDTO> awLogHashTagDTOs = new ArrayList<>();
		AwLogHashTag awLogHashTag = awLogHashTagRepository.findByDescription(hashtag);

		if(null!=awLogHashTag) {
			//Se itera los log encontrados segun el hashset para ser retornados con la estructura de AwLogLoggerHashTagDTO
			awLogHashTag.getAwLogLoggers().stream().forEach(awLogHashTag2 -> {
				AwLogLoggerDTO awLogHashTagDTO = mapEntityToDto(awLogHashTag2);
				awLogHashTagDTOs.add(awLogHashTagDTO);
			});	
			return awLogHashTagDTOs;
		}else {
			throw new NotFoundException("No se ha encontrado los logs segun el hashtag: "+hashtag);

		}
		
	}

	
	/**
	 * Actualiza el hashtag segun el id. En caso de estar repetido el nombre/description captura IllegalArgumentException
	 * @param awLogHashTagDTO
	 * @return AwLogHashTagDTO
	 * @throws Exception 
	 * @exception IllegalArgumentException
	 */
	@Transactional
	@Override
	public AwLogHashTagDTO updHashTag(AwLogHashTagDTO awLogHashTagDTO) throws Exception {
		logger.info("Se actualiza el hashtag, " + awLogHashTagDTO);

		// Se realizan validaciones
		if (null == awLogHashTagDTO || null==awLogHashTagDTO.getId()) {
			throw new Exception("el ID del hashtag a actualizar no puede ser nulo");
		}
		if (null==awLogHashTagDTO.getDescription() || awLogHashTagDTO.getDescription().isEmpty()) {
			throw new IllegalArgumentException("el nombre/description del hashtag a actualizar no puede ser nulo");
		}
		AwLogHashTag awLogHashTagFind = awLogHashTagRepository.findByDescription(awLogHashTagDTO.getDescription());

		if(null!=awLogHashTagFind) {
			throw new IllegalAccessException("El nombre del hashtag ya existe");
		}else {
			if(!awLogHashTagRepository.existsById(awLogHashTagDTO.getId())) {
				throw new NotFoundException("No se ha encontrado el hashtag ID: "+awLogHashTagDTO.getId());
			}else {
				AwLogHashTag awLogHashTag=new AwLogHashTag();
				awLogHashTag.setId(awLogHashTagDTO.getId());
				awLogHashTag.setDescription(awLogHashTagDTO.getDescription());
				awLogHashTagRepository.save(awLogHashTag);
				return awLogHashTagDTO;	
			}
		}

	}
	
	
	
	/**
	 * Transforma la entidad de AwLogHashTagDTO a AwLogLogger y recupera los
	 * hashtags de la base de datos, en caso de no existir, se crean,
	 * 
	 * @param awLogHashTagDTO
	 * @Param awLogLogger
	 * @return Nothing
	 */
	private void mapDtoToEntity(AwLogLoggerDTO awLogHashTagDTO, AwLogLogger awLogLogger) {
		awLogLogger.setHost(awLogHashTagDTO.getHost());
		awLogLogger.setCreationDate(new Date());
		awLogLogger.setOrigin(awLogHashTagDTO.getOrigin());
		awLogLogger.setDetails(awLogHashTagDTO.getDetails());
		awLogLogger.setStacktrace(awLogHashTagDTO.getStacktrace());

		awLogHashTagDTO.getHashtags().stream().forEach(description -> {
			AwLogHashTag awLogHashTag = awLogHashTagRepository.findByDescription(description);
			if (null == awLogHashTag) {
				logger.info("El hashtag no existe, se creara el registro");
				awLogHashTag = new AwLogHashTag();
				awLogHashTag.setAwLogLoggers(new HashSet<>());
				awLogHashTag.setDescription(description);
				awLogHashTagRepository.save(awLogHashTag);
			}
			awLogHashTag.setDescription(description);
			awLogLogger.addHashTag(awLogHashTag);
		});
	}

	/**
	 * Transforma la entidad AwLogLogger a AwLogHashTagDTO
	 * 
	 * @Param awLogLogger
	 * @return AwLogHashTagDTO
	 */
	private AwLogLoggerDTO mapEntityToDto(AwLogLogger awLogLogger) {
		AwLogLoggerDTO awLogHashTagDTO = new AwLogLoggerDTO();
		awLogHashTagDTO.setId(awLogLogger.getId());
		awLogHashTagDTO.setHost(awLogLogger.getHost());
		awLogHashTagDTO.setOrigin(awLogLogger.getOrigin());
		awLogHashTagDTO.setDetails(awLogLogger.getDetails());
		awLogHashTagDTO.setStacktrace(awLogLogger.getStacktrace());
		awLogHashTagDTO.setHashtags(
				awLogLogger.getAwLogHashTags().stream().map(AwLogHashTag::getDescription).collect(Collectors.toSet()));
		return awLogHashTagDTO;
	}
}
