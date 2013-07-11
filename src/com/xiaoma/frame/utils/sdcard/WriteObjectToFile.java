package com.xiaoma.frame.utils.sdcard;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @Title: WriteObjectToFile.java
 * @Package
 * @Description: ������д���ļ���(�����ļ����͵�Ŷ)
 * @author XiaoMa
 */
public class WriteObjectToFile
{
    
    /**
     * ������д���ļ���
     * 
     * @param object ��д��Ķ���
     * @param filePath д����ŵ�·����ȫ·����
     */
    public static void writeObject(Object object, File filePath)
    {
        FileOutputStream outStream = null;
        try
        {
            // FileOutputStream outStream = new FileOutputStream(
            // "D:/ObjectToFile.xml");
            outStream = new FileOutputStream(filePath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outStream);
            objectOutputStream.writeUTF("UTF-8");
            objectOutputStream.writeObject(object);
            outStream.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    /*
     * ���ļ��ж�ȡ��������
     */
    public static Object readObject(File filePath, Object object)
    {
        FileInputStream freader = null;
        try
        {
            // freader = new FileInputStream("D:/ObjectToFile.xml");
            freader = new FileInputStream(filePath);
            ObjectInputStream objectInputStream = new ObjectInputStream(freader);
            Object obj = (Object)objectInputStream.readObject();
            return obj;
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return null;
        
    }
}
