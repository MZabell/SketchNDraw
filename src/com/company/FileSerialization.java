package com.company;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileSerialization {
    static public void saveData(Canvas canvas) {
        try {
            FileOutputStream fos = new FileOutputStream("save");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(canvas);
            oos.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
