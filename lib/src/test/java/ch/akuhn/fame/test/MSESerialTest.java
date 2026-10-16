package ch.akuhn.fame.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.Map;

import org.junit.Test;

import ch.akuhn.fame.Repository;
import ch.akuhn.fame.parser.InputSource;

public class MSESerialTest {

    @Test
    public void preservesElementSerialsAcrossMSEExportAndImport() {
        CompositeExample example = new CompositeExample();
        Repository source = example.model();
        CompositeExample.Container parent = example.newParent(source);
        CompositeExample.Leaf child = example.newChildA(source);
        parent.children.add(child);

        StringBuilder mse = new StringBuilder();
        Map<Object, Integer> exportedSerials = source.exportMSEWithSerials(mse);

        Repository target = example.model();
        Map<Integer, Object> importedSerials = target.importMSEWithSerials(InputSource.fromString(mse));

        assertEquals(source.size(), target.size());
        assertEquals(source.size(), exportedSerials.size());
        assertEquals(target.size(), importedSerials.size());
        assertSame(importedSerials.get(exportedSerials.get(child)),
                ((CompositeExample.Container) importedSerials.get(exportedSerials.get(parent))).children.iterator().next());
    }
}
