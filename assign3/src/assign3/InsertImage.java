package assign3;

import java.awt.BorderLayout;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * This generates the tab for Inserting new image to the app
 * @author Rahmi
 * 
 */

public class InsertImage extends JPanel implements ActionListener{
	
	private JButton saveButton;
	
	ImageProperties imgproperties;
	ImageChooser imgchooser;
	
	private String path,tt,ct,ds,pg;
	
	
	
	/**
	 *  Constructor
	 *  Create ImageChooser to upload image and
	 *  an ImageProperties to enter information of the picture
	 */
	public InsertImage(){
		
		
		this.setLayout(new BorderLayout());
		
    	imgchooser = new ImageChooser();
    	imgproperties = new ImageProperties();
    	
    	saveButton = new JButton("Save Image");
    	saveButton.addActionListener(this);
    	
    	add(imgproperties,BorderLayout.EAST);
 		add(imgchooser,BorderLayout.CENTER);
 		add(saveButton,BorderLayout.SOUTH);
 	}

	
	
	/**
	 * actionPerformed call after pressing save image button
	 * first check if the necessary information are correct
	 * then save the new image to imageCollection
	 */
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		boolean fail = false;
		
		path = imgchooser.getFilePath();
		
		//Check if the path is valid
		if(path == null){
			JOptionPane.showMessageDialog(null, "Error opening file!",
                    "Select a valid image file", JOptionPane.ERROR_MESSAGE);
			fail = true;
		}
		
		//Check if the title field is empty
		tt = imgproperties.getTitle();
		if(!fail && tt == null){
			JOptionPane.showMessageDialog(null, "Error saving file!",
                    "Give a title to your image", JOptionPane.ERROR_MESSAGE);
			
			fail = true;
		}
		
		String ct = imgproperties.getCategory();
		String pg = imgproperties.getPhotoGrapher();
		String ds = imgproperties.getDescription();
		
	
		// Check validity of date inserted
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		Date testDate = null;
		String date = imgproperties.getDate();
		
		try{
		    testDate = df.parse(date);
		} catch (ParseException ey){ JOptionPane.showMessageDialog(null, 
                "Invalid date format", "Error saving file!", JOptionPane.ERROR_MESSAGE);
			fail = true;
		}
		 
		if (date == null || !df.format(testDate).equals(date)){
			JOptionPane.showMessageDialog(null, 
	                "Invalid date!", "Error saving file!", JOptionPane.ERROR_MESSAGE);
				fail = true;
		} else {
		    System.out.println("valid date");
		}
		
		
		// When everything is ok, save the image information
		if(!fail){
			//saving all the info in image collections
			
			//make a picture object
			Picture pic = new Picture(path, tt, ct, pg, ds, date);
			
			//insert into image Collection
			boolean flag = ImageCollection.insertImage(pic);
			
			if(flag){
				JOptionPane.showMessageDialog(null, "Your file is successfully saved!", "Confirmation",
		                JOptionPane.INFORMATION_MESSAGE);
				
				System.out.println("picture Count: "+ImageCollection.images.size());
			}
			else{
				JOptionPane.showMessageDialog(null, "File already exists!",
		                "Error saving file!", JOptionPane.INFORMATION_MESSAGE);
			}
			
			saveImageFile(ImageChooser.icon, path);
		}	
	}
	
	public void saveImageFile(BufferedImage bi, String path){
		 try {
			 bi.getSource();
			 String filename = path.substring(path.lastIndexOf("/")+1, path.length());
			 String filetype = path.substring(path.lastIndexOf(".")+1, path.length());
			 
			 System.out.println(filename+" "+filetype);
			 
             File outputfile = new File("bin/assign3/images/"+filename);
             ImageIO.write(bi, filetype, outputfile);
             
             
         } catch (IOException e) {
        	 JOptionPane.showMessageDialog(null, "File already exists!",
		                "Error saving file!", JOptionPane.INFORMATION_MESSAGE);
         }
	}

}
