package starter.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import starter.model.destination.InscricaoAlunoDestinationEntity;
import starter.model.source.CadastroSourceEntity;
import starter.model.source.InscricaoAlunoSourceEntity;
import starter.model.source.InscricaoSourceEntity;
import starter.repository.destination.InscricaoAlunoDestinationRepository;
import starter.repository.source.InscricaoAlunoSourceRepository;

import java.util.List;

@Service
public class InscricaoAlunoMigrationService extends AbstractService {
    @Autowired
    private InscricaoAlunoSourceRepository sourceRepository;
    @Autowired
    private InscricaoAlunoDestinationRepository destinationRepository;
    private List<InscricaoAlunoSourceEntity> listIdGreaterThan(Integer id){
        return sourceRepository.findByIdGreaterThan(id);
    }
    public void save(InscricaoAlunoDestinationEntity entity){
        destinationRepository.save(entity);
    }
    public void startMigrationIdGreaterThan(Integer id){
        List<InscricaoAlunoSourceEntity> sources = listIdGreaterThan(id);
        sources.forEach(this::transformAndSave);
    }
    private void transformAndSave(InscricaoAlunoSourceEntity source){
        destinationRepository.save(transform(source));
    }
    private InscricaoAlunoDestinationEntity transform(InscricaoAlunoSourceEntity source){
        InscricaoAlunoDestinationEntity destination = new InscricaoAlunoDestinationEntity();
        BeanUtils.copyProperties(source,destination);
        convertSpringToUppercase(destination,"email");
        System.out.println("transform inscricao aluno " + destination.getId());
        return destination;
    }
}
