/**
 * From geek45.com
 * Email to : rubixgeek795@gmail.com
 */
package com.geek45.io;

import com.geek45.lang.StringUtils;

import java.io.*;

/**
 * @ClassName: FileUtils
 * @Decription:
 * @Author: rubik
 * rubik create FileUtils.java of 2022/3/22 6:58 下午
 */
public class FileUtils {

    /**
     * 检测是否是文件夹
     *
     * @param file 要检测的对象
     * @return true 是文件夹  false  不是文件夹
     */
    public static boolean isFolder(File file) {
        if (file.exists()) {
            return file.isDirectory();
        } else {
            return false;
        }
    }

    /**
     * 获取文件夹下面的所有文件
     *
     * @param file 要获取文件的文件夹
     * @return 如果不是文件夹，就返回空，否则就返回所有文件
     */
    public static File[] getFileAll(File file) {
        if (isFolder(file)) {
            return file.listFiles();
        } else {
            return new File[0];
        }
    }

    /**
     * 获取文件的后缀
     *
     * @param file 要获取后缀的文件
     * @return 例如 r.txt  则返回 .txt  ...
     */
    public static String getFileSuffix(File file) {
        String fileName = file.getName();
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * 追加文本
     *
     * @param file    要追加内容的文件
     * @param content 要追加的内容
     */
    public static void fileAppend(File file, String content) throws IOException {
        try (FileWriter fw = new FileWriter(file, true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(content);
            pw.flush();
        } catch (IOException e) {
            throw e;
        }
    }


    /**
     * 获取一个BufferedReader对象，方便读取文件数据中的内容
     *
     * @param file 要获取BufferedReader对象的文件
     * @return
     */
    public static BufferedReader getReaderObj(File file) throws IOException {
        if (file.isDirectory()) {
            return null;
        }
        try (FileReader fr = new FileReader(file.getPath())) {
            return new BufferedReader(fr);
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * 获取文件中所有的内容
     *
     * @param file 要获取内容的数据
     * @return
     */
    public static String[] getFileContentAll(File file) throws IOException {
        BufferedReader br = getReaderObj(file);
        String line = "";
        StringBuilder builder = new StringBuilder();
        while (StringUtils.isNotBlank(line = br.readLine())) {
            builder.append(line).append("\n");
        }
        br.close();
        return builder.toString().split("\n");
    }

    /**
     * 批量处理文件
     *
     * @param file    要处理的文件
     * @param dispose 怎么处理
     */
    public static void disposeFile(File file, Dispose dispose) {
        if (!file.isDirectory()) {
            //如果是文件
            dispose.action(file);
            return;
        }
        //如果是文件夹
        File[] files = file.listFiles();
        if (null == files || files.length == 0) {
            return;
        }
        for (File filed : files) {
            disposeFile(filed, dispose);
        }
    }

    /**
     * 处理文件抽象类
     */
    public abstract static class Dispose{
        abstract void action(File file);
    }

    /**
     * 创建一个文件
     * 如果已经存在，返回当前文件
     * 如果不存在，就创建一个文件
     *      如果创建成功，就返回文件
     *      如果创建失败，就返回null
     * @param path
     * @return
     * @throws IOException
     */
    public static final File createFile(String path) throws IOException {
        File file = new File(path);
        if (file.exists()) {
            return file;
        }
        if (file.createNewFile()) {
            return file;
        }
        return null;
    }

}
