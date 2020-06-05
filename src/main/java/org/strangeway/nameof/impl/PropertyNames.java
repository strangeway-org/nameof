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

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public final class PropertyNames {
    @SuppressWarnings("unchecked")
    public static <T> T getPropertyNameExtractor(Class<T> type) {
        DynamicType.Builder<?> builder = new ByteBuddy(ClassFileVersion.JAVA_V8)
                .subclass(type.isInterface() ? Object.class : type);

        if (type.isInterface()) {
            builder = builder.implement(type);
        }

        Class<?> proxyType = builder
                .method(ElementMatchers.any())
                .intercept(MethodDelegation.to(PropertyNameExtractorInterceptor.class))
                .make()
                .load(
                        PropertyNames.class.getClassLoader(),
                        ClassLoadingStrategy.Default.WRAPPER
                )
                .getLoaded();

        try {
            Constructor<?> constructor = proxyType.getConstructor();
            return (T) constructor.newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(
                    "Couldn't instantiate proxy for method name retrieval", e
            );
        }
    }
}