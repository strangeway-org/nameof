package org.strangeway.names.impl;

import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.This;

import java.lang.reflect.Method;

public class PropertyNameExtractorInterceptor {
    @RuntimeType
    public static Object intercept(@This PropertyNameExtractor extractor, @Origin Method method,
                                   @AllArguments Object[] args) {
        extractor.setPropertyName(getPropertyName(method));

        if (method.getReturnType() == byte.class) {
            return (byte) 0;
        }
        if (method.getReturnType() == int.class) {
            return 0;
        }
        if (method.getReturnType() == long.class) {
            return (long) 0;
        }
        if (method.getReturnType() == char.class) {
            return (char) 0;
        }
        if (method.getReturnType() == short.class) {
            return (short) 0;
        }
        return null;
    }

    private static String getPropertyName(Method method) {
        boolean hasGetterSignature = method.getParameterTypes().length == 0
                && method.getReturnType() != null;

        String name = method.getName();
        String propName = null;

        if (hasGetterSignature) {
            if (name.startsWith("get")) {
                propName = name.substring(3, 4).toLowerCase() + name.substring(4);
            } else if (name.startsWith("is")) {
                propName = name.substring(2, 3).toLowerCase() + name.substring(3);
            }
        } else {
            throw new RuntimeException("Only property getter methods are expected to be passed");
        }

        return propName;
    }
}