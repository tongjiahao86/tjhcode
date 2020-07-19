package com.tjhnode.dataservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tjhnode.dataservice.model.vo.BusinessFunctionModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface BusinessFunctionMapper extends BaseMapper<BusinessFunctionModel> {

    BusinessFunctionModel getBusinessFunctionModel(String ofid);
}
