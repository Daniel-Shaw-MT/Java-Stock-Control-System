import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JTextArea;
import javax.swing.JPasswordField;
import java.awt.Window.Type;

public class Login {

	private JFrame frmLogin;
	private JTextField IDField;
	private JTextField USRtextField;
	private JPasswordField PWORDtextField;
	
	// This is the login application this will be the first thing that the user will see when they launch the program.
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	// Creates the application.
	
	public Login() {
		initialize();
	}
	
	// Creating frame contents.
	
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmLogin.setResizable(false);
		frmLogin.setTitle("Login");
		frmLogin.setType(Type.UTILITY);
		frmLogin.setBounds(100, 100, 479, 278);
		frmLogin.getContentPane().setLayout(null);
		
		IDField = new JTextField();
		IDField.setBounds(113, 42, 220, 39);
		frmLogin.getContentPane().add(IDField);
		IDField.setColumns(10);
		
		USRtextField = new JTextField();
		USRtextField.setEnabled(false);
		USRtextField.setColumns(10);
		USRtextField.setBounds(113, 92, 220, 39);
		frmLogin.getContentPane().add(USRtextField);
		
		JLabel lblId = new JLabel("ID code:");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblId.setBounds(10, 42, 121, 39);
		frmLogin.getContentPane().add(lblId);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setEnabled(false);
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblUsername.setBounds(10, 92, 121, 39);
		frmLogin.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setEnabled(false);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblPassword.setBounds(10, 142, 104, 39);
		frmLogin.getContentPane().add(lblPassword);
		
		JLabel TitleLabel = new JLabel("Duplex enterprises shop system.");
		TitleLabel.setFont(new Font("Tahoma", Font.PLAIN, 27));
		TitleLabel.setBounds(7, 2, 597, 39);
		frmLogin.getContentPane().add(TitleLabel);
		
		// This is the search button. Once pressed it will execute the required code.
		
		JButton SearchButton = new JButton("Search");
		SearchButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		SearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				USRtextField.setEnabled(true);
				PWORDtextField.setEnabled(true);
				lblUsername.setEnabled(true);
				lblPassword.setEnabled(true);
			}
		});
		SearchButton.setBounds(343, 42, 114, 39);
		frmLogin.getContentPane().add(SearchButton);
		
		// This is the login button. Once pressed it will execute the required code.
		
		JButton LoginButton = new JButton("Login...");
		LoginButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		LoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ID;
				
				ID = IDField.getText();
				
				  String USRN;
			      String PWORD;
			      
			      String USRND;
			      String PASS;
			      int RIGHTS;
				
			    // This creates the connection to the database.
			      
				Connection conn = null;
			    try {
			      String url = "jdbc:sqlite:C:\\Users\\dansh\\eclipse-workspace\\MATSEC 2020 PROJECT\\JavaDBS\\Users.db";
			      conn = DriverManager.getConnection(url);
			      
			      // When this code is executed it will search the database using the ID of a user.
			      
			      Statement stmt = null;
			      String query = "select UName, PWord, ARights from Users where ID= "+Integer.parseInt(ID);
			      try {
			          stmt = conn.createStatement();
			          ResultSet rs = stmt.executeQuery(query);
			          
			          // Returns desired data.
			          USRND = rs.getString("UName");
			          PASS = rs.getString("PWord");
			          RIGHTS = rs.getInt("ARights");
			          
			          // Retrieves user input.
			          USRN = USRtextField.getText();
			          PWORD = PWORDtextField.getText();
			          
			          // Checks that the user input and password input.
			          if(USRN.equals(USRND) && PWORD.equals(PASS)) {
			        	  
			        	  // This handles roles/rights in the system. Allows for different levels of user rights in one application.
			      
			        	  switch(RIGHTS) {
			        	  
			        	  case 1:
			        		  // Opens administrative screen
			      
			        		  AdminScreen a = new AdminScreen();
			  				  a.setVisible(true);
			        		  
			        		  break;
			        		  
			        	  case 2:
			        		  // Opens stock keeper screen
			        		  
			        		  SearchDB d = new SearchDB();
			        		  d.setVisible(true);
			        		  
			        		  break;
			        		  
			        	  default:
			        		  // Not a valid rights level
			        		  JOptionPane.showMessageDialog(null, "You dont have access to the system. Please contact your administrator. (Error code 0x3)");
			        		 
			        		  break;
			        	  }
			        		
			         
			          }else{
			        	  // Incorrect password.
			        	  
			        	  JOptionPane.showMessageDialog(null, "Password or Username Incorrect"); 
			          }
			          
			      // SQL Error handling: //  
			          
			      } catch (SQLException e1 ) {
			          throw new Error("Problem", e1);
			      
			      } finally {
			          
			    	  if (stmt != null) { stmt.close(); }
			      }
			    
			      } catch (SQLException e1) {
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

			    }

				// End of SQL Error handling //
			
		});
		LoginButton.setBounds(343, 192, 114, 39);
		frmLogin.getContentPane().add(LoginButton);
		
		PWORDtextField = new JPasswordField();
		PWORDtextField.setEnabled(false);
		PWORDtextField.setBounds(113, 142, 220, 39);
		frmLogin.getContentPane().add(PWORDtextField);
		
		// This is the Quit button it will open the are you sure screen.
		
		JButton btnQuit = new JButton("Quit...");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AreYouSur s = new AreYouSur();
				s.setVisible(true);
			}
		});
		btnQuit.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnQuit.setBounds(10, 192, 114, 39);
		frmLogin.getContentPane().add(btnQuit);
	
		
}
}