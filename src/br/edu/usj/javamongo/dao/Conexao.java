package br.edu.usj.javamongo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mongodb.DB;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class Conexao {

	public static Connection getConexao() {
		Connection conexao = null;
		
		MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
		MongoDatabase database = mongoClient.getDatabase("usj");
		database.getCollection("myTestCollection");
		
		return conexao;
	}
}
