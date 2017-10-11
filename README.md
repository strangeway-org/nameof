Java nameOfProperty implementation
==================================

Java alternative to nameOf operator. 

[![license](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0)
[![Download](https://api.bintray.com/packages/strangeway-org/libs/nameof/images/download.svg) ](https://bintray.com/strangeway-org/libs/nameof/_latestVersion)

Based on Byte Buddy library. 

Inspired by: http://in.relation.to/2016/04/14/emulating-property-literals-with-java-8-method-references/

Examples:
```java
public class NameOfTest {
    @Test
    public void direct() {
        assertEquals("name", $$(Person.class, Person::getName));
    }

    @Test
    public void properties() {
        assertEquals("summary", Person.$(Person::getSummary));
    }
}
```