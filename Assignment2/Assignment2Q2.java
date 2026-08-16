package library.model;

public abstract class LibraryResource {
    private int resourceId;
    private String title;
    private String author;
    private static String libraryName = "Central University Library";
    private static int resourceCount = 0;

    public LibraryResource(int resourceId, String title, String author) {
        this.resourceId = resourceId;
        this.title = title;
        this.author = author;
        resourceCount++;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public static String getLibraryName() {
        return libraryName;
    }

    public static void setLibraryName(String libraryName) {
        LibraryResource.libraryName = libraryName;
    }

    protected void showBasicDetails() {
        System.out.println("Resource ID: " + resourceId);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
    }

    public abstract double calculateFine(int overdueDays);

    public static void displayTotalResources() {
        System.out.println("Total Resources Created: " + resourceCount);
    }
}

package library.model;

public interface Printable {
    void printDetails();
}

package library.model;

public class Book extends LibraryResource implements Printable {

    public Book(int resourceId, String title, String author) {
        super(resourceId, title, author);
    }

    @Override
    public double calculateFine(int overdueDays) {
        return overdueDays * 5;
    }

    @Override
    public void printDetails() {
        showBasicDetails();
        System.out.println("Resource Type: Book");
        System.out.println("Fine Rate: Rs. 5/day");
    }
}

package library.model;

public class DigitalResource extends LibraryResource implements Printable {

    public DigitalResource(int resourceId, String title, String author) {
        super(resourceId, title, author);
    }

    @Override
    public double calculateFine(int overdueDays) {
        return overdueDays * 2;
    }

    @Override
    public void printDetails() {
        showBasicDetails();
        System.out.println("Resource Type: Digital Resource");
        System.out.println("Fine Rate: Rs. 2/day");
    }
}

package library.util;

public class InputValidator {

    public static boolean validateResourceId(int resourceId) {
        return resourceId > 0;
    }

    public static boolean validateFineDays(int overdueDays) {
        return overdueDays >= 0;
    }
}

package library.main;

import library.model.LibraryResource;
import library.model.Book;
import library.model.DigitalResource;
import library.util.InputValidator;

public class Main {
    public static void main(String[] args) {

        LibraryResource[] resources = new LibraryResource[5];

        resources[0] = new Book(1, "Java Programming", "James Gosling");
        resources[1] = new DigitalResource(2, "Data Structures E-Book", "Robert Lafore");
        resources[2] = new Book(3, "Operating Systems", "Galvin");
        resources[3] = new DigitalResource(4, "Computer Networks", "Andrew Tanenbaum");
        resources[4] = new Book(5, "Database Systems", "Raghu Ramakrishnan");

        int[] overdueDays = {5, 10, 3, 7, 12};

        double totalFine = 0;

        System.out.println("Library: " + LibraryResource.getLibraryName());
        System.out.println();

        for (int i = 0; i < resources.length; i++) {
            if (!InputValidator.validateResourceId(resources[i].getResourceId())) {
                System.out.println("Invalid Resource ID");
                continue;
            }

            if (!InputValidator.validateFineDays(overdueDays[i])) {
                System.out.println("Invalid Fine Days");
                continue;
            }

            resources[i].printDetails();

            double fine = resources[i].calculateFine(overdueDays[i]);
            totalFine += fine;

            System.out.println("Overdue Days: " + overdueDays[i]);
            System.out.println("Fine: Rs. " + fine);
            System.out.println("-----------------------------");
        }

        System.out.println("Total Fine: Rs. " + totalFine);
        LibraryResource.displayTotalResources();
    }
}