package digytal.desktop.util.utils;

public enum LocalePattern {
    pt_BR ("dd/MM/yyyy","HH:mm:ss"),
    en_US("yyyy-MM-dd","HH:mm:ss");

    private String date;
    private String time;

    LocalePattern(String date, String time) {
        this.date = date;
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
    public String getDateTime(){
        return date + " " + getTime();
    }
}

