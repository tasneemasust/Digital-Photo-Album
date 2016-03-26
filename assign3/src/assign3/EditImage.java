package assign3;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

/**
 * This is a pop up window for editing a Picture.
 * 
 * @author Rahmi
 *
 */


public class EditImage extends JFrame  implements ActionListener{
	
	private JPanel panel1, panel2;
	private JPanel panel, buttonPanel;
	
	private JLabel categoryLabel, titleLabel, descriptionLabel, dateLabel, photoGrapherLabel, themeLabel ; 
	private JComboBox<String> category;
	private JTextField title, photoGrapher,datef;
	private JTextArea description;
	
	private JButton saveButton;
	private JButton cancelButton;
	
	Picture pic;

    
	/**
	 * Shows the information of pic in a separate window.
	 * Provide editing option.
	 * @param pic
	 */
    public  EditImage( Picture pic){
    	
    	this.pic = pic;
		
    	setTitle("Edit Picture Information");
    	this.setLayout(new FlowLayout());
    	this.setBounds(100, 100, 620, 400);
    	
    	// set up window with editing option
    	setUpUI();
		
	}
    
    /**
     * Calls necessary function to create UI for editing.
     * Uses GridBagLayout for alignment.
     */
    private void setUpUI(){
    	
    	//create label and comboBox for category
    	setUpCategoryPanel();
		
    	//create label and JTextField for Title
	    setUpTitlePanel();
	    
	    //create label and JTextField for photoGrapher
	    setUpPhotoGrapherPanel();
	    
	    //create label and JTextField for date
	    setUpDatePanel();
	    
	   
	    //creating panel
	    panel1 = new JPanel();
	    
	    //Adding GridBagConstraints so that all the label and fields can be aligned
	    panel1.setLayout(new GridBagLayout());
	    GridBagConstraints c = new GridBagConstraints();

	    
	    //adding label and JTextField for Title
	    c.gridx = 0;
	    c.gridy = 0;
	    panel1.add(titleLabel, c);
	    c.gridx = 1;
	    c.gridy = 0;
	    panel1.add(title, c);
	     
	    //adding label and JComboBox for Category
	    c.gridx = 0;
	    c.gridy = 1;
	    panel1.add(categoryLabel, c);
	    c.gridx = 1;
	    c.gridy = 1;
	    panel1.add(category, c);
	    
	    //adding label and JTextField for photoGrapher
	    c.gridx = 0;
	    c.gridy = 3;
	    panel1.add(photoGrapherLabel, c);
	    c.gridx = 1;
	    c.gridy = 3;
	    panel1.add(photoGrapher, c);
	     
	    //adding label and JTextField for date
	    c.gridx = 0;
	    c.gridy = 4;
	    panel1.add(dateLabel, c);
	    c.gridx = 1;
	    c.gridy = 4;
	    panel1.add(datef, c);
	
	    
	    
	    
	    //adding panel to the window
	    buttonPanel = new JPanel();
	    
	    
	    // creating save button and cancel button
	    saveButton = new JButton("Save Changes");
	    cancelButton = new JButton("Cancel");

    	buttonPanel.add(saveButton);
    	buttonPanel.add(cancelButton);
    	
    	saveButton.addActionListener(this);
    	cancelButton.addActionListener(this);
    	
    	
	    // add panel to the window
	    add(panel1);
	    
	  //creating and adding a panel for image description
	    addDescriptionPanel();
	    
	 // add buttonPanel to the window
	    add(buttonPanel);
    }
    
    
	
	/**
	 * setUpCategoryPanel() function creates a label and ComboBox
	 * to select Category for the image to be uploaded
	 */
	
	public void setUpCategoryPanel(){
		
		categoryLabel = new JLabel("Category: ");
		categoryLabel.setAlignmentX(LEFT_ALIGNMENT);
		
		category = createCategoryComboBox();
		category.setPreferredSize(new Dimension(140, 30));
		
	}
	
	
	
	/**
	 * setupTitlePanel() function creates label and textField
	 * to set title for the image to be uploaded
	 */
	
	public void setUpTitlePanel(){
		
	    
		titleLabel = new JLabel("Title: ");
		titleLabel.setAlignmentX(LEFT_ALIGNMENT);
		
		
		title = new JTextField(pic.getTitle());
		title.setPreferredSize(new Dimension(140, 30));
		
	}
	
	
	/**
	 * setUpDatePanel() function creates label and textField
	 * to set date for the image to be uploaded
	 */
	
	public void setUpDatePanel(){
		
	    
		dateLabel = new JLabel("Date: ");
		dateLabel.setAlignmentX(LEFT_ALIGNMENT);
		
		
		datef = new JTextField(pic.getDate());
		datef.setPreferredSize(new Dimension(140, 30));
		
	}
	
	
	/**
	 * setupTitlePanel() function creates label and textField
	 * to set title for the image to be uploaded
	 */
	
