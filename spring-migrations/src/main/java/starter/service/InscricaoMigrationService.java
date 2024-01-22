package starter.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import starter.model.destination.InscricaoDestinationEntity;
import starter.model.source.CadastroSourceEntity;
import starter.model.source.InscricaoSourceEntity;
import starter.repository.destination.InscricaoDestinationRepository;
import starter.repository.source.InscricaoSourceRepository;

import java.util.List;

@Service
public class InscricaoMigrationService extends AbstractService {
    @Autowired
    private InscricaoSourceRepository sourceRepository;
    @Autowired
    private InscricaoDestinationRepository destinationRepository;
    private List<InscricaoSourceEntity> listIdGreaterThan(Integer id){
        return sourceRepository.findByIdGreaterThan(id);
    }
    public void save(InscricaoDestinationEntity entity){
        destinationRepository.save(entity);
    }
    public void startMigrationIdGreaterThan(Integer id){
        List<InscricaoSourceEntity> sources = listIdGreaterThan(id);
        sources.forEach(this::transformAndSave);
    }
    private void transformAndSave(InscricaoSourceEntity source){
        destinationRepository.save(transform(source));
    }
    private InscricaoDestinationEntity transform(InscricaoSourceEntity source){
        InscricaoDestinationEntity destination = new InscricaoDestinationEntity();
        BeanUtils.copyProperties(source,destination);
        convertSpringToUppercase(destination,"email");
        System.out.println("transform inscricao " + destination.getId());
        return destination;
    }
}
