import java.awt.BorderLayout;
import java.awt.EventQueue;

import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.print.DocFlavor.STRING;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JProgressBar;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.AbstractListModel;

public class SearchDB extends JFrame {

	private JPanel contentPane;
	private JTextField QueryField;
	int val=0;
	private JLabel lblProductName;
	private JLabel lblProductType;
	private JLabel lblProductVendor;
	private JLabel lblProductPrice;
	private JTextField QuantitytextField;
	private JLabel PNlabel;
	private JLabel PTlabel;
	private JLabel PVlabel;
	private JLabel PPlabel;
	private JTable AddedItemsTable;
	private JList list;
	private JLabel lblProductId;
	private JLabel PIlabel;
	private JLabel lblProductInStock;
	private JLabel PSlabel;
	private JList ItemList;
	private JList MoneyList;
	private JButton btnClearAll;
	private JButton btnCheckout;
	private JLabel lblTotal;
	private JLabel TPlabel;
	private double Accumulator = 0;
	private String FinalTotalPrice;
	private JTextField MoneytextField;
	private JLabel lblMoneyGiven;
	private JButton btnPay;
	private JLabel lblTotalChange;
	// This is the search Data base object.
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchDB frame = new SearchDB();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Creates the application.

	public SearchDB() {
		setResizable(false);
		
		setTitle("Search The Database");
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 918, 918);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		QueryField = new JTextField();
		QueryField.setBounds(123, 11, 151, 34);
		contentPane.add(QueryField);
		QueryField.setColumns(10);
		
		// This is the search button it queries the database using ID
		
		JButton SearchButton = new JButton("Search");
		SearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String IDS = QueryField.getText();
				
				// Clears all of the fields.
				
				QueryField.setText(" ");
				PIlabel.setText(" ");
				PNlabel.setText(" ");
				PTlabel.setText(" ");
				PVlabel.setText(" ");
				PPlabel.setText(" ");
				PSlabel.setText(" ");
				
				// Creates the connection to the database.
				
