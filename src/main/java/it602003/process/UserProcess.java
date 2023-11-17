package it602003.process;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import it602003.ConnectionPool;
import it602003.ConnectionPoolImpl;
import it602003.objects.UserObject;

public class UserProcess {
	//kết nối để làm việc với csdl
		private Connection con;
		
		//bộ quản lý kết nối riêng section
		private ConnectionPool cp;
		
		public UserProcess() {
			//Xác định bộ quản lý kết nối
			this.cp = new ConnectionPoolImpl();
			
			//Xin kết nối để làm việc
			try {
				this.con = this.cp.getConnection("User");
				
				//Kiểm tra chế độ thực thi của kết nối
				if(this.con.getAutoCommit()) {
					//Hủy chế độ thực thi tự động
					this.con.setAutoCommit(false);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public ArrayList<UserObject> getUserObjects(UserObject similar, byte total){
			
			ArrayList<UserObject> items = new ArrayList<>();
			UserObject item;
			
			String sql = "SELECT * FROM tbluser ";
			sql += "";
			sql += "ORDER BY user_name ASC ";
			sql += "LIMIT ?";
			
			//Biên dịch
//			Statement sta = this.con.createStatement();
//			sta.executeQuery(sql);
//			CallableStatement call = this.con.prepareCall(sql);
			
			try {
				PreparedStatement pre = this.con.prepareStatement(sql);
				//Truyền giá trị cho tham số
				pre.setByte(1, total);
				
				ResultSet rs = pre.executeQuery(); //Lấy về tập kết quả
				if(rs != null) {
					while(rs.next()) {
						item = new UserObject();
						item.setUser_id(rs.getInt("user_id"));
						item.setUser_name(rs.getString("user_name"));
						item.setUser_created_date(rs.getString("user_created_date"));
						
						
						items.add(item);
					}
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				//trở về trạng thái an toàn của kết nối
				try {
					this.con.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			return items;
		}
		
		
		
		public boolean addSection(UserObject item) {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO tbluser(");
			sql.append("user_name, user_pass, user_fullname, user_birthday, ");
			sql.append("user_mobilephone, user_homephone, user_officephone, user_email, user_address, ");
			sql.append("user_jobarea, user_job, user_position, user_applyyear, user_permission, user_notes, ");
			sql.append("user_roles, user_logined, user_created_date, user_last_modified, user_last_logined, user_parent_id, user_actions) ");
			sql.append("VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
			try {
				PreparedStatement pre = this.con.prepareStatement(sql.toString());
				pre.setString(1, item.getUser_name());
				pre.setString(2, item.getUser_pass());
				pre.setString(3, item.getUser_fullname());
				pre.setString(4,item.getUser_birthday());
				pre.setString(5, item.getUser_mobilephone());
				pre.setString(6, item.getUser_homephone());
				pre.setString(7, item.getUser_officephone());
				pre.setString(8,item.getUser_email());
				pre.setString(9, item.getUser_address());
				pre.setString(10, item.getUser_job());
				pre.setString(11, item.getUser_job());
				pre.setString(12, item.getUser_position());
				pre.setString(13,item.getUser_applyyear());
				pre.setInt(14, item.getUser_permission());
				pre.setString(15, item.getUser_notes());
				pre.setString(16, item.getUser_roles());
				pre.setInt(17,item.getUser_logined());
				pre.setString(18, item.getUser_created_date());
				pre.setString(19, item.getUser_last_modified());
				pre.setString(20, item.getUser_last_logined());
				pre.setInt(21, item.getUser_parent_id());
				pre.setInt(22, item.getUser_actions());
				
				//thực thi
				int result = pre.executeUpdate();
				if (result==0) {
					this.con.rollback();
					return false;
				}
				
				//Ghi nhận thực thi sau cùng
				this.con.commit();
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
			
			
		}

		public boolean updateUserObject(UserObject item) {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE tbluser SET ");
			sql.append("user_name, user_pass, user_fullname, user_birthday, ");
			sql.append("user_mobilephone, user_homephone, user_officephone, user_email, user_address, ");
			sql.append("user_jobarea, user_job, user_position, user_applyyear, user_permission, user_notes, ");
			sql.append("user_roles, user_logined, user_created_date, user_last_modified, user_last_logined, user_parent_id, user_actions) ");
			sql.append("WHERE user_id = ?;");
			try {
				PreparedStatement pre = this.con.prepareStatement(sql.toString());
				pre.setString(1, item.getUser_name());
				pre.setString(2, item.getUser_pass());
				pre.setString(3, item.getUser_fullname());
				pre.setString(4,item.getUser_birthday());
				pre.setString(5, item.getUser_mobilephone());
				pre.setString(6, item.getUser_homephone());
				pre.setString(7, item.getUser_officephone());
				pre.setString(8,item.getUser_email());
				pre.setString(9, item.getUser_address());
				pre.setString(10, item.getUser_jobarea());
				pre.setString(11, item.getUser_job());
				pre.setString(12, item.getUser_position());
				pre.setString(13,item.getUser_applyyear());
				pre.setInt(14, item.getUser_permission());
				pre.setString(15, item.getUser_notes());
				pre.setString(16, item.getUser_roles());
				pre.setInt(17,item.getUser_logined());
				pre.setString(18, item.getUser_created_date());
				pre.setString(19, item.getUser_last_modified());
				pre.setString(20, item.getUser_last_logined());
				pre.setInt(21, item.getUser_parent_id());
				pre.setInt(22, item.getUser_actions());
				//thực thi
				int result = pre.executeUpdate();
				if (result==0) {
					this.con.rollback();
					return false;
				}
				
				//Ghi nhận thực thi sau cùng
				this.con.commit();
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}
		

		public boolean deleteUser(int id) {
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM tbluser WHERE user_id = ?;");
			
			try {
				PreparedStatement pre = this.con.prepareStatement(sql.toString());
				pre.setShort(1, (short) id);
				int result = pre.executeUpdate();
				if (result == 0) {
					this.con.rollback();
					return false;
				}
				this.con.commit();
				return true;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}
		public static void main(String[] args) {
			UserProcess user = new UserProcess();
			
			UserObject userObj = new UserObject();
			userObj.setUser_name("fesfsgs");
			userObj.setUser_fullname("gfderhdd fesfsgs");
			userObj.setUser_pass("kjsfdjjsf");
			userObj.setUser_email("ahjfhaf");
			userObj.setUser_created_date("adwaffds");
			userObj.setUser_parent_id(0);
//			System.out.println(userObj);
			user.addSection(userObj);
			
			ArrayList<UserObject> items = user.getUserObjects(null, (byte)10);
			for(UserObject userObject: items) {
			   System.out.println(userObject);
			}
		}
}
