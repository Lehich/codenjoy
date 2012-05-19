package net.tetris.web.controller;

import net.tetris.services.Player;
import net.tetris.services.PlayerService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class BoardController {
    public static final ArrayList<Object> EMPTY_LIST = new ArrayList<>();
    @Autowired
    private PlayerService playerService;

    public BoardController() {
    }

    //for unit test
    public BoardController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @RequestMapping(value = "/board/{playerName}",method = RequestMethod.GET)
    public String board(ModelMap model, @PathVariable("playerName") String playerName) {
        Player player = playerService.findPlayer(playerName);
        if (player == null) {
            model.addAttribute("players", EMPTY_LIST);
        }else{
            model.addAttribute("players", Collections.singletonList(player));
        }
        return "board";
    }

    @RequestMapping(value = "/board",method = RequestMethod.GET)
    public String boardAll(ModelMap model) {
        model.addAttribute("players", playerService.getPlayers());
        return "board";
    }

/*
    @RequestMapping(value = "/board", method = RequestMethod.GET)
    public String board(ModelMap model, @PathVariable String playerName) {
        System.out.println("BoardController.board");
        List<Player> players;
        if (StringUtils.isEmpty(playerName)) {
            players = playerService.getPlayers();
        } else {
            players = Collections.singletonList(playerService.findPlayer(playerName));
        }

        model.addAttribute("players", players);
        return "board";
    }
*/

}
