package com.cupsoftware.tapestry;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.apache.tapestry5.ioc.Registry;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.InjectService;
import org.apache.wicket.Component;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.application.IComponentInitializationListener;
import org.apache.wicket.application.IComponentInstantiationListener;
import org.apache.wicket.injection.ConfigurableInjector;
import org.apache.wicket.injection.IFieldValueFactory;
import org.apache.wicket.protocol.http.WebApplication;

public class TapestryIOCInjector extends ConfigurableInjector implements IComponentInstantiationListener, IComponentInitializationListener {

    private final Registry registry;
    
    public TapestryIOCInjector(final Registry registry) {

        this.registry = registry;

        WebApplication app = registry.getService(WebApplication.class);
        
        app.setMetaData(RegistryHolder.REGISTRY_KEY, new RegistryHolder(registry));
    }
    
    @Override
    public Object inject(Object object) {

        Class<?> current = object.getClass();
        boolean isStatic;
        
        do {
            Field[] currentFields = current.getDeclaredFields();
            for (final Field field : currentFields) {
                Inject injectAnnotation = field.getAnnotation(Inject.class);

                isStatic = Modifier.isStatic(field.getModifiers());
                if (isStatic) {
                    continue;
                }
                
                if (injectAnnotation != null) {
                    try {
                        Object proxy = registry.getService(field.getType());

                        if (!field.isAccessible()) {
                            field.setAccessible(true);
                        }
                        field.set(object, proxy);
                        
                        continue;
                        
                    } catch (IllegalAccessException e) {
                        throw new WicketRuntimeException(
                                String.format("Error injecting Tapestry-Service %s into field %s", object, field.getName()), e);
                    }
                }

                InjectService injectServiceAnnotation = field.getAnnotation(InjectService.class);
                if (injectServiceAnnotation != null) {
                    try {
                        Object proxy = registry.getService(injectServiceAnnotation.value(), field.getType());

                        if (!field.isAccessible()) {
                            field.setAccessible(true);
                        }
                        field.set(object, proxy);
                    } catch (IllegalAccessException e) {
                        throw new WicketRuntimeException(
                                String.format("Error injecting Tapestry-Service %s into field %s", object, field.getName()), e);
                    }
                }
            }
            current = current.getSuperclass();
        }
        // Do a null check in case Object isn't in the current classloader.
        while (current != null && current != Object.class);

        return object;
    }

    @Override
    public void onInstantiation(Component component) {

        inject(component);
    }

    @Override
    public void onInitialize(Component component) {
    
        inject(component);
    }
    
    @Override
    protected IFieldValueFactory getFieldValueFactory() {

        return null;
    }

}
