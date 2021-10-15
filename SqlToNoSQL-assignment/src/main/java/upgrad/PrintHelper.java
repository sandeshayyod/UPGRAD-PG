package upgrad;

import org.bson.Document;

public class PrintHelper {

    /**
     * Print all attributes of the row
     * @param row
     */
    public static void printAllAttributes(Document row) {
        System.out.println(row.toJson().toString());
    }

    /**
     * Print common product attributes
     * @param row
     */
    public static void printSingleCommonAttributes(Document row) {
        System.out.println("MongoDocId : " + row.get("_id") + " \t| ProductId : " + row.get("ProductId") + " \t| Category : " + row.get("Category") + " \t| Manufacturer : "+row.get("Manufacturer")+ " \t| Title : " + row.get("Title") );
    }

    /**
     * Print Product Count in Categories
     * @param row
     */
    public static void printProductCountInCategory(Document row) {
        System.out.println("Category : " + row.get("_id") + " \t| Count : " + row.get("Count"));
    }
}