package starter.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import starter.model.destination.CadastroDestinationEntity;
import starter.model.source.CadastroSourceEntity;
import starter.model.source.InscricaoSourceEntity;
import starter.repository.destination.CadastroDestinationRepository;
import starter.repository.source.CadastroSourceRepository;

import java.util.List;

@Service
public class CadastroMigrationService extends AbstractService {
    @Autowired
    private CadastroSourceRepository sourceRepository;
    @Autowired
    private CadastroDestinationRepository destinationRepository;
    private List<CadastroSourceEntity> listIdGreaterThan(Integer id){
        return sourceRepository.findByIdGreaterThan(id);
    }
    public void save(CadastroDestinationEntity entity){
        destinationRepository.save(entity);
    }
    public void startMigrationIdGreaterThan(Integer id){
        List<CadastroSourceEntity> sources = listIdGreaterThan(id);
        sources.forEach(this::transformAndSave);
    }
    private void transformAndSave(CadastroSourceEntity source){
        destinationRepository.save(transform(source));
    }
    private CadastroDestinationEntity transform(CadastroSourceEntity source){
        CadastroDestinationEntity destination = new CadastroDestinationEntity();
        BeanUtils.copyProperties(source,destination);
        convertSpringToUppercase(destination,"email");
        System.out.println("transform cadastro " + destination.getId());
        return destination;
    }
}
