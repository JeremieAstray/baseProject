package baseProject.commons.web;

import java.io.File;
import java.util.ResourceBundle;

/**
 *
 * @author Pengo.Wen
 */
public class Constant {
    
    public final static String IMAGEPATH;//图片文件夹
    
    static{
        ResourceBundle resource = ResourceBundle.getBundle("path");
        IMAGEPATH = resource.getString("imagePath");
        File imageFile = new File(IMAGEPATH);
        if(!imageFile.exists()){
            imageFile.mkdirs();
        }
    }
    
}
