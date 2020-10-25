package com.llt.health.service;

import com.llt.health.entity.QueryPageBean;
import com.llt.health.exception.MyException;
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
    void deleteById(Integer id)throws MyException;

    /**
     * 根基id查询  回显数据
     * @param id
     * @return
     */
    CheckItem findById(int id);

    /**
     * 编辑更新数据
     * @param checkItem
     */
    void update(CheckItem checkItem);
}
