package assign3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.ImageIcon;

/**
 * This class contains all the global variable for this project.
 * And some static function to generate and update those 
 * variables as necessary.
 * 
 * @author Rahmi
 *
 */
public class ImageCollection {
	
	// Hash map to store all the images
	static Map<String, Picture> images = new HashMap<String, Picture>();
	
	// ArrayList to store all unique category
	static ArrayList<String> Category = new ArrayList<>();
	
	
	// ArrayList to store all image names of certain category
	// used for browsing images
	static ArrayList<String> imgnames = new ArrayList<>();
	
	
	// ArrayList to store all image path of certain category
	// used for browsing images
	static ArrayList<String> pathArray= new ArrayList<>();
	
	
	// ArrayList to store the search result
	// used for showing search results
	static ArrayList<Picture> searchRes = new ArrayList<>();
	
	// ArrayList to store the ImageIcon
	// used for showing images
	static ArrayList<ImageIcon> imageIconList = new ArrayList<>();
	
	
	// ArrayList to store all pictures 
	// used for generating imgnames, pathArray, searchRes etc
	static ArrayList<Picture> picArray = new ArrayList<>();

	
	static String[] catArray = {"Nature","Beach", "Road", "Festival", "Cat"};
	

	static Set<String> dateSet = new HashSet<String>();
	static Set<String> catSet = new HashSet<String>();
	
	static String directory;
	static String relativePath = "images/";
	static String relativePathJar = "assign3/bin/assign3/images/";
	
	public ImageCollection(){
		
		//creating some picture objects
		
		directory = "images/";
		Picture pic0 = new Picture("images/candle.JPG", "Chrismas", "Festival", "Rahmi", "", "12/12/2013");

		
		Picture pic1 = new Picture("images/IMG_20131228_144838.jpg", "Chrismas", "Festival", "Rahmi", "", "12/12/2013");
		Picture pic2 = new Picture("images/IMG_20131228_145209.jpg", "Chrismas", "Festival", "Rahmi", "", "01/03/2006");
		Picture pic3 = new Picture("images/IMG_20131228_145818.jpg", "Chrismas", "Festival", "Rahmi", "", "01/01/2005");
		Picture pic4 = new Picture("images/IMG_20131228_145824.jpg", "Chrismas", "Festival", "Rahmi", "", "08/23/2009");
		
		Picture pic5 = new Picture("images/a.jpg", "Boltu", "Cat", "Rahmi", "", "12/12/2003");
		Picture pic6 = new Picture("images/c.jpg", "Baga", "Cat", "Rahmi", "", "01/03/2006");
		Picture pic7 = new Picture("images/d.jpg", "Simba", "Cat", "Rahmi", "", "01/01/2005");
		Picture pic8 = new Picture("images/P7020008.JPG", "Baga", "Cat", "Rahmi", "", "08/23/2009");
		
		
		Picture pic9 = new Picture("images/IMG_20140523_192602.jpg", "Joshua", "Road", "Rahmi", "", "12/12/20013");
		Picture pic10 = new Picture("images/IMG_20140816_171624.jpg", "Joshua", "Road", "Rahmi", "", "01/03/20014");
		Picture pic11 = new Picture("images/IMG_20140816_171641.jpg", "Highway", "Road", "Rahmi", "", "01/01/20015");
		Picture pic12 = new Picture("images/IMG_20141230_123207.jpg", "Highway", "Road", "Rahmi", "", "08/23/20013");
		Picture pic13 = new Picture("images/IMG_20141230_123240.jpg", "Highway", "Road", "Rahmi", "", "08/23/20014");
		
		Picture pic14 = new Picture("images/IMG_20140729_124817.jpg", "Sea", "Nature", "Rahmi", "", "12/12/2003");
		Picture pic15 = new Picture("images/IMG_20140729_124823.jpg", "Sea", "Nature", "Rahmi", "", "12/12/2003");
		Picture pic16 = new Picture("images/IMG_20140816_130123.jpg", "Sea", "Nature", "Rahmi", "", "01/03/2006");
		Picture pic17 = new Picture("images/IMG_20140816_130132.jpg", "Sea", "Nature", "Rahmi", "", "01/01/2005");
		Picture pic18 = new Picture("images/IMG_20141229_113842.jpg", "Sea", "Nature", "Rahmi", "", "08/23/2009");
		Picture pic19 = new Picture("images/IMG_20141229_152809.jpg", "Grand Canyon", "Nature", "Rahmi", "", "12/12/2003");
		Picture pic20 = new Picture("images/IMG_20141229_152814.jpg", "Grand Canyon", "Nature", "Rahmi", "", "01/03/2006");
		
		
		Picture pic21 = new Picture("images/IMG_20140729_125200.jpg", "Big Sur", "Beach", "Rahmi", "", "01/01/2005");
		Picture pic22 = new Picture("images/IMG_20140816_125859.jpg", "Big Sur", "Beach", "Rahmi", "", "08/23/2009");

		
		//inserting the Picture objects created above
		insertImage(pic0);
		insertImage(pic1);
		insertImage(pic2);
		insertImage(pic3);
		insertImage(pic4);
		insertImage(pic5);
		insertImage(pic6);
		insertImage(pic7);
		insertImage(pic8);
		insertImage(pic9);
		insertImage(pic10);
		insertImage(pic11);
		insertImage(pic12);
		insertImage(pic13);
		insertImage(pic14);
		insertImage(pic15);
		insertImage(pic16);
		insertImage(pic17);
		insertImage(pic18);
		insertImage(pic19);
		insertImage(pic20);
		insertImage(pic21);
		insertImage(pic22);
	}
	
	
	
