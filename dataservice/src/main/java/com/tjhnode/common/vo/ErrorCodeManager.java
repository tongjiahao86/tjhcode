package com.tjhnode.common.vo;

import org.springframework.stereotype.Component;

import java.util.Hashtable;

@Component
public class ErrorCodeManager {

    //错误码集合
    private static Hashtable errcode=new Hashtable();
    //private static List<Element> codelist;
    //后期改成yml配置
    public ErrorCodeManager() {
        /*try {
            XmlBuilder xmlBuilder=new XmlBuilder();
            Document document= xmlBuilder.ReadXml("/config/ErrorCode.xml");
            Element root=document.getRootElement();
            codelist=root.elements("ErrorCode");
        } catch (DocumentException e) {

        }*/

        errcode.put("0","请求成功");
        errcode.put("1","系统繁忙");
        errcode.put("99","接口异常");




        errcode.put("1001","access_token无效");
        errcode.put("1002","refresh_token无效");
        errcode.put("1003","该用户权限不足以访问该资源接口");
        errcode.put("1004","访问此资源需要完全的身份验证");
        errcode.put("1005","Bad client credentials");

        errcode.put("1011","未登录");
        errcode.put("1012","用户名或密码错误");
        errcode.put("1013","账户被禁用");
        errcode.put("1014","查询用户列表失败");
        errcode.put("1015","查询用户信息失败");

        errcode.put("10001","查询参数中包含【\\\"<>%】非法参数");
        errcode.put("10002","缺少参数:{ParameterName}");
        errcode.put("10003","{FuncName}");
        errcode.put("10004","没有找到Id:{FuncId}的服务");
        errcode.put("10005","参数为空!");
        errcode.put("10006","参数不正确");

        errcode.put("20001","获取列表数据失败");
        errcode.put("20002","新增目录失败");
        errcode.put("20003","修改目录失败");
        errcode.put("20004","获取目录详情失败");
        errcode.put("20005","删除目录失败");

        errcode.put("21001","获取接口配置列表失败");
        errcode.put("21002","获取接口配置详情失败");
        errcode.put("21003","新增接口配置失败");
        errcode.put("21004","修改接口配置失败");
        errcode.put("21005","删除接口配置失败");

        errcode.put("22001","获取参数配置列表失败");
        errcode.put("22002","获取参数配置详情失败");
        errcode.put("22003","新增参数配置失败");
        errcode.put("22004","修改参数配置失败");
        errcode.put("22005","删除参数配置失败");

        errcode.put("23001","获取sql配置详情失败");
        errcode.put("23002","新增sql配置失败");
        errcode.put("23003","修改sql配置失败");
        errcode.put("23004","删除sql配置失败");
        errcode.put("23005","获取数据库对象失败");

        errcode.put("24001","获取接口参数失败");
        errcode.put("24002","添加接口参数失败");
        errcode.put("24003","删除接口参数失败");

        errcode.put("25001","获取菜单失败");
    }

    /**
     * 取得错误码
     * @param code
     * @return
     */
    public static ErrorCode GetCode(String code)
    {
        boolean flag = ErrorCodeManager.errcode.containsKey(code);
        ErrorCode result;
        if (flag)
        {
            result = new ErrorCode();
            result.Code = code;
            result.Message = ErrorCodeManager.errcode.get(code).toString();
        }
        else
        {
            result = new ErrorCode();
            result.Code = "-1";
            result.Message = "未知错误";
        }
        return result;
    }


}

