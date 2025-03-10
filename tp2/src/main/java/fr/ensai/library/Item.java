package fr.ensai.library;

public abstract class Item {
    public String title;
    private int year;
    private int pageCount;

    public Item(String title, int year, int pageCount){
        this.title = title;
        this.year = year;
        this.pageCount= pageCount;
    }

    public abstract String toString();
}