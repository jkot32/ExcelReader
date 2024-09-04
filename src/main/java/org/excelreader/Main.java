package org.excelreader;

import org.excelreader.excel.ExcelRowIterator;
import org.excelreader.validators.PrimeChecker;

import java.math.BigInteger;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.*;

public class Main {
    private static final Logger log = initLogger();
    private static Logger initLogger() {
        var logger = Logger.getLogger(Main.class.getName());
        var consoleHandler = new ConsoleHandler();
        Formatter formatter = new SimpleFormatter() {
            private final String format = "%1$tF %1$tT.%1$tL [%2$s] %3$s %n";
            @Override
            public synchronized String format(LogRecord lr) {
                return String.format(format, new Date(lr.getMillis()), lr.getLevel().getLocalizedName(),
                        lr.getMessage());
            }
        };
        consoleHandler.setFormatter(formatter);
        logger.setUseParentHandlers(false);
        logger.addHandler(consoleHandler);
        return logger;
    }
    public static void main(String[] args) {
        if (args.length != 1) {
            log.log(Level.SEVERE, "Invalid arguments, expecting file path");
            log.log(Level.INFO, "Args:" + String.join(" ", args));
            return;
        }
        String filename = args[0];
        var pc = new PrimeChecker(BigInteger.valueOf(10000), 4);
        var iterator = new ExcelRowIterator<>(filename, pc);

        if (!iterator.isLoaded()) {
            log.severe("Unable to open file: "+ args[0]);
            return;
        }
        AtomicInteger found = new AtomicInteger();
        iterator.iterate((value) -> {
            found.getAndIncrement();
            log.info(value.toString());
        });
        log.info(String.format("Found %d prime numbers", found.get()));
    }
}