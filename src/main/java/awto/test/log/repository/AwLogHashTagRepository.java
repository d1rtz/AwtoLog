package awto.test.log.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import awto.test.log.models.AwLogHashTag;

@Repository
public interface AwLogHashTagRepository extends JpaRepository<AwLogHashTag, Integer>{

    
    public AwLogHashTag findByDescription(String description);
    

}

