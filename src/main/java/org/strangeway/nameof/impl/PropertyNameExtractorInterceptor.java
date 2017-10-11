/*
 * Copyright (c) 2017 Yuriy Artamonov.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.strangeway.nameof.impl;

import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;

import java.lang.reflect.Method;

public class PropertyNameExtractorInterceptor {

    private static final ThreadLocal<String> currentExtractedMethodName = new ThreadLocal<>();

    @RuntimeType
    public static Object intercept(@Origin Method method) {
        currentExtractedMethodName.set(getPropertyName(method));

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

    public static String extractMethodName() {
        String methodName = currentExtractedMethodName.get();
        currentExtractedMethodName.remove();
        return methodName;
    }
}