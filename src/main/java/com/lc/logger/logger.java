package com.lc.logger;

import java.io.IOException;

@SuppressWarnings("all")
public class logger {
	
	public static void getLogger(Object obj){

		try {
			Write.WriteLog(	obj.toString());
		} catch (IOException e) {
			e.printStackTrace();
			try {
				Write.WriteLog("出现了io异常");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}


	}
	

}
