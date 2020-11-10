package awto.test.log.service;

import java.util.List;

import awto.test.log.dto.AwLogHashTagDTO;
import awto.test.log.dto.AwLogLoggerDTO;
import javassist.NotFoundException;

public interface AwLogLoggerService {
    

	/**
	 * Recupera listado de todos loslogs y sus hashtags
	 * @param Nothing
	 * @return List<AwLogHashTagREQ>
	 */
	public List<AwLogLoggerDTO> getAllLogs();
	
	/**
	 * Agrega nuevo log. Si no contiene hashtags, captura IllegalArgumentException
	 * @param awLogHashTagREQ
	 * @return AwLogHashTagREQ
	 * @exception IllegalArgumentException
	 */
	public AwLogLoggerDTO addLog(AwLogLoggerDTO awLogHashTagREQ);
	
	/**
	 * Recupera listado de logs segun el hashtag entrante
	 * @param hashtag
	 * @return List<AwLogHashTagREQ>
	 * @throws NotFoundException 
	 */
	public List<AwLogLoggerDTO> getLogsByHashTag(String hashtag) throws NotFoundException;
	
	/**
	 * Recupera el log segun el id
	 * 
	 * @param logId
	 * @return AwLogHashTagREQ
	 * @throws NotFoundException 
	 */
	public AwLogLoggerDTO getLogById(Integer logId) throws NotFoundException;

	
	/**
	 * Actualiza el hashtag segun el id. En caso de estar repetido el nombre/description captura IllegalArgumentException
	 * @param awLogHashTagDTO
	 * @return AwLogHashTagDTO
	 * @throws Exception 
	 * @exception IllegalArgumentException
	 */
	public AwLogHashTagDTO updHashTag(AwLogHashTagDTO awLogHashTagDTO) throws Exception;
}
