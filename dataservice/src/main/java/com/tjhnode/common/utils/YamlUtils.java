package com.tjhnode.common.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @program: dataservice
 * @description: 读取yaml配置文件
 * @author: tjh
 * @create: 2019-12-08 15:46
 **/
public class YamlUtils {
    /**
     * 判断数据库是否存在
     * @param dbName
     * @return
     */
    public static boolean isDbConfig(String dbName) throws IOException {
        if(StringUtils.isEmpty(dbName)){
            return false;
        }
        List<String> strings = getdbName();
        return strings.contains(dbName)?true:false;
    }


    /**
     *获取连接数据源名称
     * @return
     * @throws IOException
     */
    public static List<String> getdbName() {
        List<String> list = new ArrayList<>();
        //读取yml配置
        try {
            Map<String,Object> map= readYamlHandler(new ClassPathResource("application.yml"));
            List<String> slist=new ArrayList<>();
            Set<Map.Entry<String,Object>> entries=map.entrySet();
            for (Map.Entry<String,Object> entry: entries) {
                slist.add(entry.getKey());
            }

            for (String str:slist){
                if(str.contains("spring.datasource.dynamic.datasource.")){
                    String str1=str.substring(str.indexOf("spring.datasource.dynamic.datasource."));
                    if(str1.contains(".password")){
                        String s=str1.substring(0,str.lastIndexOf("."));
                        list.add(s.substring(s.lastIndexOf(".")+1));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static Map<String, Object> readYamlHandler(@NonNull Resource resource)throws IOException{
        Map<String,Object> result= new LinkedHashMap<>();
        StringBuilder sb=new StringBuilder();
        InputStream stream=resource.getInputStream();
        try {
            BufferedReader br=new BufferedReader(new InputStreamReader(stream));
            Yaml yaml = new Yaml();
            Object object= yaml.load(br);
            if (object instanceof Map){
                Map map=(Map)object;
                buildFlattenedMap(result,map,null);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 这部分代码来至springboot源码部分对yaml的解析
     * YamlProcessor.java buildFlattenedMap方法
     * @param result
     * @param source
     * @param path
     */
    private static void buildFlattenedMap(Map<String, Object> result, Map<String, Object> source, @Nullable String path) {
        //循环读取原数据
        source.forEach((key, value) -> {
            //如果存在路径进行拼接
            if (StringUtils.hasText(path)) {
                if (key.startsWith("[")) {
                    key = path + key;
                } else {
                    key = path + '.' + key;
                }
            }
            //数据类型匹配
            if (value instanceof String) {
                result.put(key, value);
            } else if (value instanceof Map) {
                //如果是map,就继续读取
                Map<String, Object> map = (Map)value;
                buildFlattenedMap(result, map, key);
            } else if (value instanceof Collection) {
                Collection<Object> collection = (Collection)value;
                if (collection.isEmpty()) {
                    result.put(key, "");
                } else {
                    int count = 0;
                    Iterator var7 = collection.iterator();

                    while(var7.hasNext()) {
                        Object object = var7.next();
                        buildFlattenedMap(result, Collections.singletonMap("[" + count++ + "]", object), key);
                    }
                }
            } else {
                result.put(key, value != null ? value : "");
            }
        });
    }
}
