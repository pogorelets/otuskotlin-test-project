package ru.otus.otuskotlin.microblog.biz

import com.crowdproj.kotlin.cor.handlers.CorChainDsl
import com.crowdproj.kotlin.cor.handlers.worker
import com.crowdproj.kotlin.cor.rootChain
import ru.otus.otuskotlin.common.McblContext
import ru.otus.otuskotlin.common.models.McblCommand
import ru.otus.otuskotlin.common.models.McblTipId
import ru.otus.otuskotlin.common.models.McblWorkMode
import ru.otus.otuskotlin.microblog.common.McblCorSettings
import ru.otus.otuskotlin.microblog.stubs.McblTipStubs
import ru.otus.otuskotlin.microblog.biz.groups.operation
import ru.otus.otuskotlin.microblog.biz.groups.stubs
import ru.otus.otuskotlin.microblog.biz.workers.*
import ru.otus.otuskotlin.microblog.biz.validation.*

class McblTipProcessor(
    private val corSettings: McblCorSettings = McblCorSettings.NONE
) {
suspend fun exec(ctx: McblContext) = BusinessChain.exec(ctx.also { it.corSettings = corSettings })

    companion object {
        private val BusinessChain = rootChain<McblContext>{
            initStatus("Инициализация цепочки")
            operation("Запрос CREATE", McblCommand.CREATE) {
               stubs("Обработка стабов"){
                   stubCreateSuccess("Стаб успешной обработки запроса CREATE")
                   stubValidationBadTitle("Стаб ошибки валидации заголовка")
                   stubValidationBadDescription("Стаб ошибки валидации описания")
                   stubDbError("Стаб ошибки работы с БД")
                   stubNoCase("Ошибка: запрошенный стаб недопустим")
               }
                validation {
                    worker("Копируем поля в tipValidating") { tipValidating = tipRequest.deepCopy() }
                    validateTitleNotEmpty("Проверка, что заголовок не пуст")
                    validateTitleHasContent("Проверка символов")
                    validateDescriptionNotEmpty("Проверка, что описание не пусто")
                    validateDescriptionHasContent("Проверка символов")
                    finishTipValidation("Завершение проверок")
                }
            }
            operation("Запрос READ", McblCommand.READ) {
                stubs("Обработка стабов"){
                    stubReadSuccess("Стаб успешной обработки запроса READ")
                    stubValidationBadId("Стаб ошибки валидации id")
                    stubDbError("Стаб ошибки работы с БД")
                    stubNoCase("Ошибка: запрошенный стаб недопустим")
                }

                validation {
                    worker("Копируем поля в adValidating") { tipValidating = tipRequest.deepCopy() }
                    worker("Очистка id") { tipValidating.id = McblTipId(tipValidating.id.asString().trim()) }
                    validateIdNotEmpty("Проверка на непустой id")
                    validateIdProperFormat("Проверка формата id")
                    finishTipValidation("Успешное завершение процедуры валидации")
                }
            }
            operation("Запрос UPDATE", McblCommand.UPDATE) {
                stubs("Обработка стабов"){
                    stubUpdateSuccess("Стаб успешной обработки запроса Update")
                    stubValidationBadId("Стаб ошибки валидации id")
                    stubValidationBadTitle("Стаб ошибки валидации заголовка")
                    stubValidationBadDescription("Стаб ошибки валидации описания")
                    stubDbError("Стаб ошибки работы с БД")
                    stubNoCase("Ошибка: запрошенный стаб недопустим")
                }
                validation {
                    worker("Копируем поля в adValidating") { tipValidating = tipRequest.deepCopy() }
                    worker("Очистка id") { tipValidating.id = McblTipId(tipValidating.id.asString().trim()) }
                    worker("Очистка заголовка") { tipValidating.title = tipValidating.title.trim() }
                    worker("Очистка описания") { tipValidating.description = tipValidating.description.trim() }
                    validateIdNotEmpty("Проверка на непустой id")
                    validateIdProperFormat("Проверка формата id")
                    validateTitleNotEmpty("Проверка на непустой заголовок")
                    validateTitleHasContent("Проверка на наличие содержания в заголовке")
                    validateDescriptionNotEmpty("Проверка на непустое описание")
                    validateDescriptionHasContent("Проверка на наличие содержания в описании")
                    finishTipValidation("Успешное завершение процедуры валидации")
                }
            }
            operation("Запрос DELETE", McblCommand.DELETE) {
                stubs("Обработка стабов"){
                    stubDeleteSuccess("Стаб успешной обработки запроса DELETE")
                    stubValidationBadId("Стаб ошибки валидации id")
                    stubDbError("Стаб ошибки работы с БД")
                    stubNoCase("Ошибка: запрошенный стаб недопустим")
                }
                validation {
                    worker("Копируем поля в adValidating") {
                        tipValidating = tipRequest.deepCopy() }
                    worker("Очистка id") { tipValidating.id = McblTipId(tipValidating.id.asString().trim()) }
                    validateIdNotEmpty("Проверка на непустой id")
                    validateIdProperFormat("Проверка формата id")
                    finishTipValidation("Успешное завершение процедуры валидации")
                }
            }
            operation("Запрос SEARCH", McblCommand.SEARCH) {
                stubs("Обработка стабов"){
                    stubSearchSuccess("Стаб успешной обработки запроса SEARCH")
                    stubValidationBadId("Стаб ошибки валидации id")
                    stubDbError("Стаб ошибки работы с БД")
                    stubNoCase("Ошибка: запрошенный стаб недопустим")
                }
                validation {
                    worker("Копируем поля в adFilterValidating") { tipFilterValidating = tipFilterRequest.copy() }
                    finishTipFilterValidation("Успешное завершение процедуры валидации")
                }
            }
        }.build()
    }
}









