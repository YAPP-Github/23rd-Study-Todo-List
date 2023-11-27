package yapp.study.todolist.common.adaptor

@Synchronized
fun syncAdaptor(func: () -> Unit) {
    func()
}
