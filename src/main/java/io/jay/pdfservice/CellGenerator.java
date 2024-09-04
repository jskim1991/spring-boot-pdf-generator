package io.jay.pdfservice;

import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;

public class CellGenerator {

    static Cell withText(String text) {
        return new Cell().add(new Paragraph(text)).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
    }

    static Cell withBoldText(String text) {
        var cell = withText(text);
        return cell.setBold();
    }
}
