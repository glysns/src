package com.digytal.control.infra.report;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

public class ReportGenerator {
    //http://mauda.com.br/?p=1386
    static final Locale BRAZIL_LOCALE = new Locale("pt", "BR");

    private File tempFolder = new File("target");
    private String reportName;
    private String reportTitle;
    private JRDataSource dataSource;
    private Object data;
    private File generatedFile;
    private String reportId;
    private Map<String, Object> parameters = new HashMap<>();
    private ReportFormat format = ReportFormat.PDF;

    public static ReportGenerator of(String reportName) {
        return new ReportGenerator().setReportName(reportName);
    }

    public ReportGenerator setReportName(String reportName) {
        this.reportName = reportName;
        return this;
    }

    public ReportGenerator setReportTitle(String reportTitle) {
        this.reportTitle = reportTitle;
        return this;
    }

    public ReportGenerator setTempFolder(File tempFolder) {
        this.tempFolder = tempFolder;
        return this;
    }

    public ReportGenerator setTempFolder(String path) {
        return setTempFolder(new File(path));
    }

    public ReportGenerator setDataSource(JRDataSource dataSource) {
        this.dataSource = dataSource;
        return this;
    }

    public ReportGenerator setData(Object data) {
        this.data = data;
        return this;
    }

    public ReportGenerator setFormat(ReportFormat format) {
        this.format = ObjectUtils.defaultIfNull(format, ReportFormat.PDF);
        return this;
    }

    public ReportGenerator setParameter(String name, Object value) {
        this.parameters.put(name, value);
        return this;
    }

    public ReportGenerator generate() throws IOException, JRException {
        this.generatedFile = generateFile();
        return this;
    }

    public ReportModel generateModel() throws IOException, JRException {
        File file = generateFile();
        ReportModel model= new ReportModel();
        model.setId(reportId);
        model.setName(reportTitle);
        model.setPath("/api/viewer/" + file.getName());
        model.setUrl("/api/rpts/viewer/" + file.getName());
        model.setFormat(format);
        model.setDateTime(LocalDateTime.now());
        return model;

    }
    public void deletePdf() {
        File[] listFiles = tempFolder.listFiles();
        for(File f: listFiles) {
            if(f.getName().contains(".pdf"))
                f.delete();
        }
    }
    public File generateFile() throws IOException, JRException {
        if (tempFolder == null) {
            throw new IllegalStateException("tempFolder not defined");
        }
        this.reportId = UUID.randomUUID().toString();

        File file = new File(tempFolder, reportId + "." + format.getExtension());
        try (OutputStream output = new FileOutputStream(file)) {
            generate(output);
        }
        return file;
    }
    public byte[] generateStream() throws IOException, JRException {
        
        this.reportId = UUID.randomUUID().toString();
        byte[] content =null;
        try (OutputStream output = new ByteArrayOutputStream()) {
            generate(output);
            content = ((ByteArrayOutputStream)output).toByteArray();
            //logger.info("Report generated | reportName={} | file={}", reportName, file.getAbsolutePath());
        }
        return content;
    }
    public void setParamImg(String param, String img) throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String resourceName = "img/" +img;
        InputStream stream = classLoader.getResourceAsStream(resourceName);
        byte[] bytes = new byte[stream.available()];
        stream.read(bytes);
        parameters.put(param,bytes);
    }
    public void setSubReports(String ... names) throws IOException, JRException {
    	for(int x=0; x<names.length;x++) {
    		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            String resourceName = "reports/" + names[x] + ".jasper";
            parameters.put("SUBREPORT_DIR"+(x+1), resourceName);
    	}
    	
    }
    
    public OutputStream generate(OutputStream output) throws IOException, JRException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String resourceName = "reports/" + reportName;
        try (InputStream jasperStream = classLoader.getResourceAsStream(resourceName)) {
            if (jasperStream == null) {
                throw new IllegalStateException("Report not found: " + reportName);
            }

            parameters.put(JRParameter.REPORT_LOCALE, BRAZIL_LOCALE);
            parameters.put("REPORT_FORMAT", format.name());
            parameters.put("EXPORTING_XLS", format == ReportFormat.EXCEL);
            if (format == ReportFormat.EXCEL) {
                parameters.put(JRParameter.IS_IGNORE_PAGINATION, true);
            }
            if (dataSource == null && data != null) {
                if (data instanceof ResultSet) {
                    dataSource = new JRResultSetDataSource((ResultSet) data);
                } else {
                    Collection<?> collection = data instanceof Collection
                            ? (Collection<?>) data
                            : Collections.singleton(data);
                    dataSource = new JRBeanCollectionDataSource(collection);
                }
            }
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperStream, parameters, dataSource);

            if (format == ReportFormat.PDF) {
                generatePdf(jasperPrint, output);
            } else if (format == ReportFormat.EXCEL) {
                generateExcel(jasperPrint, output);
            } else {
                throw new IllegalStateException("Formato de relatório não tratado: " + format);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            output.close();
        }
        return output;
    }

    private void generatePdf(JasperPrint jasperPrint, OutputStream output) throws JRException {
        JRPdfExporter pdfExporter = new JRPdfExporter();
        pdfExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        pdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(output));
        pdfExporter.exportReport();
    }

    private void generateExcel(JasperPrint jasperPrint, OutputStream out) throws JRException {
        JRXlsxExporter exporter = new JRXlsxExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
        SimpleXlsxReportConfiguration reportConfig = new SimpleXlsxReportConfiguration();

        reportConfig.setWhitePageBackground(false);
        reportConfig.setRemoveEmptySpaceBetweenColumns(true);
        // reportConfig.setRemoveEmptySpaceBetweenRows(true);
        reportConfig.setIgnorePageMargins(true);
        reportConfig.setDetectCellType(true);
        reportConfig.setCollapseRowSpan(false);
        reportConfig.setColumnWidthRatio(1.35f);

        exporter.setConfiguration(reportConfig);
        exporter.exportReport();
    }

    public String getReportId() {
        return reportId;
    }

    public File getGeneratedFile() {
        return generatedFile;
    }

}
