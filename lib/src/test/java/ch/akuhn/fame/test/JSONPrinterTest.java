package ch.akuhn.fame.test;

import ch.akuhn.fame.MetaRepository;
import ch.akuhn.fame.Tower;
import ch.akuhn.fame.internal.JSONPrettyPrinter;
import ch.akuhn.fame.internal.JSONPrinter;
import ch.akuhn.fame.parser.InputSource;
import junit.framework.TestCase;

public class JSONPrinterTest extends TestCase {

    private JSONPrettyPrinter printer;
    private Appendable stream;

    /** FIXME
     * This is not a test but a method to export a meta-model
     */
    public void testExportJSON() {
        InputSource input = InputSource.fromResource("ch/unibe/fame/resources/lib.mse");
        Tower t = new Tower();
        t.getMetamodel().importMSE(input);
        Appendable stream = new StringBuilder();
        t.metamodel.exportJSON(stream);
        System.out.println(stream);
    }

    private static String removeWhiteSpaces(String input) {
        return input.replaceAll("\\s+", "");
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        stream = new StringBuilder();
        printer = new JSONPrettyPrinter(stream);
    }

    public void testBeginAttributeSimple() {
        printer.beginAttribute("hello");
        assertEquals("\"hello\":", stream.toString());
    }

    public void testPrimitive() {
        printer.primitive("value");
        assertEquals("\"value\"", stream.toString());
    }

    public void testPrimitiveWithSpecialCharacter() {
        printer.primitive("MySuper\"String");
        assertEquals("\"MySuper\\\"String\"", stream.toString());
    }

    public void testPrimitiveWithSpecialCharacterAndActualExample() {
        printer.primitive("print(\"Printer \" + name() + \" prints \"+ thePacket.contents(),false)");
        assertEquals( "\"print(\\\"Printer \\\" + name() + \\\" prints \\\"+ thePacket.contents(),false)\"", stream.toString());
    }

    public void testReference() {
        printer.reference("hello");
        assertEquals("{\"ref\":\"hello\"}", removeWhiteSpaces(stream.toString()));
    }

    public void testReferenceIndex() {
        printer.reference(2);
        assertEquals("{\"ref\":2}", removeWhiteSpaces(stream.toString()));
    }

    public void testSerial() {
        printer.serial(2);
        assertEquals(",\"id\":2", removeWhiteSpaces(stream.toString()));
    }

    public void testBeginElement() {
        printer.beginElement("Java.Class");
        assertEquals("{\"FM3\":\"Java.Class\"", removeWhiteSpaces(stream.toString()));
    }

    public void testEmptyEntityPrettyPrinter() {
        String str = "((FM3.Package))";
        Tower t = new Tower();
        t.getMetamodel().importMSE(str);
        MetaRepository repo = t.getMetamodel();
        repo.accept( printer);
        assertEquals("[{\"FM3\":\"FM3.Package\",\"id\":1}]", removeWhiteSpaces(stream.toString()));
    }

    public void testEmptyEntityJSONPrinter() {
        String str = "((FM3.Package))";
        Tower t = new Tower();
        t.getMetamodel().importMSE(str);
        MetaRepository repo = t.getMetamodel();
        repo.accept( new JSONPrinter(stream));
        assertEquals("[{\"FM3\":\"FM3.Package\",\"id\":1}]", stream.toString());
    }

    public void testEntityWithAttributePrettyPrinter() {
        String str = "((FM3.Package (name 'Blah')))";
        Tower t = new Tower();
        t.getMetamodel().importMSE(str);
        MetaRepository repo = t.getMetamodel();
        repo.accept( printer);
        assertEquals("[{\"FM3\":\"FM3.Package\",\"id\":1,\"name\":\"Blah\"}]", removeWhiteSpaces(stream.toString()));
    }

    public void testEntityWithAttributeJSONPrinter() {
        String str = "((FM3.Package (name 'Blah')))";
        Tower t = new Tower();
        t.getMetamodel().importMSE(str);
        MetaRepository repo = t.getMetamodel();
        repo.accept( new JSONPrinter(stream));
        assertEquals("[{\"FM3\":\"FM3.Package\",\"id\":1,\"name\":\"Blah\"}]", stream.toString());
    }

}