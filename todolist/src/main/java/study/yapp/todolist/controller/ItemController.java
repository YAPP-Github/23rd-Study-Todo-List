package study.yapp.todolist.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import study.yapp.todolist.common.Response;
import study.yapp.todolist.dto.ItemDto;
import study.yapp.todolist.week1.service.ItemService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    /**
     * todoItem 생성
     * @param registerItemDto
     * @return
     */
    @PostMapping("/todo")
    public Response<ItemDto.ResponseItemDto> registerItem(@RequestBody ItemDto.RequestItemDto registerItemDto) {
        ItemDto.ResponseItemDto result = itemService.saveItem(registerItemDto);

        return new Response<>(result);
    }

    /**
     * todoItem 수정
     * @param updateItemDto
     * @return
     */
    @PatchMapping("/todo/{itemId}")
    public Response<ItemDto.ResponseItemDto> updateItem(@RequestBody ItemDto.RequestUpdateItemDto updateItemDto) {
        ItemDto.ResponseItemDto result = itemService.updateItem(updateItemDto);

        return new Response<>(result);
    }

    /**
     * todoItem 삭제
     * @param deleteItemDto
     * @return
     */
    @DeleteMapping("/todo")
    public Response<ItemDto.ResponseDeleteItemDto> deleteItem(@RequestBody ItemDto.RequestDeleteDto deleteItemDto) {
        ItemDto.ResponseDeleteItemDto result = itemService.deleteItem(deleteItemDto);

        return new Response<>(result);
    }

    @GetMapping("/member/{memberId}/todo")
    public Response<List<ItemDto.ResponseItemDto>> getAllItems(@PathVariable(name="memberId") Long memberId) {
        List<ItemDto.ResponseItemDto> result = itemService.getAllItems(memberId);

        return new Response<>(result);
    }

    @GetMapping("/todo/{itemId}")
    public Response<ItemDto.ResponseItemDto> getItem(@PathVariable(name="itemId") Long itemId) {
        ItemDto.ResponseItemDto result = itemService.getItem(itemId);

        return new Response<>(result);
    }
}