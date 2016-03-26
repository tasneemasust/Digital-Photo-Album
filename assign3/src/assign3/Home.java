package assign3;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

/**
 * Start page with main function
 * @author Rahmi
 *
 */
public class Home {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home window = new Home();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Home() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		//Create frame and set Boundaries
		frame = new JFrame();
		frame.setBounds(100, 100, 680, 420);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

    	
		// creating instance of ImageCollection
		// To load some image data for the app
    	ImageCollection ic = new ImageCollection();
    	
    	

    	// center the mainFrame on screen
    	frame.setLocationRelativeTo(null);
    	
    	JTabbedPane tabbedPane = new JTabbedPane();
    	
    	tabbedPane.addTab("Home", new FrontPage());
    	tabbedPane.addTab("Insert Image", new InsertImage());
    	tabbedPane.addTab("Browse Image", new ImageBrowser());
    	tabbedPane.addTab("Search Image", new ImageSearch());
    	
    	frame.getContentPane().add(tabbedPane);
    	frame.add(tabbedPane, BorderLayout.CENTER);
    	frame.setVisible(true);
	}

}
