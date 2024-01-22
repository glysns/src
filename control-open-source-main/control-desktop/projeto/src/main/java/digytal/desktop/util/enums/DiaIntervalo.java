package digytal.desktop.util.enums;

public enum DiaIntervalo {
    SEMANAL("E0602",7),
    QUINSENAL("E0603",14),
    MENSAL("E0604",30),
    DIARIO("E0601",1),
    ;
    private String id;
    private int dias;
    private DiaIntervalo(String id, int dias){
        this.id = id;
        this.dias = dias;
    }

    public int getDias() {
        return dias;
    }

    public String getId() {
        return id;
    }
}
