package jspservlet.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jspservlet.dao.UserDAO;
import jspservlet.db.DBConnect;
import jspservlet.vo.Member;
import jspservlet.vo.UserLogin;

public class UserDAOImpl implements UserDAO{
	
	//用户更新所有的个人信息
	/**
	 * 
	 */



		
		public int queryByUsername(Member member) throws Exception {
			// TODO Auto-generated method stub
			int flag = 0;
			String sql = "select * from member where membername=?";
	        PreparedStatement pstmt = null ;
	        DBConnect dbc = null;

	        // 下面是针对数据库的具体操作
	        try{
	            // 连接数据库
	            dbc = new DBConnect() ;
	            pstmt = dbc.getConnection().prepareStatement(sql) ;
	            pstmt.setString(1,member.getMemberName()) ;
	            // 进行数据库查询操作
	            ResultSet rs = pstmt.executeQuery();
	            System.out.println(member.getMemberName());
	            while(rs.next()){
	                // 查询出内容，之后将查询出的内容赋值给person对象
	                if(rs.getString("memberPWD").equals(member.getMemberPWD())){
	                	UserLogin.setMemberID(rs.getString("memberID"));
						UserLogin.setHomeID(rs.getString("homeID"));
	                	flag = 1;
	                }
	            }
	            rs.close() ;
	            pstmt.close() ;
	        }catch (SQLException e){
	            System.out.println(e.getMessage());
	        }finally{
	            // 关闭数据库连接
	            dbc.close() ;
	        }
			return flag;
		}


		Member member1 = new Member();
		
		//用户更新所有的个人信息
		/**
		 * 
		 */
		public int create(Member member) throws Exception {
			// TODO Auto-generated method stub
			int flag = 0;
			DBConnect dbc = new DBConnect();
			dbc.getConnection();
			PreparedStatement pst = null;

			member1.setMemberID(this.showInfo(member).getMemberID());
			member1.setMemberName(this.showInfo(member).getMemberName());
			member1.setMemberPhone(this.showInfo(member).getMemberPhone());
			member1.setHomeID(this.showInfo(member).getHomeID());
			
			 System.out.println("是否有正确传入");
			 System.out.println(member.getMemberID());
			 System.out.println(member1.getMemberName());
			 System.out.println(member1.getMemberGender());
			 System.out.println(member1.getMemberPhone());
			 System.out.println(member1.getHomeID());
				
//			if(member.getMemberName()) {
//					
//				}
//			 
			//String sql = "insert into member (memberID, memberName, memberGender, memberPhone, homeID, memberPWD) "	+"values(?, ?, ?, ?, ?, ?)"; 
			
			 
			 
			 
			 
			String sql ="update member set memberName=?, memberGender=?, memberPhone=? where memberID="+UserLogin.memberID;
			
			try {
				pst = (PreparedStatement)dbc.getConnection().prepareStatement(sql);
							
				pst.setString(1, member.getMemberName());
				pst.setString(2, member.getMemberGender());
				pst.setString(3, member.getMemberPhone());
				//pst.setString(4, member.getHomeID());			
				
				pst.executeUpdate();
				//System.out.println("插入成功");
				flag = 1;
			}catch(SQLException e){
				e.printStackTrace();
			}
			return flag;
			//update member set memberPWD=1, memberName='yu' where memberID=4;
		}


		//查询id为110的用户的个人信息 成功
		//缺乏将数据发送到前端
		
	//更新密码
		public int infochange(Member member, String newPWD) throws Exception {
			// TODO Auto-generated method stub
			
			
			int flag = 0;
			DBConnect dbc = new DBConnect();
			dbc.getConnection();
			
			String sql = "select * from member where memberID="+UserLogin.memberID;
			
			PreparedStatement pst;
			pst = (PreparedStatement)dbc.getConnection().prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			
			try {
				while(rs.next()) {
					if(member.getMemberPWD().equals(rs.getString(3))) {
						//若输入密码与数据库所读到的密码相同，则可以修改密码
						PreparedStatement pst1 = null;
						String sql1 = "update member set memberPWD=? where memberID="+UserLogin.memberID;
						pst1=(PreparedStatement)dbc.getConnection().prepareStatement(sql1);
						pst1.setString(1, newPWD);
					pst1.executeUpdate();
						System.out.println("更新");
						flag=1;
					}
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}

			//
			
			return flag;
		}

	//Member传入更改以后的所有信息
	//逻辑是先跳转到show页面，在首页上按下修改按钮之后进入修改页面执行该函数
		//与show页面表现形式基本一致，个人信息栏变为可编辑状态
		public int changePInfo(Member member) throws Exception {
				// TODO Auto-generated method stub
			this.showInfo(member);
			int flag = 0;
			
			DBConnect dbc = new DBConnect();
			dbc.getConnection();
			String sql = "select * from member where memberID="+UserLogin.memberID;
			
			PreparedStatement pst;
			pst = (PreparedStatement)dbc.getConnection().prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			
			try {
				while(rs.next()) {
					
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
			return flag;
		}

		/**
		 * showInfo利用传入的member的id进行查询，查询该id在数据库中的个人信息，最后以Member对象的形式返回该member在Member表中的信息
		 * 
		 * @param member Member 需要用到传入的member，读取其id以在数据库中进行查询
		 */
		public Member showInfo(Member member) throws Exception {
			DBConnect dbc = new DBConnect();
			dbc.getConnection();
			Member member1 = new Member();
			String sql = "select * from member where memberID ='"+UserLogin.memberID+"'";

			PreparedStatement pst;
			try {
				pst = (PreparedStatement)dbc.getConnection().prepareStatement(sql);

				ResultSet rs = pst.executeQuery();
				while(rs.next()) {
					member1.setMemberID(rs.getString(1));
					member1.setMemberName(rs.getString(2));
					member1.setMemberGender(rs.getString(3));
					member1.setMemberPhone(rs.getString(4));
					member1.setHomeID(rs.getString(6));
					member1.setMemberPWD(rs.getString(5));
				}

			}catch(SQLException e) {
				e.printStackTrace();

			}

			return member1;

		}




	}



		