	public void setUpPhotoGrapherPanel(){
		
	    
		photoGrapherLabel = new JLabel("PhotoGrapher: ");
		photoGrapherLabel.setAlignmentX(LEFT_ALIGNMENT);
		
		
		photoGrapher = new JTextField(pic.getPhotoGrapher());
		photoGrapher.setPreferredSize(new Dimension(140, 30));
		
	}
	
	
	
	/**
	 * addDescriptionPanel() function creates a panel with a label and textArea
	 * to set Description for the image to be uploaded
	 */
	
	public void addDescriptionPanel(){
		 
		//creating panel for Description
		panel2 = new JPanel();
		
		//setting layout
	    panel2.setLayout(new FlowLayout(FlowLayout.LEFT));
	    //panel3.setPreferredSize(new Dimension(240,100)); 
	    
	    descriptionLabel = new JLabel("Description: ");
	    descriptionLabel.setAlignmentX(LEFT_ALIGNMENT);
	    
	    description = new JTextArea(pic.getDescription());
		description.setPreferredSize(new Dimension(160, 120));
		
		description.setLineWrap(true);
		description.setWrapStyleWord(true);
		
		JScrollPane scroll = new JScrollPane (description);
		
		//adding label and TextArea for title
		panel2.add(descriptionLabel);
		panel2.add(scroll);
		
		//adding panel to main pane
		add(panel2);
	}
	
	

	/**
    * JComboxBox is a Swing component that renders
    *  a drop-down list of choices and lets the user selects one item from the list. 
    *  The ComboBox can be read-only or editable. 
    */
	public JComboBox createCategoryComboBox(){  
		   
	      category = new JComboBox(ImageCollection.catArray);
	      category.setForeground(Color.BLUE);
	      category.setFont(new Font("Arial", Font.BOLD, 12));
	      category.setEditable(true);
	      
	        // add the event handler
	        category.addActionListener(new ActionListener(){
	        	public void actionPerformed(ActionEvent e){
	        		 JComboBox cb = (JComboBox)e.getSource();
	        	     String place = (String)cb.getSelectedItem();        	    
	        	     categoryLabel.setText(place);
	        	}
	        }
	        );            
	      return category;  
	   }

	
	/**
	 * Listens to "Save Changes" button and "Cancel"
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
        if (command.equals("Save Changes")) {
        	
        	try{
        		// if user want to save changes it calls the editAction
        		editAction();
        		this.dispose();
        	}
        	catch(Exception e1){
        		
        	}
        }
        else if(command.equals("Cancel")){
        	this.dispose();
        }
	}
	
	
	/**
	 * Collects all the new information from the input fields and updates
	 * hash-map in the ImageCollection class.
	 */
	public void editAction(){

		// assume everything is ok, if there is any problem set fail = true.
		boolean fail = false;
		
		//get title
		String tt = title.getText();
		if(!fail && tt == null){
			JOptionPane.showMessageDialog(null, "Error saving file!",
                    "Give a title to your image", JOptionPane.ERROR_MESSAGE);
			
			fail = true;
		}
		
		// get category, photographer and description.
		String ct = (String) category.getSelectedItem();
		String pg = photoGrapher.getText();
		String ds = description.getText();
		
		
		
		/*
		 * check if the date entered is in correct format
		 */
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		Date testDate = null;
		String date = datef.getText();
		try{
		    testDate = df.parse(date);
		} catch (ParseException ey){ JOptionPane.showMessageDialog(null, "Error saving file!",
                "Invalid date format", JOptionPane.ERROR_MESSAGE);
			fail = true;
		}
		 
		if (!df.format(testDate).equals(date)){
			JOptionPane.showMessageDialog(null, "Error saving file!",
	                "Invalid date!", JOptionPane.ERROR_MESSAGE);
				fail = true;
		} else {
		    System.out.println("valid date");
		}
		
		
		
		if(!fail){
			//saving all the info in image collections
			
			//make a picture object
			Picture p = new Picture(pic.getFilePath(), tt, ct, pg, ds, date);
			
			//insert into image Collection
			boolean flag = ImageCollection.editImage(p);
			
			if(flag){
				JOptionPane.showMessageDialog(null, "Your file is successfully edited!", "Confirmation",
		                JOptionPane.INFORMATION_MESSAGE);
				
				System.out.println("picture Count: "+ImageCollection.images.size());
			}
			else{
				JOptionPane.showMessageDialog(null, "File already exists!",
		                "Error saving file!", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
}
    
