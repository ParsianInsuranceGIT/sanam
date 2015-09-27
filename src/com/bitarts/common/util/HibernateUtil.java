package com.bitarts.common.util;

import com.bitarts.common.hibernate.BaseDAO;
import com.bitarts.parsian.service.IAsnadeSodorService;
import com.bitarts.parsian.service.factory.InsuranceServiceFactory;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.hql.QueryTranslator;
import org.hibernate.hql.QueryTranslatorFactory;
import org.hibernate.hql.ast.ASTQueryTranslatorFactory;

import java.util.Collections;

/**
 * User: a.sabzechian
 * Date: 5/14/14
 * Time: 1:15 PM
 */
public class HibernateUtil {

    public static String hqlToSql(String hql){
        return InsuranceServiceFactory.getWithoutSessionAsnadeSodorService().hqlToSql(hql);
    }
}
