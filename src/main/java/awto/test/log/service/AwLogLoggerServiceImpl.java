package awto.test.log.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import awto.test.log.models.AwLogHashTag;
import awto.test.log.models.AwLogLogger;
import awto.test.log.repository.AwLogHashTagRepository;
import awto.test.log.repository.AwLogLoggerRepository;
import awto.test.log.request.AwLogHashTagREQ;

@Service
public class AwLogLoggerServiceImpl implements AwLogLoggerService {

	@Autowired
	AwLogLoggerRepository awLogLoggerRepository;

	@Autowired
	AwLogHashTagRepository awLogHashTagRepository;

	@Override
	public Optional<AwLogLogger> findById(int id) {
        return awLogLoggerRepository.findById(id);
	}

	@Override
	public AwLogLogger save(AwLogLogger awLogLogger) {
		 return awLogLoggerRepository.save(awLogLogger);
	}

	@Transactional
	@Override
	public AwLogHashTagREQ addLog(AwLogHashTagREQ awLogHashTagREQ) {
		AwLogLogger awLogLogger=new AwLogLogger();
		mapDtoToEntity(awLogHashTagREQ, awLogLogger);
		
		AwLogLogger savedAwLogLogger=awLogLoggerRepository.save(awLogLogger);
		return mapEntityToDto(savedAwLogLogger);
		
	}
	
	@Override
	public List<AwLogHashTagREQ> getAllLogs(){
		 List<AwLogHashTagREQ> awLogHashTagREQs = new ArrayList<>();
	        List<AwLogLogger> awLogHashTags = awLogLoggerRepository.findAll();
	        awLogHashTags.stream().forEach(awLogHashTag -> {
	        	AwLogHashTagREQ AwLogHashTagREQ = mapEntityToDto(awLogHashTag);
	        	awLogHashTagREQs.add(AwLogHashTagREQ);
	        });
	        return awLogHashTagREQs;
	}
	
	
	
	
	private void mapDtoToEntity(AwLogHashTagREQ awLogHashTagREQ, AwLogLogger awLogLogger) {
		awLogLogger.setHost(awLogHashTagREQ.getHost());
		awLogLogger.setCreation_date(new Date());
		awLogLogger.setOrigin(awLogHashTagREQ.getOrigin());
		awLogLogger.setDetails(awLogHashTagREQ.getDetails());
		awLogLogger.setStacktrace(awLogHashTagREQ.getStacktrace());
		
        if (null == awLogLogger.getAwLogHashTags()) {
        	awLogLogger.setAwLogHashTags(new HashSet<>());
        	//TODO RETORNAR EXCEPCION
        }
        awLogHashTagREQ.getHashtags().stream().forEach(description -> {
        	AwLogHashTag awLogHashTag = awLogHashTagRepository.findByDescription(description);
            if (null == awLogHashTag) {
            	awLogHashTag = new AwLogHashTag();
            	awLogHashTag.setAwLogLoggers(new HashSet<>());
                
                //TODO capture Exception
            	//debe crearse
            	awLogHashTag.setDescription(description);
            	awLogHashTagRepository.save(awLogHashTag);
            }
            awLogHashTag.setDescription(description);
            awLogLogger.addHashTag(awLogHashTag);
        });
    }
	
	
	private AwLogHashTagREQ mapEntityToDto(AwLogLogger awLogLogger) {
		AwLogHashTagREQ awLogHashTagREQ = new AwLogHashTagREQ();
		awLogHashTagREQ.setId(awLogLogger.getId());
		awLogHashTagREQ.setHost(awLogLogger.getHost());
		awLogHashTagREQ.setOrigin(awLogLogger.getOrigin());
		awLogHashTagREQ.setDetails(awLogLogger.getDetails());
		awLogHashTagREQ.setStacktrace(awLogLogger.getStacktrace());
		awLogHashTagREQ.setHashtags(awLogLogger.getAwLogHashTags().stream().map(AwLogHashTag::getDescription).collect(Collectors.toSet()));
		return awLogHashTagREQ;
    }

	@Override
	public AwLogHashTagREQ save(AwLogHashTagREQ awLogHashTagREQ) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