	/**
	 * This function insert new Picture.
	 * Take a Picture object and saves it to images and picArray.
	 * Also save the category and date of the Picture to respective 
	 * sets, so they can unique.
	 * 
	 * @param pic
	 * @return true after successful insertion
	 */
	public static boolean insertImage(Picture pic) {
		
		try{
			String path = pic.getFilePath();
			String filename = path.substring(path.lastIndexOf("/")+1, path.length());
			path = "images/"+filename;
			
			//pic.setFilePath(path);
			images.put(path, pic);
			picArray.add(pic);
			
			dateSet.add(pic.getDate());
			catSet.add(pic.getCategory());
			
			//imageIconList.add(createImageIcon(pic.getFilePath()));
			
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
	
	/**
	 * This function deletes a  Picture object.
	 * Take an index as input. Then find the object in Hash-map and delete from there.
	 * It also delete the object from picArray, pathArray, and imgnames
	 * @param index
	 */
	public static void deleteImage (int index) {
		
		String key = pathArray.get(index);

		Picture ob = images.get(key);
		
		//Delete from picArray
		picArray.remove(ob);
		
  	  	//delete from HashMap
		images.remove(key);
		
    	//Delete from pathArray
		pathArray.remove(index);
		
  	  	//Delete from imgnames
		imgnames.remove(index);
		
	}
	
	/**
	 * This function is called when edit button is pressed.
	 * It receives a Picture object and update the object in the hash-map
	 * @param pic
	 * @return
	 */
	public static boolean editImage(Picture pic) {
		
		try{
			images.put(pic.getFilePath(), pic);
			dateSet.add(pic.getDate());
			return true;
		}
		catch(Exception e){
			return false;
		}
		
	}
	
	
	/**
	 * 
	 * @return number of picture currently in the app
	 */
	public static int collectionSize(){
		return images.size();
	}
	
	
	
	/**
	 * This function updates pathArray and imgnames for a specific Category.
	 * When user selects a category on "Image Browser" tab. This function 
	 * generates a list of image name for the user to view, edit or delete.
	 * 
	 * @param Cat
	 */
	public static void updateImageListByCategory(String Cat){
		
		imgnames.clear();
		pathArray.clear();
		
		for(Picture pic : picArray){
			if(pic.getCategory() == Cat){
				
				String path = pic.getFilePath();
				
				// extract file name from absolute path
				String filename = path.substring(path.lastIndexOf("/")+1, path.length());
				pathArray.add("images/"+filename);
				//pathArray.add(relativePathJar+filename);
	    		//System.out.println("path in image collection"+relativePathJar+filename);

				imgnames.add(filename);
			}
		}
		
	}
	
	
	
	
	
	/**
	 * This function updates pathArray and imgnames for a specific Date.
	 * When user selects a date on "Image Browser" tab. This function 
	 * generates a list of image name for the user to view, edit or delete.
	 * 
	 * @param date
	 */
	public static void updateImageListByDate(String date){
		
		imgnames.clear();
		pathArray.clear();

		
		for(Picture pic : picArray){
			
			if(pic.getDate() == date){
				String path = pic.getFilePath();
				pathArray.add(path);
				// extract file name from absolute path
				imgnames.add(path.substring(path.lastIndexOf("/")+1, path.length()));
			}
		}
	}
	
	
	/**
	 * This function generates image list in ArrayList searchRes for a search token.
	 * User enter token in the search tab, when search button is pressed, this function 
	 * generates image list of images which has the specific token.
	 * It Makes a string using  title, category, date, Photographer, date and description,
	 * then search the token in the entire string.
	 * 
	 * Search can work on any field title, date, Photographer, category, or description
	 * @param token
	 */
	static void searchImages(String token){
		
		String str;
		searchRes.clear();
		
		for(Picture pic : picArray){
			
			// make a string using  title, category, date, Photographer, date and description
			str = pic.getTitle() + pic.getCategory() + pic.getPhotoGrapher() + pic.getDate() + pic.getDescription();
			
			str = str.toLowerCase();
			token = token.toLowerCase();
			
			if (str.contains(token)) {
				  // it contains token
					searchRes.add(pic);
			}
		}
		
	}
	
	

	
}
