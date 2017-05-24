package com.ctrip.blog.service;

import com.ctrip.blog.pojo.Blogger;

/**
 * Created by qiuhl on 2017/5/24.
 */
public interface BloggerService {
    Blogger getByUsername(String username);
}
