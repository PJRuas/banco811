//package com.santander.banco811.annotations;
//
//import lombok.experimental.UtilityClass;
//
//import java.lang.annotation.Annotation;
//import java.lang.reflect.Field;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Map.Entry;
//
//@UtilityClass
//public class AnnotationService {
//
//    private static Map annotations = Map.ofEntries(
//            Map.entry(Format.class,  runFormatAnnotation())
//    );
//
//    public <G> Field[] getClassFields(G genericObject) {
//        return genericObject.getClass().getDeclaredFields();
//    }
//
//    public <G> Object execute(G genericObject) {
//
//        for (Field field : getClassFields(genericObject)) {
//            for (Object annotation : annotations.keySet()) {
//                Class clazz = annotation.getClass();
//                if (field.isAnnotationPresent(clazz)) {
//                    field.setAccessible(true);
//                    var test = annotations.get(annotation);
//                }
//            }
//        }
//        return
//    }
//
//    private void runFormatAnnotation(Field field){}
//    private void runDefaultAnnotation(Field field){}
//    private void runSimplifyAnnotation(Field field){}
//}
