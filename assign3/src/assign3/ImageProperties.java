package assign3;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.*;

/**
 * This class generate a panel with input fields to add information for 
 * an Picture.
 * @author Rahmi
 *
 */
public class ImageProperties extends JPanel  {
	
	
	private JLabel categoryLabel, titleLabel, descriptionLabel, dateLabel, photoGrapherLabel, themeLabel ; 
	private JComboBox<String> category, theme;
	private JTextField title, photoGrapher,date;
	private JTextArea description;
	private JPanel panel, panel3;
	
	
	public ImageProperties(){
		
		/*
		 * set up layout for inserting information
		 */
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

		/*
		 * create label and comboBox for category
		 */
		setUpCategoryPanel();
		
		/*
		 * create label and JTextField for Title
		 */
	    setUpTitlePanel();
	    
	    /*
	     * create label and JTextField for photoGrapher
	     */
	    setUpPhotoGrapherPanel();
	    
	    /*
	     * create label and JTextField for date
	     */
	    setUpDatePanel();
	    
	    
	    /*
	     * creating panel
	     */
	    panel = new JPanel();
	    
	    /*
	     * Adding GridBagConstraints so that all the label and fields can be aligned
	     */
	    panel.setLayout(new GridBagLayout());
	    GridBagConstraints c = new GridBagConstraints();
	    
	    /*
	     *  adding label and JTextField for Title
	     */
	    c.gridx = 0;
	    c.gridy = 0;
	    panel.add(titleLabel, c);
	    c.gridx = 1;
	    c.gridy = 0;
	    panel.add(title, c);
	    
	    /*
	     * adding label and JComboBox for Category
	     */
	    c.gridx = 0;
	    c.gridy = 1;
	    panel.add(categoryLabel, c);
	    c.gridx = 1;
	    c.gridy = 1;
	    panel.add(category, c);
	    
	    /*
	     *  adding label and JTextField for photoGrapher
	     */
	    c.gridx = 0;
	    c.gridy = 3;
	    panel.add(photoGrapherLabel, c);
	    c.gridx = 1;
	    c.gridy = 3;
	    panel.add(photoGrapher, c);
	    
	    /*
	     *  adding label and JTextField for date
	     */
	    c.gridx = 0;
	    c.gridy = 4;
	    panel.add(dateLabel, c);
	    c.gridx = 1;
	    c.gridy = 4;
	    panel.add(date, c);
	    
	    
	 
	    
	    /*
	     *  adding panel to the window
	     */
	    add(panel);
	    
	    
	    /*
	     * creating and adding a panel for image description
	     */
	    addDescriptionPanel();
	   	
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
		
		title = new JTextField();
		title.setPreferredSize(new Dimension(140, 30));
		
	}
	
	
	/**
	 * setUpDatePanel() function creates label and textField
	 * to set date for the image to be uploaded
	 */
	
	public void setUpDatePanel(){
		
	    
		dateLabel = new JLabel("Date: ");
		dateLabel.setAlignmentX(LEFT_ALIGNMENT);
		
		date = new HintTextField("MM/DD/YYYY");
		date.setPreferredSize(new Dimension(140, 30));
		
	}
	
	
	/**
	 * setupTitlePanel() function creates label and textField
	 * to set title for the image to be uploaded
	 */
	
	public void setUpPhotoGrapherPanel(){
		
	    
		photoGrapherLabel = new JLabel("PhotoGrapher: ");
		photoGrapherLabel.setAlignmentX(LEFT_ALIGNMENT);
		
		
		photoGrapher = new JTextField();
		photoGrapher.setPreferredSize(new Dimension(140, 30));
		
	}
	
	
	
	/**
	 * addDescriptionPanel() function creates a panel with a label and textArea
	 * to set Description for the image to be uploaded
	 */
	
	public void addDescriptionPanel(){
		 
		//creating panel for Description
		panel3 = new JPanel();
		
		//setting layout
	    panel3.setLayout(new FlowLayout(FlowLayout.LEFT));
	    
	    //creating label for description
	    descriptionLabel = new JLabel("Description: ");
	    descriptionLabel.setAlignmentX(LEFT_ALIGNMENT);
	    
	    //creating JTextArea for description
	    description = new JTextArea("Description");
		description.setPreferredSize(new Dimension(160, 120));
		
		description.setLineWrap(true);
		description.setWrapStyleWord(true);
		
		JScrollPane scroll = new JScrollPane (description);
		
		
		//adding label and TextArea for title
		panel3.add(descriptionLabel);
		panel3.add(scroll);
		
		//adding panel to main pane
		add(panel3);
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
	 * 
	 * @return title to the main page for saving
	 */
	public String getTitle(){
		return title.getText();
	}
	

	/**
	 * 
	 * @return category to the main page for saving
	 */
	public String getCategory(){
		return category.getSelectedItem().toString();
	}
	

	/**
	 * 
	 * @return photoGrapher to the main page for saving
	 */
	public String getPhotoGrapher(){
		return photoGrapher.getText();
	}
	

	/**
	 * 
	 * @return description to the main page for saving
	 */
	public String getDescription(){
		return description.getText();
	}
	
	/**
	 * 
	 * @return date to the main page for saving
	 */
	public String getDate(){
		return date.getText();
	}

}


