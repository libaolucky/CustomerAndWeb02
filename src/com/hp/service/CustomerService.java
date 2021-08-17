package com.hp.service;

import com.hp.dao.CustomerDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerService {
    //全查
   public Map selectByParam(Map map){
       CustomerDao dao=new CustomerDao();
       List<Map> maps=dao.selectAllByParam(map);
       Map codeMap=new HashMap();
       codeMap.put("code",0);
       codeMap.put("data",maps);
       codeMap.put("msg","ok");
       Map countMap =selectAllParamCount(map);
       int count= (int) countMap.get("data");
       codeMap.put("count",count);

       return codeMap;
   }


   //全查 总条数  多的
    public Map selectAllParamCount(Map map){
       Map codeMap=new HashMap();
       CustomerDao dao=new CustomerDao();
       int i=dao.selectAllByParamCount(map);
       codeMap.put("code",0);
       codeMap.put("data",i);
       codeMap.put("msg","ok");
       return codeMap;
    }

}
