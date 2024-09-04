package io.jay.pdfservice;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.DashedBorder;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;

import java.io.FileNotFoundException;
import java.util.List;

public class DocumentGenerator {

    public static void main(String[] args) throws FileNotFoundException {
        String path = "invoice.pdf";
        PdfWriter pdfWriter = new PdfWriter(path);

        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        pdfDocument.setDefaultPageSize(PageSize.A4); // w=595.0F, h=842.0F
        Document document = new Document(pdfDocument);

        Table table = new Table(new float[]{350f, 220f});
        table.addCell(new Cell().add(new Paragraph("Invoice")).setFontSize(20f).setBorder(Border.NO_BORDER).setBold());

        Table nestedTable = new Table(new float[]{220f / 2, 220f / 2});
        nestedTable.addCell(CellGenerator.withBoldText("Invoice No."));
        nestedTable.addCell(CellGenerator.withText("123456"));
        nestedTable.addCell(CellGenerator.withBoldText("Invoice Date"));
        nestedTable.addCell(CellGenerator.withText("2024-09-04"));
        nestedTable.setBorder(Border.NO_BORDER);
        table.addCell(new Cell().add(nestedTable).setBorder(Border.NO_BORDER));


        document.add(table);

        Border border = new SolidBorder(ColorConstants.GRAY, 2f);
        Table divider = TableGenerator.withColumns(1);
        divider.setBorder(border);

        Paragraph lineBreak = new Paragraph("\n");
        document.add(lineBreak);


        document.add(divider);
        document.add(lineBreak);

        Table twoColumnTable = TableGenerator.withColumns(2);
        twoColumnTable.addCell(CellGenerator.withBoldText("Billing Information").setFontSize(13f));
        twoColumnTable.addCell(CellGenerator.withBoldText("Shipping Information").setFontSize(13f));
        twoColumnTable.setMarginBottom(12f);
        document.add(twoColumnTable);

        Table firstSection = TableGenerator.withColumns(2);
        firstSection.addCell(CellGenerator.withBoldText("Company"));
        firstSection.addCell(CellGenerator.withBoldText("Name"));
        firstSection.addCell(CellGenerator.withText("Jay Company"));
        firstSection.addCell(CellGenerator.withText("Jay"));
        firstSection.setMarginBottom(10f);
        document.add(firstSection);

        Table secondSection = TableGenerator.withColumns(2);
        secondSection.addCell(CellGenerator.withBoldText("Name"));
        secondSection.addCell(CellGenerator.withBoldText("Address"));
        secondSection.addCell(CellGenerator.withText("Jay"));
        secondSection.addCell(CellGenerator.withText("1131-4 some avenue, Seoul"));
        secondSection.setMarginBottom(10f);
        document.add(secondSection);

        Table thirdSection = TableGenerator.withColumns(1);
        thirdSection.addCell(CellGenerator.withBoldText("Address"));
        secondSection.addCell(CellGenerator.withText("1131-4 some avenue, Seoul"));
        thirdSection.addCell(CellGenerator.withBoldText("Email"));
        thirdSection.addCell(CellGenerator.withText("jay@domain.com"));
        thirdSection.setMarginBottom(10f);
        document.add(thirdSection);

        Table dashedDivider = TableGenerator.withColumns(1);
        Border dashedBorder = new DashedBorder(ColorConstants.GRAY, 0.5f);
        dashedDivider.setBorder(dashedBorder);
        document.add(dashedDivider);

        document.add(new Paragraph("Products").setBold());
        Table threeColumnTable1 = TableGenerator.withColumns(3);
        threeColumnTable1.setBackgroundColor(ColorConstants.BLACK, 0.6f);
        threeColumnTable1.addCell(CellGenerator.withBoldText("Description").setTextAlignment(TextAlignment.LEFT).setFontColor(ColorConstants.WHITE));
        threeColumnTable1.addCell(CellGenerator.withBoldText("Quantity").setTextAlignment(TextAlignment.CENTER).setFontColor(ColorConstants.WHITE));
        threeColumnTable1.addCell(CellGenerator.withBoldText("Price").setTextAlignment(TextAlignment.RIGHT).setFontColor(ColorConstants.WHITE));

        document.add(threeColumnTable1);

        List<Product> productList = List.of(
                new Product("Apple", 2, 159),
                new Product("Mango", 4, 205),
                new Product("Banana", 2, 59),
                new Product("Grape", 2, 99),
                new Product("Coconut", 2, 15)
        );

        Table productTable = TableGenerator.withColumns(3);
        productTable.setMarginBottom(20f);
        int total = 0;
        for (var product : productList) {
            productTable.addCell(CellGenerator.withText(product.name()).setTextAlignment(TextAlignment.LEFT));
            productTable.addCell(CellGenerator.withText(String.valueOf(product.quantity())).setTextAlignment(TextAlignment.CENTER));
            productTable.addCell(CellGenerator.withText(String.valueOf(product.price())).setTextAlignment(TextAlignment.RIGHT));
            total += product.price() * product.quantity();
        }
        document.add(productTable);

        Table totalTableArea = new Table(new float[]{265f, 305f});
        totalTableArea.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        totalTableArea.addCell(new Cell().add(dashedDivider).setBorder(Border.NO_BORDER));
        document.add(totalTableArea);

        Table totalTable = TableGenerator.withColumns(3);
        totalTable.addCell(CellGenerator.withText(""));
        totalTable.addCell(CellGenerator.withBoldText("Total").setTextAlignment(TextAlignment.CENTER));
        totalTable.addCell(CellGenerator.withText(String.valueOf(total)).setTextAlignment(TextAlignment.RIGHT));
        document.add(totalTable);

        document.add(dashedDivider);
        document.add(lineBreak);
        document.add(divider);
        document.add(lineBreak);

        Table tb = TableGenerator.withColumns(1);
        tb.addCell(CellGenerator.withBoldText("Terms and Conditions"));
        tb.addCell(CellGenerator.withText("1. This is a computer generated invoice and does not require signature."));
        tb.addCell(CellGenerator.withText("2. By making this purchase you agree to our terms and conditions."));

        document.add(tb);



        document.close();
    }
}
