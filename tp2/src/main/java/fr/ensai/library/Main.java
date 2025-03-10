package fr.ensai.library;

public class Main {

    public static void main(String[] args) {
        Author tolkien = new Author("J.R.R. Tolkien", 81, "UK");
        Magazine mag1 = new Magazine(
            "2222225", 
            "Magazine num 1", 
            5, 
            2025, 
            50);

        Magazine mag2 = new Magazine(
            "2222226", 
            "Magazine num 2", 
            5, 
            2025, 
            50);

        Book fellowshipOfTheRing = new Book(
                "978-0-618-26025-6",
                "The Fellowship of the Ring",
                tolkien,
                1954,
                423);

        Library ChezLeLibraire = new Library("ChezLeLibraire");
        ChezLeLibraire.loadBooksFromCSV("books.csv");
        ChezLeLibraire.addItem(mag1);
        ChezLeLibraire.addItem(mag2);
        ChezLeLibraire.displayItems();

        System.out.println(fellowshipOfTheRing.toString());
    }
}