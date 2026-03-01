package com.nexxus.server.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.nexxus.common.AccountInfo;
import com.nexxus.common.AccountInfoContext;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class CustomMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        AccountInfo accountInfo = AccountInfoContext.get();
        String operator = accountInfo != null ? accountInfo.getAccountId().toString() : "system";
        this.strictInsertFill(metaObject, "createdAt", Instant.class, Instant.now());
        this.strictInsertFill(metaObject, "updatedAt", Instant.class, Instant.now());
        this.strictInsertFill(metaObject, "createdBy", String.class, operator);
        this.strictInsertFill(metaObject, "updatedBy", String.class, operator);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        AccountInfo accountInfo = AccountInfoContext.get();
        String operator = accountInfo != null ? accountInfo.getAccountId().toString() : "system";
        this.strictUpdateFill(metaObject, "updatedAt", Instant.class, Instant.now());
        this.strictUpdateFill(metaObject, "updatedBy", String.class, operator);

        // only fill deletedBy when deletedAt is being set (logical delete operation)
        Object deletedAtValue = metaObject.getValue("deletedAt");
        if (deletedAtValue != null) {
            this.strictUpdateFill(metaObject, "deletedBy", String.class, operator);
        }
    }
}
