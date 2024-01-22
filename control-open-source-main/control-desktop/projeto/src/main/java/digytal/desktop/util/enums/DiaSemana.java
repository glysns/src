package digytal.desktop.util.enums;

import java.time.LocalDate;
import java.time.LocalTime;

public enum DiaSemana {
    SEG("SEGUNDA",1),
    TER("TERÇA",2),
    QUA("QUARTA",3),
    QUI("QUINTA",4),
    SEX("SEXTA",5),
    SAB("SÁBADO",6),
    DOM("DOMINGO",7);
    private String descricao;
    private int dia;

    private DiaSemana(String descricao,int dia){
        this.dia = dia;
        this.descricao=descricao;
    }

    public String getDescricao() {
        return descricao;
    }
    public String getId(){
        return this.name();
    }

    public int getDia() {
        return dia;
    }

    public static void main(String[] args) {
        LocalTime time = LocalTime.of(8,0);
        for(int x=0;x<=16;x++){
                String sql = String.format("INSERT INTO apl_agendamentos.tab_agenda\n" +
                        "(en_dia_semana, horario, periodo,fl_chatbot)\n" +
                        "VALUES('%s', '%s', 'M','false');",SEG.name(), time.toString());
                System.out.println(sql);
                time=time.plusMinutes(15);
        }
    }
}
