// Automagically generated code, please do not change
package org.moosetechnology.model.famix.famix;

import ch.akuhn.fame.FameDescription;
import ch.akuhn.fame.FamePackage;
import ch.akuhn.fame.FameProperty;
import org.moosetechnology.model.famix.famixtraits.TCaughtException;
import org.moosetechnology.model.famix.famixtraits.TWithCaughtExceptions;


@FamePackage("FAMIX")
@FameDescription("CaughtException")
public class CaughtException extends Exception implements TCaughtException {

    private TWithCaughtExceptions definingEntity;
    


    @FameProperty(name = "definingEntity", opposite = "caughtExceptions")
    public TWithCaughtExceptions getDefiningEntity() {
        return definingEntity;
    }

    public void setDefiningEntity(TWithCaughtExceptions definingEntity) {
        if (this.definingEntity != null) {
            if (this.definingEntity.equals(definingEntity)) return;
            this.definingEntity.getCaughtExceptions().remove(this);
        }
        this.definingEntity = definingEntity;
        if (definingEntity == null) return;
        definingEntity.getCaughtExceptions().add(this);
    }
    


}

