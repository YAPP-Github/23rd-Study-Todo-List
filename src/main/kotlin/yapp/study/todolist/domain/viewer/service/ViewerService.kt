package yapp.study.todolist.domain.viewer.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import yapp.study.todolist.common.adaptor.syncAdaptor
import yapp.study.todolist.common.error.errors.BadRequestException
import yapp.study.todolist.domain.viewer.entity.ViewerCount
import yapp.study.todolist.domain.viewer.entity.ViewerId
import yapp.study.todolist.domain.viewer.repository.ViewerCountRepository
import yapp.study.todolist.domain.viewer.repository.ViewerIdRepository

@Service
class ViewerService(
    private val viewerCountRepository: ViewerCountRepository,
    private val viewerIdRepository: ViewerIdRepository
) {
    fun updateCount(increase: Boolean, id: Long) {
        syncAdaptor {
            viewerCountRepository.findByIdOrNull(1)
                ?. let {
                    if (increase) {
                        it.increaseCount()
                    } else {
                        it.decreaseCount()
                    }
                    viewerCountRepository.save(it)
                }
                ?: viewerCountRepository.save(ViewerCount())
            viewerIdRepository.save(ViewerId(id))
        }
    }

    fun extendTtl(id: Long) {
        syncAdaptor {
            if (viewerIdRepository.existsById(id)) {
                viewerCountRepository.findByIdOrNull(1)
                    ?.let {
                        it.extendTtl()
                        viewerCountRepository.save(it)
                    }
                viewerIdRepository.save(ViewerId(id))
            } else {
                throw BadRequestException("invalid viewer")
            }
        }
    }
}
