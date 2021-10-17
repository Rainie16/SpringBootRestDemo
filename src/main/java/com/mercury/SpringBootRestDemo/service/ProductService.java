package com.mercury.SpringBootRestDemo.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mercury.SpringBootRestDemo.bean.Product;
import com.mercury.SpringBootRestDemo.dao.ProductDao;

@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    public void save(Product product) {
        productDao.save(product);
    }

    public Product getProductById(Integer id) {
        return productDao.findById(id).get();
//		Optional<Product> p = getProduct(id);
//		if(p.isPresent()) {
//			return p.get();
//		} else {
//			throw new NullPointerException();
//		}
    }


    public Product getProductByName(String name) {
        return productDao.findByName(name);
    }

    public List<Product> getAll() {
        return productDao.findAll();
    }

    public Product getProductByPrice(int price){
        return productDao.getByPrice(price);
    }

    public ByteArrayInputStream getProductsInPDF() {
        //ByteArrayOutputStream class is used to write common data into multiple files
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document document = new Document();
        try {
            PdfPTable table = new PdfPTable(5);

            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            PdfPCell hcell;
            hcell = new PdfPCell(new Phrase("ID", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            hcell = new PdfPCell(new Phrase("Name", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            hcell = new PdfPCell(new Phrase("Brand", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            hcell = new PdfPCell(new Phrase("Price", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            hcell = new PdfPCell(new Phrase("Stock", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            // pd.save(p);	// oracle
            // fd.save(f);	// mySQL

            // exception below:
            for(Product p : productDao.findAll()) {
                PdfPCell cell;

                cell = new PdfPCell(new Phrase(Integer.toString(p.getId())));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(p.getName()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(p.getBrand()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(Integer.toString(p.getPrice())));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(Integer.toString(p.getStock())));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }

            PdfWriter.getInstance(document, out);
            document.open();
            document.add(table);
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
