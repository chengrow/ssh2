package cap.util;

import cap.action.LoginAction;
import cap.bean.Admin;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import java.util.Map;

public class CheckLoginInterceptor extends AbstractInterceptor {
    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        System.out.print("开始拦截器拦截");
        Object obj = actionInvocation.getAction();
        if (obj instanceof LoginAction) {
            System.out.print("登录不需要拦截");
            return actionInvocation.invoke();
        }
        Map<String, Object> session = actionInvocation.getInvocationContext().getSession();
        Admin admin = (Admin) session.get("admin");
        if (admin != null) {
            System.out.print("已经登录不需要拦截");
            return actionInvocation.invoke();
        } else {
            System.out.print("您还没有登录，跳转到登录界面 ");
            return Action.LOGIN;
        }
    }
}
