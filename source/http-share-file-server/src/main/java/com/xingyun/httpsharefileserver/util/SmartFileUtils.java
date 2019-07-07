package com.xingyun.httpsharefileserver.util;

import lombok.ToString;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author fairy 查询文件夹下的文件
 ***/
public class SmartFileUtils {
    /**
     * 查看某个文件夹下所有文件和文件夹列表
     * 无递归
     * */
    public final static List<File> lookFollder(String startDir) {
        return lookFollderInfo(new File(startDir), ".*");
    }
    /**
     * 查看某个文件夹下指定类型的文件和文件夹列表
     * 无递归
     * */
    public final static List<File> lookFollder(String startDir, String regex) {
        return lookFollderInfo(new File(startDir), ".*\\" + regex);
    }
    private final static List<File> lookFollderInfo(File rootFolder, final String regex) {
        return Arrays.asList(
                rootFolder.listFiles(new FilenameFilter() {
                    private Pattern pattern = Pattern.compile(regex);
                    @Override
                    public boolean accept(File dir, String name) {
                        // TODO Auto-generated method stub
                        return pattern.matcher(name).matches();
                    }
                }));
    }

    /**
     * 查看某个文件夹下所有的文件和文件夹列表
     * 递归遍历
     *  */
    public final static FileTreeInfo watchFolder(String startDir) {
        return watchDirs(new File(startDir), ".*");
    }
    /**
     * 查看某个文件夹下指定类型的文件和文件夹列表
     * 递归遍历
     * */
    public final static FileTreeInfo watchFolder(String startDir, String regex) {
        return watchDirs(new File(startDir), ".*" + regex);
    }
    private final static FileTreeInfo watchDirs(File startDir, String regex) {
        FileTreeInfo resultInfo = new FileTreeInfo();

        for (File item : startDir.listFiles()) {
            if (item.isDirectory()) {
                resultInfo.folderList.add(item);
                resultInfo.addAll(watchDirs(item, regex));
            } else {
                if (item.getName().matches(regex)) {
                    resultInfo.fileList.add(item);
                }
            }
        }
        return resultInfo;
    }
    /**
     * TreeInfo
     ***/

    @ToString
    public static class FileTreeInfo implements Iterable<File> {
        public List<File> fileList = new ArrayList<File>();
        public List<File> folderList = new ArrayList<File>();

        public Iterator<File> iterator() {
            return fileList.iterator();
        }

        void addAll(FileTreeInfo other) {
            fileList.addAll(other.fileList);
            folderList.addAll(other.folderList);
        }
    }
}
