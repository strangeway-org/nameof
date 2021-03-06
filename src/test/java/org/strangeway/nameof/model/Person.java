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
package org.strangeway.nameof.model;

import java.util.function.Function;

import static org.strangeway.nameof.LangUtils.nameOfProperty;

public class Person {
    public static String $(Function<Person, ?> bridge) {
        return nameOfProperty(Person.class, bridge);
    }

    public static String NAME() {
        return nameOfProperty(Person.class, Person::getName);
    }

    public static String SUMMARY() {
        return nameOfProperty(Person.class, Person::getSummary);
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

    public String generateSummary() {
        return "Demo";
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}