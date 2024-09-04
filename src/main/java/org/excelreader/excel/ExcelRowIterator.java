package org.excelreader.excel;

import com.monitorjbl.xlsx.StreamingReader;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.excelreader.validators.Validator;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.function.Consumer;

public class ExcelRowIterator<T> {

    private Workbook workbook;
    private final Validator<T> validator;
    private boolean isLoaded;

    public boolean isLoaded() {
        return isLoaded;
    }

    public ExcelRowIterator(String fileName, Validator<T> validator) {
        try {
            InputStream is = new FileInputStream(fileName);
            workbook = StreamingReader.builder()
                    .rowCacheSize(50)
                    .open(is);
            isLoaded = true;
        } catch (Exception e) {
            isLoaded = false;
        }
        this.validator = validator;
    }
    public void iterate(Consumer<? super T> consumer) {
        for (Sheet sheet : workbook) {
            for (Row r : sheet) {
                for (Cell c : r) {
                    var number = validator.validate(c.getStringCellValue());
                    number.ifPresent(consumer);
                    // expects only one value per row
                    if (number.isPresent()) {
                        break;
                    }
                }
            }
        }
    }
}
