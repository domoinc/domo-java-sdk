package com.domo.sdk.request;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPOutputStream;

/**
 * Created by bobbyswingler on 6/7/17.
 */
public class Gzipper {

    private static final Logger logger = LoggerFactory.getLogger(Gzipper.class);

    public List<File> toGzipFilesUTF8( List<File> sourceFiles, String path){
        List<File> files = new ArrayList<>();
        for (File sourceFile : sourceFiles) {
            String zipFileName = sourceFile.getName().replace(".csv", ".zip");
            if (!zipFileName.contains(".zip")){
                zipFileName += ".zip";
            }
            files.add(toGzipFileUTF8(sourceFile, path + zipFileName));
        }



        return files;
    }

    public File toGzipFileUTF8( File csvFile, String zipFilePath){
        File outputFile = new File(zipFilePath);
        try {
            GZIPOutputStream gzos = new GZIPOutputStream(new FileOutputStream(outputFile));
            BufferedReader reader = new BufferedReader(new FileReader(csvFile));

            String currentLine;
            while ((currentLine = reader.readLine()) != null){
                currentLine += System.lineSeparator();
                gzos.write(currentLine.getBytes("UTF-8")); // Specifying UTF-8 encoding is critical; getBytes() uses ISO-8859-1 by default
            }

            gzos.flush();
            gzos.finish();
            gzos.close();
        }
        catch(IOException e) {
            logger.error("Error compressing a string to gzip", e);
        }

        return outputFile;
    }
}
