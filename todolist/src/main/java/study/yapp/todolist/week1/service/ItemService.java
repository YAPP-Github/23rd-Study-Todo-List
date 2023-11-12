package study.yapp.todolist.week1.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import study.yapp.todolist.common.ResponseCode;
import study.yapp.todolist.dto.ItemDto;
import study.yapp.todolist.exception.InvalidItemException;
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

    /**
     * todoItem 저장
     * @param request
     * @return
     */
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

    /**
     * todoItem 수정
     * @param request
     * @return
     */
    public ItemDto.ResponseItemDto updateItem(ItemDto.RequestUpdateItemDto request) {
        Item item = itemRepository.findById(request.getItemId());
        if (item == null) {
            throw new InvalidItemException("존재하지 않는 항목입니다.", ResponseCode.INVALID_ITEM);
        }
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

    /**
     * todoItem 삭제
     * @param request
     * @return
     */
    public ItemDto.ResponseDeleteItemDto deleteItem(ItemDto.RequestDeleteDto request) {
        Item item = itemRepository.findById(request.getItemId());
        if (item == null) {
            throw new InvalidItemException("존재하지 않는 항목입니다.", ResponseCode.INVALID_ITEM);
        }
        if (item.getMember_id() != request.getMemberId()) {
            throw new InvalidItemException("잘못된 유저의 삭제 시도입니다.", ResponseCode.INVALID_USER_ACCESS);
        }
        itemRepository.deleteById(request.getItemId());

        ItemDto.ResponseDeleteItemDto result = ItemDto.ResponseDeleteItemDto.builder()
                                                                .itemId(request.getItemId())
                                                                .title(item.getTitle())
                                                                .contents(item.getContents())
                                                                .build();

        return result;
    }

    /**
     * todoItem 단건 조회
     * @param itemId
     * @return
     */
    public ItemDto.ResponseItemDto getItem(Long itemId) {
        Item item = itemRepository.findById(itemId);
        if (item == null) {
            throw new InvalidItemException("존재하지 않는 항목입니다.", ResponseCode.INVALID_ITEM);
        }
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

    /**
     * todoItem 다건 조회
     * @param memberId
     * @return
     */
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