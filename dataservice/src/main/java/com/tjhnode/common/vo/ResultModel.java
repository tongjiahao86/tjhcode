package com.tjhnode.common.vo;

import lombok.Data;

/**
 * @program: dataservice
 * @description:
 * @author: tjh
 * @create: 2020-01-09 21:18
 **/
public class ResultModel<T> {

    public ErrorCode Code;
    public T DataContext;
}
