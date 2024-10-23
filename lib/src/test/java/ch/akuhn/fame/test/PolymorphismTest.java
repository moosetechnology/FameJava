package ch.akuhn.fame.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ch.akuhn.fame.FameDescription;
import ch.akuhn.fame.FameProperty;
import ch.akuhn.fame.Tower;

public class PolymorphismTest {

    @FameDescription
    static class A {

        @FameProperty
        public Object p;

    }

    static class B extends A {

    }


    @Test
    public void addPolymorphElement() {
        Tower t = new Tower();
        t.metamodel.with(A.class);
        int metamodelSize = t.metamodel.size();
        assertEquals(0, t.model.size());
        t.model.add(new B());
        assertEquals(metamodelSize, t.metamodel.size());
        assertEquals(1, t.model.size());
    }
}
