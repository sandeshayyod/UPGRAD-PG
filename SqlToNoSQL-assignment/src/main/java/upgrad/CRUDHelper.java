package upgrad;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.*;
import org.bson.Document;

import java.util.Arrays;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

public class CRUDHelper {

    /**
     * Display ALl products
     * @param collection
     */
    public static void displayAllProducts(MongoCollection<Document> collection) {
        System.out.println("------ Displaying All Products ------");
        // Call printSingleCommonAttributes to display the attributes on the Screen
        // db.products.find()
        collection.find()
                .forEach(PrintHelper::printSingleCommonAttributes);
    }

    /**
     * Display top 5 Mobiles
     * @param collection
     */
    public static void displayTop5Mobiles(MongoCollection<Document> collection) {
        System.out.println("------ Displaying Top 5 Mobiles ------");
        // Call printAllAttributes to display the attributes on the Screen
        // db.products.find({"Category":"Mobiles"}).limit(5)
        collection.find(eq("Category", "Mobiles"))
                .limit(5)
                .forEach(PrintHelper::printAllAttributes);
    }

    /**
     * Display products ordered by their categories in Descending order without auto generated Id
     * @param collection
     */
    public static void displayCategoryOrderedProductsDescending(MongoCollection<Document> collection) {
        System.out.println("------ Displaying Products ordered by categories ------");
        // Call printAllAttributes to display the attributes on the Screen
        // db.products.find().projections("_id":0).sort({"Category":1})
        collection.find()
                .projection(Projections.excludeId())
                .sort(Sorts.descending("Category"))
                .forEach(PrintHelper::printAllAttributes);
    }


    /**
     * Display number of products in each group
     * @param collection
     */
    public static void displayProductCountByCategory(MongoCollection<Document> collection) {
        System.out.println("------ Displaying Product Count by categories ------");
        // Call printProductCountInCategory to display the attributes on the Screen
        // db.products.aggregate({$group:{"_id":"$Category", "sum":{$sum:1}}})
        collection.aggregate(Arrays.asList(
                            Aggregates.group("$Category", Accumulators.sum("Count", 1))))
                .forEach(PrintHelper::printProductCountInCategory);
    }

    /**
     * Display Wired Headphones
     * @param collection
     */
    public static void displayWiredHeadphones(MongoCollection<Document> collection) {
        System.out.println("------ Displaying Wired headphones ------");
        // Call printAllAttributes to display the attributes on the Screen
        // db.products.find({$and:[{"Category" : "Headphones"},{"ConnectorType" : "Wired"}]})
        // OR
        // db.products.find({"Category" : "Headphones", "ConnectorType" : "Wired"})
        collection.find(and(Arrays.asList(eq("Category", "Headphones"), eq("ConnectorType", "Wired"))))
                .forEach(PrintHelper::printAllAttributes);
    }
}