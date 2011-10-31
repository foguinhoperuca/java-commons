package org.sigerar.interceptor;

import org.sigerar.dao.DaoFactory;
import org.vraptor.Interceptor;
import org.vraptor.LogicException;
import org.vraptor.LogicFlow;
import org.vraptor.view.ViewException;
import org.vraptor.annotations.Out;

public class DaoInterceptor implements Interceptor {

    private final DaoFactory factory = new DaoFactory();

    public void intercept(LogicFlow flow) throws LogicException, ViewException {
        flow.execute();
        if (factory.hasTransaction()) {
              factory.rollback();
        }
        factory.close();
    }

    @Out(key="org.sigerar.dao.DaoFactory")
    public DaoFactory getFactory() {
        return factory;
    }
}