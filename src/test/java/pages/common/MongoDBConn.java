package pages.common;

public class MongoDBConn {
//	public static String getConfirmationCodeFromDB(String email) {
//		String DBUrl = Util.getValFromResource("qaDatabase.url", FileType.CONFIG_FILE);
//		MongoClientURI mongoClientURI = new MongoClientURI(DBUrl);
//		MongoClient mongoClient = new MongoClient(mongoClientURI);
//		MongoDatabase database = mongoClient.getDatabase("bank");  
//		 DB db = mongoClient.getDB( "bank" );
//		    DBCollection collection = db.getCollection("user/users");
//		    System.out.println("connected to mongo db");
//		    BasicDBObject query = new BasicDBObject();
//		    query.put("email", email); 
//		    
//		    DBObject dbo = collection.findOne(query);
//		    String name = (String) dbo.get("confirm_token");
//		    System.out.println("confirm code is: "+name);
//		
//		return name;
//	}
}
