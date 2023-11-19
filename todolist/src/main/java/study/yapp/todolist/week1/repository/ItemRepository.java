package study.yapp.todolist.week1.repository;

import org.springframework.stereotype.Repository;
import study.yapp.todolist.common.ResponseCode;
import study.yapp.todolist.exception.InvalidItemException;
import study.yapp.todolist.week1.dao.Item;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

//@Repository
public class ItemRepository {

    public AtomicLong ITEM_INDEX = new AtomicLong(0);
    private final ConcurrentHashMap<Long, Item> itemList = new ConcurrentHashMap<>();

    public void save(Item item) {
        itemList.put(item.getItem_id(), item);
    }

    public Optional<Item> findById(Long id) {
        Optional<Item> item  = Optional.ofNullable(itemList.get(id));

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