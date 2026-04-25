package Entities.Interfaces;

import Entities.Item.Item;

public interface Container {
    boolean add(Item item);
    boolean remove(Object item);
    boolean contains(Object item);
}
