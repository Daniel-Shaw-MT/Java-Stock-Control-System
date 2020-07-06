import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;

public class AddToDB extends JFrame {
	private JTextField PNtextField;
	private JTextField PTtextField;
	private JTextField PVtextField;
	private JTextField PStextField;
	private JTextField PPtextField;

	// This is the Add to the database panel.
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddToDB frame = new AddToDB();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Creates the application.
	
	public AddToDB() {
		setResizable(false);
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Add To Database (Admin)");
		setBounds(100, 100, 692, 313);
		getContentPane().setLayout(null);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter Product Name:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblNewLabel.setBounds(10, 11, 260, 44);
		getContentPane().add(lblNewLabel);
		
		JLabel lblEnterProductType = new JLabel("Enter Product Type:");
		lblEnterProductType.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblEnterProductType.setBounds(10, 66, 260, 44);
		getContentPane().add(lblEnterProductType);
		
		JLabel lblEnterProductVendor = new JLabel("Enter Product Vendor:");
		lblEnterProductVendor.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblEnterProductVendor.setBounds(10, 121, 260, 44);
		getContentPane().add(lblEnterProductVendor);
		
		JLabel lblEnterProductStock = new JLabel("Enter Product Stock:");
		lblEnterProductStock.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblEnterProductStock.setBounds(10, 176, 260, 44);
		getContentPane().add(lblEnterProductStock);
		
		JLabel lblEnterProductPrice = new JLabel("Enter Product Price:");
		lblEnterProductPrice.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblEnterProductPrice.setBounds(10, 231, 260, 44);
		getContentPane().add(lblEnterProductPrice);
		
		PNtextField = new JTextField();
		PNtextField.setBounds(280, 11, 241, 44);
		getContentPane().add(PNtextField);
		PNtextField.setColumns(10);
		
		PTtextField = new JTextField();
		PTtextField.setColumns(10);
		PTtextField.setBounds(280, 66, 241, 44);
		getContentPane().add(PTtextField);
		
		PVtextField = new JTextField();
		PVtextField.setColumns(10);
		PVtextField.setBounds(280, 121, 241, 44);
		getContentPane().add(PVtextField);
		
		PStextField = new JTextField();
		PStextField.setColumns(10);
		PStextField.setBounds(280, 176, 241, 44);
		getContentPane().add(PStextField);
		
		PPtextField = new JTextField();
		PPtextField.setColumns(10);
		PPtextField.setBounds(280, 231, 241, 44);
		getContentPane().add(PPtextField);
		
		// This is the add to database button. Once this button is clicked it will take the user input and add it to the database.
		
		JButton AddToDBButton = new JButton("Add to Database");
		AddToDBButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// Creates the link to the database.
				
				Connection conn = null;
			    try {
			      String url = "jdbc:sqlite:C:\\\\Users\\\\dansh\\\\eclipse-workspace\\\\MATSEC 2020 PROJECT\\\\JavaDBS\\\\Shop.db";
			      conn = DriverManager.getConnection(url);
			      	
			      // Retrieves user input.
					String GName = PNtextField.getText();
					String GType = PTtextField.getText();
					String GVendor = PVtextField.getText();
					String GStock = PStextField.getText();
					String GPrice = PPtextField.getText();
					
					// Parses it into correct variables.
					
					int FStock = Integer.parseInt(GStock);
					double FPrice = Double.parseDouble(GPrice);
				  
				  // This code will insert the proper data into the correct database.	
					
			      Statement stmt = null;
			      String insert = "INSERT INTO Items (Name, Type, Vendor, Stock, Price)"+"VALUES(?,?,?,?,?)";
				
			      // This code sends the correct information to the database to added.
			      
			      try {
			    	  PreparedStatement pst = conn.prepareStatement(insert);
			          pst.setString(1, GName);
			          pst.setString(2, GType);
			          pst.setString(3, GVendor);
			          pst.setInt(4, FStock);
			          pst.setDouble(5, FPrice);
			          
			          pst.execute();
			          pst.close();
			          conn.close();
			          JOptionPane.showMessageDialog(null, "Added to the database");
			        
			      // SQL Error handling: //
			          
			      } catch (SQLException e2 ) {
			    	  throw new Error("Problem", e2);
			          
			      } finally {
			          
			    	  if (stmt != null) { stmt.close(); }
			      }
			    
			      } catch (SQLException e1) {
			    	  JOptionPane.showMessageDialog(null,"Bad input, please check inputs! (Error code: 1x2)");
			    	  throw new Error("Problem", e1);
			    	  
			      } finally {
			    	
			    	  try {
			    		  if (conn != null) {
			    			  conn.close();
			    		  }
			    	  } catch (SQLException ex) {
			    		  System.out.println(ex.getMessage());
			    	  }
			
			      }


			    // End of SQL Error handling //
			    
			    // This clears the fields after code is executed.
				PNtextField.setText(" ");
				PTtextField.setText(" ");
				PVtextField.setText(" ");
				PPtextField.setText(" ");
				PStextField.setText(" ");
			   
			}
		});
		AddToDBButton.setBounds(528, 11, 148, 44);
		getContentPane().add(AddToDBButton);
	}
}
