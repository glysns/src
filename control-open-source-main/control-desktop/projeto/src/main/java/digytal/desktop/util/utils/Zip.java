package digytal.desktop.util.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
	public static void execute(File file, String zipFullName) throws IOException {
		execute(file, "", zipFullName);
	}

	public static void execute(String srcFile, String path, String zipFullName) throws IOException {
		execute(new File(srcFile), path, zipFullName);
	}

	public static void execute(File file, String path, String zip) throws IOException {
		// FIXME: Tornar uma unica implementacao
		if ((!path.isEmpty()) && (!path.endsWith(File.separator))) {
			path = path + File.separator;
		}
		File fileZip = new File(path, zip);
		execute(file, fileZip);
	}

	public static void execute(File file, File zip) throws IOException {
		FileOutputStream fos = new FileOutputStream(zip);
		ZipOutputStream zipOut = new ZipOutputStream(fos);
		execute(file, file.getName(), zipOut);
		zipOut.close();
		fos.close();
	}

	public static void execute(File fileToZip, String fileName, ZipOutputStream zipOut) throws IOException {
		if (fileToZip.isHidden()) {
			return;
		}

		if (fileToZip.isDirectory()) {
			File[] children = fileToZip.listFiles();
			for (File childFile : children) {
				execute(childFile, fileName + "/" + childFile.getName(), zipOut);
			}
			return;
		}
		FileInputStream fis = new FileInputStream(fileToZip);
		ZipEntry zipEntry = new ZipEntry(fileName);
		zipOut.putNextEntry(zipEntry);
		byte[] bytes = new byte[1024];
		int length;
		while ((length = fis.read(bytes)) >= 0) {
			zipOut.write(bytes, 0, length);
		}
		fis.close();
	}
}
