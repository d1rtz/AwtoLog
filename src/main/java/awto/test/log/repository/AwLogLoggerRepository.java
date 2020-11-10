package awto.test.log.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import awto.test.log.models.AwLogLogger;


@Repository
public interface AwLogLoggerRepository extends JpaRepository<AwLogLogger, Integer>{

    

}

