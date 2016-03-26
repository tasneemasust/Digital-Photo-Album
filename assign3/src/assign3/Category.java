package assign3;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;


import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * This class setup data and view for ImageViewer.java
 * 
 * @author Rahmi
 *
 */
public class Category implements ListSelectionListener {
	
	private JLabel picture;
	
	//List of image names
	private JList<String> list;
	static DefaultListModel listModel;
	
	private JSplitPane splitPane;
	private JScrollPane listScrollPane;
	Picture pic;
	 
	public Category() {

		listModel = new DefaultListModel();
	    
		// Make a image list using ImageCollection.imgnames
		makeListModel(ImageCollection.imgnames);
		
		//create image panel
		createImagePanel();
		
        //set up picture label
        picture = new JLabel();
        picture.setFont(picture.getFont().deriveFont(Font.ITALIC));
        picture.setHorizontalAlignment(JLabel.CENTER);
        picture.setVerticalAlignment(JLabel.CENTER);
        picture.setPreferredSize(new Dimension(340, 230));
        
        
        JScrollPane pictureScrollPane = new JScrollPane(picture);

        //Create a split pane with the two scroll panes in it.
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                                   listScrollPane, pictureScrollPane);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(120);
        
        //Provide minimum sizes for the two components in the split pane.
        Dimension minimumSize = new Dimension(100, 50);
        
        //Provide a preferred size for the split pane.
        splitPane.setPreferredSize(new Dimension(480, 300));
        
	}
	
	
	/**
	 * Add listModel to JList and add the list to the listScrollPane
	 * 
	 */
	private void createImagePanel() {
		
		list = new JList(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setSelectedIndex(0);
	    list.addListSelectionListener(this);
	     
	    listScrollPane = new JScrollPane(list);
	}
	
	
	/**
	 * This function is called when user changes category in the image viewer tab.
	 * Clears the listModel and update with new image names according to the user
	 * selected category or date.
	 * 
	 * @param imgnames
	 */
	public static void makeListModel(ArrayList<String> imgnames){
		
		listModel.clear();
		
		for (String s: imgnames)
		{
		    String sbtr = s.substring(0, s.length() );
		    listModel.addElement(sbtr);
		}
	}

	
	
	/**
	 * Listens to the image list
	 */
    public void valueChanged(ListSelectionEvent e) {
    	
    	if(list.getSelectedIndex() >= 0){
    		//pic = 
    		updateLabel(ImageCollection.pathArray.get(list.getSelectedIndex()));
    	}
    }
    
    /**
     * Return list to the image viewer.
     * @return
     */
	public JList getImageList() {
        return list;
    }
	
	
	/**
     * Return JSplitPane to the image viewer.
     * @return
     */
	public JSplitPane getSplitPane() {
        return splitPane;
    }
	
	
	

	/**
	 * Make the height and width of the image proportioned to the label.
	 * Renders the selected image. Then add it to the label.
	 * 
	 * @param icon
	 * @return resized image icon
	 */
    protected void updateLabel (String name) {
        ImageIcon icon = createImageIcon(name );
        ImageIcon imgThisImg =icon;
        
        if  (icon != null) {
        	 Image img = imgThisImg.getImage();
             Image newimg;
             int h, w;
             
             // make the height and width proportioned to the label
             // calculate new h and w for the image
             
             if(imgThisImg.getIconHeight() > imgThisImg.getIconWidth()){
             	h = picture.getHeight();
             	w = imgThisImg.getIconWidth() *  h / imgThisImg.getIconHeight();
             	if(w > picture.getWidth() ) w = picture.getWidth();

             }
             else{
             	w = picture.getWidth();
             	h = imgThisImg.getIconHeight() *  w / imgThisImg.getIconWidth();
             	
             	if(h > picture.getHeight()) h = picture.getHeight();
             }
             
             // resize image for perfect fit
             newimg = img.getScaledInstance( w, h ,java.awt.Image.SCALE_SMOOTH);
     		 ImageIcon icon1 = new ImageIcon(newimg); 
     		
             picture.setIcon(icon1);
             picture.setText(null);
        } 
        else {
            picture.setText("Image not found");
        }
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

}
