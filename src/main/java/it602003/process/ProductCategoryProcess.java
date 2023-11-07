package it602003.process;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import it602003.ConnectionPool;
import it602003.ConnectionPoolImpl;
import it602003.objects.ProductCategoryObject;

public class ProductCategoryProcess {
	//kết nối để làm việc với csdl
		private Connection con;
		
		//bộ quản lý kết nối riêng section
		private ConnectionPool cp;
		
		public ProductCategoryProcess() {
			//Xác định bộ quản lý kết nối
			this.cp = new ConnectionPoolImpl();
			
			//Xin kết nối để làm việc
			try {
				this.con = this.cp.getConnection("Product Category");
				
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
		public ArrayList<ProductCategoryObject> getProductCategoryObjects(ProductCategoryObject similar, byte total){
			ArrayList<ProductCategoryObject> items = new ArrayList<>();
			ProductCategoryObject item;
			
			String sql = "SELECT * FROM tblpc ";
			sql += "";
			sql += "ORDER BY pc_name ASC ";
			sql += "LIMIT ?";
			
			try {
				PreparedStatement pre = this.con.prepareStatement(sql);
				//Truyền giá trị cho tham số
				pre.setByte(1, total);
				
				ResultSet rs = pre.executeQuery(); //Lấy về tập kết quả
				if(rs != null) {
					while(rs.next()) {
						item = new ProductCategoryObject();
						item.setPc_id(rs.getInt("pc_id"));
						item.setPc_name(rs.getString("Pc_name"));
						item.setPc_created_date(rs.getString("Pc_created_date"));
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

		public boolean addProductCategory(ProductCategoryObject item) {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO tblpc(");
			
			sql.append("pc_name, pc_pg_id, pc_ps_id, pc_manager_id, ");
			sql.append("pc_notes, pc_delete, pc_deleted_date, pc_deleted_author, ");
			sql.append("pc_modified_date, pc_created_date, pc_image, pc_enable, ");
			sql.append("pc_name_en, pc_created_author_id, pc_language) ");
			sql.append("VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
			try {
				PreparedStatement pre = this.con.prepareStatement(sql.toString());
				pre.setString(1, item.getPc_name());
				pre.setInt(2, item.getPc_pg_id());
				pre.setInt(3, item.getPc_ps_id());
				pre.setInt(4, item.getPc_manager_id());
				pre.setString(5, item.getPc_notes());
				
//				pre.setBoolean(6, item.isSection_delete());
//				pre.setString(7, item.getSection_last_modified());
//				pre.setInt(8, item.getSection_created_author_id());
//				pre.setString(9, item.getSection_name_en());
//				pre.setByte(10, item.getSection_language());
				
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
		
		public static void main(String[] args) {
			ProductCategoryProcess pro = new ProductCategoryProcess();
//			ArrayList<ProductCategoryObject> items = pro.getProductCategoryObjects(null, (byte)10);
//			for (ProductCategoryObject productCategoryObject : items) {
//				System.out.println(productCategoryObject);
//			}
			ProductCategoryObject proObj = new ProductCategoryObject();
			
		}
		
}
