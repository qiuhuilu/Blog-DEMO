package com.ctrip.blog.service.impl;

import com.ctrip.blog.dao.BloggerMapper;
import com.ctrip.blog.pojo.Blogger;
import com.ctrip.blog.pojo.BloggerExample;
import com.ctrip.blog.service.BloggerService;

import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

/**
 * Created by qiuhl on 2017/5/24.
 */
@Service("bloggerService")
public class BloggerServiceImpl implements BloggerService{
    @Resource
    private BloggerMapper bloggerMapper;
    @Resource
    private BloggerExample bloggerExample;
    public Blogger getByUsername(String username) {
        bloggerExample.createCriteria().andUsernameEqualTo(username);
        List<Blogger> bloggerList = bloggerMapper.selectByExample(bloggerExample);
        return bloggerList.get(0);
    }
}
