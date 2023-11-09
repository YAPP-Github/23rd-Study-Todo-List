package study.yapp.todolist.week1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.yapp.todolist.dto.ItemDto;
import study.yapp.todolist.week1.dao.Item;
import study.yapp.todolist.week1.repository.ItemRepository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");

    public ItemDto.ResponseItemDto saveItem(ItemDto.RequestItemDto request) {
        Item item = Item.builder()
                        .item_id(itemRepository.ITEM_INDEX++)
                        .title(request.getTitle())
                        .created_date(new Date())
                        .updated_date(new Date())
                        .member_id(request.getMemberId())
                        .contents(request.getContents())
                        .build();

        itemRepository.save(item);

        String createdDate = simpleDateFormat.format(item.getCreated_date());

        ItemDto.ResponseItemDto result = ItemDto.ResponseItemDto.builder()
                                                                .itemId(item.getItem_id())
                                                                .title(item.getTitle())
                                                                .createdDate(createdDate)
                                                                .updatedDate(createdDate)
                                                                .contents(item.getContents())
                                                                .build();

        return result;
    }

    public ItemDto.ResponseItemDto updateItem(ItemDto.RequestUpdateItemDto request) {
        Item item = itemRepository.findById(request.getItemId());

        item = Item.builder()
                .item_id(item.getItem_id())
                .title(request.getTitle())
                .created_date(item.getCreated_date())
                .updated_date(new Date())
                .member_id(request.getMemberId())
                .contents(request.getContents())
                .build();

        itemRepository.save(item);

        String createdDate = simpleDateFormat.format(item.getCreated_date());
        String updatedDate = simpleDateFormat.format(item.getUpdated_date());

        ItemDto.ResponseItemDto result = ItemDto.ResponseItemDto.builder()
                .itemId(item.getItem_id())
                .title(item.getTitle())
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .contents(item.getContents())
                .build();

        return result;
    }

    public ItemDto.ResponseDeleteItemDto deleteItem(ItemDto.RequestDeleteDto request) {
        Item item = itemRepository.findById(request.getItemId());
        if (item.getMember_id() != request.getMemberId()) {
            throw new RuntimeException("잘못된 유저의 삭제 시도");
        }
        itemRepository.deleteById(request.getItemId());

        ItemDto.ResponseDeleteItemDto result = ItemDto.ResponseDeleteItemDto.builder()
                                                                .itemId(request.getItemId())
                                                                .title(item.getTitle())
                                                                .contents(item.getContents())
                                                                .build();

        return result;
    }

    public ItemDto.ResponseItemDto getItem(Long itemId) {
        Item item = itemRepository.findById(itemId);

        String createdDate = simpleDateFormat.format(item.getCreated_date());
        String updatedDate = simpleDateFormat.format(item.getUpdated_date());

        ItemDto.ResponseItemDto result = ItemDto.ResponseItemDto.builder()
                .itemId(item.getItem_id())
                .title(item.getTitle())
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .contents(item.getContents())
                .build();

        return result;
    }

    public List<ItemDto.ResponseItemDto> getAllItems(Long memberId) {
        List<Item> itemList = itemRepository.findAllByMemberId(memberId);
        List<ItemDto.ResponseItemDto> result = new ArrayList<>();
        for (Item item : itemList) {
            String createdDate = simpleDateFormat.format(item.getCreated_date());
            String updatedDate = simpleDateFormat.format(item.getUpdated_date());

            ItemDto.ResponseItemDto ret = ItemDto.ResponseItemDto.builder()
                    .itemId(item.getItem_id())
                    .title(item.getTitle())
                    .createdDate(createdDate)
                    .updatedDate(updatedDate)
                    .contents(item.getContents())
                    .build();

            result.add(ret);
        }
        return result;
    }

}