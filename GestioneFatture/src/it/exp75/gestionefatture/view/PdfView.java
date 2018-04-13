package it.exp75.gestionefatture.view;

import java.awt.Desktop;
import java.io.File;

//Cross platform solution to view a PDF file
public class PdfView {

	public static void main(String[] args) {

	  try {

		File pdfFile = new File("c:\\fattura002_14012016.pdf");
		if (pdfFile.exists()) {

			if (Desktop.isDesktopSupported()) {
				Desktop.getDesktop().open(pdfFile);
			} else {
				System.out.println("Awt Desktop is not supported!");
			}

		} else {
			System.out.println("File is not exists!");
		}

		System.out.println("Done");

	  } catch (Exception ex) {
		ex.printStackTrace();
	  }

	}
}