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
package org.strangeway.nameof;

import org.strangeway.nameof.impl.PropertyNameExtractor;
import org.strangeway.nameof.impl.PropertyNames;

import java.util.function.Function;

/**
 * Utility class that can provide name of bean property using method reference of a getter.
 */
public final class LangUtils {
    public static <T> String nameOfProperty(Class<T> clazz, Function<? super T, ?> bridge) {
        T extractor = PropertyNames.getPropertyNameExtractor(clazz);
        bridge.apply(extractor);
        return ((PropertyNameExtractor) extractor).getPropertyName();
    }

    public static <T> String $$(Class<T> clazz, Function<T, ?> bridge) {
        return nameOfProperty(clazz, bridge);
    }
}