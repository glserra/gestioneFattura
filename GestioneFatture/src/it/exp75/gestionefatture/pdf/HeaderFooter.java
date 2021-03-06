package it.exp75.gestionefatture.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

class HeaderFooter extends PdfPageEventHelper {

	Font ffont = new Font(Font.FontFamily.UNDEFINED, 5, Font.ITALIC);
	
    /** The header/footer text. */

    String header;

    /** The template with the total number of pages. */

    PdfTemplate total;

    /**

     * Allows us to change the content of the header.

     * @param header The new header String

     */

    public void setHeader(String header) {
        this.header = header;
    }

    /**

     * Creates the PdfTemplate that will hold the total number of pages

     */

    public void onOpenDocument(PdfWriter writer, Document document) {
        total = writer.getDirectContent().createTemplate(25, 16);
    }

    /**

     * Adds a header to every page

     */

    
    public void onEndPage(PdfWriter writer, Document document) {
        PdfContentByte cb = writer.getDirectContent();
        Phrase header = new Phrase("this is a header", ffont);
        Phrase footer = new Phrase(String.format("Page %2d ",writer.getPageNumber()), ffont);
        Phrase test = new Phrase();
        
        ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
                header,
                (document.right() - document.left()) / 2 + document.leftMargin(),
                document.top() + 10, 0);
        

        ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT,
                footer,
                (document.right() - document.left()) / 2 + document.leftMargin(),
                300, 0);
    }
    
    
//    public void onEndPage(PdfWriter writer, Document document) {
//
//        PdfPTable table = new PdfPTable(2);
//
//        try {
//
//            table.setWidths(new int[]{200, 30});
//            table.setLockedWidth(true);
//            table.getDefaultCell().setBorder(Rectangle.SUBJECT);
//            table.addCell(header);
//            table.addCell(String.format("Page %d ", writer.getPageNumber()));
//            Rectangle page = document.getPageSize();
//            table.setTotalWidth(page.getWidth() - document.leftMargin() - document.rightMargin());
//            table.writeSelectedRows(0, -1, document.leftMargin(), page.getHeight() - document.topMargin() + table.getTotalHeight()+5, writer.getDirectContent());          
//
//        }
//
//        catch(DocumentException de) {
//            throw new ExceptionConverter(de);
//        }
//
//    }

    /**

     * Fills out the total number of pages before the document is closed

     */

    public void onCloseDocument(PdfWriter writer, Document document) {
        ColumnText.showTextAligned(total, Element.ALIGN_LEFT, new Phrase(String.valueOf(writer.getPageNumber() - 1)), 2, 2, 0);

    }

}
