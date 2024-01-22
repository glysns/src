package digytal.desktop.app.utils.report;


public enum ReportFormat {

    PDF ("pdf"),
    EXCEL("xlsx"),
    DOCX("docx");

    private String extension;

    ReportFormat(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }

}
