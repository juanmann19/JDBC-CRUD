package com.alura.jdbc.factory;



public class EnvConfig {
		public String url = System.getenv("DB_URL");
		public String user = System.getenv("DB_USER");
		public String pw = System.getenv("DB_PW");

}
