// Automagically generated code, please do not change
package org.moosetechnology.model.famix.famixcentities;

import ch.akuhn.fame.FameDescription;
import ch.akuhn.fame.FamePackage;
import ch.akuhn.fame.FameProperty;
import org.moosetechnology.model.famix.famixtraits.TNamedEntity;


@FamePackage("Famix-C-Entities")
@FameDescription("UnknownBehaviour")
public class UnknownBehaviour extends Entity implements TNamedEntity {

    private String name;
    


    @FameProperty(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    


}

