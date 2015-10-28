package org.samples.docxconverters.jodconverter.pdf;

import java.io.File;

import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;

public class TableWithRowsColsSpanToPDF {

	public static void main(String[] args) {

		// 1) Start LibreOffice in headless mode.
		OfficeManager officeManager = null;
		try {
			officeManager = new DefaultOfficeManagerConfiguration().setOfficeHome(new File("/usr/lib/libreoffice"))
					.buildOfficeManager();
			officeManager.start();

			// 2) Create JODConverter converter
			OfficeDocumentConverter converter = new OfficeDocumentConverter(officeManager);

			// 3) Create PDF
			createPDF(converter);
			createPDF(converter);

		} finally {
			// 4) Stop LibreOffice in headless mode.
			if (officeManager != null) {
				officeManager.stop();
			}
		}
	}

	private static void createPDF(OfficeDocumentConverter converter) {
		try {
			long start = System.currentTimeMillis();
			converter.convert(new File("docx/TableWithRowsColsSpan.docx"), new File("pdf/TableWithRowsColsSpan.pdf"));
			System.err.println(
					"Generate pdf/TableWithRowsColsSpan.pdf with " + (System.currentTimeMillis() - start) + "ms");
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
