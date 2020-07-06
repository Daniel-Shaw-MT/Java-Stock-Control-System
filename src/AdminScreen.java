import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Window.Type;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class AdminScreen extends JFrame {

	private JPanel contentPane;

	// This is the administrator control panel.
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminScreen frame = new AdminScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Creates the application.
	
	public AdminScreen() {
		setTitle("Control Panel");
		setType(Type.UTILITY);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 280, 264);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Opens the add to Database object.
		
		JButton AddToDBButton = new JButton("Add To The Data Base");
		AddToDBButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddToDB a = new AddToDB();
				a.setVisible(true);
			}
		});
		AddToDBButton.setBounds(10, 65, 257, 43);
		contentPane.add(AddToDBButton);
		
		// Opens the update the Database object.
		
		JButton btnUpdateTheData = new JButton("Update The Data Base");
		btnUpdateTheData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateDB u = new UpdateDB();
				u.setVisible(true);
			}
		});
		btnUpdateTheData.setBounds(10, 120, 257, 43);
		contentPane.add(btnUpdateTheData);
		
		// Opens the Search the Database object.
		
		JButton btnSearchTheData = new JButton("Search The Data Base");
		btnSearchTheData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchDB s = new SearchDB();
				s.setVisible(true);
			}
		});
		btnSearchTheData.setBounds(10, 174, 257, 43);
		contentPane.add(btnSearchTheData);
		
		JLabel lblNewLabel = new JLabel("Administrative Panel");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblNewLabel.setBounds(10, 11, 257, 43);
		contentPane.add(lblNewLabel);
	}

}
