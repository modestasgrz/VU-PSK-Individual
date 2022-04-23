package com.example.task1.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;

@Interceptor
@Log
public class Logger implements Serializable {
    @AroundInvoke
    public Object logMethodInvocation(InvocationContext ctx) throws Exception {
        System.out.println("LOGGER called action: " + ctx.getMethod().getName());
        return ctx.proceed();
    }
}
