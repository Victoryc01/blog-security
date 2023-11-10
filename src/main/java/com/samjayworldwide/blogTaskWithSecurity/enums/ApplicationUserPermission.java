package com.samjayworldwide.blogTaskWithSecurity.enums;

import com.samjayworldwide.blogTaskWithSecurity.entity.User;
import lombok.Getter;

@Getter
public enum ApplicationUserPermission {
    ADMIN_WRITE_A_POST("admin:writeAPost"),
    ADMIN_DELETE_A_POST("admin:deleteAPost"),
    ADMIN_WRITE_A_COMMENT("admin:writeAComment"),
    USER_WRITE_A_COMMENT("user:writeAComment"),
    USER_LIKE_A_POST("user:likeAPost");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }
}
