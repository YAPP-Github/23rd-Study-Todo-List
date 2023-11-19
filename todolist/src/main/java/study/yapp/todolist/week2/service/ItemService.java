package study.yapp.todolist.week2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.yapp.todolist.common.ResponseCode;
import study.yapp.todolist.dto.ItemDto;
import study.yapp.todolist.exception.InvalidItemException;
import study.yapp.todolist.week2.dao.Item;
import study.yapp.todolist.week2.dao.Member;
import study.yapp.todolist.week2.repository.ItemBulkRepository;
import study.yapp.todolist.week2.repository.ItemRepository;
import study.yapp.todolist.week2.repository.MemberRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    private final ItemBulkRepository itemBulkRepository;

    @Transactional
    public ItemDto.ResponseItemDto saveItem(ItemDto.RequestItemDto request) {
        Member member = memberRepository.findById(request.getMemberId()).get();
        Date createdDate = new Date();

        Item item = Item.builder()
                .created_date(createdDate)
                .title(request.getTitle())
                .contents(request.getContents())
                .build();
        item.setMember(member);

        itemRepository.save(item);


        ItemDto.ResponseItemDto result = ItemDto.ResponseItemDto.builder()
                .itemId(item.getId())
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
    @Transactional
    public ItemDto.ResponseItemDto updateItem(ItemDto.RequestUpdateItemDto request, Long itemId) {
        Item item = itemRepository.findById(itemId).get();
        if (item == null) {
            throw new InvalidItemException("존재하지 않는 항목입니다.", ResponseCode.INVALID_ITEM);
        }

        item.updateItem(new Date(), request.getTitle(), request.getContents());

        itemRepository.save(item);

        ItemDto.ResponseItemDto result = ItemDto.ResponseItemDto.builder()
                .itemId(item.getId())
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
    @Transactional
    public ItemDto.ResponseDeleteItemDto deleteItem(Long itemId, Long memberId) {
        Item item = itemRepository.findById(itemId).get();
        if (item == null) {
            throw new InvalidItemException("존재하지 않는 항목입니다.", ResponseCode.INVALID_ITEM);
        }
        if (item.getMember().getId() != memberId) {
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
                .itemId(item.getId())
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
        Member member = memberRepository.findById(memberId).get();
        List<Item> itemList = itemRepository.findAllByMember(member);

        List<ItemDto.ResponseItemDto> result = itemList.stream()
                .map(item -> ItemDto.ResponseItemDto.builder()
                        .itemId(item.getId())
                        .title(item.getTitle())
                        .createdDate(item.getCreated_date())
                        .updatedDate(item.getUpdated_date())
                        .contents(item.getContents())
                        .build())
                .collect(Collectors.toList());

        return result;
    }

    @Transactional
    public ItemDto.ResponseBulkItem createBulk(Long count, Long memberId) {
        List<Item> itemList = new ArrayList<>();
        Member member = memberRepository.findById(memberId).get();
        for (int i = 0 ; i < count ; i++) {
            String title = new StringBuilder().append(member.getName()).append(" ").append(i).append(" title").toString();
            String contents = new StringBuilder().append(member.getName()).append(" ").append(i).append(" contents").toString();
            Item item = Item.builder()
                    .title(title)
                    .contents(contents)
                    .created_date(new Date())
                    .build();
            item.setMember(member);

            itemList.add(item);
        }
        itemBulkRepository.saveAll(itemList);

        return ItemDto.ResponseBulkItem.builder().count(count).memberId(memberId).build();
    }

}