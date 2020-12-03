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
        /**
         * 一级版本:5
11
12
13
9.
10
二级版本:39
12.10
11.10
11.11
13.3
12.4
11.5
10.6
13.2
12.3
11.4
10.5
13.1
12.2
11.3
10.4
13.0
12.1
11.2
10.3
12.0
11.1
10.2
11.0
10.1
10.0
12.9
12.8
11.9
13.6
12.7
11.8
13.5
12.6
11.7
10.8
13.4
12.5
11.6
10.7
三级版本:353
12.9.5
12.9.4
12.9.3
11.3.0
12.9.2
12.9.9
12.9.8
12.9.7
11.3.6
11.3.5
11.3.8
11.3.1
11.3.4
11.3.3
12.1.1
11.7.10
12.1.0
11.7.11
11.3.9
11.7.12
12.10.10
12.1.4
12.10.12
12.1.3
12.10.11
12.1.2
12.10.14
12.9.1
12.1.9
12.10.13
12.9.0
12.1.8
13.2.10
12.1.6
12.5.10
10.5.8
13.6.1
13.6.0
10.5.7
10.5.6
10.5.5
10.5.4
10.5.3
10.5.2
10.5.1
10.5.0
12.2.9
11.4.5
11.4.4
11.4.7
11.4.6
11.4.0
12.0.12
11.4.3
12.2.0
11.4.9
11.4.8
12.2.4
12.2.3
12.2.1
12.2.8
12.2.7
12.2.6
12.2.5
13.5.0
13.5.2
13.5.1
13.5.4
13.5.3
10.6.6
10.6.5
10.6.4
10.6.3
10.6.2
10.6.1
10.6.0
12.7.7
11.1.0
12.7.6
12.7.5
11.3.14
11.1.2
12.7.4
11.1.1
11.3.12
11.3.13
12.7.9
11.3.10
12.7.8
11.3.11
11.9.0
11.1.8
11.1.7
11.9.1
11.1.4
11.1.6
11.9.8
11.9.7
11.9.9
11.9.4
11.9.6
12.7.2
12.7.0
13.0.1
13.0.0
13.0.3
12.1.11
12.1.12
13.0.5
13.0.4
13.0.7
13.0.6
13.0.9
12.1.13
13.0.8
12.1.14
10.3.1
10.3.0
12.1.17
11.11.8
10.3.9
11.11.7
10.3.8
11.11.4
10.3.7
11.11.5
10.3.6
11.11.2
10.3.5
11.11.3
10.3.4
11.11.0
10.3.3
11.11.1
10.3.2
12.8.6
12.8.5
11.2.1
11.2.0
12.8.9
12.8.8
12.8.7
11.2.7
11.2.8
11.2.3
11.2.2
11.2.5
11.2.4
12.0.2
12.0.1
12.0.0
12.0.6
12.0.4
12.0.3
12.8.2
12.8.1
12.0.9
12.8.0
12.0.8
10.4.0
11.8.10
10.4.7
10.4.6
10.4.5
10.4.4
10.4.3
13.1.11
10.4.2
13.1.10
10.4.1
12.5.9
12.5.7
12.5.6
12.2.12
11.7.4
11.7.3
11.7.0
11.7.6
11.7.5
11.7.7
12.5.1
12.5.0
12.5.5
12.5.4
12.5.3
12.5.2
13.2.1
13.2.0
13.2.3
13.2.2
13.2.5
13.2.4
13.2.7
13.2.6
13.2.9
10.1.3
13.2.8
10.1.2
10.1.1
10.1.0
10.1.7
10.1.6
10.1.5
10.1.4
12.6.8
11.0.1
12.6.7
11.0.0
12.6.6
11.0.3
11.0.2
12.8.10
11.8.1
11.8.0
11.8.3
11.8.2
11.0.5
11.0.4
11.0.6
11.8.9
11.8.8
11.9.11
11.9.10
11.9.12
11.8.7
11.8.6
12.6.0
13.0.14
13.0.13
13.0.12
12.6.4
12.6.3
13.0.10
12.6.2
12.6.1
11.10.0
11.4.14
11.4.13
13.1.0
11.4.12
11.4.11
13.1.2
11.4.10
13.1.1
13.1.4
13.1.3
13.1.6
13.1.5
13.1.8
13.1.7
10.2.2
13.1.9
10.2.1
10.2.0
11.10.7
11.10.8
11.10.5
10.2.8
11.10.6
10.2.7
11.10.3
10.2.6
11.10.4
10.2.5
11.10.1
10.2.4
11.10.2
10.2.3
12.3.9
12.3.8
11.5.4
11.5.3
11.5.6
11.5.5
11.5.0
11.5.2
11.5.1
11.5.7
12.3.3
12.3.2
12.3.1
12.3.0
12.3.7
12.3.6
12.3.5
12.3.4
10.7.7
12.9.10
10.7.6
13.4.1
13.4.0
13.4.3
13.4.2
13.4.5
13.4.4
13.4.6
11.6.10
11.6.11
10.7.5
10.7.4
10.7.3
10.7.2
10.7.1
10.7.0
12.4.8
12.4.7
11.5.11
11.6.3
11.5.10
11.6.2
11.6.5
11.6.4
12.10.0
12.10.1
11.6.1
11.6.0
12.10.5
12.10.2
12.10.3
12.10.8
12.10.9
12.10.6
11.6.9
12.10.7
11.6.8
12.4.2
12.4.1
12.4.0
12.4.6
12.4.5
12.4.4
12.4.3
10.8.7
10.8.6
10.8.5
13.3.0
13.3.2
13.3.1
13.3.4
13.3.3
13.3.6
13.3.5
13.3.8
13.3.7
13.3.9
10.8.4
10.8.3
10.8.2
10.8.1
10.8.0
10.0.7
10.0.6
10.0.5
0

         */
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

