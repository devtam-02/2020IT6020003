package it602003.adcms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.JTable;
import it602003.objects.*;
import it602003.process.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


import java.awt.Color;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;

public class ViewSection extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
 	public static void main(String[] args) {
		
		Section s = new Section();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewSection frame = new ViewSection(s);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ViewSection(Section s) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1145, 641);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHinThChuyn = new JLabel("Hiển thị chuyên mục có trong hệ thống");
		lblHinThChuyn.setBounds(242, 11, 580, 62);
		lblHinThChuyn.setHorizontalAlignment(SwingConstants.CENTER);
		lblHinThChuyn.setFont(new Font("Tahoma", Font.BOLD, 25));
		contentPane.add(lblHinThChuyn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(76, 119, 976, 450);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setCellSelectionEnabled(true);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setBounds(0, 0, 997, 430);
		scrollPane.setViewportView(table);
		
		ArrayList<SectionObject> itemsArrayList = s.getSectionObjects(null, (byte) 20);
		loadTable(itemsArrayList);
	}
	public void loadTable(ArrayList<SectionObject> itemsArrayList) {
		String[] columnNames = {"Id", "Name", "Notes"};
		
		DefaultTableModel tableData = new DefaultTableModel();
		tableData.setColumnIdentifiers(columnNames);
		for(int i = 0; i < itemsArrayList.size(); i++) {
			Object[] obj = new Object[] {
					itemsArrayList.get(i).getSection_id(),
					itemsArrayList.get(i).getSection_name(),
					itemsArrayList.get(i).getSection_notes()
			};
			tableData.addRow(obj);
		}
		table.setModel(tableData);
	}
	
//	public void loadTable(ArrayList<CategoryObject> itemsArrayList) {
//		String[] columnNames = {"ID","Name", "SectionId", "Notes", "CreatedDate","AuthorId", "LastModified",
//				"ManagerId", "Image", "EnglishName", "Language"};
//		
//		DefaultTableModel tableData = new DefaultTableModel();
//		tableData.setColumnIdentifiers(columnNames);
//		//Chuyển đổi category object sang object
//		for(int i = 0; i < itemsArrayList.size(); i++) {
//			Object[] obj = new Object[] {
//					itemsArrayList.get(i).getCategory_id(),
//					itemsArrayList.get(i).getCategory_name(),
//					itemsArrayList.get(i).getCategory_section_id(),
//					itemsArrayList.get(i).getCategory_notes(),
//					itemsArrayList.get(i).getCategory_created_date(),
//					itemsArrayList.get(i).getCategory_created_author_id(),
//					itemsArrayList.get(i).getCategory_last_modified(),
//					itemsArrayList.get(i).getCategory_manager_id(),
//					itemsArrayList.get(i).getCategory_image(),
//					itemsArrayList.get(i).getCategory_name_en(),
//					itemsArrayList.get(i).getCategory_language()
//			};
//			tableData.addRow(obj);
//		}
//		tblCategoryList.setModel(tableData);
//	}
}
