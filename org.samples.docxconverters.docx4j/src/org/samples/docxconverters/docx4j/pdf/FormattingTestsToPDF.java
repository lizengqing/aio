package org.samples.docxconverters.docx4j.pdf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.docx4j.convert.out.pdf.PdfConversion;
import org.docx4j.convert.out.pdf.viaXSLFO.PdfSettings;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

public class FormattingTestsToPDF {

	public static void main(String[] args) {
		createPDF();
		createPDF();
	}

	private static void createPDF() {
		try {
			long start = System.currentTimeMillis();

			// 1) Load DOCX into WordprocessingMLPackage
			InputStream is = new FileInputStream(new File(
					"docx/FormattingTests.docx"));
			WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage
					.load(is);

			// 2) Prepare Pdf settings
			PdfSettings pdfSettings = new PdfSettings();

			// 3) Convert WordprocessingMLPackage to Pdf
			OutputStream out = new FileOutputStream(new File(
					"pdf/FormattingTests.pdf"));
			PdfConversion converter = new org.docx4j.convert.out.pdf.viaXSLFO.Conversion(
					wordMLPackage);
			converter.output(out, pdfSettings);

			System.err.println("Generate pdf/FormattingTests.pdf with "
					+ (System.currentTimeMillis() - start) + "ms");

		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
