package it.exp75.gestionefatture.pdf;

import java.io.IOException;
import java.net.MalformedURLException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

import it.exp75.gestionefatture.model.Cliente;
import it.exp75.gestionefatture.model.DatiStampaFattura;
import it.exp75.gestionefatture.model.Intestazione;
import it.exp75.gestionefatture.resources.Log;

public class HeaderFooterPageEvent extends PdfPageEventHelper {

    private PdfTemplate t;
    private Image total;
    private Cliente cl;
    private Intestazione intest;
	private String imponibile;
	private String totIva;
	private String totFattura;
	
    public HeaderFooterPageEvent(DatiStampaFattura df) {
		super();
		this.intest = df.getIntestazione();
		this.cl = df.getCliente();
		this.imponibile = df.getImponibile();
		this.totIva = df.getTotIva();
		this.totFattura = df.getTotFattura();
	}

	public void onOpenDocument(PdfWriter writer, Document document) {
        t = writer.getDirectContent().createTemplate(30, 16);
        try {
            total = Image.getInstance(t);
            total.setRole(PdfName.ARTIFACT);
        } catch (DocumentException de) {
            throw new ExceptionConverter(de);
        }
    }

    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        addHeader(writer);
        addFooter(writer);
    }

    private void addHeader(PdfWriter writer){
        PdfPTable header = new PdfPTable(2);
        try {
            // set defaults
            header.setWidths(new int[]{2, 24});
            header.setTotalWidth(527);
            header.setLockedWidth(true);
            header.getDefaultCell().setFixedHeight(70);
            header.getDefaultCell().setBorder(0);
//            header.getDefaultCell().setBorder(Rectangle.BOTTOM);
//            header.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);

            // add image
            Image logo = Image.getInstance("./image001.png");
            header.addCell(logo);

            // add text
            PdfPCell azienda = new PdfPCell();
            azienda.setPaddingBottom(15);
            azienda.setPaddingLeft(10);
//            text.setBorder(Rectangle.BOTTOM);
//            text.setBorderColor(BaseColor.LIGHT_GRAY);
            azienda.addElement(new Phrase(intest.getRagione_sociale(), new Font(Font.FontFamily.HELVETICA, 12)));
            
            Font fontHelvetica8 = new Font(Font.FontFamily.HELVETICA, 8);
			
            azienda.addElement(new Phrase(intest.getIndirizzo(), fontHelvetica8));
            azienda.addElement(new Phrase(intest.getTelefono(), fontHelvetica8));
            azienda.addElement(new Phrase(intest.getEmail(), fontHelvetica8));
            azienda.addElement(new Phrase("CF: " + intest.getCF() + " - P.IVA: " + intest.getPIVA(), fontHelvetica8));
                        
            azienda.setBorder(1);
            header.addCell(azienda);
            
            header.completeRow();
            header.addCell("");
            
            PdfPCell cliente = new PdfPCell();
            cliente.setIndent(150);
            cliente.setPaddingTop(20);
            cliente.setPaddingLeft(250);
            cliente.addElement(new Phrase( cl.getRagioneSociale(), new Font(Font.FontFamily.HELVETICA, 12)));
            cliente.addElement(new Phrase(cl.getIndirizzo(), fontHelvetica8));
            
            
            String indirizzo = "";
            
            if(cl.getCap() != null) {
            	indirizzo += cl.getCap() + " ";
            }
            
            if(cl.getCitta() != null) {
            	indirizzo += cl.getCitta() + " ";
            }
            
            if(cl.getProvincia() != null) {
            	indirizzo += "(" + cl.getProvincia() + ")";
            }
            
            cliente.addElement(new Phrase(indirizzo, fontHelvetica8));
            
            
            if(cl.getCodiceFiscale() != null && cl.getCodiceFiscale().trim().length() > 0) {
            	String codiceFiscale = "CF: " + cl.getCodiceFiscale();
            	cliente.addElement(new Phrase(codiceFiscale, fontHelvetica8));
            }
            
            if(cl.getPartitaIva() != null && cl.getPartitaIva().trim().length() > 0) {
            	String partitaIva = "P.IVA: " + cl.getPartitaIva();
            	cliente.addElement(new Phrase(partitaIva, fontHelvetica8));
            }
                        
            cliente.setBorder(1);
            header.addCell(cliente);

            // write content
            header.writeSelectedRows(0, -1, 34, 803, writer.getDirectContent());
        } catch(DocumentException de) {
            throw new ExceptionConverter(de);
        } catch (MalformedURLException e) {
            throw new ExceptionConverter(e);
        } catch (IOException e) {
            throw new ExceptionConverter(e);
        }
    }

    private void addFooter(PdfWriter writer){
        
    	Log.getLogger().info(new Integer(writer.getCurrentPageNumber()).toString());
    	Log.getLogger().info(new Integer(writer.getPageNumber()).toString());

    	PdfPTable footer = new PdfPTable(3);
        try {
            // set defaults
            footer.setWidths(new int[]{40, 24, 40});
            footer.setTotalWidth(527);
            footer.setLockedWidth(true);
            footer.getDefaultCell().setFixedHeight(30);

            footer.getDefaultCell().setBorder(Rectangle.TOP);
            footer.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);

            // add copyright
            footer.addCell("");
            footer.addCell(new Phrase("Imponibile:", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
            footer.addCell(new Phrase(imponibile, new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
            footer.completeRow();
            
            footer.addCell("");
            footer.addCell(new Phrase("IVA:", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
            footer.addCell(new Phrase(totIva, new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
            footer.completeRow();
            
            footer.addCell("");
            footer.addCell(new Phrase("Totale:", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
            footer.addCell(new Phrase(totFattura, new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
            footer.completeRow();


            // write page
            PdfContentByte canvas = writer.getDirectContent();
            canvas.beginMarkedContentSequence(PdfName.ARTIFACT);
            footer.writeSelectedRows(0, -1, 34, 200, canvas);
            canvas.endMarkedContentSequence();
        } catch(DocumentException de) {
            throw new ExceptionConverter(de);
        }
    	
    	PdfPTable footer2 = new PdfPTable(3);
        try {
            // set defaults
            footer2.setWidths(new int[]{24, 2, 1});
            footer2.setTotalWidth(527);
            footer2.setLockedWidth(true);
            footer2.getDefaultCell().setFixedHeight(40);
            footer2.getDefaultCell().setBorder(Rectangle.TOP);
            footer2.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);

            // add copyright
            footer2.addCell(new Phrase("\u00A9 Memorynotfound.com", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));

            // add current page count
            footer2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
            footer2.addCell(new Phrase(String.format("Page %d of", writer.getPageNumber()), new Font(Font.FontFamily.HELVETICA, 8)));

            // add placeholder for total page count
            PdfPCell totalPageCount = new PdfPCell(total);
            totalPageCount.setBorder(Rectangle.TOP);
            totalPageCount.setBorderColor(BaseColor.LIGHT_GRAY);
            footer2.addCell(totalPageCount);

            // write page
            PdfContentByte canvas = writer.getDirectContent();
            canvas.beginMarkedContentSequence(PdfName.ARTIFACT);
            footer2.writeSelectedRows(0, -1, 34, 50, canvas);
            canvas.endMarkedContentSequence();
        } catch(DocumentException de) {
            throw new ExceptionConverter(de);
        }
    }

    public void onCloseDocument(PdfWriter writer, Document document) {
        int totalLength = String.valueOf(writer.getPageNumber()).length();
        int totalWidth = totalLength * 5;
        ColumnText.showTextAligned(t, Element.ALIGN_RIGHT,
                new Phrase(String.valueOf(writer.getPageNumber()), new Font(Font.FontFamily.HELVETICA, 8)),
                totalWidth, 6, 0);
    }
}
