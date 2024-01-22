import com.digytal.control.infra.utils.Calculos;
import com.digytal.control.model.modulo.contrato.request.ContratoItemRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CalculosTest {
    @Test
    public void aplicarEscala2Test(){
        Double valorCalculado = Calculos.aplicarEscala(460.58288);
        Double valorEsperado = 460.58;
        Assertions.assertEquals(valorCalculado,valorEsperado);

        valorCalculado = Calculos.aplicarEscala(460.58588);
        valorEsperado = 460.59;
        Assertions.assertEquals(valorCalculado,valorEsperado);
    }
    @Test
    public void aplicarEscala4Test(){
        Double valorCalculado = Calculos.aplicarEscala(4,460.58288);
        Double valorEsperado = 460.5829;
        Assertions.assertEquals(valorCalculado,valorEsperado);
    }
    @Test
    public void aplicarEscala4ContratoItens(){
        List<ContratoItemRequest> itens = new ArrayList<>();
        ContratoItemRequest item = new ContratoItemRequest();
        item.setQuantidade(15.37);
        item.setValorUnitario(4.294);
        item.setValorAplicado(Calculos.aplicarEscala(4, 65.99878));
        itens.add(item);

        item = new ContratoItemRequest();
        item.setQuantidade(125.98);
        item.setValorUnitario(3.656);
        item.setValorAplicado(Calculos.aplicarEscala(4, 460.58288));
        itens.add(item);

        Double valorItens = itens.stream().mapToDouble(i -> i.getValorAplicado()).sum();
        Double valorTotal = Calculos.aplicarEscala(4,526.58166);
        Assertions.assertEquals(valorItens,valorTotal);
    }
}
