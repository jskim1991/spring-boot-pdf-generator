package io.jay.pdfservice;

import com.itextpdf.layout.element.Table;

import static io.jay.pdfservice.DocumentConstants.FULL_PAGE_WIDTH;

public class TableGenerator {

    public static Table withColumns(int columns) {
        var cols = new float[columns];
        for (int i = 0; i < columns; i++) {
            cols[i] = FULL_PAGE_WIDTH / columns;
        }

        return new Table(cols);
    }
}
