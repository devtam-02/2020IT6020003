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
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				
		table.setCellSelectionEnabled(true);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setBounds(66, 113, 997, 430);
		contentPane.add(table);
		

		ArrayList<SectionObject> itemsArrayList = s.getSectionObjects(null, (byte) 20);
		String[] columnNames = {"Id", "Name", "Notes"};
		
		DefaultTableModel tableData = (DefaultTableModel) table.getModel();
		tableData.setColumnCount(3);
		tableData.addRow(columnNames);

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
}
