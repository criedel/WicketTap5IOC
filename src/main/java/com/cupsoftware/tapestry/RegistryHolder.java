package com.cupsoftware.tapestry;

import org.apache.tapestry5.ioc.Registry;
import org.apache.wicket.IClusterable;
import org.apache.wicket.MetaDataKey;

public class RegistryHolder implements IClusterable {

    private static final long serialVersionUID = 2838728823426130843L;

    /**
     * Metadata key used to store Registry holder in application's metadata
     */
    public static MetaDataKey<RegistryHolder> REGISTRY_KEY = new MetaDataKey<RegistryHolder>() {

        private static final long serialVersionUID = 5364260722261959308L;

    };

    private final Registry registry;

    /**
     * Constructor
     * 
     * @param registry
     */
    public RegistryHolder(Registry registry) {

        this.registry = registry;
    }

    public Registry getRegistry() {

        return registry;
    }

}
