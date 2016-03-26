package assign3;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;

import javax.imageio.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * 
 * @author Rahmi
 *
 */
public class ImageChooser extends JPanel implements ActionListener {
	private JButton openBtn;
	
	private JPanel panel;
	private JLabel label;
	private String fileName, filePath;
	
	protected static  BufferedImage icon;

	public ImageChooser() {   	
	     
	     // Create the panel
	     
	     panel = new JPanel();
	     panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
	     panel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
	     panel.setPreferredSize(new Dimension(380, 300));

	     
	     //Create the picture label
	     label = new JLabel();
	     
	     //set  bg color for label
	     label.setBackground(Color.WHITE);
	     label.setOpaque(true);
	     
	     // Set the picture in the center of the label
	     label.setHorizontalAlignment(SwingConstants.CENTER);
	     label.setVerticalAlignment(SwingConstants.CENTER);
	     
	     //set size for label
	     label.setMaximumSize(new Dimension(380, 260));
	     
	     //Add the Label to panel
	     panel.add(label);
	     
	     
	     // Create the button
	     openBtn = new JButton("Open File");
	     openBtn.setActionCommand("open");
	     
	     
	     // Add the event handlers to the buttons
	     openBtn.addActionListener(this);
	 
	    
	     // Add the buttons to the panel
	     panel.add(openBtn);
	  
	    
	     // Add the panel to the window
	     add(panel,BorderLayout.PAGE_END);
    }	
	
	
	/**
	 * button event handling
	 */
		
	public void actionPerformed(ActionEvent e) {
        boolean status = false;
        String command = e.getActionCommand();
        if (command.equals("open")) {
            try {
                status = openFile();
                if (!status)
                    JOptionPane.showMessageDialog(null, "Error opening file!",
                            "File Open Error", JOptionPane.ERROR_MESSAGE);
            } catch (IOException e1) {e1.printStackTrace();}      
        } 
    }
	
	
	
	/**
	 * Open image file
	 * @return true when image successfully uploaded
	 * @throws IOException
	 */
	boolean openFile() throws IOException {
		
		// create file chooser
        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Open File");
        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fc.setCurrentDirectory(new File("."));
        
        
        // set filters for files
        // only image files are allowed to upload
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg","JPG", "gif","png","gif","bmp");
        fc.setFileFilter(filter);
        
        int result = fc.showOpenDialog(this);
        
        
        if (result == JFileChooser.CANCEL_OPTION) {
            return true;
        } 
        else if (result == JFileChooser.APPROVE_OPTION) {

            File currentFile = fc.getSelectedFile();
            FileInputStream fileInputStream = new FileInputStream(currentFile);            
            
            icon = ImageIO.read(fileInputStream);
            
            System.out.println(currentFile);
            //set file path
            String  path = currentFile.getAbsolutePath();

            filePath = path;
            		
            //new File("/Users/Rahmi/Documents/workspace/assign3/./bin/assign3/").toURI().relativize(new File(path).toURI()).getPath();
            
            //make a image icon that fits the label
            ImageIcon icon1 = setIcontoLable(icon);
			
            //set icon to the label
            label.setIcon(icon1);
           
        } 
        else {
            return false;
        }
        return true;
    }
	
	
	
	/**
	 * Make the height and width of the image proportioned to the label
	 * @param icon
	 * @return resized image icon
	 */
	public ImageIcon setIcontoLable(BufferedImage icon){
		
		
		ImageIcon imgThisImg = new ImageIcon(icon);
        
        Image img = imgThisImg.getImage();
        Image newimg;
        
        int h, w;
        
        // make the height and width proportioned to the label
        // calculate new h and w
        if(imgThisImg.getIconHeight() > imgThisImg.getIconWidth()){
        	h = label.getHeight();
        	w = imgThisImg.getIconWidth() *  h / imgThisImg.getIconHeight();
        	if(w > label.getWidth() ) w = label.getWidth();

        }
        else{
        	w = label.getWidth();
        	h = imgThisImg.getIconHeight() *  w / imgThisImg.getIconWidth();
        	
        	if(h > label.getHeight()) h = label.getHeight();
        }
        
        
        //scale the image according to the proportion
        newimg = img.getScaledInstance( w, h ,java.awt.Image.SCALE_SMOOTH);
        
		return new ImageIcon(newimg);  
	}
	
	
	/**
	 * 
	 * @return
	 */
	
	public String getFilePath(){
		return filePath;
	}
}
	
