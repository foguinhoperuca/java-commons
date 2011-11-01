package org.awknet.commons.interceptor;

import java.io.IOException;
import org.awknet.commons.model.entity.User;
import org.vraptor.Interceptor;
import org.vraptor.LogicException;
import org.vraptor.LogicFlow;
import org.vraptor.annotations.In;
import org.vraptor.scope.ScopeType;
import org.vraptor.view.ViewException;

public class AuthorizerInterceptor implements Interceptor {

    @In(scope=ScopeType.SESSION, required=false)
    private User user;    

    public void intercept (LogicFlow flow) throws LogicException, ViewException {
        if (this.user == null) {
            try {
                flow.getLogicRequest().getResponse().sendRedirect("user.login.logic");
            } catch (IOException e) {
                throw new LogicException(e);
            }
        } else {
            flow.execute();
        }
    }
}
