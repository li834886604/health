package com.llt.health.service;

import com.llt.health.entity.QueryPageBean;
import com.llt.health.pojo.CheckItem;
import com.llt.health.pojo.PageResult;

import java.util.List;

public interface CheckItemService {
    /**
     * 查询所有
     * @return
     */
    List<CheckItem> findAll();

    void add(CheckItem checkItem);

    PageResult findPage(QueryPageBean queryPageBean);

    /**
     * 删除检查项
     * @param id
     */
    void deleteById(Integer id);
}
