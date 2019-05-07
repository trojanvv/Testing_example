package main;

import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.util.Set;

public class AnnotationScanner {

    private static Reflections reflection;;

    public Set<Class<?>> getClassesAnnotatedWith(Class<? extends Annotation> annotation, String packegeName) {
        reflection = new Reflections(packegeName);
        return reflection.getTypesAnnotatedWith(annotation);
    }
}