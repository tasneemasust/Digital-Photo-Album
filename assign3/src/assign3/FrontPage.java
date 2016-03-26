package assign3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

/**
 * This is the front page of the app.
 * It can show all the images in the system.
 * @author Rahmi
 *
 */
public class FrontPage extends JPanel  implements ActionListener{
	
	private JLabel middle, bottom;
	private JPanel top, picture, left, right;
	private JButton prevButton, nextButton;
	int index;
	
	public FrontPage() {
		
		//uses Border layout
		this.setLayout(new BorderLayout());

		setUpandAddTop();
		setUpandAddMiddle(ImageCollection.picArray.get(0));
		setUpandAddBottom();
		setUpButtonPanels();
		
		// first image will be the picture with index 0
		index = 0;
		
		// add panels in window
    	add(top,BorderLayout.NORTH);
 		add(picture,BorderLayout.CENTER);
 		add(bottom,BorderLayout.SOUTH);
 		add(left,BorderLayout.WEST);
 		add(right,BorderLayout.EAST);
	}
	
	/**
	 * This function creates a colorful title for the application
	 * Uses a color array, and alternatively choose color for each character of the title
	 */
	private void setUpandAddTop(){
		
		top = new JPanel();
		top.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
		
		//make color array
		Color[] cl = {Color.CYAN, Color.BLUE, Color.RED, Color.ORANGE, Color.yellow, Color.MAGENTA};
		
		String title = "My Art Photo Showcase";
		for(int i = 0; i < title.length(); i++){
			
			// create a temporary label for each character
			JLabel temp = new JLabel(title.substring(i, i+1));
			
			// assign a color from the array
			temp.setForeground(cl[i % 6]);
			temp.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
			
			//add character to the top panel
			top.add(temp);
		}
	}
	
	/**
	 * Set up the center part of the window with a picture from picArray.
	 * @param pic
	 */
	private void setUpandAddMiddle(Picture pic){
		
		picture = new JPanel();
		
		middle = new JLabel();
		middle.setHorizontalAlignment(JLabel.CENTER);
		middle.setVerticalAlignment(JLabel.CENTER);
		
		middle.setPreferredSize(new Dimension(340, 240));
		
		middle.setForeground(Color.BLUE);
		picture.add(middle);
		
		//call function to set picture
		setPicturetoMiddle (pic.getFilePath()) ;
	}
	
	/**
	 * This function set up left and right panel for prev and next button
	 * Left has prev button with a image
	 * Right has next button with a image
	 */
	private void setUpButtonPanels(){
		
		left = new JPanel();
		prevButton = new JButton("prev");
		prevButton.addActionListener(this);
		left.add(prevButton);
		
		//remove border of the button
		prevButton.setBorder(BorderFactory.createEmptyBorder());
		
		
		right = new JPanel();
		nextButton = new JButton("next");
		nextButton.addActionListener(this);
		right.add(nextButton);
		
		//remove border of the button
		nextButton.setBorder(BorderFactory.createEmptyBorder());
		
		//cal function to set picture
		setPictureforButton();
	}

	
	/**
	 * This function set up bottom panel with developer information
	 * Use html format to show multiple line text on the bottom.
	 */
	private void setUpandAddBottom(){
		
		//make html string to show multiple line text
		String str = "<html>Assignmet #3 "+
    			"<br>Submitted By - "+ 
    			"<br>Marufa Rahmi "+ 
    			", Std Id: W1128039"+
    			"</html>";
		
		bottom = new JLabel(str);
		bottom.setFont(new Font("Comic Sans MS", Font.ITALIC, 14));

		bottom.setHorizontalAlignment(SwingConstants.CENTER);

	}
	
	/**
	 * Renders the selected images for prev and next button
	 * call makeResizedImageIcon() to get the resized image that fits 
	 */
	
    protected void setPictureforButton() {
    	
    	//get image for prev button
        ImageIcon icon = Category.createImageIcon("Button/prev.png" );
        ImageIcon imgThisImg =icon;
        
        if  (icon != null) {
        	 
        	ImageIcon icon1 = makeResizedImageIcon(imgThisImg, 80,80);
     		prevButton.setIcon(icon1);
     		prevButton.setText(null);
        } else {
        	prevButton.setText("Prev");
        }
        
    	//get image for next button
        icon = Category.createImageIcon("Button/next.png" );
        imgThisImg =icon;
        
        if  (icon != null) {
        	 
        	ImageIcon icon1 = makeResizedImageIcon(imgThisImg, 80,80);
     		nextButton.setIcon(icon1);
     		nextButton.setText(null);
        } else {
        	nextButton.setText("Next");
        }
    }
	
    /**
	 * Renders the selected images for middle panel
	 * call makeResizedImageIcon() to get the resized image that fits 
	 */
    protected void setPicturetoMiddle (String name) {
    	
        ImageIcon icon = Category.createImageIcon(name );
        ImageIcon imgThisImg =icon;
        
        if  (icon != null) {
        	 
        	ImageIcon icon1 = makeResizedImageIcon(imgThisImg, 340,260);
     		middle.setIcon(icon1);
     		middle.setText(null);
        } else {
        	middle.setText("Image not found");
        }
    }
    
    
    /**
     * Take an imageIcon and height width, make a imageIcon proportioned to the
     * height and width
     * Then return the rendered image
     * 
     * @param imgicon
     * @param w
     * @param h
     * @return
     */
    private ImageIcon makeResizedImageIcon(ImageIcon imgicon, int w,  int h){
    	
    	Image img = imgicon.getImage();
        Image newimg;
        
        
        if(imgicon.getIconHeight() > imgicon.getIconWidth()){
        	//calculate new width
        	w = imgicon.getIconWidth() *  h / imgicon.getIconHeight();
        	
        }
        else{
        	//calculate new height
        	h = imgicon.getIconHeight() *  w / imgicon.getIconWidth();
        	
        }
        newimg = img.getScaledInstance( w, h ,java.awt.Image.SCALE_SMOOTH);
        
        //returns rendered image
        return new ImageIcon(newimg); 
    }
    

	/**
	 * Listens to "prevButton" button and "nextButton".
	 * next button set the next image of the picArray by incrementing index by 1.
	 * prev button set the previous image of the picArray by decrementing index by 1.
	 * Always get the index % size of picArray so there will be no out of bound search
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		int size = ImageCollection.collectionSize();
        if (e.getSource() == prevButton) {
        	index = (index - 1);
        	
        }
        else if(e.getSource() == nextButton){
        	index = (index + 1);
        }
        int x = index % size;
        if (x < 0)
        {
        	//if negative index, make it circular
            x += size;
        }
    	setPicturetoMiddle(ImageCollection.picArray.get(x).getFilePath());

	}
}
