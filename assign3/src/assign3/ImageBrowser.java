package assign3;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JPanel;


/**
 * This is Image Browser panel. it has two parts.
 * part1: Navigator - provides combo box to choose category or date.
 * then show then in a list for the user to choose.
 * part2: image viwer - to show imagelist, image and picture information,
 * also provide edit and delete option.
 * 
 * @author Rahmi
 *
 */
public class ImageBrowser extends JPanel{
	
	public ImageBrowser(){
		
		this.setLayout(new BorderLayout());
		
		//create ImageViewer to show imagelist, image and picture information
		ImageViewer imageViewer = new ImageViewer();
		
		//create navigator to select category or date
		Navigator navigator = new Navigator();
		
		add(navigator,BorderLayout.WEST);
 		add(imageViewer,BorderLayout.CENTER);
		
	
	}
	
}
