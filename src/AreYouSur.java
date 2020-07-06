import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;

public class AreYouSur extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AreYouSur frame = new AreYouSur();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AreYouSur() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setType(Type.UTILITY);
		setTitle("Quit?");
		setIconImage(Toolkit.getDefaultToolkit().getImage(AreYouSur.class.getResource("/javax/swing/plaf/metal/icons/Warn.gif")));
		setBounds(100, 100, 460, 159);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(20, 69, 134, 38);
		contentPane.add(btnCancel);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnQuit.setBounds(290, 69, 134, 38);
		contentPane.add(btnQuit);
		
		JLabel lblAreYouSure = new JLabel("Are you sure that you want to quit?");
		lblAreYouSure.setFont(new Font("IBM Plex Sans SemiBold", Font.PLAIN, 18));
		lblAreYouSure.setBounds(20, 11, 331, 47);
		contentPane.add(lblAreYouSure);
	}

}
