package com.llt.health.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.llt.health.entity.QueryPageBean;
import com.llt.health.pojo.CheckGroup;
import com.llt.health.pojo.PageResult;
import javafx.scene.control.Pagination;

import java.util.List;


public interface CheckgroupService {
    /**
     * 添加检查组
     * @param checkGroup
     * @param checkitemIds
     */
    void add(CheckGroup checkGroup, Integer[] checkitemIds);

    /**
     * 查询检查组
     * @param
     */
    PageResult<CheckGroup> findPage(QueryPageBean queryPageBean);

    /**
     * 根据Id查询检查组
     * @param checkGroupId
     * @return
     */
    CheckGroup findById(int checkGroupId);

    /**
     * 编辑: 查询已选中的检查项
     * @param checkGroupId
     * @return
     */
    List<Integer> findCheckItemIdsByCheckGroupId(int checkGroupId);

    /**
     * 编辑:更新检查组和检查项关系
     * @param checkitemIds
     * @param checkgroup
     */
    void update(Integer[] checkitemIds, CheckGroup checkgroup);

    /**
     * 编辑:删除检查组
     * @param id
     */
    void deleteById(Integer id) throws Exception;
}
