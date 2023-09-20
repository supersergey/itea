package com.example.demo.annotations;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@DummyAnnotation
class AnnotatedClassTest {
    @Test
    void shouldBeAbleToReadAnnotation() {
        assertThat(AnnotatedClassTest.class.isAnnotationPresent(DummyAnnotation.class)).isTrue();
    }

    @Test
    void shouldBeAbleToReadAnnotationFromField() {
        var testClass = new TestClass();
        Field[] fields = testClass.getClass().getDeclaredFields();

        var annotated = Arrays.stream(fields)
                .filter(field -> field.isAnnotationPresent(DummyAnnotation.class))
                .map(Field::getName)
                .toList();

        assertThat(annotated).containsExactly("annotatedField");
    }

    static class TestClass {
        @DummyAnnotation
        private String annotatedField;
        private String nonAnnotatedField;
    }

}
