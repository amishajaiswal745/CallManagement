importjava.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
importjava.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;
importjavax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPasswordField;

publicclassManagerMenuextends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTable table;
	private JTextField mobile;
	private JTextField type;
	private JTextField time;
	private JTextField bal;
	private JTextField rech;
	private JTextField amt;
	private JPasswordField passwordField;
	private JTextField uid;

	/**
	 * Launch the application.
	 */
	publicstaticvoid main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			publicvoid run() {
				try {
					ManagerMenu frame = new ManagerMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public ManagerMenu() throws ClassNotFoundException, SQLException {
		
		Connection con;
		
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/callsystem","root","");
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1305, 756);
		contentPane = new JPanel();
		setResizable(false);
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCustomerMobileNo = new JLabel("Customer Mobile no");
		lblCustomerMobileNo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCustomerMobileNo.setBounds(40, 232, 164, 25);
		contentPane.add(lblCustomerMobileNo);
		
		mobile = new JTextField();
		mobile.setColumns(10);
		mobile.setBounds(249, 237, 207, 19);
		contentPane.add(mobile);
		
		type = new JTextField();
		type.setColumns(10);
		type.setBounds(249, 295, 207, 19);
		contentPane.add(type);
		
		time = new JTextField();
		time.setColumns(10);
		time.setBounds(249, 349, 207, 19);
		contentPane.add(time);
		
		bal = new JTextField();
		bal.setColumns(10);
		bal.setBounds(249, 405, 207, 19);
		contentPane.add(bal);
		
		JLabel lblPrepaidpostpaid = new JLabel("Prepaid/Postpaid");
		lblPrepaidpostpaid.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPrepaidpostpaid.setBounds(40, 298, 164, 25);
		contentPane.add(lblPrepaidpostpaid);
		
		JLabel lblNextRechargeDate = new JLabel("Next Recharge Date");
		lblNextRechargeDate.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNextRechargeDate.setBounds(40, 444, 164, 25);
		contentPane.add(lblNextRechargeDate);
		
		JLabel lblAmount = new JLabel("Call balance(in Rs)");
		lblAmount.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAmount.setBounds(40, 400, 199, 25);
		contentPane.add(lblAmount);
		
		rech = new JTextField();
		rech.setColumns(10);
		rech.setBounds(249, 449, 207, 19);
		contentPane.add(rech);
		
		amt = new JTextField();
		amt.setColumns(10);
		amt.setBounds(249, 484, 207, 19);
		contentPane.add(amt);
		
		JLabel lblCallTotalTime = new JLabel("call total time");
		lblCallTotalTime.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCallTotalTime.setBounds(40, 344, 164, 25);
		contentPane.add(lblCallTotalTime);
		
		JLabel lblDueAmountinRs = new JLabel("Due Amount(in Rs)");
		lblDueAmountinRs.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDueAmountinRs.setBounds(40, 479, 164, 25);
		contentPane.add(lblDueAmountinRs);
		
		JLabel lblNewLabel = new JLabel("Customer Form");
		lblNewLabel.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 18));
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBounds(191, 128, 220, 31);
		contentPane.add(lblNewLabel);
		
		JLabel taskk = new JLabel("Customer name");
		taskk.setFont(new Font("Tahoma", Font.BOLD, 15));
		taskk.setBounds(40, 182, 164, 25);
		contentPane.add(taskk);
		
		
		JLabel cp = new JLabel("Password for Customer");
		cp.setFont(new Font("Tahoma", Font.BOLD, 15));
		cp.setBounds(40, 568, 194, 25);
		contentPane.add(cp);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(249, 573, 207, 19);
		contentPane.add(passwordField);
		
		
		
		name = new JTextField();
		name.setBounds(249, 187, 207, 19);
		contentPane.add(name);
		name.setColumns(10);
		
		JButton add = new JButton("Add Customer");
		add.setFont(new Font("Tahoma", Font.BOLD, 13));
		add.addActionListener(new ActionListener() {
			publicvoid actionPerformed(ActionEvent e) {
				
				String NAME=name.getText();
				String MOBILE=mobile.getText();
				String TYPE=type.getText();
				String TIME=time.getText();
				String BALANCE=bal.getText();
				String RECHARGE=rech.getText();
				String AMOUNT=amt.getText();
				String CP=passwordField.getText();
				String UID=uid.getText();
			
				
				
				PreparedStatement insert;
				
				try {
					insert=con.prepareStatement("insert into details(customername,customermobile,simtype,calltime,balance,rechargedate,dueamount,customerpassword,userid)values(?,?,?,?,?,?,?,?,?)");
					
					insert.setString(1, NAME);
					insert.setString(2, MOBILE);
					insert.setString(3, TYPE);
					insert.setString(4, TIME);
					
					insert.setString(5, BALANCE);
					insert.setString(6, RECHARGE);
					insert.setString(7, AMOUNT);
					insert.setString(8, CP);
					insert.setString(9, UID);
					insert.executeUpdate();
					
					JOptionPane.showMessageDialog(null,"Customer added");
					uid.setText("");
					passwordField.setText("");
					amt.setText("");
					name.setText("");
					mobile.setText("");
					type.setText("");
					time.setText("");
					bal.setText("");
					rech.setText("");
					
					inta;
					insert=con.prepareStatement("select*from details");
					ResultSet rs=insert.executeQuery();
				ResultSetMetaData rd=(ResultSetMetaData) rs.getMetaData();
					a=rd.getColumnCount();
				DefaultTableModel df=(DefaultTableModel) table.getModel();
					df.setRowCount(0);
					
					
					while(rs.next()) {
						
						Vectorv=newVector();
						for(inti=1;i<=a;i++)
							
						{
				
						v.add(rs.getString("id"));
						v.add(rs.getString("customername"));
						v.add(rs.getString("customermobile"));
						v.add(rs.getString("simtype"));
						v.add(rs.getString("calltime"));
						v.add(rs.getString("balance"));
						v.add(rs.getString("rechargedate"));
						v.add(rs.getString("dueamount"));
							
					}
					df.addRow(v);
						
						
					}
					
					
					
					
					
					
					
					
					
					
					
					
					
					
				} catch (SQLException e1) {
					// infopage Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
			}
		});
		add.setBounds(105, 626, 140, 31);
		contentPane.add(add);
		
		JButton edit = new JButton("Update");
		edit.addActionListener(new ActionListener() {
			publicvoid actionPerformed(ActionEvent e) {
				
				
				DefaultTableModel df=(DefaultTableModel) table.getModel();
				ints=table.getSelectedRow();
				intid=Integer.parseInt(df.getValueAt(s, 0).toString());/
				
				PreparedStatement pst;
				
				
				try {
					String NAME=name.getText();
					String MOBILE=mobile.getText();
					String TYPE=type.getText();
					String TIME=time.getText();
					String BALANCE=bal.getText();
					String RECHARGE=rech.getText();
					String AMOUNT=amt.getText();
					
					pst=con.prepareStatement("update details set customername=?,customermobile=?,simtype=?,calltime=?,balance=?,rechargedate=?,dueamount=? where id=?");//means yahape id as a input jaarahihaiuske basis pe important and other ka data update hoga
					pst.setString(1, NAME);
					pst.setString(2, MOBILE);
					pst.setString(3, TYPE);
					pst.setString(4, TIME);
					pst.setString(5, BALANCE);
					pst.setString(6, RECHARGE);
					pst.setString(7, AMOUNT);
					pst.setInt(8, id);
					
					pst.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "list Updated");

					inta;
pst=con.prepareStatement("select*from details ");
ResultSet rs=pst.executeQuery();
ResultSetMetaData rd=(ResultSetMetaData)rs.getMetaData();
a=rd.getColumnCount();//coloun count
DefaultTableModel df1=(DefaultTableModel) table.getModel();
df1.setRowCount(0);

					
					while(rs.next()) {
						
						Vectorv=newVector();
						for(inti=1;i<=a;i++)
							
						{
							v.add(rs.getString("id"));
							v.add(rs.getString("customername"));
							v.add(rs.getString("customermobile"));
							v.add(rs.getString("simtype"));
							v.add(rs.getString("calltime"));
							v.add(rs.getString("balance"));
							v.add(rs.getString("rechargedate"));
							v.add(rs.getString("dueamount"));
							
						}
						df1.addRow(v);
						
						
					}
					
					
					
					
					
					
				} catch (SQLException e1) {
					// infopage Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
				
				
			}
		});
		edit.setFont(new Font("Tahoma", Font.BOLD, 13));
		edit.setBounds(278, 626, 97, 31);
		contentPane.add(edit);
		
		JButton done = new JButton("Done");
		done.addActionListener(new ActionListener() {
			publicvoid actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		done.setFont(new Font("Tahoma", Font.BOLD, 13));
		done.setBounds(278, 676, 97, 31);
		contentPane.add(done);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(516, 133, 775, 563);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			
			@Override
			publicvoid mouseClicked(MouseEvent e) {
				
				DefaultTableModel df=(DefaultTableModel) table.getModel();
				intselected=table.getSelectedRow();
			intid=Integer.parseInt(df.getValueAt(selected, 0).toString());
				name.setText(df.getValueAt(selected, 1).toString());
				mobile.setText(df.getValueAt(selected, 2).toString());
				type.setText(df.getValueAt(selected, 3).toString());
				time.setText(df.getValueAt(selected, 4).toString());
				bal.setText(df.getValueAt(selected, 5).toString());
				rech.setText(df.getValueAt(selected, 6).toString());
				amt.setText(df.getValueAt(selected, 7).toString());
				
				
				
			}
		});
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"serial no", "Customer name", "customer mobile no", "prepaid/postpaid", "call total time", "Call balance", "Next Recharge Date", "Due Amount"
			}
		));
		table.getColumnModel().getColumn(1).setPreferredWidth(87);
		table.getColumnModel().getColumn(2).setPreferredWidth(102);
		
		JButton btnNewButton = new JButton("Show customer List");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.addActionListener(new ActionListener() {
			publicvoid actionPerformed(ActionEvent e) {
				
				
				PreparedStatement insert;
				
				inta;
					try {
					insert=con.prepareStatement("select*from details");
					
					
					ResultSet rs=insert.executeQuery();					ResultSetMetaData rd=(ResultSetMetaData) rs.getMetaData();
					a=rd.getColumnCount();//coloun count 
				DefaultTableModel df=(DefaultTableModel) table.getModel();
					df.setRowCount(0);
					
					
					while(rs.next()) {
						
						Vectorv=newVector();
						for(inti=1;i<=a;i++)
							
						{
							v.add(rs.getString("id"));
							v.add(rs.getString("customername"));
							v.add(rs.getString("customermobile"));
							v.add(rs.getString("simtype"));
							v.add(rs.getString("calltime"));
							v.add(rs.getString("balance"));
							v.add(rs.getString("rechargedate"));
							v.add(rs.getString("dueamount"));
							
						}
						df.addRow(v);
						
						
					}
			}
			catch (SQLException e1) {
				// infopage Auto-generated catch block
				e1.printStackTrace();
					
					
				
				
				
				
			 }}
		});
		btnNewButton.setBounds(828, 79, 228, 31);
		contentPane.add(btnNewButton);
		
		JLabel lblUserId = new JLabel("User Id for Customer");
		lblUserId.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUserId.setBounds(40, 533, 164, 25);
		contentPane.add(lblUserId);
		
		uid = new JTextField();
		uid.setColumns(10);
		uid.setBounds(249, 538, 207, 19);
		contentPane.add(uid);
		
		JButton logout = new JButton("LogOut");
		logout.addActionListener(new ActionListener() {
			publicvoid actionPerformed(ActionEvent e) {
				mlogin c= new mlogin();
				c.setVisible(true);
				dispose();
				setVisible(false);
			}
		});
		logout.setBounds(29, 30, 85, 21);
		contentPane.add(logout);
		
		JButton dlt = new JButton("Delete");
		dlt.setFont(new Font("Tahoma", Font.BOLD, 13));
		dlt.addActionListener(new ActionListener() {
			publicvoid actionPerformed(ActionEvent e) {
				DefaultTableModel df=(DefaultTableModel) table.getModel();
				ints=table.getSelectedRow();	
				intid=Integer.parseInt(df.getValueAt(s, 0).toString());

				PreparedStatement pst;
				
				
				try {
					String NAME=name.getText();
					String MOBILE=mobile.getText();
					String TYPE=type.getText();
					String TIME=time.getText();
					String BALANCE=bal.getText();
					String RECHARGE=rech.getText();
					String AMOUNT=amt.getText();
					
			pst=con.prepareStatement("delete from details where id=?");
					
					pst.setInt(1, id);
					
					pst.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "list deleted");

					inta;
					pst=con.prepareStatement("select*from details ");					ResultSet rs=pst.executeQuery();					ResultSetMetaData rd=(ResultSetMetaData) rs.getMetaData();
					a=rd.getColumnCount();//coloun count DefaultTableModel df1=(DefaultTableModel) table.getModel();
					df1.setRowCount(0);
					
					while(rs.next()) {
						
						Vectorv=newVector();
						for(inti=1;i<=a;i++)
							
						{
							v.add(rs.getString("id"));
							v.add(rs.getString("customername"));
							v.add(rs.getString("customermobile"));
							v.add(rs.getString("simtype"));
							v.add(rs.getString("calltime"));
							v.add(rs.getString("balance"));
							v.add(rs.getString("rechargedate"));
							v.add(rs.getString("dueamount"));
							
						}
						df1.addRow(v);
						
						
					}
					
					
					
					
					
					
				} catch (SQLException e1) {
					// infopage Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
				
				
			}
				
				
		});
		dlt.setBounds(395, 626, 111, 31);
		contentPane.add(dlt);
		
		
		
		
		
		
	}
}

	
			
