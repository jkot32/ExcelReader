package org.excelreader.validators;

import java.math.BigInteger;
import java.util.Optional;
import java.util.stream.Stream;


public class PrimeChecker implements Validator<BigInteger> {
    public PrimeChecker(BigInteger threshold, int certainty) {
        this.threshold = threshold;
        this.certainty = certainty;
    }

    private final BigInteger threshold;
    private final int certainty;

    private boolean isPrimeAccurate(BigInteger v) {
       return v.equals(BigInteger.TWO) || Stream.iterate(BigInteger.TWO, i -> i.compareTo(v.sqrt()) <= 0, i -> i.add(BigInteger.ONE))
        .noneMatch(n -> (v.mod(n).equals(BigInteger.ZERO)));
    }

    @Override
    public Optional<BigInteger> validate(String value) {
        try {
            var v = new BigInteger(value);
            if (v.compareTo(BigInteger.ONE) > 0) {
                if ((v.compareTo(threshold) > 0) ? v.isProbablePrime(certainty) : isPrimeAccurate(v)) {
                    return Optional.of(v);
                }
            }
        } catch (NumberFormatException ignored) {
        }
        return Optional.empty();
    }
}

