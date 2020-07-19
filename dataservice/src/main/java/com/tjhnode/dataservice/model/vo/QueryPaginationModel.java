package com.tjhnode.dataservice.model.vo;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @program: dataservice
 * @description: 查询分页model
 * @author: tjh
 * @create: 2019-11-24 13:45
 **/
@Data
public class QueryPaginationModel {

    //每页尺寸
    public int pagesize;

    //当前页码
    public int pageindex;

    public List<ExtraParameter> extraParameters;

    //查询集合
    public Map<String,String> querylist;


}
