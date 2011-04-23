package org.awknet.commons.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

@SuppressWarnings("deprecation")
public class HibernateUtil {

    private static final SessionFactory factory;

    static {
        try {
            Configuration conf = new AnnotationConfiguration();
            conf.configure();
            factory = conf.buildSessionFactory();

        } catch (Throwable ex) {
            System.err.println("Ow CRAP! Initial Hibernate Session Factory creation failed!! Motive --> " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() {
        return factory.openSession();
    }

    public static SessionFactory getSessionFactory() {
        return factory;
    }

}