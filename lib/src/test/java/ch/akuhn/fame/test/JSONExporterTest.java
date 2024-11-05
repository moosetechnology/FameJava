package ch.akuhn.fame.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.akuhn.fame.Tower;
import ch.akuhn.fame.codegen.target.DungeonCodegen;
import ch.akuhn.fame.test.DungeonExample.Hero;
import ch.akuhn.fame.test.DungeonExample.Treasure;

public class JSONExporterTest {

    private Tower t;
    private Appendable stream;

    @BeforeClass
    public static void runCodegen() {
        DungeonCodegen.main();
    }

    @Before
    public void setup() {
        t = new Tower();
        t.metamodel.withAll(Hero.class, Treasure.class);
        stream = new StringBuilder();
    }
    
    @Test
    public void testExport() {
        Hero h1 = new Hero();
        Treasure t1 = new Treasure();
        Treasure t2 = new Treasure();
        t1.setOwner(h1);
        t2.setOwner(h1);

        t.model.add(h1, t1, t2);
        
        t.model.exportJSON(stream);
        System.err.println(stream.toString());
        // [[{"FM3":"RPG.Hero","id":1,"talisman":{"ref":2}},{"FM3":"RPG.Treasure","id":2,"owner":{"ref":1}},{"FM3":"RPG.Treasure","id":3,}]]
        assertEquals("[{\"FM3\":\"RPG.Hero\",\"id\":1,\"talisman\":{\"ref\":2}},{\"FM3\":\"RPG.Treasure\",\"id\":2,\"owner\":{\"ref\":1}},{\"FM3\":\"RPG.Treasure\",\"id\":3,}]", stream.toString());
        
    }
    
}
