Java nameOfProperty implementation
==================================

Java alternative to nameOf operator. Based on Byte Buddy library.

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