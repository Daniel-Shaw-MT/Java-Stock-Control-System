import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Window.Type;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class UpdateDB extends JFrame {

	private JPanel contentPane;
	private JTextField UNtextField;
	private JTextField PTtextField;
	private JTextField PVtextField;
	private JTextField PStextField;
	private JTextField PPtextField;
	private JTextField PItextField;

	// This is the Update application the user would use this to update/delete existing records in the database.
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateDB frame = new UpdateDB();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Creates the application.
	
	public UpdateDB() {
		setResizable(false);
		setTitle("Update Database (Admin)");
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 494, 365);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// This is the update database button. Once this button is clicked it will take the user input and update the database.
		
		JButton btnUpdateDb = new JButton("Update Record");
		btnUpdateDb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn = null;
				  try {
					  	// Retrieves the user input.
						String PID = PItextField.getText();
						String PName = UNtextField.getText();
						String PType = PTtextField.getText();
						String PVendor = PVtextField.getText();
						String PPrice = PPtextField.getText();
						String PStock = PStextField.getText();
						
						// Parsing the user input into correct variables.
						int FID = Integer.parseInt(PID);
						int FStock = Integer.parseInt(PStock);
						double FPrice = Double.parseDouble(PPrice);
					  
					  // Creates connection to the database.
				      String url2 = "jdbc:sqlite:C:\\\\Users\\\\dansh\\\\eclipse-workspace\\\\MATSEC 2020 PROJECT\\\\JavaDBS\\\\Shop.db";
				      conn = DriverManager.getConnection(url2);
				      String uquery = "UPDATE items set Name=?,Type=?,Vendor=?,Stock=?,Price=? where ID=?";
				      PreparedStatement pst = conn.prepareStatement(uquery);
				      
				      // Sets statement values.
				      pst.setString(1, PName);
				      pst.setString(2, PType);
				      pst.setString(3, PVendor);
				      pst.setInt(4, FStock);
				      pst.setDouble(5, FPrice);
				      pst.setInt(6, FID);
				      int i=pst.executeUpdate();
				      
				  }catch(Exception e1){
					  
				  }
			}
		});
		btnUpdateDb.setBounds(334, 57, 138, 43);
		contentPane.add(btnUpdateDb);
		
		JLabel lblNewLabel = new JLabel("Product Name:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblNewLabel.setBounds(10, 53, 145, 43);
		contentPane.add(lblNewLabel);
		
		JLabel lblProductType = new JLabel("Product Type:");
		lblProductType.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblProductType.setBounds(10, 111, 145, 43);
		contentPane.add(lblProductType);
		
		JLabel lblProductVendor = new JLabel("Product Vendor:");
		lblProductVendor.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblProductVendor.setBounds(10, 165, 159, 43);
		contentPane.add(lblProductVendor);
		
		JLabel lblProductStock = new JLabel("Product Stock:");
		lblProductStock.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblProductStock.setBounds(10, 219, 145, 43);
		contentPane.add(lblProductStock);
		
		JLabel lblProductPrice = new JLabel("Product Price:");
		lblProductPrice.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblProductPrice.setBounds(10, 273, 145, 43);
		contentPane.add(lblProductPrice);
		
		UNtextField = new JTextField();
		UNtextField.setBounds(165, 57, 159, 43);
		contentPane.add(UNtextField);
		UNtextField.setColumns(10);
		
		PTtextField = new JTextField();
		PTtextField.setColumns(10);
		PTtextField.setBounds(165, 111, 159, 43);
		contentPane.add(PTtextField);
		
		PVtextField = new JTextField();
		PVtextField.setColumns(10);
		PVtextField.setBounds(165, 165, 159, 43);
		contentPane.add(PVtextField);
		
		PStextField = new JTextField();
		PStextField.setColumns(10);
		PStextField.setBounds(165, 219, 159, 43);
		contentPane.add(PStextField);
		
		PPtextField = new JTextField();
		PPtextField.setColumns(10);
		PPtextField.setBounds(165, 273, 159, 43);
		contentPane.add(PPtextField);
		
		// This is the delete record button. It will delete a selected record.
		
		JButton button = new JButton("Delete Record");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String IDS = PItextField.getText();
				int ID = Integer.parseInt(IDS);
				
				// Creates connection to the database.
				Connection conn = null;
			    try {
			      String url = "jdbc:sqlite:C:\\Users\\dansh\\Desktop\\JavaDBS\\Shop.db";
			      conn = DriverManager.getConnection(url);
			      String query = "delete from items where ID =?";
			      try {
			    	  PreparedStatement pst = conn.prepareStatement(query);
			    	  pst.setInt(1, ID);
			          pst.executeUpdate();
			    
			      
			      // SQL Error handling: //
			      } catch (SQLException e2 ) {
			    	  System.out.println(e2.getMessage());
			    	  JOptionPane.showMessageDialog(null,"Database inaccessibile! (Error code: 0x2)");
			    	 
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
			    // End of SQL Error handling //
			      	}

			    }
	
		});
		button.setBounds(334, 111, 138, 43);
		contentPane.add(button);
		
		JLabel lblProductId = new JLabel("Product ID:");
		lblProductId.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblProductId.setBounds(10, 0, 145, 43);
		contentPane.add(lblProductId);
		
		PItextField = new JTextField();
		PItextField.setColumns(10);
		PItextField.setBounds(165, 4, 159, 43);
		contentPane.add(PItextField);
		
		// This is the search for record button. It will search by record ID.
		
		JButton btnSearchForRecord = new JButton("Search for Record");
		btnSearchForRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String IDS = PItextField.getText();
				
				PItextField.setText(" ");
				UNtextField.setText(" ");
				PTtextField.setText(" ");
				PVtextField.setText(" ");
				PPtextField.setText(" ");
				PStextField.setText(" ");
				
				// Creates connection to the database.
				Connection conn = null;
			    try {
			      String url = "jdbc:sqlite:C:\\Users\\dansh\\Desktop\\JavaDBS\\Shop.db";
			      conn = DriverManager.getConnection(url);	
			      Statement stmt = null;
			      String query = "select * from items where ID = "+IDS;
			      
			      try {
			          stmt = conn.createStatement();
			          ResultSet rs = stmt.executeQuery(query);
			          if(rs.next()) {	
							do{
								// This queries the database using the inputed strings.
								String GID = rs.getString(1);
								String GName = rs.getString(2);
								String GType = rs.getString(3);
								String GVendor = rs.getString(4);
								String GStock = rs.getString(5);
								String GPrice = rs.getString(6);
								
								// Sets the labels to the retrieved data.
								PItextField.setText(GID);
								UNtextField.setText(GName);
								PTtextField.setText(GType);
								PVtextField.setText(GVendor);
								PStextField.setText(GStock);
								PPtextField.setText(GPrice);
								
							}while(rs.next());
							
							
						
			          }else{
						
							JOptionPane.showMessageDialog(null,"Product Not Found (Error code: 1x1)");
			          }
			      
			       // SQL Error handling: //
			          
			      } catch (SQLException e2 ) {
			    	  JOptionPane.showMessageDialog(null,"Database inaccessibile! (Error code: 0x2)");
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
			    	  // End of SQL Error handling //
			      }

			}
		});
		btnSearchForRecord.setBounds(334, 3, 138, 43);
		contentPane.add(btnSearchForRecord);
	}

}
