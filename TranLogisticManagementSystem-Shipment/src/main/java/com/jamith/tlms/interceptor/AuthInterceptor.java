package com.jamith.tlms.interceptor;

import com.jamith.tlms.annotation.Auth;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

@Auth
@Interceptor
public class AuthInterceptor {
    @AroundInvoke
    public Object auth(InvocationContext ic) throws Exception{
        System.out.println("AuthInterceptor...");
        return ic.proceed();
    }
}
