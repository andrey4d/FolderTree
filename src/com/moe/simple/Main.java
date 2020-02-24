package com.moe.simple;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
	SubFolders subFolders = new SubFolders();
	String root = "/tmp";
	List<String> subfolders = subFolders.getAllSubFoldersRecurcive(root);
	print(subfolders);

	List<String> subfoldersN = subFolders.getAllSubFoldersStack(root);
	print(subfoldersN);
    }

    private static void print(List<String> list){
        System.out.println(list.size());
        Collections.sort(list);
        System.out.println(list);

    }
}
