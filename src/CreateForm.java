import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;

import dao.CustomerDAO;

public class CreateForm extends JFrame implements ActionListener {

	JButton save = new JButton("Save");
	JButton cancel = new JButton("Cancel");
	
	JTextField txtName = new JTextField();
	JTextField txtHarga = new JTextField();
	
	JTextField txtMenu = new JTextField();
	JTextField txtStock = new JTextField();
	
	
	JMenuItem exit = new JMenuItem("Exit");
	
	public CreateForm() {
		// TODO Auto-generated constructor stub
		initMenuBar();
		initFrame();
	}
	
	public void initMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu1 = new JMenu("Actions");
		
		exit.addActionListener(this);
		menu1.add(exit);
		
		menuBar.add(menu1);
		
		setJMenuBar(menuBar);
	}
	
	public void initFrame() {
		setTitle("Create Form");
		setSize(400, 500);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocation(400, 200);
		setLayout(new GridLayout(0,2));
		setResizable(false);
		setVisible(true);
		
		initComponent();
	}
	
	public void initComponent() {
		JLabel name = new JLabel("Nama Menu :");
		add(name);
		add(txtName);
		
		JLabel Harga = new JLabel("Harga Menu :");
		add(Harga);
		add(txtHarga);
		
		JLabel Menu = new JLabel("Kode Menu :");
		add(Menu);
		add(txtMenu);
		
		JLabel Stok = new JLabel("Stok Menu :");
		add(Stok);
		add(txtStock);
		
		
		save.addActionListener(this);
		cancel.addActionListener(this);
		add(save);
		add(cancel);
	}
	
	
	
	
	public boolean validateData() {
		if(txtName.getText().isEmpty() || txtHarga.getText().isEmpty() || txtMenu.getText().isEmpty() || txtStock.getText().isEmpty() ){
			return false;
		}else {
			return true;
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(save)) {
			if(validateData() == false) {
				JOptionPane.showMessageDialog(null, "Please fill all input");
			}else {
				CustomerDAO customerDAO = new CustomerDAO();
				customerDAO.insertData(txtName.getText(), txtPhone.getText(), cmbCity.getSelectedItem().toString(), grpGender.getSelection().getActionCommand().toString());
				JOptionPane.showMessageDialog(null, "Success Add Data");
			}
		}else if(e.getSource().equals(cancel)){
			new MainMenu();
			setVisible(false);
		}else if(e.getSource().equals(exit)) {
			System.exit(0);
		}
	}

}

