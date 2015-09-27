package com.bitarts.common.hibernate;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;
import org.springframework.util.ClassUtils;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 5/26/11
 * Time: 8:40 PM
 */
public class ExtendedAnnotationSessionFactoryBean extends AnnotationSessionFactoryBean {

    private String[] basePackages;
    private ClassLoader beanClassLoader;
    private String schemaExportOutputFile;

    public void setBasePackages( String[] basePackages ) {
        this.basePackages = basePackages;
    }

    public void setAnnotatedPackages( String annotatedPackages ) {
        this.basePackages = new String[] { annotatedPackages };
    }

    public void setSchemaExportOutputFile( String schemaExportOutputFile ) {
        this.schemaExportOutputFile = schemaExportOutputFile;
    }

    public void setBeanClassLoader( ClassLoader beanClassLoader ) {
        this.beanClassLoader = beanClassLoader;
    }

    public void afterPropertiesSet() throws Exception {
        Collection<Class<?>> entities = new ArrayList<Class<?>>();
        ClassPathScanningCandidateComponentProvider scanner = this.createScanner();
        for ( String basePackage : this.basePackages ) {
            this.findEntities( scanner, entities, basePackage );
        }
        this.setAnnotatedClasses( entities.toArray( new Class<?>[entities.size()] ) );
        this.setAnnotatedPackages( this.basePackages );
        super.afterPropertiesSet();
        if(schemaExportOutputFile != null){
            export(this.getConfiguration());
        }
    }

    private ClassPathScanningCandidateComponentProvider createScanner() {
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider( false );
        scanner.addIncludeFilter( new AnnotationTypeFilter( Entity.class ) );
        return scanner;
    }

    private void findEntities( ClassPathScanningCandidateComponentProvider scanner,
                               Collection<Class<?>> entities, String basePackage ){

        Set<BeanDefinition> annotatedClasses = scanner.findCandidateComponents( basePackage );
        for ( BeanDefinition bd : annotatedClasses ) {
            String className = bd.getBeanClassName();
            Class<?> type = ClassUtils.resolveClassName(className, this.beanClassLoader);
            entities.add( type );
        }
    }

    private void export(Configuration configuration){
        SchemaExport schemaExport = new SchemaExport(configuration);
        schemaExport.setOutputFile(schemaExportOutputFile);
        schemaExport.setDelimiter(";");
        schemaExport.setFormat(true);
        schemaExport.execute(true, false, false, true);
    }
}
