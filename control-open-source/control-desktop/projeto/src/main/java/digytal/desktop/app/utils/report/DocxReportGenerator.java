package digytal.desktop.app.utils.report;

public class DocxReportGenerator {
	/*
    static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private File tempFolder;
    private Object context;
    private String resourceName;

    public DocxReportGenerator setTempFolder(File tempFolder) {
        this.tempFolder = tempFolder;
        return this;
    }

    public DocxReportGenerator setResourceName(String resourceName) {
        this.resourceName = resourceName;
        return this;
    }

    public DocxReportGenerator setContext(Object context) {
        this.context = context;
        return this;
    }

    public ObjectNode createJsonContext() {
        ObjectNode jsonContext = OBJECT_MAPPER.createObjectNode();
        this.context = jsonContext;
        return jsonContext;
    }

    public void generate(OutputStream out) throws IOException {
        DocxStamperConfiguration config = new DocxStamperConfiguration();
        if (context instanceof ObjectNode) {
            JsonNode jsonContext = (JsonNode) context;
            this.context = OBJECT_MAPPER.convertValue(jsonContext, new TypeReference<Map<String, Object>>(){});
        }
        try (InputStream template = ResourceUtils.getAsStream(resourceName)) {
            DocxStamper<Object> stamper	= new DocxStamper<>(config);

            stamper.stamp(template, context, out);
        }
    }

    protected void toPdf(InputStream docxInput, OutputStream pdfOutput) throws Docx4JException {
        WordprocessingMLPackage wmlPackage = WordprocessingMLPackage.load(docxInput);
        Docx4J.toPDF(wmlPackage, pdfOutput);
    }

    public File generateFile() throws IOException {
    	String[] parts = resourceName.split("/");
        String lastName = parts[parts.length - 1];
        String baseName = lastName.substring(0, lastName.length() - 5);
        String fileName = baseName + "_" + UUID.randomUUID().toString() + ".docx";
        File file = tempFolder != null ? new File(tempFolder, fileName) : File.createTempFile(lastName, ".docx");
        try (OutputStream out = new FileOutputStream(file)) {
            generate(out);
        }
        return file;
    }

    public File generatePdfFile() throws Exception {
        File docxFile = generateFile();
        File pdfFile = new File(docxFile.getParentFile(), docxFile.getName() + ".pdf");

        try (FileInputStream docxInput = new FileInputStream(docxFile)) {
            try (FileOutputStream pdfOutput = new FileOutputStream(pdfFile)) {
                toPdf(docxInput, pdfOutput);
            }
        }

        return pdfFile;
    }
	*/
}
