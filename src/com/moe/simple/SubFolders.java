package com.moe.simple;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class SubFolders {

    public List<String> getSubFolders(String rootFolder) throws IOException {

        File[] directories = new File(rootFolder).listFiles(file -> file.isDirectory());
        List<String> out = new ArrayList<>();
        for (File dir: directories) {
            out.add(dir.getName());
        }
        return out;
    }

    public List<String> getSubFoldersStream(String rootFolder) throws IOException {
        List<String> out = new ArrayList<>();
        try {
            out = Files
                    .list(Paths.get(rootFolder))
                    .filter(path -> Files.isDirectory(path))
                    .map(path -> path.getFileName().toString())
                    .collect(Collectors.toList());
        } catch(IOException ioe){
            return out;
        }
        return out;
    }


    public List<String> getAllSubFoldersRecurcive(String rootFolder) throws IOException {
        return getAllSubFoldersRecurcive(rootFolder, new ArrayList<>());
    }

    private List<String> getAllSubFoldersRecurcive(String rootFolder, List<String> out) throws IOException {
//        out.add(rootFolder);
        List<String > folders = getSubFoldersStream(rootFolder);
        for (String folder : folders) {
            String dir = rootFolder + "/" + folder;
            out.add(dir);
            getAllSubFoldersRecurcive(dir,out);
        }
        return out;
    }

    public List<String > getAllSubFoldersStack(String rootFolder) throws IOException {
         Stack<String> stack = new Stack();
         List<String> out = new ArrayList<>();
//         out.add(rootFolder);
         stack.push(rootFolder);
         while (!stack.isEmpty()){
             String root = stack.pop();
             for (String folder: getSubFoldersStream(root)){
                 String dir = root + "/" + folder;
                 stack.push(dir);
                 out.add(dir);
             }
         }
        return out;
    }
}