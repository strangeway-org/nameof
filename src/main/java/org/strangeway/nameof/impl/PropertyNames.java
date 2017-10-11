package org.strangeway.nameof.impl;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.FieldAccessor;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

import static net.bytebuddy.matcher.ElementMatchers.named;

public final class PropertyNames {
    public static <T> T getPropertyNameExtractor(Class<T> type) {
        DynamicType.Builder<?> builder = new ByteBuddy()
                .subclass(type.isInterface() ? Object.class : type);

        if (type.isInterface()) {
            builder = builder.implement(type);
        }

        Class<?> proxyType = builder
                .implement(PropertyNameExtractor.class)
                .defineField("propertyName", String.class, Visibility.PRIVATE)
                .method(ElementMatchers.any())
                    .intercept(MethodDelegation.to(PropertyNameExtractorInterceptor.class))
                .method(named("setPropertyName")
                        .or(named("getPropertyName")))
                    .intercept(FieldAccessor.ofBeanProperty())
                .make()
                .load(
                        PropertyNameExtractor.class.getClassLoader(),
                        ClassLoadingStrategy.Default.WRAPPER
                )
                .getLoaded();

        try {
            @SuppressWarnings("unchecked")
            Class<T> typed = (Class<T>) proxyType;
            return typed.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(
                    "Couldn't instantiate proxy for method name retrieval", e
            );
        }
    }
}