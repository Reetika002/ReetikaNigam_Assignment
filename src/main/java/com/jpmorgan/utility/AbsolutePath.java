package com.jpmorgan.utility;

import java.io.File;

public class AbsolutePath {
	public String absPath = null;
	   

    public AbsolutePath(String projectName)
    {
       absPath = new File(projectName).getAbsolutePath();
       absPath = absPath.substring(0, absPath.indexOf(projectName));
       absPath = absPath.replace("/", "\\");
    }
    
    public String path()
    {
         return absPath;
    }
}
