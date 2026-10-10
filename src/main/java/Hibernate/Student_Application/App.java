package Hibernate.Student_Application;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.hibernate.jpa.HibernatePersistenceProvider;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class App {
    public static void main(String[] args) {
        
        Map<String, String> properties = new HashMap<>();
        properties.put("jakarta.persistence.jdbc.driver", "com.mysql.cj.jdbc.Driver");
        properties.put("jakarta.persistence.jdbc.url", "jdbc:mysql://localhost:3306/add_a8?createDatabaseIfNotExist=true");
        properties.put("jakarta.persistence.jdbc.user", "root");
        properties.put("jakarta.persistence.jdbc.password", "Yuvaraj@2001");

        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.put("hibernate.show_sql", "false");
        properties.put("hibernate.format_sql", "false");
        properties.put("hibernate.hbm2ddl.auto", "update");

        EntityManagerFactory emf = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(properties), properties);
        
        EntityManager em = emf.createEntityManager();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        System.out.println("=== PRODUCT MANAGEMENT CLI SYSTEM ===");

        while (!exit) {
            System.out.println("\nSelect an operation:");
            System.out.println("1. Insert Product");
            System.out.println("2. Find Product By ID");
            System.out.println("3. Update Product Price");
            System.out.println("4. Delete Product");
            System.out.println("5. View All Products"); // <-- New Option
            System.out.println("6. Exit");
            System.out.print("Enter choice (1-6): ");
            
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1: // --- INSERT ---
                    System.out.println("\n--- Insert New Product ---");
                    System.out.print("Enter ID: ");
                    int insertId = scanner.nextInt();
                    scanner.nextLine(); 
                    
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    
                    System.out.print("Enter Type: ");
                    String type = scanner.nextLine();
                    
                    System.out.print("Enter Price: ");
                    double price = scanner.nextDouble();

                    try {
                        em.getTransaction().begin();
                        Product newProduct = new Product(insertId, name, type, price);
                        em.persist(newProduct);
                        em.getTransaction().commit();
                        System.out.println(" [SUCCESS] Product inserted: " + newProduct);
                    } catch (Exception e) {
                        if (em.getTransaction().isActive()) em.getTransaction().rollback();
                        System.out.println(" [ERROR] Failed to insert product.");
                    }
                    break;

                case 2: // --- FIND ---
                    System.out.println("\n--- Find Product By ID ---");
                    System.out.print("Enter Product ID to find: ");
                    int findId = scanner.nextInt();

                    Product foundProduct = em.find(Product.class, findId);
                    if (foundProduct != null) {
                        System.out.println(" [FOUND] " + foundProduct);
                    } else {
                        System.out.println(" [NOT FOUND] No product with ID: " + findId);
                    }
                    break;

                case 3: // --- UPDATE ---
                    System.out.println("\n--- Update Product Price ---");
                    System.out.print("Enter Product ID to update: ");
                    int updateId = scanner.nextInt();

                    Product productToUpdate = em.find(Product.class, updateId);
                    if (productToUpdate != null) {
                        System.out.println("Current Details: " + productToUpdate);
                        System.out.print("Enter New Price: ");
                        double newPrice = scanner.nextDouble();

                        try {
                            em.getTransaction().begin();
                            productToUpdate.setPrice(newPrice);
                            em.getTransaction().commit();
                            System.out.println(" [SUCCESS] Price updated successfully.");
                        } catch (Exception e) {
                            if (em.getTransaction().isActive()) em.getTransaction().rollback();
                            System.out.println(" [ERROR] Failed to update.");
                        }
                    } else {
                        System.out.println(" [NOT FOUND] Product does not exist.");
                    }
                    break;

                case 4: // --- DELETE ---
                    System.out.println("\n--- Delete Product ---");
                    System.out.print("Enter Product ID to delete: ");
                    int deleteId = scanner.nextInt();

                    Product productToDelete = em.find(Product.class, deleteId);
                    if (productToDelete != null) {
                        try {
                            em.getTransaction().begin();
                            em.remove(productToDelete);
                            em.getTransaction().commit();
                            System.out.println(" [SUCCESS] Product deleted.");
                        } catch (Exception e) {
                            if (em.getTransaction().isActive()) em.getTransaction().rollback();
                            System.out.println(" [ERROR] Failed to delete.");
                        }
                    } else {
                        System.out.println(" [NOT FOUND] Product does not exist.");
                    }
                    break;

                case 5: // --- VIEW ALL PRODUCTS ---
                    System.out.println("\n--- Current Inventory List ---");
                    // Fetch list using JPQL (Java Persistence Query Language)
                    List<Product> allProducts = em.createQuery("SELECT p FROM Product p", Product.class).getResultList();
                    
                    if (allProducts.isEmpty()) {
                        System.out.println("The table is completely empty.");
                    } else {
                        for (Product prod : allProducts) {
                            System.out.println(prod);
                        }
                    }
                    break;

                case 6: // --- EXIT ---
                    exit = true;
                    System.out.println("\nExiting System. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid selection! Enter a value between 1 and 6.");
            }
        }

        scanner.close();
        em.close();
        emf.close();
    }
}
