package assign3;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * This JPanel uses for showing Image.It has three parts.
 * Part 1: shows names of the images in a list.
 * Part 2: Shows selected image from the list
 * Part 3: shows detail information about selected image
 * and holds edit and delete button.
 * 
 * @author Rahmi
 *
 */
public class ImageViewer extends JPanel implements ActionListener, ListSelectionListener{
	private JLabel label;
	private JPanel panel;
	
	JList theList;
	
	private JButton editButton, deleteButton;
	
	Picture pic;
	
	public ImageViewer() {
		
		//Create an instance of SplitPaneDemo
		//System.out.println("print 1"+ImageCollection.images.size());
		
        Category category = new Category();
        JSplitPane top = category.getSplitPane();
        
        category.getImageList().addListSelectionListener(this);

        top.setBorder(null);

        //Create a label to show information
        label = new JLabel("<html>Click on a category on the left."
        		+ "<br> All image  names of that category will appear on above."
        		+ "<br>Then click on an image name in the list.",
                           JLabel.CENTER);
        
        
        panel = new JPanel();
        
        
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");
        
        editButton.addActionListener(this);
        deleteButton.addActionListener(this);
        
        
        panel.add(label);
        panel.add(editButton);
        panel.add(deleteButton);
        
        //these buttons are initially not visible
        //become available to user after selecting an image
        editButton.setVisible(false);
        deleteButton.setVisible(false);
        
        //Create a split pane and put "top" (a split pane)
        //and JLabel instance in it.
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, top, panel);
        
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(240);

        //Provide minimum sizes for the two components in the split pane
        top.setMinimumSize(new Dimension(100, 50));
        label.setMinimumSize(new Dimension(100, 30));

        //Add the split pane to this frame
        add(splitPane);
	}

	
	
	
	/**
	 * ListListener function.
	 * called every time an item on the image list is selected by user.
	 */
	public void valueChanged(ListSelectionEvent e) {
		
        if (e.getValueIsAdjusting())
            return;

        theList = (JList)e.getSource();
        
        if (theList.isSelectionEmpty()) {
            label.setText("Nothing selected.");
        } 
        else {
            
            try{
            	// getting filePath from ImageCollection
	            String key = ImageCollection.pathArray.get(theList.getSelectedIndex());
	            
	            System.out.println(theList.getSelectedIndex());
	            // getting Picture object using filePath
	            pic = ImageCollection.images.get(key);
	            
	            // update the bottom label with picture information
	            updateLabel(pic);
	            
	            //editButton and deleteButton become available
	            editButton.setVisible(true);
	            deleteButton.setVisible(true);
            }
            catch(Exception ex){
            	label.setText("error fatching image");
            }
        }
	}	
	
	
	/**
	 * Update label with picture information.
	 * Make a html string for multiple line text for the label.
	 * @param pic
	 */
	public void updateLabel(Picture pic){
		String a = "",b = "",c = "",d="",e="",f= "";
		if(pic.getTitle() != null)a =pic.getTitle();
		if(pic.getCategory() != null)b =pic.getCategory();
		if(pic.getDate() != null)c =pic.getDate();
		if(pic.getPhotoGrapher() != null)d =pic.getPhotoGrapher();
		if(pic.getDescription() != null)e =pic.getDescription();
		
		String str = "<html>Title: "+a+
    			"<br>Category: "+ b+
    			"<br>Date: "+ c+
    			"<br>Photographer: "+c+
    			"<br>Description: "+ e+
    			"</html>";
    
		label.setText(str);
	}
	
	
	/**
	 * Action listener for edit and delete.
	 */
	public void actionPerformed(ActionEvent e) {
        boolean status = false;
        String command = e.getActionCommand();
        
        if (command.equals("Edit")) {
            try {
                editFile();
           
            } catch (Exception e1) {
            	e1.printStackTrace();
            	System.out.println("error in image viewer");
            	}      
        } 
       
        else if (command.equals("Delete")) {
                	
            if (theList.isSelectionEmpty()) {
            	
                label.setText("Nothing selected.");
                editButton.setVisible(false);
	            deleteButton.setVisible(false);
            } 
            
            
            else {
            	
                int index = theList.getSelectedIndex();
                                
                int dialogButton = JOptionPane.YES_NO_OPTION;
            	int dialogResult = JOptionPane.showConfirmDialog(this, 
            			"Are you sure you want to delete this picture?", "Delete confirmation", dialogButton);
            	
            	
            	if(dialogResult == 0) {
            		System.out.println("Yes option");
            	  
            		//delete from list
            		Category.listModel.remove(index);
            		
            		ImageCollection.deleteImage(index);
            	  
            	  
            	} else {
            	  System.out.println("Do Nothing");
            	} 
            }
            
            
        	
        }
    }


	/**
	 * This function creates a pop up window for editing picture information.
	 */
	private void editFile() {
		
		EditImage editImage = new EditImage(pic);
		editImage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE );
		editImage.setVisible(true);
	}
	

}
