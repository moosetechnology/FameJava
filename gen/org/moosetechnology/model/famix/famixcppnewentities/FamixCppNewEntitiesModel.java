// Automagically generated code, please do not change
package org.moosetechnology.model.famix.famixcppnewentities;

import ch.akuhn.fame.MetaRepository;

public class FamixCppNewEntitiesModel {

    public static MetaRepository metamodel() {
        MetaRepository metamodel = new MetaRepository();
        importInto(metamodel);
        return metamodel;
    }
    
    public static void importInto(MetaRepository metamodel) {
		metamodel.with(org.moosetechnology.model.famix.famixcppnewentities.Class.class);
		metamodel.with(org.moosetechnology.model.famix.famixcppnewentities.CppNewModel.class);
		metamodel.with(org.moosetechnology.model.famix.famixcppnewentities.Entity.class);
		metamodel.with(org.moosetechnology.model.famix.famixcppnewentities.ImplicitVar.class);
		metamodel.with(org.moosetechnology.model.famix.famixcppnewentities.Inheritance.class);
		metamodel.with(org.moosetechnology.model.famix.famixcppnewentities.Method.class);
		metamodel.with(org.moosetechnology.model.famix.famixcppnewentities.Namespace.class);

    }

}

