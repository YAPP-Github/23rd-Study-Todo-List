package study.yapp.todolist.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.yapp.todolist.common.Response;
import study.yapp.todolist.dto.ItemDto;
import study.yapp.todolist.week2.service.ItemService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path="/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class ItemController {
    private final ItemService itemService;

    /**
     * todoItem 생성
     * @param registerItemDto
     * @return
     */
    @PostMapping("/todo")
    public ResponseEntity<Response<ItemDto.ResponseItemDto>> registerItem(@RequestBody ItemDto.RequestItemDto registerItemDto) {
        ItemDto.ResponseItemDto result = itemService.saveItem(registerItemDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(new Response<>(result));
    }

    /**
     * todoItem 수정
     * @param updateItemDto
     * @return
     */
    @PatchMapping("/todo/{itemId}")
    public ResponseEntity<Response<ItemDto.ResponseItemDto>> updateItem(@RequestBody ItemDto.RequestUpdateItemDto updateItemDto, @PathVariable(name = "itemId") Long itemId) {
        ItemDto.ResponseItemDto result = itemService.updateItem(updateItemDto, itemId);

        return ResponseEntity.status(HttpStatus.RESET_CONTENT).body(new Response<>(result));
    }

    /**
     * todoItem 삭제
     * @param itemId
     * @return
     */
    @DeleteMapping("/todo")
    public Response<ItemDto.ResponseDeleteItemDto> deleteItem(@RequestParam(name = "itemId") Long itemId, @RequestParam(name = "memberId") Long memberId) {
        ItemDto.ResponseDeleteItemDto result = itemService.deleteItem(itemId, memberId);

        return new Response<>(result);
    }

    /**
     * todoItem 다건 조회
     * @param memberId
     * @return
     */
    @GetMapping("/member/{memberId}/todo")
    public Response<List<ItemDto.ResponseItemDto>> getAllItems(@PathVariable(name="memberId") Long memberId) {
        List<ItemDto.ResponseItemDto> result = itemService.getAllItems(memberId);

        return new Response<>(result);
    }

    /**
     * todoItem 단건 조회
     * @param itemId
     * @return
     */
    @GetMapping("/todo/{itemId}")
    public Response<ItemDto.ResponseItemDto> getItem(@PathVariable(name="itemId") Long itemId) {
        ItemDto.ResponseItemDto result = itemService.getItem(itemId);

        return new Response<>(result);
    }

    @PostMapping("/todo/bulk")
    public ResponseEntity<Response<ItemDto.ResponseBulkItem>> createBulkItem(@RequestParam(name = "count") Long count, @RequestParam(name = "memberId") Long memberId) {
        ItemDto.ResponseBulkItem result = itemService.createBulk(count, memberId);

        return ResponseEntity.status(HttpStatus.CREATED).body(new Response<>(result));
    }
}