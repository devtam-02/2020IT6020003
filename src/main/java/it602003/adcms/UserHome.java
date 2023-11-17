package it602003.adcms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import it602003.objects.UserObject;
import it602003.process.UserProcess;

import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class UserHome extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tblUserList;
	private UserProcess userProcess;
	private ArrayList<UserObject> userList;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		UserProcess userProcess = new UserProcess();
		ArrayList<UserObject> userList = userProcess.getUserObjects(null,(byte) 50);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					UserHome frame = new UserHome(userProcess, userList);
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
	public UserHome(UserProcess userProcess,ArrayList<UserObject> userList) {
		this.userList = userList;
		this.userProcess = userProcess;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1098, 617);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("USER LIST");
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(new Color(128, 128, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setBounds(267, 51, 351, 46);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 127, 1004, 422);
		contentPane.add(scrollPane);
		
		tblUserList = new JTable();
//		scrollPane.add(tblUserList);
		scrollPane.add(tblUserList);
		scrollPane.setViewportView(tblUserList);
		
		loadTable(userList);
		
	}
	public void loadTable(ArrayList<UserObject> itemsArrayList) {
		String[] columnNames = {"ID","Full Name","PassWord","BirthDay","Mobile Phone","Email","Address","Job","Position","Notes"};			
		DefaultTableModel tableData = new DefaultTableModel();
		
		tableData.setColumnIdentifiers(columnNames);
		
		for (UserObject u : itemsArrayList) {
			System.err.println(u);
		}
		
		for(int i = 0; i < itemsArrayList.size(); i++) {
			Object[] obj = new Object[] {
					itemsArrayList.get(i).getUser_id(),
					itemsArrayList.get(i).getUser_fullname(),
					itemsArrayList.get(i).getUser_pass(),
					itemsArrayList.get(i).getUser_birthday(),
					itemsArrayList.get(i).getUser_mobilephone(),
					itemsArrayList.get(i).getUser_email(),
					itemsArrayList.get(i).getUser_address(),
					itemsArrayList.get(i).getUser_job(),
					itemsArrayList.get(i).getUser_position(),
					itemsArrayList.get(i).getUser_notes()
			};
			tableData.addRow(obj);
		}
		tblUserList.setModel(tableData);
	}
}
