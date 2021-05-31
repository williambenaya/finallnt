import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.*;

import dao.CustomerDAO;

public class UpdateForm extends JFrame implements ActionListener, MouseListener {

	JMenuItem back, exitMenu;
	JTextField txtName, txtHarga ,txtKode,txtStock;
	
	JButton update;
	JTable table;
	String id = "";
	
	public UpdateForm() {
		initMenuBar();
		initFrame();
		addUpdateButton();
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
		setTitle("Update Form");
		setSize(500, 500);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocation(400, 200);
		setLayout(new GridLayout(3,1));
		setResizable(false);
		setVisible(true);
		
		initTable();
		initFormUpdate();
	}
	
	public void initTable() {
		Vector<String> column = new Vector<>();
		column.add("Kode");
		column.add("Nama Menu");
		column.add("Harga");
		column.add("Stock");
		
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
	
	public void initFormUpdate() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0,2));
		
		JLabel name = new JLabel("Nama Menu :");
		txtName = new JTextField();
		panel.add(name);
		panel.add(txtName);
		
		JLabel Harga = new JLabel("Harga :");
		txtHarga = new JTextField();
		panel.add(Harga);
		panel.add(txtHarga);
		
		JLabel Kode = new JLabel("Kode :");
		txtKode = new JTextField();
		panel.add(Kode);
		panel.add(txtKode);
		
		JLabel Stock = new JLabel("Stok :");
		panel.add(Stock);
		panel.add(txtStock);
		
		add(panel);
	}
	
	
	
	
	
	public void addUpdateButton() {
		update = new JButton("Update Data");
		update.addActionListener(this);
		add(update);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(back)){
			new MainMenu();
			setVisible(false);
		}else if(e.getSource().equals(exitMenu)) {
			System.exit(0);
		}else if(e.getSource().equals(update)) {
			if(id.equals("")) {
				JOptionPane.showMessageDialog(null, "You have to choose data first!");
			}else {
				CustomerDAO customerDAO = new CustomerDAO();
				customerDAO.updateData(id, txtName.getText(), txtHarga.getText(), txtKode.getText(), txtStock.getText());
				JOptionPane.showMessageDialog(null, "Success Update!");
				new UpdateForm();
				setVisible(false);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int selectRowIndex = table.getSelectedRow();
		id = table.getValueAt(selectRowIndex, 0).toString();
		String name = table.getValueAt(selectRowIndex, 1).toString();
		String Harga = table.getValueAt(selectRowIndex, 2).toString();
		String Kode = table.getValueAt(selectRowIndex, 3).toString();
		String Stock = table.getValueAt(selectRowIndex, 4).toString();
		txtName.setText(name);
		txtHarga.setText(Harga);
		txtKode.setText(Kode);
		txtStock.setText(Stock);
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

}
