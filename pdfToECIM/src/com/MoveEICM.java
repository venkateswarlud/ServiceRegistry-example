package com;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;

public class MoveEICM {
    public static void main(String[] args) throws Exception{

        String REMOTE_DIRECTORY = "smb://vfiler-ecm01/ECM01-PRD/TASJAS";

        File file = new File("/opt");

        FileFilter fileFilter = new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isFile() && "pdf".equalsIgnoreCase(file.getName().substring(file.getName().lastIndexOf(".")+1));
            }
        };

        File[] files = file.listFiles(fileFilter);

        //download jcifs.jar
        NtlmPasswordAuthntication authntication = new NtlmPasswordAuthentication("DHC","userID","password");

        for(int i=0;i<files.length;i++) {

            String fileName = files[i].getName();
            String finalFileName = REMOTE_DIRECTORY + fileName;

            SmbFile smbFile = new SmbFile(finalFileName, authntication);
            SmbFileoutputStream stream = new SmbFileOutputStream(smbFile);
            FileInputStream fileInputStream = new FileInputStream(files[i]);

            byte[] content = new byte[16 * 1024];

            int cnt=0;
            while((cnt = fileInputStream.read(content,0, content.length))>0){
                stream.write(content,0,cnt);
            }
            stream.flush();
            stream.close();
            fileInputStream.close();


            //delete from the folder after move it

            File f= files[i];
            f.delete();
        }
    }
}
