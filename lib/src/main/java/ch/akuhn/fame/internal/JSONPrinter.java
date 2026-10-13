package ch.akuhn.fame.internal;

public class JSONPrinter extends AbstractPrintClient {

    /**
     * Constructor
     *
     * @param stream in which we have to write
     */
    public JSONPrinter(Appendable stream) {
        super(stream);
    }

    @Override
    public void beginAttribute(String name) {
        appendJSONString(name);
        append(":");

    }

    @Override
    public void beginDocument() {
        append("[");
    }

    @Override
    public void beginElement(String name) {
        append("{");
        append("\"FM3\":");
        appendJSONString(name);
    }

    public void beginMultivalue(String name) {
        append("[");
    }

    @Override
    public void directive(String name, String... params) {
    }

    @Override
    public void endAttribute(String name) {
    }

    @Override
    public void endDocument() {
        append("]");
        close();
    }

    @Override
    public void endElement(String name) {
        append("}");
    }

    public void endMultivalue(String name) {
        append("]");
    }

    @Override
    public void primitive(Object value) {
        if (value.getClass() == String.class){
            appendJSONString((String) value);
        } else if (value instanceof Boolean || value instanceof Number) {
            append(value.toString());
        } else {
            primitive(value.toString());
        }
    }

    @Override
    public void reference(int index) {
        append("{");
        append("\"ref\":");
        append(String.valueOf(index));
        append("}");
    }

    @Override
    public void reference(String name) {
        append("{");
        append("\"ref\":");
        appendJSONString(name);
        append("}");
    }

    @Override
    public void reference(String name, int index) {
    }

    @Override
    public void serial(int index) {
    	printPropertySeparator();
        append("\"id\":");
        append(String.valueOf(index));
    }

    public void printEntitySeparator() {
        append(",");
    }

    public void printPropertySeparator() {
        append(",");
    }

}
