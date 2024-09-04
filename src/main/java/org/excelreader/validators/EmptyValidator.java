package org.excelreader.validators;

import java.util.Optional;

public class EmptyValidator implements Validator<Void>{
    @Override
    public Optional<Void> validate(String value) {
        return Optional.empty();
    }
}
