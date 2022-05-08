package com.jnguetsop.multitenant.interceptor;

import com.jnguetsop.multitenant.datasource.DataSourceContextHolder;
import com.jnguetsop.multitenant.datasource.DataSourceEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@RequiredArgsConstructor
@Component
public class DataSourceInterceptor implements HandlerInterceptor {

    private static final String X_TENANT_ID = "X-Tenant-Id";
    private final DataSourceContextHolder dataSourceContextHolder;

    @Override
    public boolean preHandle(final HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("DataSourceInterceptor.preHandle");
        var branchContext = DataSourceEnum.FIRST_DATABASE;

        if (StringUtils.hasText(request.getHeader(X_TENANT_ID))) {
            var tenantId = request.getHeader(X_TENANT_ID);
            branchContext = switch (tenantId) {
                case "second" -> DataSourceEnum.SECOND_DATABASE;
                case "third" -> DataSourceEnum.THIRD_DATABASE;
                default -> DataSourceEnum.FIRST_DATABASE;
            };
        }

        log.info("Executing request on database {}", branchContext);
        dataSourceContextHolder.setBranchContext(branchContext);

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
