package ch.akuhn.fame.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ch.akuhn.fame.FameDescription;
import ch.akuhn.fame.FamePackage;
import ch.akuhn.fame.FameProperty;
import ch.akuhn.fame.Tower;
import ch.akuhn.fame.fm3.MetaDescription;
import ch.akuhn.fame.fm3.PropertyDescription;

@FamePackage("Test")
public class ArrayFieldTest {

    @FameDescription
    private static class Dummy {
        @FameProperty
        public float[] array;
    }
    
    @Test
    public void metamodel() {
        Tower t = new Tower();
        t.metamodel.with(Dummy.class);
        assertEquals(1, t.metamodel.allClassDescriptions().size());
        assertEquals(1, t.metamodel.allPropertyDescriptions().size());
        PropertyDescription prop = t.metamodel.allPropertyDescriptions().iterator().next();
        assertEquals(true, prop.isMultivalued());
        assertEquals(true, prop.isPrimitive());
        assertEquals(MetaDescription.NUMBER, prop.getType());
    }
    
    @Test
    public void exportModel() {
        Tower t = new Tower();
        t.metamodel.with(Dummy.class);
        assertEquals(0, t.model.size());
        Dummy d = new Dummy();
        d.array = new float[] { 0.5f, 1.0f, 2.75f };
        t.model.add(d);
        assertEquals(1, t.model.size());
        String mse = t.model.exportMSE();
        assertTrue(mse.contains("(array 0.5 1.0 2.75)"));
    }

    @Test
    public void importModel() {
        // Init
        Tower t = new Tower();
        t.metamodel.with(Dummy.class);
        Dummy d = new Dummy();
        d.array = new float[] { 0.5f, 1.0f, 2.75f };
        t.model.add(d);
        String mse = t.model.exportMSE();

        // Tests
        t = new Tower();
        t.metamodel.with(Dummy.class);
        assertEquals(0, t.model.size());
        t.model.importMSE(mse);
        assertEquals(1, t.model.size());
        Dummy dummy = (Dummy) t.model.getElements().iterator().next();
        assertEquals(3, dummy.array.length);
        assertTrue(0.5 == dummy.array[0]);
        assertTrue(1.0 == dummy.array[1]);
        assertTrue(2.75 == dummy.array[2]);
        
    }    
    
}
