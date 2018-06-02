package me.travisgray.Repositories;

import me.travisgray.Models.Item;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ${TravisGray} on 2/28/2018.
 */
public interface ItemRepository extends CrudRepository<Item,Long> {

    Iterable<Item> findAllByItemNameContainingIgnoreCase(String searchitems);
}
