package ch.akuhn.fame.codegen;

import java.io.IOException;
import java.util.Collection;
import java.util.TreeSet;

public abstract class GenFile {

    protected StringBuilder body;
    protected StringBuilder fields;
    protected Collection<String> imports;
    protected Collection<String> traits;
    protected String myPackage;
    protected String name;
    protected String superName;
    protected String modelPackagename;
    protected String modelClassname;

    public GenFile(String myPackage, String name) {
        this.myPackage = myPackage;
        this.name = name;
        this.body = new StringBuilder();
        this.fields = new StringBuilder();
        this.imports = new TreeSet<>();
        this.traits = new TreeSet<>();
    }

    public <T> void addImport(Class<T> tee) {
        this.addImport(tee.getPackage().getName(), tee.getSimpleName());
    }

    public void addImport(String aPackage, String className) {
        if (aPackage.equals(myPackage)) return;
        if (aPackage.equals("java.lang")) return;
        imports.add(aPackage + "." + className);
    }

    public void addSuperclass(String packageName, String superClassName) {
        if (superClassName.equals("Object") && packageName.equals("java.lang")) return;

        if (this.name.equals(superClassName)) {
            this.superName = packageName + "." + superClassName;
        } else {
            this.addImport(packageName, superClassName);
            this.superName = superClassName;
        }
    }

    public abstract void generateCode(Appendable stream) throws IOException;

    public StringBuilder getBodyContentStream() {
        return body;
    }

    public StringBuilder getFieldsContentStream() {
        return fields;
    }

    public String getImports() {
        StringBuilder stream = new StringBuilder();
        for (String each : imports) {
            stream.append("import ").append(each).append(";\n");
        }
        return stream.toString();
    }

    public void addTrait(String trait) {
        traits.add(trait);
    }

    public void setTraits(Collection<String> traits) {
        this.traits = traits;
    }

    public String getTraits() {
        if (traits.isEmpty())
            return "";
        return "implements " + String.join(", ", traits);
    }

    public String getModelClassname() {
        return modelClassname;
    }

    public String getModelPackagename() {
        return modelPackagename;
    }

    public void setModelClassname(String modelClassname) {
        this.modelClassname = modelClassname;
    }

    public void setModelPackagename(String modelPackagename) {
        this.modelPackagename = modelPackagename;
    }

}
