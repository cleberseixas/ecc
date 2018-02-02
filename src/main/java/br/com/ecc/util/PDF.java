package br.com.ecc.util;

import com.itextpdf.text.*;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

public class PDF {
	
	public static PdfWriter criaPDF(Document doc, String caminhoArquivo) throws FileNotFoundException, DocumentException {
		return PdfWriter.getInstance(doc, new FileOutputStream(caminhoArquivo));
	}
	
	public static void abreDocumento(Document doc) {
		doc.open();
	}
	
	public static void fechaDocumento(Document doc) {
		doc.close();
	}
	
	public static void setTamanhoPaginaA4(Document doc) {
		doc.setPageSize(PageSize.A4);
	}
	
	public static void setImagemCentralizada(Document doc, String caminhoImagem) throws DocumentException, MalformedURLException, IOException {
		Image figura = Image.getInstance(caminhoImagem);
		figura.scaleToFit(238, 77);
		figura.setAlignment(Element.ALIGN_CENTER);
		doc.add(figura);
	}
	
	public static void setLinhaEmBranco(Document doc) throws DocumentException {
		doc.add(new Paragraph("\n"));
	}
	
	public static void setTextoNegrito(Document doc, String texto, char posicaoTexto) throws DocumentException {
		Font fontHeader = new Font(FontFamily.HELVETICA, 12, Font.BOLD);
		Paragraph p = new Paragraph(texto,fontHeader);
		switch (posicaoTexto) {
			case 'C': p.setAlignment(Element.ALIGN_CENTER); break;
			case 'J': p.setAlignment(Element.ALIGN_JUSTIFIED); break;
			case 'E': p.setAlignment(Element.ALIGN_LEFT); break;
			case 'D': p.setAlignment(Element.ALIGN_RIGHT); break;
		}
		doc.add(p);
	}
	
	public static void setTexto(Document doc, String texto, char posicaoTexto) throws DocumentException {
		Paragraph p = new Paragraph(texto);
		p.setFirstLineIndent(50);
		switch (posicaoTexto) {
			case 'C': p.setAlignment(Element.ALIGN_CENTER); break;
			case 'J': p.setAlignment(Element.ALIGN_JUSTIFIED); break;
			case 'E': p.setAlignment(Element.ALIGN_LEFT); break;
			case 'D': p.setAlignment(Element.ALIGN_RIGHT); break;
		}
		doc.add(p);
	}
	
	public static void setRodape(PdfWriter writer, Document document) {
		PdfPTable foot = new PdfPTable(1);
		PdfPCell cell = new PdfPCell(new Phrase("Ministério Público do Estado de Rondônia - Promotoria de Justiça de Ariquemes"));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_CENTER);
		cell.setBorder(0);
		cell.setBorderWidthTop(1);
		foot.addCell(cell);
		
		Rectangle page = document.getPageSize();
		foot.setTotalWidth(page.getWidth() - document.leftMargin() - document.rightMargin());
		foot.writeSelectedRows(0, -1, document.leftMargin(), document.bottomMargin(), writer.getDirectContent());
	}
}
