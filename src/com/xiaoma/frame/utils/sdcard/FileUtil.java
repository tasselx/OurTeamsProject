package com.xiaoma.frame.utils.sdcard;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @Title: FileUtils.java
 * @Package com.lvguo.frame.sdcard.utils
 * @Description: �ļ����ļ��в�����
 * @author XiaoMa
 */
public class FileUtil
{
    
    public FileUtil()
    {
    }
    
    /**
     * @Title: FileUtil.java
     * @Package com.lvguo.frame.utils.sdcard
     * @Description: �ļ���������ɸѡָ����ʽ�ļ�
     * @author XiaoMa
     */
    class FileFilter implements FilenameFilter
    {
        
        public boolean accept(File dir, String filename)
        {
            return false;
        }
        
    }
    
    // ��ջ���
    public static boolean deleteFiles()
    {
        boolean b = false;
        try
        {
            File f = new File(SDCardCtrl.DOWNLOADPATH);
            String[] filenames = f.list();
            for (int i = 0; i < filenames.length; i++)
            {
                File file = new File(SDCardCtrl.DOWNLOADPATH + File.separator + filenames[i]);
                if (file.exists())
                {
                    file.delete();
                    b = true;
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return b;
    }
    
    /**
     * �ļ��Ƿ����
     * 
     * @param fullPathName �ļ�·��
     * @return true�����ڣ� false�������ڣ�
     */
    public static boolean isFileExist(String fullPathName)
    {
        File file = new File(fullPathName);
        return file.exists();
    }
    
    /**
     * ��������д���ļ�
     * 
     * @param fullPathName
     * @param input
     * @return
     */
    public static File writeFromInput(String fullPathName, InputStream input)
    {
        File file = null;
        OutputStream output = null;
        try
        {
            file = new File(fullPathName);
            if (file.exists())
                return file;
            
            int length = 0;
            output = new FileOutputStream(file);
            byte buffer[] = new byte[4 * 1024];
            while ((length = input.read(buffer)) > 0)
            {
                output.write(buffer, 0, length);
            }
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                output.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        
        return file;
    }
    
    /**
     * ɾ��ָ���ļ����µ��ļ�
     * 
     * @param file Ҫɾ�����ļ�
     * @return �ɹ�ʧ��
     */
    public static boolean deleteInFolder(File file)
    {
        boolean result = false;
        if (file != null)
        {
            String s[] = file.list();
            for (int i = 0; i < s.length; i++)
            {
                File f = new File(file.getPath() + File.separator + s[i]);
                if (f.exists())
                {
                    f.delete();
                    result = true;
                }
            }
        }
        return result;
    }
    
    /**
     * ɾ�������ļ�
     * 
     * @param file
     * @return �Ƿ�ɾ���ɹ�
     */
    public static boolean deleteFile(File file)
    {
        boolean result = false;
        if (file != null)
        {
            try
            {
                File file2 = file;
                file2.delete();
                result = true;
            }
            catch (Exception e)
            {
                e.printStackTrace();
                result = false;
            }
        }
        return result;
    }
    
    /**
     * ɾ���ļ��м��������е����ļ�
     * 
     * @param folder Ҫɾ����Ŀ���ļ���
     * @return ɾ���Ƿ�ɹ�
     */
    public static boolean deleteFolder(File folder)
    {
        boolean result = false;
        try
        {
            String childs[] = folder.list();
            if (childs == null || childs.length <= 0)
            {
                if (folder.delete())
                {
                    result = true;
                }
            }
            else
            {
                for (int i = 0; i < childs.length; i++)
                {
                    String childName = childs[i];
                    String childPath = folder.getPath() + File.separator + childName;
                    File filePath = new File(childPath);
                    if (filePath.exists() && filePath.isFile())
                    {
                        if (filePath.delete())
                        {
                            result = true;
                        }
                        else
                        {
                            result = false;
                            break;
                        }
                    }
                    else if (filePath.exists() && filePath.isDirectory())
                    {
                        if (deleteFolder(filePath))
                        {
                            result = true;
                        }
                        else
                        {
                            result = false;
                            break;
                        }
                    }
                }
                folder.delete();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            result = false;
        }
        return result;
    }
    
    /**
     * �ƶ��ļ�
     * 
     * @param oldLocation Դ�ļ���
     * @param newLocation Ŀ���ļ���
     * @throws IOException �쳣
     */
    public static void moveFile(File oldLocation, File file)
        throws IOException
    {
        boolean isMkdirs = false;
        if (!file.exists())
        {
            isMkdirs = file.mkdirs();
            if (!isMkdirs)
                return;
        }
        String childs[] = oldLocation.list();
        if (oldLocation != null && oldLocation.exists())
        {
            for (int i = 0; i < childs.length; i++)
            {
                String childName = childs[i];
                String childPath = oldLocation.getPath() + File.separator + childName;
                File filePath = new File(childPath);
                if (filePath.exists() && filePath.isFile())
                {
                    FileInputStream fis = null;
                    FileOutputStream fos = null;
                    try
                    {
                        fis = new FileInputStream(filePath);
                        File fout = new File(file.getPath() + File.separator + childName);
                        if (fout.exists())
                        {
                            fout.delete();
                        }
                        fos = new FileOutputStream(fout, false);
                        
                        byte[] buff = new byte[8192];
                        int readIn;
                        while ((readIn = fis.read(buff, 0, buff.length)) != -1)
                        {
                            fos.write(buff, 0, readIn);
                        }
                        fos.flush();
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    finally
                    {
                        try
                        {
                            if (fis != null)
                            {
                                fos.close();
                            }
                            if (fos != null)
                            {
                                fos.close();
                            }
                        }
                        catch (Exception e2)
                        {
                            e2.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
