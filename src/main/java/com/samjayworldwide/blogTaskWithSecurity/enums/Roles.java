package com.samjayworldwide.blogTaskWithSecurity.enums;

import com.google.common.collect.Sets;
import lombok.Getter;

import java.util.Set;

import static com.samjayworldwide.blogTaskWithSecurity.enums.ApplicationUserPermission.*;
@Getter
public enum Roles {
    USER(Sets.newHashSet(USER_WRITE_A_COMMENT,USER_LIKE_A_POST)),
    ADMIN(Sets.newHashSet(ADMIN_WRITE_A_POST,ADMIN_DELETE_A_POST,ADMIN_WRITE_A_COMMENT));
    private final Set<ApplicationUserPermission> permissions;

    Roles(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }
}
