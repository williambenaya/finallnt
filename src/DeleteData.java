import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.*;

import dao.CustomerDAO;

public class DeleteData extends JFrame implements ActionListener, MouseListener {

	JMenuItem back, exitMenu;
	JTable table;
	JLabel txtName, txtPhone, txtCity, txtGender;
	JButton delete;
	String id = "";
	
	public DeleteData() {
		initMenuBar();
		initFrame();
		addDeleteButton();
	}
	
	public void initMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu1 = new JMenu("Actions");

		back = new JMenuItem("Back to main menu");
		exitMenu = new JMenuItem("Exit");
		
		back.addActionListener(this);
		exitMenu.addActionListener(this);

		menu1.add(back);
		menu1.add(exitMenu);
		
		menuBar.add(menu1);
		
		setJMenuBar(menuBar);
	}
	
	public void initFrame() {
		setTitle("Delete Data");
		setSize(500, 500);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocation(400, 200);
		setLayout(new GridLayout(3,1));
		setResizable(false);
		setVisible(true);
		
		initTable();
		initComponent();
	}
	
	public void initTable() {
		Vector<String> column = new Vector<>();
		column.add("ID");
		column.add("Name");
		column.add("Phone");
		column.add("City");
		column.add("Gender");
		CustomerDAO customerDAO = new CustomerDAO();
		
		table = new JTable(customerDAO.getData(), column) {
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		
		table.addMouseListener(this);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(0, 0, 500, 500);
		
		add(scroll);
	}
	
	public void initComponent() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0,2));
		
		JLabel name = new JLabel("Name :");
		txtName = new JLabel();
		panel.add(name);
		panel.add(txtName);
		
		JLabel phone = new JLabel("Phone :");
		txtPhone = new JLabel();
		panel.add(phone);
		panel.add(txtPhone);
		
		JLabel city = new JLabel("City :");
		txtCity = new JLabel();
		panel.add(city);
		panel.add(txtCity);
		
		JLabel gender = new JLabel("Gender :");
		txtGender = new JLabel();
		panel.add(gender);
		panel.add(txtGender);

		add(panel);
	}
	
	public void addDeleteButton() {
		delete = new JButton("Delete Data");
		delete.addActionListener(this);
		add(delete);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int selectRowIndex = table.getSelectedRow();
		id = table.getValueAt(selectRowIndex, 0).toString();
		String name = table.getValueAt(selectRowIndex, 1).toString();
		String phone = table.getValueAt(selectRowIndex, 2).toString();
		String city = table.getValueAt(selectRowIndex, 3).toString();
		String gender = table.getValueAt(selectRowIndex, 4).toString();
		
		txtName.setText(name);
		txtPhone.setText(phone);
		txtCity.setText(city);
		txtGender.setText(gender);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(back)){
			new MainMenu();
			setVisible(false);
		}else if(e.getSource().equals(exitMenu)) {
			System.exit(0);
		}else if(e.getSource().equals(delete)) {
			if(id.equals("")) {
				JOptionPane.showMessageDialog(null, "You have to choose data first!");
			}else {
				CustomerDAO customerDAO = new CustomerDAO();
				customerDAO.deleteData(id);
				JOptionPane.showMessageDialog(null, "Success Delete!");
				new DeleteData();
				setVisible(false);
			}
		}
	}

}
