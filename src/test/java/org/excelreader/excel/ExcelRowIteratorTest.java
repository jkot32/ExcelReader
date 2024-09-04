package org.excelreader.excel;

import org.excelreader.validators.EmptyValidator;
import org.excelreader.validators.Validator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ExcelRowIteratorTest {

    @Test
    void construction() {
        var dummyValidator = new EmptyValidator();
        var iterator = new ExcelRowIterator<>("nonExistingFile", dummyValidator);
        assertFalse(iterator.isLoaded());
        var iterator2 = new ExcelRowIterator<>("test_data/vzorek_dat.xlsx", dummyValidator);
        assertTrue(iterator2.isLoaded());
    }
    @Test
    void iterate() {
        var validator = new Validator<String>() {
            @Override
            public Optional<String> validate(String value) {
                return Optional.of(value);
            }
        };
        var iterator = new ExcelRowIterator<>("test_data/abcde.xlsx", validator);
        assertTrue(iterator.isLoaded());
        var list = new ArrayList<String>();
        iterator.iterate(list::add);
        assertEquals("abcde", String.join("", list));
    }
}