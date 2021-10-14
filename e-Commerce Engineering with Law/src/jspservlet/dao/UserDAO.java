package jspservlet.dao;

import jspservlet.vo.Member;

//数据库相关操作
public interface UserDAO {
    //新用户添加个人信息ok
    public int create(Member member)  throws Exception;

    //用户更改个人信息
    public int changePInfo(Member member) throws Exception;

    //展示用户所有信息ok
    public Member showInfo(Member member) throws Exception;
    
    public int queryByUsername(Member member) throws Exception;
    
    

    //用户更改密码ok
    public int infochange(Member member,String newPWD) throws Exception;
    

}
