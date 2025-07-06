package jpabasic.items;

import javax.persistence.Entity;

@Entity
public class Book extends ItemInfo {
    private String author;
    private String isbn;
}
