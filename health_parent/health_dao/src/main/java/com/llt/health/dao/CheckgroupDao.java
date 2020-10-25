package com.llt.health.dao;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.Page;
import com.llt.health.entity.QueryPageBean;
import com.llt.health.pojo.CheckGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CheckgroupDao {
    /**
     * 检查组添加
     * @param checkGroup
     */
    void add(CheckGroup checkGroup);

    /**
     * 关系表添加
     * @param groupId
     * @param checkitemId
     */
    void addCheckItemIdAndGroupId(@Param("groupId") Integer groupId, @Param("checkitemId") Integer checkitemId);



    /**
     * 查询检查组
     * @param
     * @return
     */
    Page<CheckGroup> findPage(String queryString);

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
     * 编辑:更新检查组
     * @param checkgroup
     */
    void update(CheckGroup checkgroup);

    /**
     * 编辑:删除检查组和检查项关系
     * @param id
     */
    void deleteCheckGroupCheckItem(Integer id);

    /**
     * 编辑:插入新的关系
     * @param checkitemId
     * @param id
     */
    void addCheckGroupCheckItem(@Param("checkitemId") Integer checkitemId,@Param("id") Integer id);

    /**
     * 编辑:删除检查组
     * @param id
     * @return
     */
    int findSetmealCountByCheckGroupId(Integer id) ;
    //删除与检查项管理的数据
    void deletedeleteCheckGroupCheckItem(Integer id);
    //删除检查组
    void deleteById(Integer id);
}
