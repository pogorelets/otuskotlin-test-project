import ru.otus.otuskotlin.microblog.app.common.IMcblAppSettings
import ru.otus.otuskotlin.microblog.biz.McblTipProcessor
import ru.otus.otuskotlin.microblog.common.McblCorSettings

data class McblAppSettings(
    val appUrls: List<String> = emptyList(),
    override val processor: McblTipProcessor = McblTipProcessor(),
    override val corSettings: McblCorSettings = McblCorSettings(),
) : IMcblAppSettings
