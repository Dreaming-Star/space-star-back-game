package com.spacestar.back.game.service;

import com.spacestar.back.game.dto.res.GameOptionResDto;
import com.spacestar.back.game.repository.GameRepository;
import com.spacestar.back.game.vo.res.GameResVo;
import com.spacestar.back.global.GlobalException;
import com.spacestar.back.global.ResponseEntity;
import com.spacestar.back.global.ResponseStatus;
import com.spacestar.back.global.ResponseSuccess;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;

    @Override
    public ResponseEntity<List<GameResVo>> getGames() {
        List<GameResVo> gameResVos = gameRepository.findAllGameNames();
        for (int i = 0; i < gameResVos.size(); i++) {
            gameResVos.get(i).setIndex(i);
        }
        return new ResponseEntity<List<GameResVo>>(ResponseSuccess.GET_GAMES_SUCCESS, gameResVos);
    }

    @Override
    public GameOptionResDto getGameOption(Long gameId) {
        return gameRepository.findGameOption(gameId).orElseThrow(
                ()-> new GlobalException(ResponseStatus.GAME_NOT_FOUND)
        );
    }
}
