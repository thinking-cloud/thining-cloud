package com.thinking.cloud.demo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * <P>
 * </P>
 * @author zhouxinke
 * @date 2020年12月1日
 */

public class Main {
	public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        Set oneLevel =new HashSet();
        Set twoLevel =new HashSet();
        Set threeLevel =new HashSet();
        Set fourLevel = new HashSet();
        for (int i = 1; i <= 150; i++) {
    		URL url = new URL("https://packages.gitlab.com/app/gitlab/gitlab-ce/search?page="+i);
            URLConnection uc = url.openConnection();
            InputStream in = uc.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line = null;
            while((line = br.readLine()) != null) {
            	if(line.trim().startsWith("<a href=\"/gitlab/gitlab-ce/")) {
            		line = line.trim();
            		int oneEnd = line.indexOf(">");
            		line = line.substring(oneEnd+1,line.length());
            		int twoEnd = line.lastIndexOf("<");
            		line = line.substring(0, twoEnd);
            		//System.out.println(line);
            		
            		String one = line.substring(10, 12);
            		oneLevel.add(one);
            		if(one.indexOf(".")>=0) continue;
            		int start = line.indexOf(".");
            		int end  = line.substring(start+1,line.length()).indexOf(".");
            		String two = line.substring(start, start+1+end);
            		twoLevel.add(one+two);
            		
            		line = line.substring(start+1+end, line.length());
            		start = line.indexOf(".");
            		end  =line.substring(start+1,line.length()).indexOf("-");
            		if(end == -1) {
            			end  =line.substring(start+1,line.length()).indexOf("_");
            		}else if(end == -1) {
            			end  =line.substring(start+1,line.length()).indexOf("~");
            		}
            		String three = line.substring(start, start+1+end);
            		threeLevel.add(one+two+three);
            	}
            	
            }
            br.close();
        }
        System.out.println("一级版本:"+oneLevel.size());
        for (Object object : oneLevel) {
			System.out.println(object);
		}
        System.out.println("二级版本:"+twoLevel.size());
        for (Object object : twoLevel) {
			System.out.println(object);
		}
        System.out.println("三级版本:"+threeLevel.size());
        for (Object object : threeLevel) {
			System.out.println(object);
		}
        System.out.println(fourLevel.size());
        for (Object object : fourLevel) {
			System.out.println(object);
		}
	}
}

