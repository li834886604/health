package com.llt.health.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
import com.llt.health.dao.CheckItemDao;
import com.llt.health.entity.QueryPageBean;
import com.llt.health.exception.MyException;
import com.llt.health.pojo.CheckItem;
import com.llt.health.pojo.PageResult;
import com.llt.health.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * interfaceClass 发布服务时的接口
 */
@Service(interfaceClass = CheckItemService.class)
public class CheckItemServiceImpl implements CheckItemService {

    @Autowired
    CheckItemDao checkItemDao;

    /**
     * 查询所有
     * @return
     */
    @Override
    public List<CheckItem> findAll() {

        List<CheckItem> checkItems= checkItemDao.findAll();
        return checkItems;
    }

    @Override
    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);

    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());

        //模糊查询  拼接%
        //判断是否有查询条件
        if (!StringUtil.isEmpty(queryPageBean.getQueryString())) {
            //有查询条件  进行拼接%
            queryPageBean.setQueryString("%"+queryPageBean.getQueryString()+"%");
        }

        //查询语句会被分页
        Page<CheckItem> page=checkItemDao.findPage(queryPageBean.getQueryString());
        //封装到结果对象中去
        PageResult<CheckItem> pageResult = new PageResult<>(page.getTotal(), page.getResult());
        return pageResult;
    }
    /**
     * 删除检查项
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        int cnt =checkItemDao.findCountByCheckitemId(id);

        if(cnt>0){
            //已经被检查组使用了 ,则不能删除,报错
            throw  new MyException("该检查项已经被使用了,不能删除");

        }
        checkItemDao.delete(id);
    }

    /**
     * 根基id查询  回显数据
     * @param id
     * @return
     */
    @Override
    public CheckItem findById(int id) {
        CheckItem checkItem=checkItemDao.findById(id);

        return checkItem;
    }

    /**
     * 编辑更新数据
     * @param checkItem
     */

    @Override
    public void update(CheckItem checkItem) {
        checkItemDao.update(checkItem);

    }


//    @Override
//    public void deleteById(int id) {
//       int cnt =checkItemDao.findCountByCheckitemId(id);
//
//       if(cnt>0){
//            //已经被检查组使用了 ,则不能删除,报错
//           throw  new MyException("该检查项已经被使用了,不能删除");
//
//       }
//       checkItemDao.delete(id);
//    }
}
