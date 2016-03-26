package assign3;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

/**
 * This is a pop up frame to view larger image.
 * 
 * @author Rahmi
 *
 */
public class ShowImage extends JFrame {
	
	private JPanel picture;
	private JLabel label1;
	
	/**
	 * shows the parameter pic in a separate window.
	 * @param pic
	 */
	public ShowImage(Picture pic){
		
		//set title with file path
		super.setTitle(pic.getFilePath());
		
		//set layout and boundary
		this.setLayout(new FlowLayout());
    	this.setBounds(100, 100, 620, 400);
    	
    	//set up image in the picture panel
    	setImagePanel(pic);
		
	}
	
	
	/**
	 * Set up label for picture. Then add picture to the label.
	 * Add label to the picture panel.
	 * 
	 * @param pic
	 */
	public void setImagePanel(Picture pic){
		//create panel
		picture = new JPanel();
		
		//create and set up label
		label1 = new JLabel();
		label1.setHorizontalAlignment(JLabel.CENTER);
		label1.setVerticalAlignment(JLabel.CENTER);
		
		label1.setPreferredSize(new Dimension(480, 340));
		label1.setBackground(Color.white);
		label1.setOpaque(true);
		label1.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		
		//add label to the picture panel
		picture.add(label1);
		
		// add image to the label
		setPicturetoLabel(pic.getFilePath()) ;
		
		//add picture panel to the window
		add(picture);
	}

	
	/**
	 * Make the height and width of the image proportioned to the label.
	 * Renders the selected image. Then add it to the label.
	 * 
	 * @param name
	 */
	
    protected void setPicturetoLabel(String name) {
    	
        ImageIcon icon = Category.createImageIcon(name );
        ImageIcon imgThisImg =icon;
        
        
        if  (icon != null) {
        	
        	 Image img = imgThisImg.getImage();
             Image newimg;
             int h, w;
             
             // make the height and width proportioned to the label
             // calculate new h and w
             if(imgThisImg.getIconHeight() > imgThisImg.getIconWidth()){
            	 h = (int) label1.getPreferredSize().getHeight();
            	 w = imgThisImg.getIconWidth() *  h / imgThisImg.getIconHeight();
            	 if(w > label1.getPreferredSize().getWidth() ) w = (int) label1.getPreferredSize().getWidth() ;

             }
             else{
            	 w = (int) label1.getPreferredSize().getWidth() ;
            	 h = imgThisImg.getIconHeight() *  w / imgThisImg.getIconWidth();
             	
             	 if(h > label1.getPreferredSize().getHeight()) h = (int) label1.getPreferredSize().getHeight();
             }

             newimg = img.getScaledInstance( w, h ,java.awt.Image.SCALE_SMOOTH);
             ImageIcon icon1 = new ImageIcon(newimg); 
     		
             label1.setIcon(icon1);
        } 
        else {
        	label1.setText("Image not found");
        }
    }
    
}
