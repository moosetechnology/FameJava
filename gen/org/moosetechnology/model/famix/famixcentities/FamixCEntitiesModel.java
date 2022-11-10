// Automagically generated code, please do not change
package org.moosetechnology.model.famix.famixcentities;

import ch.akuhn.fame.MetaRepository;

public class FamixCEntitiesModel {

    public static MetaRepository metamodel() {
        MetaRepository metamodel = new MetaRepository();
        importInto(metamodel);
        return metamodel;
    }
    
    public static void importInto(MetaRepository metamodel) {
		metamodel.with(org.moosetechnology.model.famix.famixcentities.Access.class);
		metamodel.with(org.moosetechnology.model.famix.famixcentities.AliasType.class);
		metamodel.with(org.moosetechnology.model.famix.famixcentities.Attribute.class);
		metamodel.with(org.moosetechnology.model.famix.famixcentities.BehaviouralReference.class);
		metamodel.with(org.moosetechnology.model.famix.famixcentities.CModel.class);
		metamodel.with(org.moosetechnology.model.famix.famixcentities.DerefInvocation.class);
		metamodel.with(org.moosetechnology.model.famix.famixcentities.Entity.class);
		metamodel.with(org.moosetechnology.model.famix.famixcentities.Enum.class);
		metamodel.with(org.moosetechnology.model.famix.famixcentities.EnumValue.class);
		metamodel.with(org.moosetechnology.model.famix.famixcentities.Function.class);
		metamodel.with(org.moosetechnology.model.famix.famixcentities.GlobalVar.class);
		metamodel.with(org.moosetechnology.model.famix.famixcentities.Invocation.class);
		metamodel.with(org.moosetechnology.model.famix.famixcentities.LocalVar.class);
		metamodel.with(org.moosetechnology.model.famix.famixcentities.Parameter.class);
		metamodel.with(org.moosetechnology.model.famix.famixcentities.PrimitiveType.class);
		metamodel.with(org.moosetechnology.model.famix.famixcentities.Reference.class);
		metamodel.with(org.moosetechnology.model.famix.famixcentities.Struct.class);
		metamodel.with(org.moosetechnology.model.famix.famixcentities.Type.class);
		metamodel.with(org.moosetechnology.model.famix.famixcentities.Union.class);
		metamodel.with(org.moosetechnology.model.famix.famixcentities.UnknownBehaviour.class);

    }

}

