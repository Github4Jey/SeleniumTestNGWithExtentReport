package coreFramework;

import java.io.File;
import java.io.FilenameFilter;

public class LogFilterFilter implements FilenameFilter {

	@Override
    public boolean accept(File dir, String fileName)
    {
        return (fileName.endsWith(".zip"));
    }

}
