import com.mongodb.client.*;
import org.bson.Document;

import java.util.Arrays;

import static com.mongodb.client.model.Accumulators.sum;
import static com.mongodb.client.model.Aggregates.group;

public class Orders {

    public static void main(String[] args) {
        MongoClient mongoClient = MongoClients.create("mongodb://ec2-3-80-213-115.compute-1.amazonaws.com");
        System.out.println(mongoClient);

        MongoDatabase db = mongoClient.getDatabase("products");
        //create collection
        MongoCollection<Document> studentsCollection = db.getCollection("purchases");
        for (Document doc : studentsCollection.find().limit(2)) {
            System.out.println(doc.toJson());
        }

        System.out.println("Total sales based on segment");
        for (Document document : studentsCollection.aggregate(Arrays.asList(
                group("$Segment", sum("Total sales", "$Sales"))))) {
            System.out.println(document.toJson());
        }
        System.out.println("Total sales across all the orders");
        for (Document document : studentsCollection.aggregate(Arrays.asList(
                group("id", sum("Total sales", "$Sales"))))) {
            System.out.println(document.toJson());
        }
    }
}
