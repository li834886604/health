package com.llt.health.dao;

import com.github.pagehelper.Page;
import com.llt.health.pojo.CheckItem;

import java.util.List;

public interface CheckItemDao {
    /**
     * 查询所有
     * @return
     */
    List<CheckItem> findAll();

    /**
     * 添加检查项
     * @param checkItem
     */
    void add(CheckItem checkItem);

    /**
     * 分页显示
     * @param queryString
     * @return
     */
    Page<CheckItem> findPage(String queryString);

    /**
     * 按id查找检查组中是否有此检查项
     * @param id
     * @return
     */
    int findCountByCheckitemId(int id);

    /**
     * 根据id删除检查项
     * @param id
     */
    void delete(int id);
}
