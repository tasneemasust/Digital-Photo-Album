package assign3;

import java.util.Date;

import javax.swing.ImageIcon;

/**
 * This is the Piture object. Used by all the classes in this project.
 * @author Rahmi
 *
 */
public class Picture {
	
	private String filePath, title, category;
	private String photoGrapher, description;
	private String date;	
	
	
	public Picture(){}
	
	
	public Picture( String filePath, String title, String category,
			String photoGrapher, String description, String date){
		
		this.filePath = filePath;
		this.title = title;
		this.category = category;
		this.photoGrapher = photoGrapher;
		this.description = description;
		this.date = date;
	}
	
	
	/**
	 * setter function for file path of the Picture
	 * @param filepath
	 */
	public void setFilePath(String filepath){
		this.filePath = filepath;
	}
	
	
	/**
	 * setter function for title of the Picture
	 * @param title
	 */
	public void setTitle(String title){
		this.title = title;
	}
	
	
	/**
	 * setter function for category of the Picture
	 * @param category
	 */
	public void setCategory(String category){
		this.category = category;
	}
	
	
	/**
	 * setter function for photographer of the Picture
	 * @param photoGrapher
	 */
	public void setPhotoGrapher(String photoGrapher){
		this.photoGrapher = photoGrapher;
	}
	
	
	/**
	 * setter function for Description of the Picture
	 * @param description
	 */
	public void setDescription(String description){
		this.description = description;
	}
	
	
	/**
	 * setter function for date of the Picture
	 * @param date
	 */
	public void setDate(String date){
		this.date = date;
	}
	
	
	
	
	/**
	 * Getter function on file path
	 * @return file path of the picture
	 */
	public String getFilePath(){
		return this.filePath;
	}
	
	
	/**
	 * Getter function on tile
	 * @return title of the picture
	 */
	public String getTitle(){
		return this.title;
	}
	

	/**
	 * Getter function on category
	 * @return category of the picture
	 */	
	public String getCategory(){
		return this.category;
	}
	
	
	/**
	 * Getter function on photoGrapher
	 * @return photoGrapher of the picture
	 */	
	public String getPhotoGrapher(){
		return this.photoGrapher;
	}
	
	

	/**
	 * Getter function on description
	 * @return description of the picture
	 */	
	public String getDescription(){
		return this.description;
	}
	
	
	/**
	 * Getter function on date
	 * @return date of the picture
	 */	
	public String getDate(){
		return this.date;
	}
	
	
//	public ImageIcon getImageIcon(){
//		return this.imageIcon;
//	}
//	
	
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
