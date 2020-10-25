package com.llt.health.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.llt.health.constant.MessageConstant;
import com.llt.health.entity.QueryPageBean;
import com.llt.health.entity.Result;
import com.llt.health.pojo.CheckGroup;
import com.llt.health.pojo.CheckItem;
import com.llt.health.pojo.PageResult;
import com.llt.health.service.CheckgroupService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.util.List;


@RestController
@RequestMapping("/checkgroup")
public class CheckgroupController {

    /**
     * 添加检查组
     */
    @Reference
    CheckgroupService checkgroupService;
    @PostMapping("/add")
    public Result add(@RequestBody CheckGroup checkGroup, Integer[] checkitemIds){
        //添加检查组  也会添加检查项   所以要对`t_checkgroup`
        //`t_checkgroup_checkitem`表进行更新
        checkgroupService.add(checkGroup,checkitemIds);

        return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);

    }

    /**
     * 分页查询
     * @param queryPageBean
     * @return
     */
    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){

        PageResult<CheckGroup> pageResult= checkgroupService.findPage(queryPageBean);
        return new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS,pageResult);
    }

    /**
     * 根据Id查询检查组
     * @param checkGroupId
     * @return
     */
    @GetMapping("/findById")
    public Result findById(int checkGroupId){
           CheckGroup checkGroup= checkgroupService.findById(checkGroupId);

           return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,checkGroup);
    }
    /**
     * 编辑: 查询已选中的检查项
     *
     */
    @GetMapping("/findCheckItemIdsByCheckGroupId")
    public Result findCheckItemIdsByCheckGroupId(int checkGroupId){

       List<Integer> checkitemIds= checkgroupService.findCheckItemIdsByCheckGroupId(checkGroupId);

       return  new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,checkitemIds);
    }

    /**
     * 编辑:更新检查组和检查项关系
     * @param checkitemIds
     * @param checkgroup
     * @return
     */
    @PostMapping("/update")
    public Result update( Integer[] checkitemIds ,@RequestBody CheckGroup checkgroup){

        checkgroupService.update(checkitemIds,checkgroup);
        return new Result(true,MessageConstant.EDIT_CHECKGROUP_SUCCESS);


    }

    /**
     * 编辑:删除检查组
     * @param id
     * @return
     */
    @PostMapping("/deleteById")
    public Result deleteById(Integer id){

        checkgroupService.deleteById(id);
        return  new Result(true,MessageConstant.DELETE_CHECKGROUP_SUCCESS);

    }


}
