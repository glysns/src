package jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfissaoCrud {
    private Connection conexao;
    public ProfissaoCrud(){
       conexao = FabricaoConexao.getConnection();
    }
    public void save (Profissao profissao){
        try {
            String sql = "INSERT INTO tab_profissao (nome) VALUES (?)";

            PreparedStatement statement = conexao.prepareStatement(sql);
            statement.setString(1, profissao.getNome());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Uma nova profissao foi salva com sucesso!");
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void update(Profissao profissao){
        try {
            String sql = "UPDATE tab_profissao SET nome = ? WHERE id = ?";

            PreparedStatement statement = conexao.prepareStatement(sql);
            statement.setString(1, profissao.getNome());
            statement.setInt(2, profissao.getId());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Profissão alterada com sucesso!");
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public int delete(Integer id){
        int rowsDeleted=0;
        try {
            String sql = "DELETE FROM tab_profissao WHERE id = ?";

            PreparedStatement statement = conexao.prepareStatement(sql);
            statement.setInt(1, id);
            rowsDeleted = statement.executeUpdate();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return  rowsDeleted;
    }
    //buscar um profissao na base atraves do seu id ?
    public Profissao findById(Integer id){
        Profissao profissao = null;
        try {
            String sql = "SELECT * FROM tab_profissao WHERE id = ?";

            PreparedStatement statement = conexao.prepareStatement(sql);
            statement.setInt(1,id);

            ResultSet result = statement.executeQuery();

            while (result.next()){
               profissao = new Profissao();
                profissao.setId(result.getInt("id"));
                profissao.setNome(result.getString("nome"));
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return profissao;
    }
    public List<Profissao> findAll(){
        List<Profissao> registros = new ArrayList<>();
        try {
            String sql = "SELECT * FROM tab_profissao";
            Statement statement = conexao.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()){
                Profissao profissao = new Profissao();
                profissao.setId(result.getInt("id"));
                profissao.setNome(result.getString("nome"));
                registros.add(profissao);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return registros;
    }

}
