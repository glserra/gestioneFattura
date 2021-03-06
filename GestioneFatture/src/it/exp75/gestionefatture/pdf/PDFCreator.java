package it.exp75.gestionefatture.pdf;



import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;

import java.util.List;
import java.util.logging.Logger;

import com.itextpdf.text.BadElementException;

import com.itextpdf.text.BaseColor;

import com.itextpdf.text.Chunk;

import com.itextpdf.text.Document;

import com.itextpdf.text.DocumentException;

import com.itextpdf.text.Element;

import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import it.exp75.gestionefatture.model.Cliente;
import it.exp75.gestionefatture.model.DatiStampaFattura;
import it.exp75.gestionefatture.model.Prestazione;
import it.exp75.gestionefatture.resources.Log;

/**

 * This is to create a PDF file.

 */

public class PDFCreator {

	private final static String[] HEADER_ARRAY = {"S.No.", "CompanyName", "Income", "Year", "Income", "Year"};
	public final static Font SMALL_BOLD = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD);
	public final static Font MEDIUM_BOLD = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
	public final static Font MEDIUM_BOLD_ITALIC = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLDITALIC);
	
	public final static Font NORMAL_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.NORMAL);
	public final static Font MEDIUM_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
	
	public static final String IMG = "./image001.png";
	
	public static void addMetaData(Document document, String sqlXMLFileName) {

		document.addTitle("Sample Report");
		document.addSubject("Using iText");
		document.addAuthor("Arun");

	}



	public static void addPrestazioni(Document document, List<Prestazione> prestaz) throws DocumentException {

		Paragraph paragraph = new Paragraph();
//		paragraph.setSpacingAfter(20);
//		paragraph.setSpacingBefore(100);
		paragraph.setFont(NORMAL_FONT);
		createPrestazioniTable(paragraph, prestaz);
		document.add(paragraph);

	}
		
	
	private static void createPrestazioniTable(Paragraph paragraph, List<Prestazione> prestazioni) throws DocumentException {
		
		PdfPTable table = new PdfPTable(6);
//		table.setWidthPercentage(100);
		table.setTotalWidth(550);
		table.setLockedWidth(true);
		int[] mis = {20,80,5,5,5,10};
		table.setWidths(mis);

		if(null == prestazioni){

			paragraph.add(new Chunk("No data to display."));
			return;

		}

		addHeaderInTable(HEADER_ARRAY, table);

		int count = 1;

		for(Prestazione prest : prestazioni){

			addToTable(table, prest.getSezione());
			addToTable(table, prest.getDescrizione());
			addToTable(table, prest.getQuantita());
			addToTable(table, prest.getUnita_misura());
			addToTable(table, prest.getIva());
			addToTable(table, prest.getImporto());


		}
		

			table.setSplitLate(false);

		
		Log.getLogger().info("altezza tabella: " + new Float(table.getTotalHeight()).toString());
		paragraph.add(table);

	}
	
	public static void addTotali(Document document, DatiStampaFattura df) throws DocumentException {

		Paragraph paragraph = new Paragraph();
		addEmptyLine(paragraph, 2);
		paragraph.setFont(NORMAL_FONT);
		createTotaliTable(paragraph, df);
		document.add(paragraph);

	}
	
	private static void createTotaliTable(Paragraph paragraph, DatiStampaFattura df) throws DocumentException {
		
		PdfPTable table = new PdfPTable(2);
//		table.setWidthPercentage(100);
		table.setTotalWidth(550);
		table.setLockedWidth(true);
		int[] mis = {300,100};
		table.setWidths(mis);

		if(null == df){
			paragraph.add(new Chunk("No data to display."));
			return;
		}

		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		addToTable(table, "Imponibile");
		addToTable(table, df.getImponibile());
		addToTable(table, "Iva");
		addToTable(table, df.getTotIva());
		addToTable(table, "Totale");
		addToTable(table, df.getTotFattura());

		table.setSplitLate(true);
		
		paragraph.add(table);

	}

	
	/*
	 *  old  
	 */
	
	public static void addContent(Document document, List<DataObject> dataObjList) throws DocumentException {

		Paragraph paragraph = new Paragraph();
		paragraph.setFont(NORMAL_FONT);
		createReportTable(paragraph, dataObjList);
		document.add(paragraph);

	}
	
	private static void createReportTable(Paragraph paragraph, List<DataObject> dataObjList) throws BadElementException {

		PdfPTable table = new PdfPTable(4);
		table.setWidthPercentage(100);
		paragraph.add(new Chunk("Report Table :- ", SMALL_BOLD));

		if(null == dataObjList){

			paragraph.add(new Chunk("No data to display."));
			return;

		}

		addHeaderInTable(HEADER_ARRAY, table);

		int count = 1;

		for(DataObject dataObject : dataObjList){

			addToTable(table, String.valueOf(count)+".");
			addToTable(table, dataObject.getComanyName());
			addToTable(table, dataObject.getIncome());
			addToTable(table, dataObject.getYear());
			count++;

		}

		paragraph.add(table);

	}

	
	/*
	 *  old END 
	 */
	
	
	/** Helper methods start here **/  

	public static void addTitlePage(Document document, String title) throws DocumentException {

		Paragraph preface = new Paragraph();
		addEmptyLine(preface, 3);
		preface.add(new Phrase("Test Report: ", NORMAL_FONT));
		preface.add(new Phrase(title, PDFCreator.NORMAL_FONT));
		addEmptyLine(preface, 1);
		preface.add(new Phrase("Date: ", PDFCreator.SMALL_BOLD));
		preface.add(new Phrase(new Date().toString(), PDFCreator.NORMAL_FONT));
		addEmptyLine(preface, 1);
		preface.add(new Phrase("Report generated by: ", PDFCreator.SMALL_BOLD));
		preface.add(new Phrase("Arun", PDFCreator.NORMAL_FONT));
		addEmptyLine(preface, 2);
		preface.add(new Phrase("This is basically a sample report.", PDFCreator.NORMAL_FONT));
		document.addSubject("PDF : " + title);
		preface.setAlignment(Element.ALIGN_CENTER);
		document.add(preface);
		document.newPage();

	}

	public static void addAzienda(Document document) throws DocumentException, MalformedURLException, IOException {
		
		Paragraph preface = new Paragraph();
		Image img = Image.getInstance(IMG);
		preface.add(img);
		preface.add(new Phrase("INSTESTAZIONE",PDFCreator.MEDIUM_BOLD_ITALIC));
		addEmptyLine(preface, 1);
		preface.add(new Phrase("nome cognome",PDFCreator.NORMAL_FONT));
		addEmptyLine(preface, 1);
		document.add(preface);
		
	}
	
	
	public static void addCliente(Document document, Cliente cliente) throws DocumentException {
		float ident = 350.00f;
		float ident2 = 370.00f;
		Paragraph preface = new Paragraph();
		preface.setIndentationLeft(ident);
		preface.add(new Phrase("Spett.le:",PDFCreator.MEDIUM_FONT));
		document.add(preface);
		
		Paragraph preface2 = new Paragraph();
		preface2.setIndentationLeft(ident2);
		preface2.add(new Phrase("INSTESTAZIONE1",PDFCreator.MEDIUM_BOLD_ITALIC));
		addEmptyLine(preface2, 1);
		preface2.add(new Phrase("nome cognome dasdfasdf",PDFCreator.NORMAL_FONT));
		addEmptyLine(preface2, 1);
		document.add(preface2);
	}
	
	
	public static void addEmptyLine(Paragraph paragraph, int number) {

		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}

	}

	public static void addHeaderInTable(String[] headerArray, PdfPTable table){

		PdfPCell c1 = null;

		for(String header : headerArray) {

			c1 = new PdfPCell(new Phrase(header, PDFCreator.SMALL_BOLD));
			c1.setBackgroundColor(BaseColor.GREEN);
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);

		}

		table.setHeaderRows(1);

	}

	public static void addToTable(PdfPTable table, String data){        
		
		table.addCell(new Phrase(data, PDFCreator.NORMAL_FONT));
		
	}

	public static void addToTable(PdfPTable table, Double data){        
		
		table.addCell(new Phrase(data.toString(), PDFCreator.NORMAL_FONT));
		
	}
	
	public static void addToTable(PdfPTable table, Integer data){        
		
		table.addCell(new Phrase(data.toString(), PDFCreator.NORMAL_FONT));
		
	}
	
	
	public static Paragraph getParagraph(){        

		Paragraph paragraph = new Paragraph();
		paragraph.setFont(PDFCreator.NORMAL_FONT);
		addEmptyLine(paragraph, 1);
		return paragraph;

	}

}