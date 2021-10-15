import com.mongodb.client.*;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Sorts;
import org.bson.Document;

import java.util.Arrays;

import static com.mongodb.client.model.Accumulators.avg;

public class App {

    public static void main(String[] args) {
        //create mongo client
        MongoClient mongoClient = MongoClients.create("mongodb://ec2-3-80-213-115.compute-1.amazonaws.com");
        System.out.println(mongoClient);


        //create db
        MongoDatabase db = mongoClient.getDatabase("myfirstdb");

        //create collection
        /*MongoCollection<Document> studentsCollection = db.getCollection("students");
        System.out.println("Collection: " + studentsCollection);

        mongoClient = MongoClients.create(connectionString);
        MongoDatabase db = mongoClient.getDatabase(dBName);*/
        MongoCollection<Document> collection = db.getCollection("students");

        Document doc = new Document("_id", 141)
                .append("name", "Rahul")
                .append("courseId", 114)
                .append("age", 28);
        collection.insertOne(doc);

        for (Document document : collection.find()) {
            System.out.println(document.toJson());
        }
        /*
        Student student = new Student(2, "sanketh", 100, 31);
        Student student1 = new Student(3, "vaibhav", 100, 31);
        Student student2 = new Student(4, "anudeep", 100, 31);
        Student student3 = new Student(5, "rakshith", 100, 31);
        Student student4 = new Student(6, "shreyas", 100, 31);
        Student student5 = new Student(1, "sandesh", 100, 31);

        List<Document> studentList = new ArrayList<>();
        studentList.add(student.createDBObject());
        studentList.add(student1.createDBObject());
        studentList.add(student2.createDBObject());
        studentList.add(student3.createDBObject());
        studentList.add(student4.createDBObject());
        studentList.add(student5.createDBObject());

        studentsCollection.insertMany(studentList);*//* for (Document document : studentsCollection.find()) {
            System.out.println(document.toJson());
        }
        Student student = new Student(2, "sanketh", 100, 31);
        Student student1 = new Student(3, "vaibhav", 100, 31);
        Student student2 = new Student(4, "anudeep", 100, 31);
        Student student3 = new Student(5, "rakshith", 100, 31);
        Student student4 = new Student(6, "shreyas", 100, 31);
        Student student5 = new Student(1, "sandesh", 100, 31);

        List<Document> studentList = new ArrayList<>();
        studentList.add(student.createDBObject());
        studentList.add(student1.createDBObject());
        studentList.add(student2.createDBObject());
        studentList.add(student3.createDBObject());
        studentList.add(student4.createDBObject());
        studentList.add(student5.createDBObject());

        studentsCollection.insertMany(studentList);*/

        //read a specific record
        //Bson filter = and(eq("name", "sandesh"), eq("_id", 1));
       /* MongoCursor<Document> cursor = studentsCollection.find().sort(Sorts.ascending("name")).cursor();
        while (cursor.hasNext()) {
            System.out.println(cursor.next().toJson());
        }

        cursor = studentsCollection.aggregate(
                    Arrays.asList(Aggregates.group("null",
                            avg("average age", "$age"))))
                    .cursor();
        while (cursor.hasNext()) {
            System.out.println(cursor.next().toJson());
        }*/


    }
}
