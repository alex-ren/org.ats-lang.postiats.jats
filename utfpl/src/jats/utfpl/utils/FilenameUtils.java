package jats.utfpl.utils;

import java.io.File;

public class FilenameUtils {
	static public final String cCSPS = "mycsps";
	static public final String cPATCSPS = "csps";
	static public final String cDATS = "dats";
	static public final String cJSON = "json";
	static public final String cUTFPL = "utfpl";
	static public final String cSTFPL2 = "stfpl2";
//	static public final String cMUTFPL = "mats";
	static public final String cINS = "ins";
	static public final String cCSHARP = "cs";
	
	
	private static String removeExtension2String(File path) {
    	String strPath = path.getPath();
        int extensionIndex = strPath.lastIndexOf(".");
        if (extensionIndex == -1)
            return strPath;

        return strPath.substring(0, extensionIndex);
	}
	
    public static File removeExtension(File path) {

    	// 12/19/2013 Now we just cut the extension
//        String separator = System.getProperty("file.separator");
//        String filename = s;

//        // Remove the path upto the filename.
//        int lastSeparatorIndex = s.lastIndexOf(separator);
//        if (lastSeparatorIndex == -1) {
//            filename = s;
//        } else {
//            filename = s.substring(lastSeparatorIndex + 1);
//        }

        return new File(removeExtension2String(path));
    }
    
    private static String getExtension(File path) {
    	String strPath = path.getPath();
    	int extensionIndex = strPath.lastIndexOf(".");
        if (extensionIndex == -1) {
        	return null;
        } else {
        	return strPath.substring(extensionIndex + 1, strPath.length());        	
        }
            
    }

	public static boolean isATS(File path) {
	    String ext = getExtension(path);
//	    System.out.println("ext is " + ext);
	    return "dats".equals(ext);
    }
	
	public static File changeExt(File path, String newExt) {
		String noExt = removeExtension2String(path);
		return new File(noExt + "." + newExt);
	}

	public static File toJson(File path) {
	    String noExt = removeExtension2String(path);
	    String ext = getExtension(path);
	    String strJson = noExt + "_" + ext + "." + cJSON;
	    return new File(strJson);
    }

}



