package yapp.study.todolist.common.response

import yapp.study.todolist.domain.base.PageParam

class PageResponse<T> (
        val content: List<T>,
        val totalDataSize: Int,
        val curPage: Int,
        val totalPage: Int,
        val hasNextPage: Boolean
) {
    companion object {
        fun <T> toResponse(pageParam: PageParam, data: List<T>): PageResponse<T> {
            val from = pageParam.pageSize * (pageParam.pageNumber - 1)
            val to = if (data.size < pageParam.pageSize * pageParam.pageNumber )
                    data.size
                else
                    pageParam.pageSize * pageParam.pageNumber
            val newData = data.toMutableList().subList(from, to)
            val totalPage = calcTotalPage(data.size, pageParam.pageSize)
            val hasNextPage = calcHasNextPage(data.size, pageParam.pageSize, pageParam.pageNumber, totalPage)
            return PageResponse(
                    totalDataSize = data.size,
                    content = newData,
                    curPage = pageParam.pageNumber,
                    totalPage = totalPage,
                    hasNextPage = hasNextPage
            )
        }
    }
}

fun calcTotalPage(dataSize: Int, pageSize: Int): Int {
    return if (dataSize % pageSize == 0)
        dataSize / pageSize
    else
        dataSize / pageSize + 1
}

fun calcHasNextPage(dataSize: Int, pageSize: Int, pageNum:Int, totalPage: Int): Boolean {
    return if (dataSize / pageSize == 0)
        false
    else
        totalPage != pageNum
}