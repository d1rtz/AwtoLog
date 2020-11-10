package awto.test.log.service;

import java.util.List;
import java.util.Optional;

//import awto.test.log.models.AwLogHashTag;
import awto.test.log.models.AwLogLogger;
import awto.test.log.request.AwLogHashTagREQ;

public interface AwLogLoggerService {
//	Iterable<AwLogLogger> getAllLogs();
    Optional<AwLogLogger> findById(int id);
//    Optional<AwLogHashTag> findByHashLog(String hashLog);
    AwLogLogger save(AwLogLogger awLogLogger);
    
    
	AwLogHashTagREQ save(AwLogHashTagREQ awLogHashTagREQ);
	public List<AwLogHashTagREQ> getAllLogs();
	public AwLogHashTagREQ addLog(AwLogHashTagREQ awLogHashTagREQ);
}
