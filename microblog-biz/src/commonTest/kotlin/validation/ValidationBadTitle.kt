package validation
import kotlinx.coroutines.test.runTest
import ru.otus.otuskotlin.common.McblContext
import ru.otus.otuskotlin.common.models.*
import ru.otus.otuskotlin.microblog.biz.McblTipProcessor
import ru.otus.otuskotlin.microblog.stubs.McblTipStubs
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

private val stub = McblTipStubs.get()
fun validationTitleCorrect(command: McblCommand, processor: McblTipProcessor) = runTest {
    val ctx = McblContext(
        command = command,
        state = McblState.NONE,
        workMode = McblWorkMode.TEST,
        tipRequest = McblTip(
            id = stub.id,
            title = "Тeма",
            description = stub.description,
            user = stub.user,
        ),
    )
    processor.exec(ctx)
    assertEquals(0, ctx.errors.size)
    assertNotEquals(McblState.FAILING, ctx.state)
    assertEquals("Тeма", ctx.tipValidated.title)
}

fun validationTitleEmpty(command: McblCommand, processor: McblTipProcessor) = runTest {
    val ctx = McblContext(
        command = command,
        state = McblState.NONE,
        workMode = McblWorkMode.TEST,
        tipRequest = McblTip(
            id = stub.id,
            title = "",
            description = "abc",
            user = ""
        ),
    )
    processor.exec(ctx)
    assertEquals(1, ctx.errors.size)
    assertEquals(McblState.FAILING, ctx.state)
    val error = ctx.errors.firstOrNull()
    assertEquals("title", error?.field)
    assertContains(error?.message ?: "", "title")
}

//@OptIn(ExperimentalCoroutinesApi::class)
//fun validationTitleSymbols(command: McblCommand, processor: McblTipProcessor) = runTest {
//    val ctx = McblContext(
//        command = command,
//        state = McblState.NONE,
//        workMode = McblWorkMode.TEST,
//        tipRequest = McblTip(
//            id = McblTipId("123"),
//            title = "!@#$%^&*(),.{}",
//            description = "abc",
//            user = ""
//        ),
//    )
//    processor.exec(ctx)
//    assertEquals(1, ctx.errors.size)
//    assertEquals(McblState.FAILING, ctx.state)
//    val error = ctx.errors.firstOrNull()
//    assertEquals("title", error?.field)
//    assertContains(error?.message ?: "", "title")
//}