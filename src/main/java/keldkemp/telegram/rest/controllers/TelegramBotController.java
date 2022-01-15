package keldkemp.telegram.rest.controllers;

import keldkemp.telegram.rest.dto.TelegramBotDto;
import keldkemp.telegram.rest.mappers.TelegramMapper;
import keldkemp.telegram.services.TelegramBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RestController
@RequestMapping("/api/telegram/bot")
public class TelegramBotController {

    @Autowired
    private TelegramBotService telegramBotService;

    @Autowired
    private TelegramMapper telegramMapper;

    @GetMapping("/list")
    public List<TelegramBotDto> getTelegramBotsList() {
        return telegramMapper.toTelegramBotsDtoFromPo(telegramBotService.getBots());
    }

    @GetMapping("/{id}")
    public TelegramBotDto getTelegramBot(@PathVariable Long id) {
        return telegramMapper.toTelegramBotDtoFromPo(telegramBotService.getBot(id));
    }

    @PostMapping()
    public TelegramBotDto saveTelegramBot(@RequestBody TelegramBotDto telegramBotDto) {
        return telegramMapper.toTelegramBotDtoFromPo(telegramBotService.save(telegramMapper.toTelegramBotPoFromDto(telegramBotDto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTelegramBot(@PathVariable Long id) {
        telegramBotService.delete(id);
        return ResponseEntity.ok().build();
    }
}
