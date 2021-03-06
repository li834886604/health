package com.llt.health.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.llt.health.constant.MessageConstant;
import com.llt.health.entity.QueryPageBean;
import com.llt.health.entity.Result;
import com.llt.health.pojo.CheckItem;
import com.llt.health.pojo.PageResult;
import com.llt.health.service.CheckItemService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.awt.geom.FlatteningPathIterator;
import java.util.List;

@RestController
@RequestMapping("/checkitem")
public class CheckitemController {

    @Reference
    private CheckItemService checkItemService;

    @RequestMapping("/findAll")
    public Result findAll(){

       List<CheckItem> checkItems=checkItemService.findAll();

       return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,checkItems);

    }


    @RequestMapping("/add")
    public Result add(@RequestBody CheckItem checkItem){

       checkItemService.add(checkItem);
//        boolean flag;
//        if (i>0){
//            flag=true;
//        }else {
//            flag =false
//        }
        return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS);


    }
    @RequestMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){

       PageResult<CheckItem> pageResult= checkItemService.findPage(queryPageBean);

       return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,pageResult);



    }
    /**
     * 删除检查项
     * @param id
     */
   @PostMapping("/deleteById")
    public Result deleteById( int id) {
       checkItemService.deleteById(id);
    return new Result(true ,MessageConstant.DELETE_CHECKITEM_SUCCESS);
   }
    /**
     * 根基id查询  回显数据
     */
   @GetMapping("/findById")
    public Result findById(int id){
       CheckItem checkItem=checkItemService.findById(id);

       return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,checkItem);
   }

    /**
     * 编辑更新数据
     * @param checkItem
     * @return
     */
   @PostMapping("/update")
    public Result update(@RequestBody CheckItem checkItem){
       checkItemService.update(checkItem);
       return new Result(true,MessageConstant.EDIT_CHECKITEM_SUCCESS);
   }


}


