package com.llt.health.service.impl;


import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
import com.llt.health.dao.CheckgroupDao;
import com.llt.health.entity.QueryPageBean;
import com.llt.health.pojo.CheckGroup;
import com.llt.health.pojo.PageResult;
import com.llt.health.service.CheckgroupService;
import javafx.scene.control.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.beans.Transient;
import java.util.List;


@Service(interfaceClass = CheckgroupService.class)
public class CheckgroupServiceImpl implements CheckgroupService {
    @Autowired
    CheckgroupDao checkgroupDao;

    /**
     * 添加检查组
     * @param checkGroup
     * @param checkitemIds
     */
    @Override
    public void add(CheckGroup checkGroup, Integer[] checkitemIds) {
        //对t_checkgroup进行添加
        checkgroupDao.add(checkGroup);

        //获得checkGroup的Id
        Integer groupId = checkGroup.getId();

        if (checkitemIds != null){
            for (Integer checkitemId : checkitemIds) {
                //添加中间数据表
                checkgroupDao.addCheckItemIdAndGroupId(groupId,checkitemId);
            }
        }

    }

    /**
     * 查询检查组
     * @param
     */
    @Override
    public PageResult<CheckGroup> findPage(QueryPageBean queryPageBean)  {
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());

        //如果有查询条件的 模糊查询
        if (!StringUtil.isEmpty(queryPageBean.getQueryString())) {

            queryPageBean.setQueryString("%"+queryPageBean.getQueryString()+"%");
        }

        //查询被分页
        Page<CheckGroup>page=checkgroupDao.findPage(queryPageBean.getQueryString());

        return new PageResult<CheckGroup>(page.getTotal(),page.getResult());

    }

    /**
     * 根据Id查询检查组
     * @param checkGroupId
     * @return
     */
    @Override
    public CheckGroup findById(int checkGroupId) {
        CheckGroup checkGroup=checkgroupDao.findById(checkGroupId);
        return checkGroup;
    }

    /**
     * 编辑: 查询已选中的检查项
     * @param checkGroupId
     * @return
     */
    @Override
    public List<Integer> findCheckItemIdsByCheckGroupId(int checkGroupId) {
        return checkgroupDao.findCheckItemIdsByCheckGroupId(checkGroupId);


    }

    /**
     * 编辑:更新检查组和检查项关系
     * @param checkitemIds
     * @param checkgroup
     */
    @Override
    @Transactional
    public void update(Integer[] checkitemIds, CheckGroup checkgroup) {
        //更新检查组
        checkgroupDao.update(checkgroup);
        //删除检查组和检查项关系
        checkgroupDao.deleteCheckGroupCheckItem(checkgroup.getId());
        //更新关系
        if (checkitemIds != null){
            for (Integer checkitemId : checkitemIds) {
                //插入新的关系
                checkgroupDao.addCheckGroupCheckItem(checkitemId,checkgroup.getId());

            }
        }
    }

    /**
     * 编辑:删除检查组
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        //查询是否有被套餐使用
       int cnt= checkgroupDao.findSetmealCountByCheckGroupId(id);


       if (cnt>0){

          throw new HeadlessException("该检查组已被套餐使用");
       }
       //删除与检查项管理的数据
        checkgroupDao.deletedeleteCheckGroupCheckItem(id);
       //删除检查组
        checkgroupDao.deleteById(id);
    }
}
