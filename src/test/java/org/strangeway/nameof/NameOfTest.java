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

import org.junit.Test;
import org.strangeway.nameof.model.Person;

import static org.junit.Assert.assertEquals;
import static org.strangeway.nameof.LangUtils.$$;

public class NameOfTest {
    @Test
    public void direct() {
        assertEquals("name", $$(Person.class, Person::getName));
    }

    @Test
    public void properties() {
        assertEquals("summary", Person.$(Person::getSummary));
        assertEquals("name", Person.$(Person::getName));
    }

    @Test
    public void constants() {
        assertEquals("summary", Person.SUMMARY());
        assertEquals("name", Person.NAME());
    }

    @Test(expected = RuntimeException.class)
    public void exception() {
        assertEquals("summary", $$(Person.class, Person::generateSummary));
    }
}