				Connection conn = null;
			    try {
			      String url = "jdbc:sqlite:C:\\\\Users\\\\dansh\\\\eclipse-workspace\\\\MATSEC 2020 PROJECT\\\\JavaDBS\\\\Shop.db";
			      conn = DriverManager.getConnection(url);
			      
			      // Selects the records with ID.
			      
			      Statement stmt = null;
			      String query = "select * from items where ID = "+IDS;
			      try {
			          stmt = conn.createStatement();
			          ResultSet rs = stmt.executeQuery(query);
			          if(rs.next())
						{
							do{
								String GID = rs.getString(1);
								String GName = rs.getString(2);
								String GType = rs.getString(3);
								String GVendor = rs.getString(4);
								String GStock = rs.getString(5);
								String GPrice = rs.getString(6);
								
								// Sets the labels to the retrieved data.
								
								PIlabel.setText(GID);
								PNlabel.setText(GName);
								PTlabel.setText(GType);
								PVlabel.setText(GVendor);
								PSlabel.setText(GStock);
								PPlabel.setText(GPrice);
							
							}while(rs.next());
						}
						else
						{
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
			    		  
			    	  }catch (SQLException ex) {
			    		  System.out.println(ex.getMessage());
			    	  }
			    
			    // End of SQL Error handling //
			    	 
			      }

			    }

				
				
				
			
		});
		SearchButton.setBounds(286, 11, 114, 34);
		contentPane.add(SearchButton);
		
		// Add To Cart
		
		DefaultListModel RecieptProducer = new DefaultListModel();
		DefaultListModel MoneyProducer = new DefaultListModel();
		// This button adds to cart when pressed.

		
		JButton AddTCButton = new JButton("Add To Cart");
		AddTCButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Creating the connection to the Database Shop.db.
				
			    	// Handling inputs. Converting where needed.
					
					String RStock = PSlabel.getText();
					int FStock = Integer.parseInt(RStock);
					
					
					String quantityr = QuantitytextField.getText();
					int quantity = Integer.parseInt(quantityr);
					
					String LName = PNlabel.getText();		
					
					// Parsing price to double.
					
					String pricer = PPlabel.getText();
					Double pricefin = Double.parseDouble(pricer);
					
					// Calculating total price.
					
					double EndTot = quantity*pricefin;
					
					Accumulator+=EndTot;
					FinalTotalPrice = Double.toString(Accumulator);
					
					TPlabel.setText(FinalTotalPrice);
					
					// Calculating stock after purchase.
					
					int calcstock = FStock-quantity;
					String finalstock = Integer.toString(calcstock);
					
					// Doesn't allow user to purchase more than current stock.
					
					if(calcstock<0) {
						JOptionPane.showMessageDialog(null ,"Not Enough In Stock.");

					}
					else {
						String SID = PIlabel.getText();
						int ID = Integer.parseInt(SID);
						Connection connq = null;
						
					    try {
					      String url = "jdbc:sqlite:C:\\Users\\dansh\\Desktop\\JavaDBS\\Shop.db";
					      connq = DriverManager.getConnection(url);
					      String uquery = "UPDATE items set Stock=? where ID=?";
					      PreparedStatement pst = connq.prepareStatement(uquery);
					      
					      pst.setString(1, finalstock);
					      pst.setInt(2,ID );
					      int i=pst.executeUpdate();
					    } catch (SQLException e1 ) {
					    	  
					    	  throw new Error("Problem", e1);
					    }
					
					}
					
					if(calcstock>0) {
					
					// This will update the stock after every purchase.
						
					Connection connUp = null;
					
					// This function appends retrieved and calculated values into the JList
					
					RecieptProducer.addElement(" ");
					RecieptProducer.addElement("Product Name: "+LName);
					RecieptProducer.addElement("Product Quantity: "+quantity);
					RecieptProducer.addElement("Total Price: "+EndTot);
					RecieptProducer.addElement(" ");
					ItemList.setModel(RecieptProducer);
						}
					else {
						JOptionPane.showMessageDialog(null, "Not enough in stock. 1x2");
					
					}
				
			}
		});
		AddTCButton.setBounds(410, 426, 135, 34);
		contentPane.add(AddTCButton);
		
		
		JLabel lblNewLabel = new JLabel("Enter ID:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblNewLabel.setBounds(10, 11, 113, 34);
		contentPane.add(lblNewLabel);
		
		lblProductName = new JLabel("Product Name:");
		lblProductName.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblProductName.setBounds(10, 201, 174, 34);
		contentPane.add(lblProductName);
		
		lblProductType = new JLabel("Product Type:");
		lblProductType.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblProductType.setBounds(10, 246, 174, 34);
		contentPane.add(lblProductType);
		
		lblProductVendor = new JLabel("Product Vendor:");
		lblProductVendor.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblProductVendor.setBounds(10, 291, 195, 34);
		contentPane.add(lblProductVendor);
		
		lblProductPrice = new JLabel("Product Price:");
		lblProductPrice.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblProductPrice.setBounds(10, 336, 195, 34);
		contentPane.add(lblProductPrice);
		
		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblQuantity.setBounds(10, 426, 195, 34);
		contentPane.add(lblQuantity);
		
		QuantitytextField = new JTextField();
		QuantitytextField.setColumns(10);
		QuantitytextField.setBounds(123, 426, 277, 34);
		contentPane.add(QuantitytextField);
		
		PNlabel = new JLabel("");
		PNlabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
		PNlabel.setBounds(182, 201, 414, 34);
		contentPane.add(PNlabel);
		
		PTlabel = new JLabel("");
		PTlabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
		PTlabel.setBounds(182, 246, 414, 34);
		contentPane.add(PTlabel);
		
		PVlabel = new JLabel("");
		PVlabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
		PVlabel.setBounds(203, 291, 393, 34);
		contentPane.add(PVlabel);
		
		PPlabel = new JLabel("");
		PPlabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
		PPlabel.setBounds(172, 336, 435, 34);
		contentPane.add(PPlabel);
		
		lblProductId = new JLabel("Product ID:");
		lblProductId.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblProductId.setBounds(10, 156, 174, 34);
		contentPane.add(lblProductId);
		
		PIlabel = new JLabel("");
		PIlabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
		PIlabel.setBounds(144, 156, 414, 34);
		contentPane.add(PIlabel);
		
		lblProductInStock = new JLabel("Product In Stock:");
		lblProductInStock.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblProductInStock.setBounds(10, 381, 226, 34);
		contentPane.add(lblProductInStock);
		
		PSlabel = new JLabel("");
		PSlabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
		PSlabel.setBounds(213, 381, 226, 34);
		contentPane.add(PSlabel);
		
		TPlabel = new JLabel("");
		TPlabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
		TPlabel.setBounds(82, 471, 318, 34);
		contentPane.add(TPlabel);
			
		ItemList = new JList();
		ItemList.setModel(new AbstractListModel() {
			String[] values = new String[] {""};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		

		JList MoneyList = new JList();
		MoneyList.setModel(new AbstractListModel() {
			String[] values = new String[] {""};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		ItemList.setBounds(555, 11, 337, 539);
		contentPane.add(ItemList);
		
		// This button clears all fields.
		
		btnClearAll = new JButton("Clear All");
		btnClearAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				QueryField.setText(" ");
				PIlabel.setText(" ");
				PNlabel.setText(" ");
				PTlabel.setText(" ");
				PVlabel.setText(" ");
				PPlabel.setText(" ");
				PSlabel.setText(" ");
				TPlabel.setText(" ");
				ItemList.removeAll();
				MoneyList.removeAll();
			}
		});
		
		btnClearAll.setBounds(410, 11, 135, 34);
		contentPane.add(btnClearAll);
		
		JLabel lblSelectedProductInformation = new JLabel("Selected Product Information:");
		lblSelectedProductInformation.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblSelectedProductInformation.setBounds(10, 97, 535, 34);
		contentPane.add(lblSelectedProductInformation);
		
		lblTotal = new JLabel("Total:");
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblTotal.setBounds(10, 471, 195, 34);
		contentPane.add(lblTotal);
		
		MoneytextField = new JTextField();
		MoneytextField.setColumns(10);
		MoneytextField.setBounds(172, 516, 228, 34);
		contentPane.add(MoneytextField);
		
		lblMoneyGiven = new JLabel("Money Given:");
		lblMoneyGiven.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblMoneyGiven.setBounds(10, 516, 195, 34);
		contentPane.add(lblMoneyGiven);
		
		btnPay = new JButton("Pay");
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String moneyinput = MoneytextField.getText();
				Double money = Double.parseDouble(moneyinput);
				ChangeCalculator Change = new ChangeCalculator();
				Change.Calculate(Accumulator, money);
				
				   int Hundreds = Change.changeCounter[11]; 
			       int Fifty = Change.changeCounter[10];
			       int TwentyEuros = Change.changeCounter[9];
			       int TenEuros = Change.changeCounter[8];
			       int FiveEuros = Change.changeCounter[7];
			       int TwoEuros = Change.changeCounter[6];
			       int OneEuros = Change.changeCounter[5];
			       int FiftyCent = Change.changeCounter[4];
			       int TwentyCent = Change.changeCounter[3];
			       int TenCent = Change.changeCounter[2];	
			       int FiveCent = Change.changeCounter[1];
			       int OneCent = Change.changeCounter[0];
			// Sets Money To Give	
				MoneyProducer.addElement(OneCent);
				MoneyProducer.addElement(FiveCent);
				MoneyProducer.addElement(TenCent);
				MoneyProducer.addElement(TwentyCent);
				MoneyProducer.addElement(FiftyCent);
				MoneyProducer.addElement(OneEuros);
				MoneyProducer.addElement(TwoEuros);
				MoneyProducer.addElement(FiveEuros);
				MoneyProducer.addElement(TenEuros);
				MoneyProducer.addElement(TwentyEuros);
				MoneyProducer.addElement(Fifty);
				MoneyProducer.addElement(Hundreds);
				MoneyList.setModel(MoneyProducer);
			
		}
			
		});
		btnPay.setBounds(410, 516, 135, 34);
		contentPane.add(btnPay);
		
		lblTotalChange = new JLabel("Total Change:");
		lblTotalChange.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblTotalChange.setBounds(10, 561, 195, 52);
		contentPane.add(lblTotalChange);
		
		JList list_1 = new JList();
		list_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		list_1.setModel(new AbstractListModel() {
			String[] values = new String[] {"1c Coins", "5c Coins", "10c Coins", "20c Coins", "50c Coins", "\u20AC1 Coins", "\u20AC2 Coins", "\u20AC5 Notes", "\u20AC10 Notes", "\u20AC20 Notes", "\u20AC50 Notes", "\u20AC100 Notes"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list_1.setBounds(10, 629, 86, 249);
		contentPane.add(list_1);
		
		JLabel lblNewLabel_1 = new JLabel("Money:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 597, 86, 34);
		contentPane.add(lblNewLabel_1);
		
		MoneyList.setFont(new Font("Tahoma", Font.PLAIN, 15));
		MoneyList.setBounds(123, 629, 769, 249);
		contentPane.add(MoneyList);
		
			
		
			}
		}
