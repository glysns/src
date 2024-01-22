package digytal.desktop.components.desktop;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.UIManager;

public class MDISample {
	//https://www.flaticon.com/br/icone-gratis/cesta-de-compras_859270#
	//https://www.flaticon.com/br/lineal-color-icones
	//https://www.flaticon.com/br/packs/food-delivery-187

	//https://www.flaticon.com/br/icone-premium/computador-portatil_1304084?related_id=1304084
	//https://www.flaticon.com/br/packs/seo-18
	//https://www.flaticon.com/br/packs/responsive-design-43
    public static void main(String[] args) {
    	try {
    		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	        MDI mdi = new MDI();
	        mdi.setTitle("Kompras");
	        JMenu m1 = new JMenu("Cadastros");
	        //m1.setVisible(false);
	        JMenuItem m11 = new JMenuItem("Produtos");
	        m1.add(m11);
	        mdi.barraMenu.add(m1);
	        mdi.setSize(1024,768);
	        mdi.exibir(null);
    	}catch (Exception e) {
			e.printStackTrace();
		}
        
    }

	/*

	private static void qrCode() throws Exception {
		//https://www.journaldev.com/470/java-qr-code-generator-zxing-example

		String loja="sport-motos";
		String qrCodeText = "https://lojaaberta.net/lojas/" + loja;
		String fileType = "png";
		int[] sizes =new int[] { 100,200,400,600};

		for(int size:sizes) {
			String filePath = String.format("c:\\dev\\%s-%d.png", loja,size) ;

			File qrFile = new File(filePath);
			createQRImage(qrFile, qrCodeText, size, fileType);

		}
		System.out.println("DONE");



	}


	import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
	private static void createQRImage(File qrFile, String qrCodeText, int size, String fileType)
			throws WriterException, IOException {
		// Create the ByteMatrix for the QR-Code that encodes the given String
		Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<>();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix byteMatrix = qrCodeWriter.encode(qrCodeText, BarcodeFormat.QR_CODE, size, size, hintMap);
		// Make the BufferedImage that are to hold the QRCode
		int matrixWidth = byteMatrix.getWidth();
		BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
		image.createGraphics();

		Graphics2D graphics = (Graphics2D) image.getGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, matrixWidth, matrixWidth);
		// Paint and save the image using the ByteMatrix
		graphics.setColor(Color.BLACK);

		for (int i = 0; i < matrixWidth; i++) {
			for (int j = 0; j < matrixWidth; j++) {
				if (byteMatrix.get(i, j)) {
					graphics.fillRect(i, j, 1, 1);
				}
			}
		}
		ImageIO.write(image, fileType, qrFile);
	}

	 */
}
