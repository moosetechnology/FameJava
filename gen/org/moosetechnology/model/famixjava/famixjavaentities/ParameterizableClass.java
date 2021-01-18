// Automagically generated code, please do not change
package org.moosetechnology.model.famixjava.famixjavaentities;

import ch.akuhn.fame.FameDescription;
import ch.akuhn.fame.FamePackage;
import ch.akuhn.fame.FameProperty;
import ch.akuhn.fame.internal.MultivalueSet;
import java.util.*;

import org.moosetechnology.model.famixjava.famixtraits.TParameterType;
import org.moosetechnology.model.famixjava.famixtraits.TParameterizedType;
import org.moosetechnology.model.famixjava.famixtraits.TWithParameterizedTypes;


@FamePackage("Famix-Java-Entities")
@FameDescription("ParameterizableClass")
public class ParameterizableClass extends Class implements TWithParameterizedTypes {

    private Collection<TParameterizedType> parameterizedTypes; 

    @FameProperty(name = "parameterizedTypes", opposite = "parameterizableClass", derived = true)
    public Collection<TParameterizedType> getParameterizedTypes() {
        if (parameterizedTypes == null) {
            parameterizedTypes = new MultivalueSet<TParameterizedType>() {
                @Override
                protected void clearOpposite(TParameterizedType e) {
                    e.setParameterizableClass(null);
                }
                @Override
                protected void setOpposite(TParameterizedType e) {
                    e.setParameterizableClass(ParameterizableClass.this);
                }
            };
        }
        return parameterizedTypes;
    }
    
    public void setParameterizedTypes(Collection<? extends TParameterizedType> parameterizedTypes) {
        this.getParameterizedTypes().clear();
        this.getParameterizedTypes().addAll(parameterizedTypes);
    }                    
    
        
    public void addParameterizedTypes(TParameterizedType one) {
        this.getParameterizedTypes().add(one);
    }   
    
    public void addParameterizedTypes(TParameterizedType one, TParameterizedType... many) {
        this.getParameterizedTypes().add(one);
        for (TParameterizedType each : many)
            this.getParameterizedTypes().add(each);
    }   
    
    public void addParameterizedTypes(Iterable<? extends TParameterizedType> many) {
        for (TParameterizedType each : many)
            this.getParameterizedTypes().add(each);
    }   
                
    public void addParameterizedTypes(TParameterizedType[] many) {
        for (TParameterizedType each : many)
            this.getParameterizedTypes().add(each);
    }
    
    public int numberOfParameterizedTypes() {
        return getParameterizedTypes().size();
    }

    public boolean hasParameterizedTypes() {
        return !getParameterizedTypes().isEmpty();
    }



    private Collection<TParameterType> parameterTypes; 

    @FameProperty(name = "ParameterTypes")
    public Collection<TParameterType> getParameters() {
        if (parameterTypes == null) {
            parameterTypes = new ArrayList<TParameterType>();
        }
        return parameterTypes;
    }
    
    public void setParameters(Collection<? extends TParameterType> ParameterTypes) {
        this.getParameters().clear();
        this.getParameters().addAll(ParameterTypes);
    }                    
    
        
    public void addParameters(TParameterType one) {
        this.getParameters().add(one);
    }   
    
    public void addParameters(TParameterType one, TParameterType... many) {
        this.getParameters().add(one);
        for (TParameterType each : many)
            this.getParameters().add(each);
    }   
    
    public void addParameters(Iterable<? extends TParameterType> many) {
        for (TParameterType each : many)
            this.getParameters().add(each);
    }   
                
    public void addParameters(TParameterType[] many) {
        for (TParameterType each : many)
            this.getParameters().add(each);
    }
    
    public int numberOfParameters() {
        return getParameters().size();
    }

    public boolean hasParameters() {
        return !getParameters().isEmpty();
    }

}

