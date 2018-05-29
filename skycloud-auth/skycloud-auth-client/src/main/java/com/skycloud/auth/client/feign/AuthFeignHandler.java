/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.skycloud.auth.client.feign;

import com.skycloud.auth.client.annotation.IgnoreClientToken;
import com.skycloud.auth.client.annotation.IgnoreUserToken;
import feign.InvocationHandlerFactory.MethodHandler;
import feign.Target;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;

public class AuthFeignHandler implements InvocationHandler {


    private Target<?> target;

    private Map<Method, MethodHandler> handlers;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        } else {
            final IgnoreClientToken ignoreAuthClientURL = method.getAnnotation(IgnoreClientToken.class);

            final IgnoreUserToken ignoreAuthTokenURL = method.getAnnotation(IgnoreUserToken.class);

            if (Objects.isNull(ignoreAuthClientURL)) {
                return this.handlers.get(method).invoke(args);
            } else {
                AuthFeignContext.setIgnoreAuthClientURL(ignoreAuthClientURL);
                AuthFeignContext.setIgnoreAuthTokenURL(ignoreAuthTokenURL);
                return this.handlers.get(method).invoke(args);
            }
        }
    }


    public void setTarget(Target<?> target) {
        this.target = target;
    }


    public void setHandlers(Map<Method, MethodHandler> handlers) {
        this.handlers = handlers;
    }

}
