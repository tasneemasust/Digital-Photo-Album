package assign3;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

/**
 * This is a JPanel for searching images. The search can work on title, category, date,
 * photographer or description. The search results are shown in JButtons with images on
 * them. User can click on the images to see larger version of the image. Large version
 * is a pop up window.
 * 
 * @author Rahmi
 *
 */
public class ImageSearch extends JPanel implements ActionListener{
	
	private JPanel top, bottom;
	private JScrollPane scrollPane;
	private JTextField searchText;
	private JButton search;
	
	private final int height = 120, width = 120;
	private ArrayList<JButton> buttonArray = new ArrayList<>();
	
	
	
	public ImageSearch(){
		
		//set up top panel for search
		top = new JPanel();
		top.setPreferredSize(new Dimension(300,50));
		top.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		
		//Set up search field
		searchText = new JTextField();
		searchText.setPreferredSize(new Dimension(160,30));
		search = new JButton("Search");
		search.addActionListener(this);
		
		// add the textField and button to the top panel
		top.add(searchText);
		top.add(search);
		
		// add top panel to the window
		this.add(top);
		
		// create bottom panel for search result
		bottom = new JPanel();
		bottom.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));

		// grid layout to show images in a grid view
		GridLayout gridLayout = new GridLayout(0,4);
		
		// set gap  between images
		gridLayout.setVgap(20);
		gridLayout.setHgap(20);
		
		bottom.setLayout(gridLayout);
		
		//add the bottom panel in a scrollPane
		scrollPane = new JScrollPane(bottom);
		scrollPane.setPreferredSize(new Dimension(600,280));
		
		// add scrollpane to the window
		this.add(scrollPane);
		
	}
	
	
	/**
	 * Action listener for search buttons and images.
	 * 
	 * When search is pressed this function calls ImageCollection.searchImages() with
	 * the user input string. Then calls the makaegrid() function create grid view for
	 * images.
	 * 
	 * If any of the images clicked it calls findButtonIndex() function to find which 
	 * image is clicked then calls showFile() to create a popup window with a large image.
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String command = e.getActionCommand();
        if (command.equals("Search")) {
        	String str = searchText.getText();
        		
        	bottom.removeAll();
        	buttonArray.clear();
		
        	//bottom.add(new JLabel("Loading Image . . ."));
        	
        	ImageCollection.searchImages(str);
        	
		
        	//bottom.removeAll();
        	makaegrid();
        	bottom.revalidate();
        	bottom.repaint();
        }
        else{
        	
        	int index = findButtonIndex((JButton)e.getSource());
        	showFile(ImageCollection.searchRes.get(index));
        	
        }
	}
	
	
	/**
	 * This function make grid view for search result of images. Creates buttons
	 * for every image using a for loop. Adds action listener to each button. stores
	 * the buttons in a ArrayList for later use.
	 * Also resize images to fit in a certain size.
	 */
	public void makaegrid(){
		
		
		for(Picture pic : ImageCollection.searchRes){
			
			//create a new button
			JButton button = new JButton();
			button.setPreferredSize(new Dimension(width, height));
			button.addActionListener(this);
			button.setBorder(BorderFactory.createEmptyBorder());
			button.setToolTipText("Click to view larger image.");
			
			// get image icon for pic 
			ImageIcon icon = createImageIcon(pic.getFilePath());
    		//System.out.println("path in category"+pic.getFilePath());

    		//System.out.println("path in seach"+ImageCollection.pathArray.get((0)));

			Image img = icon.getImage();
			
			int h, w;
            
			// calculate new height and width for image to show on grid view
            if(icon.getIconHeight() > icon.getIconWidth()){
            	h = height;
            	w = icon.getIconWidth() *  h / icon.getIconHeight();
            	
            	if(w > width ) w = width;

            }
            else{
            	w = width;
            	h = icon.getIconHeight() *  w / icon.getIconWidth();
            	
            	if(h > height) h = height;
            }
            
            // resize image
			Image newimg = img.getScaledInstance(w, h, java.awt.Image.SCALE_SMOOTH);
			icon = new ImageIcon(newimg);  
			button.setIcon(icon);
			
			// add button to the array list
			buttonArray.add(button);
			
			// add button to the grid
			bottom.add(button);
		}
	}
	
	/**
	 * Finds which image is clicked in the grid view.
	 * Loop through all button in the array list to find out which one is clicked.
	 * Then return the index.
	 * @param btn
	 * @return
	 */
	protected int findButtonIndex(JButton btn){
		int index = 0;
		
		for(JButton button: buttonArray){
			if(btn == button){
				return index;
			}
			index++;
		}
		return 0;
	}
	
	
	
	
	 /** 
     * Returns an ImageIcon, or null if the path was invalid.
     */
    
    protected static ImageIcon createImageIcon(String path) {
       java.net.URL imgURL = Category.class.getResource(path);
       //java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } 
        else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
    
    
    
    /**
     * creates a pop up window to show bigger image
     * @param showPic
     */
    private void showFile(Picture showPic) {
		
    	ShowImage showImage = new ShowImage(showPic);
        showImage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE );
		showImage.setVisible(true);
	}

}

