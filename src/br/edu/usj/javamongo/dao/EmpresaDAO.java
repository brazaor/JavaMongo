package br.edu.usj.javamongo.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;

import br.edu.usj.javamongo.entity.Empresa;


public class EmpresaDAO {

	public List<Empresa> listarEmpresas(){
				
		List<Empresa> empresas = new ArrayList<Empresa>();
		MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
		MongoDatabase database = mongoClient.getDatabase("usj");
		MongoCollection<Document> coll = database.getCollection("empresa");
		Iterator<Document> ite = coll.find().iterator();
		
		while(ite.hasNext()) {
			Empresa empresa = new Empresa();
			Document doc = ite.next();
			empresa.setId(doc.getObjectId("_id"));
			empresa.setCodigo(doc.getInteger("codigo"));
			empresa.setNome(doc.getString("nome"));
			empresa.setRamoAtuacao(doc.getString("ramo_atuacao"));
			empresa.setAnoFundacao(doc.getInteger("ano_fundacao"));
			empresa.setCapital(doc.getDouble(("capital")));
			
			empresas.add(empresa);
		}
 
		return empresas;
	}
	
	public Empresa buscarEmpresa(ObjectId id) {
		MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
		MongoDatabase database = mongoClient.getDatabase("usj");
		MongoCollection<Document> coll = database.getCollection("empresa");
		Document doc = coll.find(Filters.eq("_id", id)).first();
		Empresa empresa = new Empresa();
		empresa.setId(doc.getObjectId("_id"));
		empresa.setCodigo(doc.getInteger("codigo"));
		empresa.setNome(doc.getString("nome"));
		empresa.setRamoAtuacao(doc.getString("ramo_atuacao"));
		empresa.setAnoFundacao(doc.getInteger("ano_fundacao"));
		empresa.setCapital(doc.getDouble(("capital")));
		return empresa;
	}
	
	public boolean gravarEmpresa(Empresa e) {
		
		MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
		MongoDatabase database = mongoClient.getDatabase("usj");
		MongoCollection<Document> coll = database.getCollection("empresa");
		
		Document doc = new Document("codigo", e.getCodigo())
				.append("nome",e.getNome())
				.append("ano_fundacao", e.getAnoFundacao())
				.append("ramo_atuacao", e.getRamoAtuacao())
				.append("capital", e.getCapital());
		coll.insertOne(doc);
		
		return true;
	}
	
	public boolean excluirEmpresa(int codigo) {
		MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
		MongoDatabase database = mongoClient.getDatabase("usj");
		MongoCollection<Document> coll = database.getCollection("empresa");
		Document doc = coll.find(Filters.eq("codigo", codigo)).first();
		System.out.println("documento = "+doc.getObjectId("_id"));
		DeleteResult result = coll.deleteOne(Filters.eq("_id", doc.getObjectId("_id")));
		return result.getDeletedCount() > 0;
	} 
}
