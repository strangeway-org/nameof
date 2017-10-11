package org.strangeway.nameof.model;

import org.strangeway.nameof.LangUtils;

import java.util.function.Function;

public class Person {
    public static String $(Function<Person, ?> bridge) {
        return LangUtils.nameOfProperty(Person.class, bridge);
    }

    private String name;
    private String summary;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}