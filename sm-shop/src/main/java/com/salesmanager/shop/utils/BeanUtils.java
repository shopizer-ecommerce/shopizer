package com.salesmanager.shop.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BeanUtils
{
 private BeanUtils(){
        
    }
    
    public static BeanUtils newInstance(){
        return new BeanUtils();
    }
    
    @SuppressWarnings( "nls" )
    public Object getPropertyValue( Object bean, String property )
        throws IntrospectionException, IllegalArgumentException, IllegalAccessException, InvocationTargetException
    {
        
        if (bean == null) {
            throw new IllegalArgumentException("No bean specified");
        }
        if(property == null){
            
            throw new IllegalArgumentException("No name specified for bean class '" + bean.getClass() + "'");
        }
        Class<?> beanClass = bean.getClass();
        PropertyDescriptor propertyDescriptor = getPropertyDescriptor( beanClass, property );
        if ( propertyDescriptor == null )
        {
            throw new IllegalArgumentException( "No such property " + property + " for " + beanClass + " exists" );
        }

        Method readMethod = propertyDescriptor.getReadMethod();
        if ( readMethod == null )
        {
            throw new IllegalStateException( "No getter available for property " + property + " on " + beanClass );
        }
        return readMethod.invoke( bean );
    }

    private PropertyDescriptor getPropertyDescriptor( Class<?> beanClass, String propertyname )
        throws IntrospectionException
    {
        BeanInfo beanInfo = Introspector.getBeanInfo( beanClass );
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        PropertyDescriptor propertyDescriptor = null;
        for ( int i = 0; i < propertyDescriptors.length; i++ )
        {
            PropertyDescriptor currentPropertyDescriptor = propertyDescriptors[i];
            if ( currentPropertyDescriptor.getName().equals( propertyname ) )
            {
                propertyDescriptor = currentPropertyDescriptor;
            }

        }
        return propertyDescriptor;
    }
    
}
