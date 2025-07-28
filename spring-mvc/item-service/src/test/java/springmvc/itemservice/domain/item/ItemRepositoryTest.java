package springmvc.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class ItemRepositoryTest {
    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        // Given
        Item item = new Item("ItemA", 10000, 10);

        // When
        Item savedItem = itemRepository.save(item);

        // Then
        Item findItem = itemRepository.findById(item.getId());

        Assertions.assertThat(findItem).isEqualTo(savedItem);
    }

    @Test
    void findAll() {
        // Given
        Item item1 = new Item("ItemA", 10000, 10);
        Item item2 = new Item("ItemB", 10000, 10);

        itemRepository.save(item1);
        itemRepository.save(item2);

        // When
        List<Item> result = itemRepository.findAll();

        // Then
        Assertions.assertThat(result).hasSize(2);
        Assertions.assertThat(result).contains(item1, item2);
    }

    @Test
    void update() {
        // Given
        Item item = new Item("ItemA", 10000, 10);

        Item savedItem = itemRepository.save(item);
        Long itemId = savedItem.getId();

        // When
        Item updateParam = new Item("ItemB", 20000, 30);
        itemRepository.update(itemId, updateParam);

        // Then
        Item findItem = itemRepository.findById(itemId);

        Assertions.assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
        Assertions.assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
        Assertions.assertThat(findItem.getQuantity()).isEqualTo(updateParam.getQuantity());
    }
}