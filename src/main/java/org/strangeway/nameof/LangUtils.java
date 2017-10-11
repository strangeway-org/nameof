package org.strangeway.nameof;

import org.strangeway.nameof.impl.PropertyNameExtractor;
import org.strangeway.nameof.impl.PropertyNames;

import java.util.function.Function;

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