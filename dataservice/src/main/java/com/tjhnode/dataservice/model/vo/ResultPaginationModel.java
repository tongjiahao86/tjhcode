package com.tjhnode.dataservice.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @program: dataservice
 * @description: 分页
 * @author: tjh
 * @create: 2019-11-24 13:40
 **/
@Data
public class ResultPaginationModel<T> {

    //总条数
    @JsonProperty("Total")
    public Long Total;

    //分页结果集
    @JsonProperty("ResultList")
    public Object ResultList;

    @JsonIgnore
    public Long getTotal() {
        return Total;
    }

    @JsonIgnore
    public Object getResultList() {
        return ResultList;
    }

    public void setTotal(Long total) {
        Total = total;
    }

    public void setResultList(Object resultList) {
        ResultList = resultList;
    }
}
