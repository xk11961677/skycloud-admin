package com.skycloud.geteway.filter;

import com.netflix.zuul.ZuulFilter;
import com.skycloud.common.base.BaseContextHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
@Slf4j
public class ClearFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        BaseContextHandler.remove();
        return null;
    }

}
