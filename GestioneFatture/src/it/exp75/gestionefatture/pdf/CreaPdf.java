package it.exp75.gestionefatture.pdf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

import it.exp75.gestionefatture.business.Utility;
import it.exp75.gestionefatture.model.Cliente;
import it.exp75.gestionefatture.model.DatiFattura;

public class CreaPdf {

	private static final String TITLE = "fattura";
	public static final String PDF_EXTENSION = ".pdf";

	public static void creaFatturaPdf(DatiFattura df) {

		Document document = null;
		try {
			//Document is not auto-closable hence need to close it separately
			String numFattFormat = String.format("%03d",df.getFattura().getNum_fattura());
			String titolo = TITLE + numFattFormat + "_" + Utility.formatDateToString(df.getFattura().getData_fattura(), "ddMMyyyy");
			document = new Document(PageSize.A4);            
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(new File(titolo + PDF_EXTENSION)));
			HeaderFooter event = new HeaderFooter();
//			event.setHeader("Test Report");
			writer.setPageEvent(event);
			document.open();
			PDFCreator.addMetaData(document, titolo);
//			PDFCreator.addTitlePage(document, TITLE);
//			PDFCreator.addContent(document, dataObjList);
			PDFCreator.addAzienda(document);
			PDFCreator.addCliente(document, df.getCliente());
			PDFCreator.addPrestazioni(document, df.getListaPrestazioni());
//			PDFCreator.addContent(document, dataObjList);
			
			document.newPage();
			
			PDFCreator.addAzienda(document);
			PDFCreator.addCliente(document, df.getCliente());
			
			
		}catch (DocumentException | FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("FileNotFoundException occurs.." + e.getMessage());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(null != document){
				document.close();
			}
		}
	}
	

	
}
