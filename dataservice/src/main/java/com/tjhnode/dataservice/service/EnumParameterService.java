package com.tjhnode.dataservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tjhnode.dataservice.model.entities.Enumparameters;
import com.tjhnode.dataservice.model.vo.QueryPaginationModel;
import com.tjhnode.dataservice.model.vo.ResultPaginationModel;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tjh
 * @since 2019-11-30
 */
 public interface EnumParameterService extends IService<Enumparameters> {

    /**
     * 参数注册列表
     * @param query 分页、条件查询
     * @return
     */
     ResultPaginationModel<Enumparameters> getEnumParameterList(QueryPaginationModel query);

    /**
     * 新增参数
     * @param model
     * @return
     */
     String insertEnumParameter(Enumparameters model);

    /**
     * 修改参数
     * @param model
     * @return
     */
     boolean updateEnumParameter(Enumparameters model);

    /**
     * 删除
     * @param KeyId 主键
     * @return
     */
     boolean deleteEnumParameter(String KeyId);

    /**
     * 参数注册详情
     * @param KeyId 主键
     * @return
     */
     Enumparameters getParameterDetail(String KeyId);
}
