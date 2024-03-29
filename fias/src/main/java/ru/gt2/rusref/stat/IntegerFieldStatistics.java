package ru.gt2.rusref.stat;

import ru.gt2.rusref.FieldType;

import java.io.PrintStream;
import java.lang.reflect.Field;
import java.math.BigInteger;

public class IntegerFieldStatistics extends ObjectFieldStatistics<Integer> {
    private int min = Integer.MAX_VALUE;
    private int max = Integer.MIN_VALUE;
    private BigInteger sum = BigInteger.ZERO;

    @Override
    protected void doUpdateStatistics(Integer value) {
        super.doUpdateStatistics(value);

        if (null == value) {
            return;
        }

        min = Math.min(min, value);
        max = Math.max(max, value);
        sum = sum.add(BigInteger.valueOf(value));
    }

    public IntegerFieldStatistics(Field field, FieldType fieldType) {
        super(field, fieldType);
    }

    @Override
    public void print(PrintStream printStream) {
        super.print(printStream);
        if (0 == notNullCount) {
            return;
        }
        printStream.print(", range = " + min + " … " + max);
        printStream.print(", average = " + getAverage(sum));
    }

    @Override
    public void fillReportParts(Object[] parts) {
        super.fillReportParts(parts);
        fillRanges(parts, min, max, sum);
    }
}
