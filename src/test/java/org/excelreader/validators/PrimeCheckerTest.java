package org.excelreader.validators;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigInteger;
import java.util.stream.Stream;


class PrimeCheckerTest {

    private final PrimeChecker validator = new PrimeChecker(BigInteger.valueOf(10000), 4);

    private static Stream<Arguments> provideArgs() {
        return Stream.of(
                Arguments.of("-125", false),
                Arguments.of("0", false),
                Arguments.of("1", false),
                Arguments.of("2", true),
                Arguments.of("3", true),
                Arguments.of("4", false),
                Arguments.of("5", true),
                Arguments.of("6", false),
                Arguments.of("7", true),
                Arguments.of("8", false),
                Arguments.of("9", false),
                Arguments.of("11", true),
                Arguments.of("13", true),
                Arguments.of("17", true),
                Arguments.of("53", true),
                Arguments.of("108086391056891903", true),
                Arguments.of("108086391056891902", false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideArgs")
    void validate(String text, boolean isPrime) {
        Assertions.assertEquals(isPrime, validator.validate(text).isPresent());
    }
}