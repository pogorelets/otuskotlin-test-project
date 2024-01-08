package ru.otus.otuskotlin.mappers.v2.exceptions

import ru.otus.otuskotlin.common.models.McblCommand

class UnknownMcblCommand(command: McblCommand) : Throwable("Wrong command $command at mapping toTransport stage")
