package keldkemp.telegram.rest.mappers;

import keldkemp.telegram.models.TelegramBots;
import keldkemp.telegram.rest.dto.TelegramBotDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class TelegramMapper {

    public abstract List<TelegramBotDto> toTelegramBotsDtoFromPo(List<TelegramBots> bot);

    public abstract TelegramBotDto toTelegramBotDtoFromPo(TelegramBots bot);

    public abstract List<TelegramBots> toTelegramBotsPoFromDto(List<TelegramBotDto> botDto);

    public abstract TelegramBots toTelegramBotPoFromDto(TelegramBotDto botDto);
}
