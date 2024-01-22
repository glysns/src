package jpa;

import jpa.dao.ClienteQL;
import jpa.dao.FabricaConexao;

public class JpaClienteQLApplication {
    private static ClienteQL clienteQL;
    public static void main(String[] args) {
        FabricaConexao.iniciarConexao();
        clienteQL = new ClienteQL();
        //clienteQL.listarClientes();
        //clienteQL.listarClientesPorNome("marlon");
        clienteQL.listarClientesParcialDto();
        //clienteQL.listarClientesDto();
    }

}
