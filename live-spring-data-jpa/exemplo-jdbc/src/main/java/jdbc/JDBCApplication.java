package jdbc;

import java.util.List;

public class JDBCApplication {
    private static ProfissaoCrud profissaoCrud;
    public static void main(String[] args) {
        FabricaoConexao.iniciarConexao();
        profissaoCrud = new ProfissaoCrud();
        incluirProfissao();
        alterarProfissao();
        listarProfissoes();
        excluirProfissao();
    }
    //pensem que estes métodos poderiam ações de seu sistema
    private static void incluirProfissao(){
        Profissao profissao  =new Profissao();
        profissao.setNome("PROGRAMADOR");
        profissaoCrud.save(profissao);
    }
    private static void alterarProfissao(){
        Profissao profissao  = profissaoCrud.findById(1);
        if(profissao!=null){
            profissao.setNome("PROGRAMADOR / INSTRUTOR");
            profissaoCrud.update(profissao);
        }else {
            System.out.println("Não existe a profissão com o id informado");
        }
    }
    private static void listarProfissoes(){
        List<Profissao> profissoes = profissaoCrud.findAll();
        for(Profissao p:profissoes){
            System.out.println(p.getId() + "--" + p.getNome());
        }
    }
    private static void excluirProfissao(){
        int linhasDeletas = profissaoCrud.delete(2);
        if(linhasDeletas==0)
            System.out.println("Nenhum registro com id informado localizado para a exclusão");
    }
}
