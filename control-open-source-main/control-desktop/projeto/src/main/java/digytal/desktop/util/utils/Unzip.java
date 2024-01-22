package digytal.desktop.util.utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Unzip {
	/*List<String> fileList;
	private static final String INPUT_ZIP_FILE = "/cfip/database.zip";
	private static final String OUTPUT_FOLDER = "/cfip";

	public static void main(String[] args) {
		Unzip unZip = new Unzip();
		unZip.unZipIt(INPUT_ZIP_FILE, OUTPUT_FOLDER);
	}*/
	public void unZipIt(String zipFile, String outputFolder) {

		byte[] buffer = new byte[1024];

		try {
			boolean unzip = true;
			File folder = new File(outputFolder);
			if (!folder.exists()) {
				folder.mkdir();
			}
			if(unzip) {
				ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));
				ZipEntry ze = zis.getNextEntry();
				while (ze != null) {
					String fileName = ze.getName();
					File newFile = new File(outputFolder + File.separator + fileName);
					System.out.println("file unzip : " + newFile.getAbsoluteFile());
					new File(newFile.getParent()).mkdirs();
					FileOutputStream fos = new FileOutputStream(newFile);
					int len;
					while ((len = zis.read(buffer)) > 0) {
						fos.write(buffer, 0, len);
					}
	
					fos.close();
					ze = zis.getNextEntry();
				}
				zis.closeEntry();
				zis.close();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}