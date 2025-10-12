import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;

import java.util.Scanner;

import static com.mongodb.client.model.Filters.*;


import java.util.ArrayList;
import java.util.List;

public class KnowledgeBaseApp {
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;
    private Scanner scanner;

    public KnowledgeBaseApp() {
        // Connect to MongoDB (default: localhost:27017)
        String connectionString = "mongodb://localhost:27017";
        mongoClient = MongoClients.create(connectionString);
        database = mongoClient.getDatabase("KnowledgeBaseDB");
        collection = database.getCollection("articles");
        scanner = new Scanner(System.in);
        
        System.out.println("Connected to MongoDB successfully!");
    }

    // Create operation
    public void createArticle() {
        System.out.println("\n=== Create New Article ===");
        
        System.out.print("Enter article ID: ");
        String articleID = scanner.nextLine();
        
        // Check if articleID already exists
        Document existingArticle = collection.find(eq("articleID", articleID)).first();
        if (existingArticle != null) {
            System.out.println("Error: Article ID already exists! Please use a different ID.");
            return;
        }
        
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        
        System.out.print("Enter content: ");
        String content = scanner.nextLine();
        
        System.out.print("Enter category: ");
        String category = scanner.nextLine();
        
        System.out.print("Enter tags (comma separated): ");
        String tags = scanner.nextLine();

        // Using user-provided articleID as String
        Document article = new Document("articleID", articleID)
                .append("title", title)
                .append("content", content)
                .append("category", category)
                .append("tags", tags)
                .append("createdAt", new java.util.Date());

        collection.insertOne(article);
        System.out.println("Article created successfully with ID: " + articleID);
    }

    // Read operations
    public void getAllArticles() {
        System.out.println("\n=== All Articles ===");
        List<Document> articles = collection.find().into(new ArrayList<>());
        
        if (articles.isEmpty()) {
            System.out.println("No articles found.");
            return;
        }

        for (Document article : articles) {
            displayArticle(article);
        }
    }

    public void getArticleById() {
        System.out.println("\n=== Find Article by ID ===");
        System.out.print("Enter article ID: ");
        String id = scanner.nextLine();

        // Search by articleID as String
        Document article = collection.find(eq("articleID", id)).first();
        if (article != null) {
            displayArticle(article);
        } else {
            System.out.println("Article not found!");
        }
    }

    public void searchArticles() {
        System.out.println("\n=== Search Articles ===");
        System.out.print("Enter search term (title/content): ");
        String searchTerm = scanner.nextLine();

        // Search in title or content
        Document query = new Document("$or", 
            java.util.Arrays.asList(
                new Document("title", new Document("$regex", searchTerm).append("$options", "i")),
                new Document("content", new Document("$regex", searchTerm).append("$options", "i"))
            )
        );

        List<Document> articles = collection.find(query).into(new ArrayList<>());
        
        if (articles.isEmpty()) {
            System.out.println("No articles found matching your search.");
        } else {
            System.out.println("Found " + articles.size() + " article(s):");
            for (Document article : articles) {
                displayArticle(article);
            }
        }
    }

    // Update operation
    public void updateArticle() {
        System.out.println("\n=== Update Article ===");
        System.out.print("Enter article ID to update: ");
        String id = scanner.nextLine();

        // Search by articleID as String
        Document existingArticle = collection.find(eq("articleID", id)).first();
        if (existingArticle == null) {
            System.out.println("Article not found!");
            return;
        }

        System.out.println("Current article:");
        displayArticle(existingArticle);

        System.out.print("Enter new title (press enter to keep current): ");
        String newTitle = scanner.nextLine();
        
        System.out.print("Enter new content (press enter to keep current): ");
        String newContent = scanner.nextLine();
        
        System.out.print("Enter new category (press enter to keep current): ");
        String newCategory = scanner.nextLine();

        Document updates = new Document();
        if (!newTitle.isEmpty()) updates.append("title", newTitle);
        if (!newContent.isEmpty()) updates.append("content", newContent);
        if (!newCategory.isEmpty()) updates.append("category", newCategory);
        
        if (!updates.isEmpty()) {
            updates.append("updatedAt", new java.util.Date());
            // Update by articleID as String
            UpdateResult result = collection.updateOne(
                eq("articleID", id), 
                new Document("$set", updates)
            );
            
            if (result.getModifiedCount() > 0) {
                System.out.println("Article updated successfully!");
            } else {
                System.out.println("No changes made.");
            }
        } else {
            System.out.println("No changes made.");
        }
    }

    // Delete operation
    public void deleteArticle() {
        System.out.println("\n=== Delete Article ===");
        System.out.print("Enter article ID to delete: ");
        String id = scanner.nextLine();

        // Delete by articleID as String
        DeleteResult result = collection.deleteOne(eq("articleID", id));
        if (result.getDeletedCount() > 0) {
            System.out.println("Article deleted successfully!");
        } else {
            System.out.println("Article not found!");
        }
    }

    private void displayArticle(Document article) {
        // Display articleID as String
        System.out.println("Article ID: " + article.getString("articleID"));
        System.out.println("Title: " + article.getString("title"));
        System.out.println("Category: " + article.getString("category"));
        System.out.println("Content: " + article.getString("content"));
        System.out.println("Tags: " + article.getString("tags"));
        System.out.println("Created: " + article.getDate("createdAt"));
        if (article.containsKey("updatedAt")) {
            System.out.println("Updated: " + article.getDate("updatedAt"));
        }
        System.out.println("---");
    }

    public void displayMenu() {
        System.out.println("\n=== Knowledge Base System ===");
        System.out.println("1. Create new article");
        System.out.println("2. View all articles");
        System.out.println("3. Find article by ID");
        System.out.println("4. Search articles");
        System.out.println("5. Update article");
        System.out.println("6. Delete article");
        System.out.println("7. Exit");
        System.out.print("Choose an option: ");
    }

    public void close() {
        if (mongoClient != null) {
            mongoClient.close();
        }
        scanner.close();
        System.out.println("Disconnected from MongoDB.");
    }

    public static void main(String[] args) {
        KnowledgeBaseApp app = new KnowledgeBaseApp();
        
        try {
            boolean running = true;
            while (running) {
                app.displayMenu();
                String choice = app.scanner.nextLine();
                
                switch (choice) {
                    case "1":
                        app.createArticle();
                        break;
                    case "2":
                        app.getAllArticles();
                        break;
                    case "3":
                        app.getArticleById();
                        break;
                    case "4":
                        app.searchArticles();
                        break;
                    case "5":
                        app.updateArticle();
                        break;
                    case "6":
                        app.deleteArticle();
                        break;
                    case "7":
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid option! Please try again.");
                }
            }
        } finally {
            app.close();
        }
    }
}