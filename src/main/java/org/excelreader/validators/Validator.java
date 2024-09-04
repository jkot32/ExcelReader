package org.excelreader.validators;

import java.util.Optional;

public interface Validator<T> {
    Optional<T> validate(String value);
}
