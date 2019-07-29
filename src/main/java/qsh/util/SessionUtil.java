/*
 * Copyright (c) 2016 xiaomaihd and/or its affiliates.All Rights Reserved.
 *            http://www.xiaomaihd.com
 */
package qsh.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


public class SessionUtil {

    public static Long getCurrUid() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        if (userDetails != null) {
            return Long.valueOf(userDetails.getUsername());
        }

        return null;

    }

    public static void clear() {

        SecurityContextHolder.clearContext();
    }
}
