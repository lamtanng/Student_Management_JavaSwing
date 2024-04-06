/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stdManage.Utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class U_Image {

    public static String uploadImage(String path, String storeFolder) throws IOException {
        final String storeFolderURL = U_Common.RESOURCE.concat(storeFolder);
        String fileName = "";
        File sourceFile = new File(path);
        File destinationFile = null;

        File directory = new File(storeFolderURL);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        if (!path.isBlank()) {
            try {
                String extension = path.substring(path.lastIndexOf('.') + 1);
                fileName = System.currentTimeMillis() + "." + extension;
                destinationFile = new File(storeFolderURL + fileName);
                Files.copy(sourceFile.toPath(), destinationFile.toPath());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Uploade Fail ", JOptionPane.DEFAULT_OPTION);
            }
        } else 
            System.out.println("U_Image: Old avatar does not exist");

        return fileName;
    }

    public static void deleteImage(String fileName, String storeFolder) {
        File imgPath = new File(U_Common.RESOURCE.concat(storeFolder) + fileName);
        if (imgPath.exists()) {
            if (imgPath.delete()) {
                System.out.println("U_Image/Success: delete previous avatar");
            } else {
                System.out.println("U_Image/Fail: delete avatar");
            }
        } else {
            System.out.println("U_Image: Previous path does not exist");
        }

    }
}
