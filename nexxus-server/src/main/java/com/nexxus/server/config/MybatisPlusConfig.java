package com.nexxus.server.config;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import com.nexxus.common.AccountInfo;
import com.nexxus.common.AccountInfoContext;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MybatisPlusConfig {

    public static final List<String> IGNORE_TABLES = List.of(
            "app", "account",
            "cos_user", "cos_comment", "cos_organization",
            "cos_role", "cos_user_role", "cos_permission"
    );

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();

        TenantLineInnerInterceptor tenantInterceptor = new TenantLineInnerInterceptor(new TenantLineHandler() {
            @Override
            public Expression getTenantId() {
                AccountInfo accountInfo = AccountInfoContext.get();
                if (accountInfo == null) {
                    return new StringValue("1");
                }
                return new StringValue(String.valueOf(accountInfo.getOrgId()));
            }

            @Override
            public String getTenantIdColumn() {
                AccountInfo accountInfo = AccountInfoContext.get();
                if (accountInfo == null) {
                    return "1";
                }
                return "org_id";
            }

            @Override
            public boolean ignoreTable(String tableName) {
                return IGNORE_TABLES.contains(tableName);
            }
        });

        PaginationInnerInterceptor pagination = new PaginationInnerInterceptor();
        pagination.setMaxLimit(200L);

        interceptor.addInnerInterceptor(tenantInterceptor);
        interceptor.addInnerInterceptor(pagination);

        return interceptor;
    }

    @Bean
    @ConditionalOnProperty(name = "app.env", havingValue = "dev")
    public ConfigurationCustomizer mybatisConfigurationCustomizer() {
        return configuration -> {
            configuration.setLogImpl(org.apache.ibatis.logging.stdout.StdOutImpl.class);
        };
    }
}
