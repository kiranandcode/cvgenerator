package com.gopiandcode.graphics.components;

@FunctionalInterface
public interface TabNameGenerator<T> {
   String generateName(T item);
}
