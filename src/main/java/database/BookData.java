package database;

import java.util.List;

public class BookData {
    private String image;
    private String title;
    private List<String> keywords;
    private String author;
    private String publisher;

    public BookData() {
    }
    
    public void setImg(String img) {
    	this.image = img;
    }
    public String getImg() {
        return image;
    }
    
    public void setTitle(String title) {
    	this.title = title;
    }
    public String getTitle() {
        return title;
    }
    
    public void setKws(List<String> kwList) {
    	this.keywords = kwList;
    }
    public List<String> getkeywords() {
        return keywords;
    }
    
    public void setAuthor(String author) {
    	this.author = author;
    }
    public String getAuthor() {
        return author;
    }
    
    public void setPublisher (String publisher) {
    	this.publisher = publisher;
    }
    public String getPublisher() {
        return publisher;
    }
}
