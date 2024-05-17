package com.spacestar.back.game.controller;

import com.spacestar.back.game.service.GameService;
import com.spacestar.back.game.vo.res.GameOptionResVo;
import com.spacestar.back.game.vo.res.GameResVo;
import com.spacestar.back.global.ResponseEntity;
import com.spacestar.back.global.ResponseSuccess;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/game")
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<GameResVo>> getGames() {
        return gameService.getGames();
    }

    @GetMapping("/option/{gameId}")
    public ResponseEntity<GameOptionResVo> getGameOption(@PathVariable Long gameId) {
        return new ResponseEntity<>(
                ResponseSuccess.GET_GAME_OPTION_SUCCESS
                , modelMapper.map(gameService.getGameOption(gameId), GameOptionResVo.class));
    }
}
