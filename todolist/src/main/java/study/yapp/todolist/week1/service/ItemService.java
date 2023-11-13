package study.yapp.todolist.week1.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import study.yapp.todolist.common.ResponseCode;
import study.yapp.todolist.dto.ItemDto;
import study.yapp.todolist.exception.InvalidItemException;
import study.yapp.todolist.week1.dao.Item;
import study.yapp.todolist.week1.repository.ItemRepository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItemService {

    private final ItemRepository itemRepository;

    /**
     * todoItem 저장
     * @param request
     * @return
     */
    public ItemDto.ResponseItemDto saveItem(ItemDto.RequestItemDto request) {
        Date createdDate = new Date();
        Item item = Item.builder()
                        .item_id(itemRepository.ITEM_INDEX.getAndIncrement())
                        .title(request.getTitle())
                        .created_date(createdDate)
                        .updated_date(createdDate)
                        .member_id(request.getMemberId())
                        .contents(request.getContents())
                        .build();

        itemRepository.save(item);


        ItemDto.ResponseItemDto result = ItemDto.ResponseItemDto.builder()
                                                                .itemId(item.getItem_id())
                                                                .title(item.getTitle())
                                                                .createdDate(item.getCreated_date())
                                                                .updatedDate(item.getUpdated_date())
                                                                .contents(item.getContents())
                                                                .build();
        return result;
    }

    /**
     * todoItem 수정
     * @param request
     * @return
     */
    public ItemDto.ResponseItemDto updateItem(ItemDto.RequestUpdateItemDto request, Long itemId) {
        Item item = itemRepository.findById(itemId).get();
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

        ItemDto.ResponseItemDto result = ItemDto.ResponseItemDto.builder()
                .itemId(item.getItem_id())
                .title(item.getTitle())
                .createdDate(item.getCreated_date())
                .updatedDate(item.getUpdated_date())
                .contents(item.getContents())
                .build();

        return result;
    }

    /**
     * todoItem 삭제
     * @param itemId
     * @param memberId
     * @return
     */
    public ItemDto.ResponseDeleteItemDto deleteItem(Long itemId, Long memberId) {
        Item item = itemRepository.findById(itemId).get();
        if (item == null) {
            throw new InvalidItemException("존재하지 않는 항목입니다.", ResponseCode.INVALID_ITEM);
        }
        if (item.getMember_id() != memberId) {
            throw new InvalidItemException("잘못된 유저의 삭제 시도입니다.", ResponseCode.INVALID_USER_ACCESS);
        }
        itemRepository.deleteById(itemId);

        ItemDto.ResponseDeleteItemDto result = ItemDto.ResponseDeleteItemDto.builder()
                                                                .itemId(itemId)
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
        Item item = itemRepository.findById(itemId).get();
        if (item == null) {
            throw new InvalidItemException("존재하지 않는 항목입니다.", ResponseCode.INVALID_ITEM);
        }

        ItemDto.ResponseItemDto result = ItemDto.ResponseItemDto.builder()
                .itemId(item.getItem_id())
                .title(item.getTitle())
                .createdDate(item.getCreated_date())
                .updatedDate(item.getUpdated_date())
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

         List<ItemDto.ResponseItemDto> result = itemList.stream()
                .map(item -> ItemDto.ResponseItemDto.builder()
                        .itemId(item.getItem_id())
                        .title(item.getTitle())
                        .createdDate(item.getCreated_date())
                        .updatedDate(item.getUpdated_date())
                        .contents(item.getContents())
                        .build())
                .collect(Collectors.toList());

        return result;
    }

}