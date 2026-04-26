package Entities.Interfaces;

import Entities.Item.ItemInstance;
import Exceptions.Item.ContainerFullException;
import Exceptions.Item.ItemAlreadyContainedException;

public interface Container {
    void add(ItemInstance item) throws ContainerFullException, ItemAlreadyContainedException, IllegalStateException;
    void remove(ItemInstance item);
    boolean contains(ItemInstance item);
}
