package assign3;


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *  This JPanel sets up a list view for category or dates.
 *  User can select a category or date, accordingly which
 *  the image list will be generated. 
 * 
 * @author Rahmi
 *
 */

public class Navigator extends JPanel implements ListSelectionListener{

	
	private static final long serialVersionUID = -7627106546665802279L;
	private JComboBox<String> optionCombo;
	private JLabel label, label2;
	private JPanel optionPanel;
	JScrollPane listScrollPane;
	
	private JList<String> list;
	DefaultListModel listModel;
	
	String selectionMode;
	
	 
	public Navigator(){
		 
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		
		this.setPreferredSize(new Dimension(150, 300));
		 
		//listModel for the JList
		listModel = new DefaultListModel();
	    
		
		createOptionPanel();
	    
	    createSelectionPanel();	
	    
	    //default selection mode
	    selectionMode = "category";
	    
	    add(listScrollPane);
	    
	    // make default list using category
	 	makeListModel(ImageCollection.catSet);
	}
	
	
	/**
	 *  create a panel to hold ComboBox
	 *  and label to show which mode is currently on
	 *  
	 */
	private void createOptionPanel(){
		
		optionPanel = new JPanel();
		
		label = new JLabel("Browse Image by -");
		
		optionPanel.add(label);
		
		optionCombo = createOptionComboBox(); 
		optionPanel.add(optionCombo);
		
		label2 = new JLabel();
		optionPanel.add(label2);
		
		// add the panel to the window
		add(optionPanel);
	}
	
	
	/**
	 * Set up the selection panel and add list listener
	 */
	private void createSelectionPanel() {
				
		list = new JList(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setSelectedIndex(0);
	    list.addListSelectionListener(this);
	    
	    
	    listScrollPane = new JScrollPane(list);
	}
	
	
	/**
	 * Making the listModel for the JList.
	 * First clear the existing list then make new one according to the
	 * combo box selection.
	 * @param array
	 */
	private void makeListModel(Set<String> array){
		listModel.clear();
		
		for (String s: array)
		{
		    String sbtr = s.substring(0, s.length() );
		    listModel.addElement(sbtr);
		}
	}

	
	
	
	/**
	* 
	* JComboxBox is a Swing component that render a drop-down list of choices and 
	* lets the user selects one item from the list. 
	* The ComboBox can be read-only or editable. 
	* 
	* This JComboxBox create with two options category and date
	*  
	*/
   public JComboBox createOptionComboBox(){    
      String [] options = {"Category", "Date"};
      optionCombo = new JComboBox(options);
  
      optionCombo.setEditable(true);
  
    // add the event handler
      optionCombo.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		 JComboBox cb = (JComboBox)e.getSource();
        	     String place = (String)cb.getSelectedItem();  
        	     
        	     label2.setText("Browsing by "+place);
        	     if(place == options[0]){
        	    	
        	    	 makeListModel(ImageCollection.catSet);
        	    	 selectionMode = "category";
        	    	 
        	     }
        	     else{
        	    	
        	    	 makeListModel(ImageCollection.dateSet);
        	    	 selectionMode = "date";

        	     }
        	}
        }
        );            
      return optionCombo;  
   }
   

   /**
    * Update the image list according to selected option by ImageCollection.updateImageListByCategory()
    * if a category is selected or ImageCollection.updateImageListByDate() when a date is selected.
    * Call the Category.makeListModel() with the new list
    * 
    * @param object
    */
   protected void updateImageList (Object object) {
	   

	   System.out.println(object);
	   
	   if(selectionMode == "category"){

		   ImageCollection.updateImageListByCategory((String)object); 
		   System.out.println(ImageCollection.imgnames.size());

		   Category.makeListModel(ImageCollection.imgnames);
	   }
	   if(selectionMode == "date"){
		   ImageCollection.updateImageListByDate((String)object);
		   Category.makeListModel(ImageCollection.imgnames);
	   }
	  
   }

   

   /**
    * This function every time a category or date selected.
    * Then call updateImageList() to generate new image list.
    * 
    */
	@Override
	public void valueChanged(ListSelectionEvent e) {

		JList list = (JList)e.getSource();
		updateImageList(list.getSelectedValue());		
	}

}
