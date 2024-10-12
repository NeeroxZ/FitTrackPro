package de.neeroxz.util;

@FunctionalInterface
public interface ValidatorFunction<T> {
    boolean validate(T input);
}
