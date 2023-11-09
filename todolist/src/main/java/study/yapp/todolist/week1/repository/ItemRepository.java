package study.yapp.todolist.week1.repository;

import org.springframework.stereotype.Repository;
import study.yapp.todolist.week1.dao.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    public Long ITEM_INDEX = 1L;
    private final Map<Long, Item> itemList = new HashMap<>();

    public void save(Item item) {
        itemList.put(item.getItem_id(), item);
    }

    public Item findById(Long id) {
        Item item = null;
        try {
            item = itemList.get(id);
        } catch (Exception e) {
            throw new RuntimeException("ee");
        }

        return item;
    }

    public void deleteById(Long id) {
        itemList.remove(id);
    }

    public List<Item> findAllByMemberId(Long memberId) {
        List<Item> result = new ArrayList<>();
        itemList.forEach((key, value) -> {
            if (value.getMember_id() == memberId) {
                result.add(value);
            }
        });

        return result;
    }
}