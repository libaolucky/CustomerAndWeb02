package com.hp.service;

import com.hp.bean.User;
import com.hp.dao.UserDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService {
    //登录
    public Map login(String username, String password, HttpServletRequest request){
        Map map = new HashMap();
        //service 层要调用dao层
        UserDao userDao = new UserDao();
        User userFromDB = userDao.login(username, password);
        if(null == userFromDB){
            //没查出来，即：账户名或密码不正确
            map.put("code",4001);
            map.put("msg","账户或密码不正确");
            return map;
        }else{
           //当登陆成功后，把信息放入到session作用域中
            HttpSession session = request.getSession();
            session.setAttribute("user",userFromDB);
            map.put("code",0);
            map.put("msg","登陆成功");
            return map;
        }
    }

    //带参数的分页查询
    public Map selectAllByParam(Map map1){
       UserDao userDao = new UserDao();
        List<User> users = userDao.selectAllParam(map1);
        int i = userDao.selectCount(map1);
        Map map = new HashMap();
        //map.put("code",0);//必须和layui的json返回的格式一样，不一样数据不出来
        map.put("code111",200);//返回的数据不符合规范
        map.put("msg111","查询成功");
        map.put("count111",i);//把死的写成活的
        map.put("data111",users);
        //根据layui的返回的json数据格式 去 传给你数据 ，需要 layui解析
       /* {
            code:0,
            msg:"",
            count:1000,
            data:[每条数据]
        }*/

       Map map2 = new HashMap();
       map2.put("number",20001);
       map2.put("message","数据查询成功");
       map2.put("object",map);
        return map2;
    }

    //修改 是否可以使用
    public Map updateUserById(Integer sfDel,Integer userId){
        UserDao dao = new UserDao();
        int i = dao.updateSelectById(sfDel, userId);
        Map map = new HashMap();
        if(i==1){
            map.put("code",0);
            map.put("msg","修改成功");
        }else{
            map.put("code",400);
            map.put("msg","修改不成功");
        }
        return map;
    }

    //修改全部
    public Map updateUser(User user){
        Map codeMap = new HashMap();
        UserDao dao = new UserDao();
        int i = dao.update(user);
        if(i==1){
            codeMap.put("code",0);
            codeMap.put("msg","修改成功");
        }else{
            codeMap.put("code",400);
            codeMap.put("msg","修改不成功");
        }
        return codeMap;
    }

    //按照id 查询1个user
    public Map selectUserById(Integer id){
        UserDao dao = new UserDao();
        User user = dao.selectUserById(id);
        Map codeMap = new HashMap();
        codeMap.put("code",0);
        codeMap.put("msg","ok");
        codeMap.put("data",user);
        return codeMap;

    }
}
