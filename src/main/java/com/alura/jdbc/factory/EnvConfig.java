package com.alura.jdbc.factory;



public class EnvConfig {
		String url = System.getenv("DB_URL");
		String user = System.getenv("DB_USER");
		String pw = System.getenv("DB_PW");

}
