package Entities.Interfaces;

import Entities.Item.Item;
import Exceptions.Item.ContainerFullException;
import Exceptions.Item.ItemAlreadyContainedException;

public interface Container {
    void add(Item item) throws ContainerFullException, ItemAlreadyContainedException, IllegalStateException;
    void remove(Item item);
    boolean contains(Item item);
}
