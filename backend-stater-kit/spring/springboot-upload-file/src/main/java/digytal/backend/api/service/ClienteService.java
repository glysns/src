package digytal.backend.api.service;

import digytal.backend.api.model.cliente.ClienteEntity;
import digytal.backend.api.model.cliente.ClienteRequest;
import digytal.backend.api.model.cliente.ClienteResponse;
import digytal.backend.api.repository.ClienteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;
    //PrePersist
    public void save(ClienteRequest request){
        //chamando o método da propria classe para cuidar em persistir no banco de dados
        this.persist(null, request);
    }
    //PreUpdate
    public void update(Integer id, ClienteRequest request){
        //chamando o método da propria classe para cuidar em persistir no banco de dados
        this.persist(id, request);
    }
    public void delete(Integer id){
       ClienteEntity entity = findById(id);
       repository.delete(entity);
    }
    public ClienteResponse getOne(Integer id){
        ClienteEntity entity = findById(id);
        ClienteResponse response = new ClienteResponse();
        //este método copia todos os atributos da entity para o response;
        BeanUtils.copyProperties(entity,response);
        return response;
    }
    public List<ClienteResponse> listAll(){
        List<ClienteEntity> entitys = repository.findAll();
        List<ClienteResponse> list = new ArrayList<>();

        for(ClienteEntity entity:entitys){
            ClienteResponse response = new ClienteResponse();
            BeanUtils.copyProperties(entity,response);
            list.add(response);
        }

        return list;
    }
    private ClienteEntity findById(Integer id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Não foi possível localizar o cliente com o ID " + id));
    }

    @Transactional
    public Integer persist(Integer id, ClienteRequest request){
        ClienteEntity entity = null;
        if(id!=null){
            entity = this.findById(id); //este noo método tem o mesmo do repositório mas observe a lógica aplicada
        }else
            entity = new ClienteEntity();

        //este método copia todos os atributos do request para o entity
        BeanUtils.copyProperties(request,entity);

        return repository.save(entity).getId();
    }

    @Autowired
    private FileUploadService fileUploadService;

    public void changePhotoPath(Integer id, MultipartFile uploadFile){
        try {
            File path = fileUploadService.saveOnDisk(uploadFile);
            ClienteEntity entity = repository.findById(id).orElse(null);
            entity.setPhotoPath(path.getAbsolutePath());
            repository.save(entity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void changeEntityPhoto(Integer id, MultipartFile uploadFile){
        try {
            ClienteEntity entity = repository.findById(id).orElse(null);
            entity.setPhotoByte(uploadFile.getBytes());
            repository.save(entity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
