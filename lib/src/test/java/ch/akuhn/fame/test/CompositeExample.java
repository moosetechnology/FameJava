package ch.akuhn.fame.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import ch.akuhn.fame.FameDescription;
import ch.akuhn.fame.FamePackage;
import ch.akuhn.fame.FameProperty;
import ch.akuhn.fame.MetaRepository;
import ch.akuhn.fame.Repository;
import ch.akuhn.fame.Tower;
import ch.akuhn.fame.fm3.PropertyDescription;


public class CompositeExample {

    @FameDescription
    @FamePackage("TEST")
    static abstract class Composite {

        @FameProperty(opposite = "children")
        public Container parent;

        @FameProperty(derived = true)
        public abstract int getTotalCount();

    }

    @FameDescription
    @FamePackage("TEST")
    static class Container extends Composite {

        @FameProperty(opposite = "parent")
        public Collection<Composite> children = new ArrayList<>();

        @Override
        public int getTotalCount() {
            int count = 0;
            for (Composite c : children)
                count += c.getTotalCount();
            return count;
        }

    }

    @FameDescription
    @FamePackage("TEST")
    static class Leaf extends Composite {

        @FameProperty
        public int count;

        @Override
        public int getTotalCount() {
            return count;
        }

    }

    public Tower createTower() {
        Tower t = new Tower();
        return t;
    }

    @Test
    public void testCreateTower() {
        Tower t = createTower();
        assertNotNull(t.getMetaMetamodel());
        assertNotNull(t.getMetamodel());
        assertNotNull(t.getModel());
    }
    
    @Test
    public void towerHasLayers() {
        Tower t = this.createTower();
        assertEquals(t.getModel().getMetamodel(), t.getMetamodel());
        assertEquals(t.getMetamodel().getMetamodel(), t.getMetaMetamodel());
        assertEquals(t.getMetaMetamodel().getMetamodel(), t.getMetaMetamodel());
    }
    
    @Test
    public void tower() {
        Tower t = this.createTower();
        assertEquals(0, t.getModel().size());
        assertEquals(0, t.getMetamodel().size());
        assertEquals(31, t.getMetaMetamodel().size());
    }
    
    // @Test
    // @Ignore // FIXME
    // public Repository createInstances(Repository $) {
    //     assertEquals(3, $.getElements().size());
    //     return $;
    // }

    public String exportMSE() {
        Repository m = model();
        setParentWithChildren(m);
        String mse = m.exportMSE();
        return mse;
    }

    @Test
    public void testExportMSE() {
        String mse = exportMSE();
        // 165 is the magic number of the size of the string. I wanted to test that the string size is more than 2 char (not empty model)
        assertEquals(165, mse.length());

    }

    @Test
    public void importMSE() {
        String mse = exportMSE();
        Repository model = model();
        model.importMSE(mse);
        assertEquals(3, model.getElements().size());
    }

    public MetaRepository metamodel () {
        Tower t = new Tower();
        t.getMetamodel().withAll(Composite.class, Container.class, Leaf.class);
        MetaRepository $ = t.getMetamodel();
        return $;
    }

    @Test
    public void testMetamodel() {
        MetaRepository $ = metamodel();
        assertEquals(3, $.allClassDescriptions().size());
        assertEquals(4, $.all(PropertyDescription.class).size());
    }

    public Repository model() {
        MetaRepository metamodel = metamodel();
        Repository $ = new Repository(metamodel);
        return $;
    }

    @Test
    public void testModel() {
        MetaRepository metamodel = metamodel();
        Repository $ = new Repository(metamodel);
        assertEquals(metamodel, $.getMetamodel());
    }


    @Test
    public void testMetamodelNames() {
        MetaRepository metamodel = metamodel();
        assertNull(metamodel.descriptionNamed("FAME"));
        assertNotNull(metamodel.descriptionNamed("TEST.Container"));
        assertNotNull(metamodel.descriptionNamed("TEST.Leaf"));
        assertNotNull(metamodel.descriptionNamed("TEST.Composite"));
    }

    public Leaf newChildA(Repository repo) {
        Object $ = repo.newInstance("TEST.Leaf");
        return (Leaf) $;
    }

    @Test
    public void testNewChildA() {
        Object $ = newChildA(model());
        assertNotNull($);
        assertEquals(Leaf.class, $.getClass());
    }

    public Leaf newChildB(Repository repo) {
        Object $ = repo.newInstance("TEST.Leaf");
        return (Leaf) $;
    }

    @Test
    public void testNewChildB() {
        Object $ = newChildB(model());
        assertNotNull($);
        assertEquals(Leaf.class, $.getClass());
    }

    public Container newParent(Repository repo) {
        Object $ = repo.newInstance("TEST.Container");
        return (Container) $;
    }

    @Test
    public void testNewParent() {
        Object $ = newParent(model());
        assertNotNull($);
        assertEquals(Container.class, $.getClass());
        
    }

    @Test
    public void numberPropertyA() {
        Repository m = model();
        Leaf a = newChildA(m);
        assertEquals((Integer) 0, (Integer) a.count);
        assertEquals((Integer) 0, (Integer) m.read("count", a));
        m.write("count", a, 42);
        assertEquals(42, a.count);
        assertEquals((Integer)42, (Integer)m.read("count", a));
    }

    @Test
    public void numberPropertyB() {
        Repository m = model();
        Leaf b = newChildB(m);
        assertEquals(0, b.count);
        assertEquals((Integer) 0, m.read("count", b));
        m.write("count", b, 23);
        assertEquals(23, b.count);
        assertEquals((Integer) 23, m.read("count", b));
    }

    @Test
    public void parentChildrenAreOpposite() {
        MetaRepository metamodel = metamodel();
        PropertyDescription parent = metamodel.descriptionNamed("TEST.Composite").attributeNamed("parent");
        PropertyDescription children = metamodel.descriptionNamed("TEST.Container").attributeNamed("children");
        assertNotNull(parent);
        assertNotNull(children);
        assertTrue(parent.hasOpposite());
        assertTrue(children.hasOpposite());
        assertEquals(parent, children.getOpposite());
        assertEquals(children, parent.getOpposite());
    }

    public void setParentWithChildren(Repository m) {
        Container p = newParent(m);
        Leaf a = newChildA(m);
        Leaf b = newChildB(m);
        m.write("children", p, a, b);
        }

    @Test
    public void parentWithChildren() {
        Repository m = model();
        Container p = newParent(m);
        Leaf a = newChildA(m);
        Leaf b = newChildB(m);
        assertEquals(0, p.children.size());
        assertEquals(null, a.parent);
        assertEquals(null, b.parent);
        m.write("children", p, a, b);
        assertEquals(2, p.children.size());
        assertEquals(p, a.parent);
        assertEquals(p, b.parent);
    }
}
