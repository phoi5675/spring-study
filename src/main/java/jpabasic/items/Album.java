package jpabasic.items;

import javax.persistence.Entity;

@Entity
public class Album extends ItemInfo {
    private String artist;
}
