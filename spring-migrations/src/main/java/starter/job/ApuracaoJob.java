package starter.job;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import starter.model.destination.InscricaoDestinationEntity;
import starter.model.destination.PSApuracaoDestinationEntity;
import starter.repository.destination.InscricaoDestinationRepository;
import starter.repository.destination.PSApuracaoDestinationRepository;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

@Component
@Slf4j
public class ApuracaoJob {
    @Autowired
    private InscricaoDestinationRepository inscricaoDestinationRepository;
    @Autowired
    private PSApuracaoDestinationRepository psApuracaoDestinationRepository;

    public void realizarApuracaoViaPlanilha(String planilha) {
        List<String> cpfs = cpfs(planilha);
        List<InscricaoDestinationEntity> inscricoes = inscricaoDestinationRepository.findAll();
        Map<String,InscricaoDestinationEntity> mapInscricoes = new LinkedHashMap<>();
        for (InscricaoDestinationEntity i : inscricoes) {
            mapInscricoes.put(i.getCpf(), i);
        }

        log.info("Total de Linhas Localizada na planilha: " + cpfs.size());
        for(String cpf:cpfs){
            InscricaoDestinationEntity preInscrito = mapInscricoes.get(cpf);
            boolean preInscricao = preInscrito!=null;
            PSApuracaoDestinationEntity apuracao = new PSApuracaoDestinationEntity();
            apuracao.setPreInscricao(preInscricao);
            apuracao.setIdEntidade(Integer.valueOf(planilha.split("\\-")[0].trim()));
            apuracao.setIdInscricao(preInscricao ? preInscrito.getId() : null );
            psApuracaoDestinationRepository.save(apuracao);
            log.info("Registrando a apuração: " + apuracao);
        }

    }

    private List<String> cpfs(String planilha) {
        List<String> cpfs = new ArrayList<>();

        try {
            FileInputStream file = new FileInputStream(new File("C:\\seduc\\ps\\inscritos\\" + planilha));

            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext())
            {
                Row row = rowIterator.next();
                if(row.getRowNum() == 0){
                    continue;
                }
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    if (cell.getColumnIndex() == 1) {
                        cpfs.add(cell.getStringCellValue());
                    }else if( cell.getColumnIndex() > 1)
                        break;
                }
            }
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cpfs;
    }

    public static void main(String[] args) {
        new ApuracaoJob().cpfs("406 - CETI RAMA BOA.xlsx");
    }
}
