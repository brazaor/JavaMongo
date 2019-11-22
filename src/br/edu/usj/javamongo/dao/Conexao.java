package br.edu.usj.javamongo.dao;

import java.sql.Connection;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class Conexao {

	public static Connection getConexao() {
		Connection conexao = null;		
		return conexao;
	}
	
	public static MongoDatabase getDatabase() {
		MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
		MongoDatabase database = mongoClient.getDatabase("usj");
		return database;
	}
}
