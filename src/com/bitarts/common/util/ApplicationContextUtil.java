package com.bitarts.common.util;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.access.BeanFactoryLocator;
import org.springframework.beans.factory.access.BeanFactoryReference;
import org.springframework.beans.factory.access.SingletonBeanFactoryLocator;


public class ApplicationContextUtil {

    public static BeanFactory getBaseFactory() {
        BeanFactoryLocator bfLocator = SingletonBeanFactoryLocator.getInstance();
        BeanFactoryReference bfReference = bfLocator.useBeanFactory("mainContext");
        BeanFactory factory = bfReference.getFactory();
        return factory;
    }

}

