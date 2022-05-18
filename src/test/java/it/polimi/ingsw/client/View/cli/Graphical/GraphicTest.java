package it.polimi.ingsw.client.View.cli.Graphical;

import it.polimi.ingsw.model.board.Archipelago;
import it.polimi.ingsw.model.board.Characters;
import it.polimi.ingsw.model.effects.*;
import it.polimi.ingsw.model.game.Game;
import it.polimi.ingsw.model.game.GameMode;
import it.polimi.ingsw.model.game.Student;
import it.polimi.ingsw.model.game.Tower;
import it.polimi.ingsw.model.player.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class GraphicTest {
    Game game = new Game(GameMode.EXPERTMODE,3, null);
    Graphic graphic = new Graphic();
    Player player1;
    Player player2;
    Player player3;
    ArrayList<PlayerInterface> players = new ArrayList<>();
    Archipelago archipelago;

    @BeforeEach
    void init(){
        String prototypes = ("""
                
                ,▄▄▄▄▄▄▄▄▄▄▄▄,            ▄▄
             ▄█▀╙  ███    └▀██           ╫███                        ▄
            ▐█Γ   ███▌  ╓▄▄  └▀  ,    ,   ╫╟                       ,▓█Q         ,
            ╫█─   ███████╙╙█   ▓███▄████Æ▓██▌   ▄██▀███ ▄███,▓███ ╙███▓╙▄███▓ Φ███µ██▀▀██╨
            └██   ████▓    └    ███▀ █▀   ██▌  ██▌  ███  ████Γ▓██⌐ ╫██⌐└Γ ╙███  ██Γ███▓▄µ
              ▀█▌-████        ▄ ███       ██▌ ║██▌ ▄███  ███  ▓██⌐ ╫██⌐    "███▄█Γ ▄╙█████
                  ███▌      ▄█▌ ███       ███Φ└████▀███▄M███  ▓██▄Æ╫██▓▓     ███   █▌, ▄██
                Φ▀████▓▀▀▓███▀  ╙          ╙└   ╙╙   ╙╙  ╙╙    ▀▀└  ╙▀      ,██    └╙▀▀╙
                                                                           ▄██
                                                                           ███   ▓
                                                                           ╙▀███▀└
                                                                           
                 USED SYMBOLS : ▲ ■ ● △ □ ◯ ✗ │ ─ ╴ ╶ ╭ ╮ ╰ ╯
                 NON USED SYMBOLS : ◌ ♥
                        
                        ╭───────────────────────────────────────────╴ISLANDS╶───────────────────────────────────────────╮
                        │   1       2       3       4       5       6 ✗     7       8       9      10      11      12   │
                        │ 0 : △ │ 0 : △ │ 1 : ▲ │ 0 : ▲ │ 0 : ▲ │ 0 : ▲ │ 0 : ▲ │ 0 : ▲ │ 0 : ▲ │ 0 : ▲ │ 0 : ▲ │ 0 : ▲ │
                        │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │
                        │ 0 : ● │ 0 : ● │ 1 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │
                        │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │
                        │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │
                        │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │
                        ╰───────────────────────────────────────────────────────────────────────────────────────────────╯
                        ╭───────────────────────────────────────╴ISLANDS╶───────────────────────────────────────╮
                        │   1   │   2   │   3   │   4   │   5   │   6 ✗ │   7   │   8   │   9   │  10   │  11   │
                        │ 0 : △ │ 0 : △ │ 0 : △ │ 1 : ▲ │ 0 : ▲ │ 0 : ▲ │ 0 : ▲ │ 0 : ▲ │ 0 : ▲ │ 0 : ▲ │ 0 : ▲ │
                        │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │
                        │ 0 : ● │ 0 : ● │ 0 : ● │ 3 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │
                        │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │
                        │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │
                        │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │
                        ╰───────────────────────────────────────────────────────────────────────────────────────╯
                        ╭────────╴CLOUDS╶───────╮ ╭───────────╴CLOUDS╶──────────╮ ╭─────╴CLOUDS╶────╮ ╭────╴CLOUDS╶───╮
                        │   1       2       3   │ │    1    │    2    │    3    │ │  1  │  2  │  3  │ │   1       2   │
                        │ 1 : ● │ 0 : ● │ 2 : ● │ │ ● ◯ ◯ ◯ │ ◯ ◯ ◯ ◯ │ ● ● ◯ ◯ │ │ ● ● │ ● ● │ ● ● │ │ 1 : ● │ 0 : ● │
                        │ 0 : ● │ 0 : ● │ 1 : ● │ │ ◯ ◯ ◯ ◯ │ ◯ ◯ ◯ ◯ │ ● ◯ ◯ ◯ │ │ ● ● │ ● ● │ ● ● │ │ 0 : ● │ 0 : ● │
                        │ 2 : ● │ 1 : ● │ 0 : ● │ │ ● ● ◯ ◯ │ ● ◯ ◯ ◯ │ ◯ ◯ ◯ ◯ │ ╰─────────────────╯ │ 2 : ● │ 1 : ● │
                        │ 1 : ● │ 1 : ● │ 0 : ● │ │ ● ◯ ◯ ◯ │ ● ◯ ◯ ◯ │ ◯ ◯ ◯ ◯ │
                        │ 0 : ● │ 2 : ● │ 1 : ● │ │ ◯ ◯ ◯ ◯ │ ● ● ◯ ◯ │ ● ◯ ◯ ◯ │
                        ╰───────────────────────╯ ╰─────────────────────────────╯
                        ╭──────────╴GIUSEPPE╶───────────╮ ╭───────╴DANIELE╶───────╮ ╭───────╴SAMUELE╶───────╮
                        │   E   │   H   │   P   │   T   │ │   E   │   H   │   P   │ │   E   │   H   │   P   │
                        │ 0 : ● │ 0 : ● │ 0 : ■ │ 0 : ▲ │ │ 0 : ● │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │ 0 : ● │
                        │ 0 : ● │ 0 : ● │ 0 : ■ │       │ │ 0 : ● │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │ 0 : ● │
                        │ 0 : ● │ 0 : ● │ 0 : ■ │       │ │ 0 : ● │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │ 0 : ● │
                        │ 0 : ● │ 0 : ● │ 0 : ■ │       │ │ 0 : ● │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │ 0 : ● │
                        │ 0 : ● │ 0 : ● │ 0 : ■ │       │ │ 0 : ● │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │ 0 : ● │
                        ╰───────────────────────────────╯ ╰───────────────────────╯ ╰───────────────────────╯
                        ╭──────────────╴GIUSEPPE╶─────────────╮ ╭────────────╴DANIELE╶──────────╮ ╭────────────╴SAMUELE╶──────────╮
                        │  E             H            P    T  │ │  E  │          H          │ P │ │  E  │          H          │ P │
                        │ ● ● │ ● ● ● ◯ ◯ ◯ ◯ ◯ ◯ ◯ │ ■ │ ▲ ▲ │ │ ● ● │ ● ● ◯ ◯ ◯ ◯ ◯ ◯ ◯ ◯ │ □ │ │ ● ● │ ● ● ● ◯ ◯ ◯ ◯ ◯ ◯ ◯ │ □ │
                        │ ● ● │ ● ● ◯ ◯ ◯ ◯ ◯ ◯ ◯ ◯ │ □ │ ▲ ▲ │ │ ● ● │ ● ● ● ● ◯ ◯ ◯ ◯ ◯ ◯ │ ■ │ │ ● ● │ ◯ ◯ ◯ ◯ ◯ ◯ ◯ ◯ ◯ ◯ │ □ │
                        │ ● ● │ ● ● ● ● ◯ ◯ ◯ ◯ ◯ ◯ │ ■ │ ▲ ▲ │ │ ● ● │ ● ● ◯ ◯ ◯ ◯ ◯ ◯ ◯ ◯ │ □ │ │ ● ● │ ● ◯ ◯ ◯ ◯ ◯ ◯ ◯ ◯ ◯ │ □ │
                        │ ● ● │ ● ● ◯ ◯ ◯ ◯ ◯ ◯ ◯ ◯ │ □ │     │ │ ● ● │ ● ● ◯ ◯ ◯ ◯ ◯ ◯ ◯ ◯ │ □ │ │ ● ● │ ● ● ● ◯ ◯ ◯ ◯ ◯ ◯ ◯ │ ■ │
                        │ ●   │ ● ◯ ◯ ◯ ◯ ◯ ◯ ◯ ◯ ◯ │ □ │     │ │ ●   │ ● ◯ ◯ ◯ ◯ ◯ ◯ ◯ ◯ ◯ │ □ │ │ ●   │ ● ● ● ◯ ◯ ◯ ◯ ◯ ◯ ◯ │ ■ │
                        ╰─────────────────────────────────────╯ ╰───────────────────────────────╯ ╰───────────────────────────────╯
                        COINS : 2                                COINS : 1                         COINS : 3
                        ╭───────────╴GIUSEPPE╶──────────╮ ╭────────╴GIUSEPPE : 2 ╶────────╮
                        │  E  │          H          │ P │ │  E  │          H          │ P │
                        │ ● ● │ ● ● ● ◯ ◯ ◯ ◯ ◯ ◯ ◯ │ ■ │ │ ● ● │ ● ● ● ◯ ◯ ◯ ◯ ◯ ◯ ◯ │ ■ │
                        │ ● ● │ ● ● ◯ ◯ ◯ ◯ ◯ ◯ ◯ ◯ │ □ │ │ ● ● │ ● ● ◯ ◯ ◯ ◯ ◯ ◯ ◯ ◯ │ □ │
                        │ ● ● │ ● ● ● ● ◯ ◯ ◯ ◯ ◯ ◯ │ ■ │ │ ● ● │ ● ● ● ● ◯ ◯ ◯ ◯ ◯ ◯ │ ■ │
                        │ ● ● │ ● ● ◯ ◯ ◯ ◯ ◯ ◯ ◯ ◯ │ □ │ │ ● ● │ ● ● ◯ ◯ ◯ ◯ ◯ ◯ ◯ ◯ │ □ │
                        │ ●   │ ● ◯ ◯ ◯ ◯ ◯ ◯ ◯ ◯ ◯ │ □ │ │ ●   │ ● ◯ ◯ ◯ ◯ ◯ ◯ ◯ ◯ ◯ │ □ │
                        │           COINS : 2           │ ╰───────────────────────────────╯
                        ╰───────────────────────────────╯
                        
                        ╭───────────╮
                        │ ELEPHANTS │
                        │           │
                        │ VAL : 9   │
                        │ MOV : 4   │
                        ╰───────────╯
                        ╭────╴1╶────╮ ╭────╴2╶────╮ ╭────╴3╶────╮ ╭────╴4╶────╮ ╭────╴5╶────╮ ╭────╴6╶────╮ ╭────╴7╶────╮ ╭────╴8╶────╮ ╭────╴9╶────╮ ╭───╴10╶────╮
                        │   LION    │ │  OSTRICH  │ │    CAT    │ │           │ │           │ │           │ │           │ │           │ │           │ │           │
                        │           │ │           │ │           │ │           │ │           │ │           │ │           │ │           │ │           │ │           │
                        │  VAL : 1  │ │  VAL : 2  │ │  VAL : 3  │ │           │ │           │ │           │ │           │ │           │ │           │ │           │
                        │  MOV : 1  │ │  MOV : 1  │ │  MOV : 2  │ │           │ │           │ │           │ │           │ │           │ │           │ │           │
                        ╰───────────╯ ╰───────────╯ ╰───────────╯ ╰───────────╯ ╰───────────╯ ╰───────────╯ ╰───────────╯ ╰───────────╯ ╰───────────╯ ╰───────────╯
                        ╭────────────╴ASSISTANTS╶───────────╮
                        │   LION       OSTRICH       CAT    │
                        │           │           │           │
                        │  VAL : 1  │  VAL : 2  │  VAL : 3  │
                        │  MOV : 1  │  MOV : 1  │  MOV : 2  │
                        ╰───────────────────────────────────╯
                        ╭────╴9╶────╮
                        │ ELEPHANTS │
                        │           │
                        │           │
                        │  MOV : 4  │
                        ╰───────────╯
                        ╭────╴9╶────╮
                        │ ELEPHANTS │
                        │           │
                        │  MOV : 4  │
                        ╰───────────╯
                        ╭╴ELEPHANTS╶╮
                        │           │
                        │ VAL : 9   │
                        │ MOV : 4   │
                        ╰───────────╯
                        DASHBOARD :
                        ╭───────────────────────────────────────────╴ISLANDS╶───────────────────────────────────────────╮ ╭────────╴CLOUDS╶───────╮
                        │   1       2       3       4       5       6 ✗     7       8       9      10      11      12   │ │   1       2       3   │
                        │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ │ 1 : ● │ 0 : ● │ 2 : ● │
                        │ 0 : ● │ 0 : ● │ 1 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │ 1 : ● │
                        │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ │ 2 : ● │ 1 : ● │ 0 : ● │
                        │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ │ 1 : ● │ 1 : ● │ 0 : ● │
                        │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ 0 : ● │ │ 0 : ● │ 2 : ● │ 1 : ● │
                        │ 0 : △ │ 0 : △ │ 1 : ▲ │ 0 : ▲ │ 0 : ▲ │ 0 : ▲ │ 0 : ▲ │ 0 : ▲ │ 0 : ▲ │ 0 : ▲ │ 0 : ▲ │ 0 : ▲ │ ╰───────────────────────╯
                        ╰───────────────────────────────────────────────────────────────────────────────────────────────╯
                        ╭──────────────╴GIUSEPPE╶─────────────╮ ╭──────────────╴GIUSEPPE╶─────────────╮ ╭──────────────╴GIUSEPPE╶─────────────╮
                        │  E             H            P    T  │ │  E             H            P    T  │ │  E             H            P    T  │
                        │ ● ● │ ● ● ● ◯ ◯ ◯ ◯ ◯ ◯ ◯ │ ■ │ ▲ ▲ │ │ ● ● │ ● ● ● ◯ ◯ ◯ ◯ ◯ ◯ ◯ │ ■ │ ▲ ▲ │ │ ● ● │ ● ● ● ◯ ◯ ◯ ◯ ◯ ◯ ◯ │ ■ │ ▲ ▲ │
                        │ ● ● │ ● ● ◯ ◯ ◯ ◯ ◯ ◯ ◯ ◯ │ □ │ ▲ ▲ │ │ ● ● │ ● ● ◯ ◯ ◯ ◯ ◯ ◯ ◯ ◯ │ □ │ ▲ ▲ │ │ ● ● │ ● ● ◯ ◯ ◯ ◯ ◯ ◯ ◯ ◯ │ □ │ ▲ ▲ │
                        │ ● ● │ ● ● ● ● ◯ ◯ ◯ ◯ ◯ ◯ │ ■ │ ▲ ▲ │ │ ● ● │ ● ● ● ● ◯ ◯ ◯ ◯ ◯ ◯ │ ■ │ ▲ ▲ │ │ ● ● │ ● ● ● ● ◯ ◯ ◯ ◯ ◯ ◯ │ ■ │ ▲ ▲ │
                        │ ● ● │ ● ● ◯ ◯ ◯ ◯ ◯ ◯ ◯ ◯ │ □ │     │ │ ● ● │ ● ● ◯ ◯ ◯ ◯ ◯ ◯ ◯ ◯ │ □ │     │ │ ● ● │ ● ● ◯ ◯ ◯ ◯ ◯ ◯ ◯ ◯ │ □ │     │
                        │ ●   │ ● ◯ ◯ ◯ ◯ ◯ ◯ ◯ ◯ ◯ │ □ │     │ │ ●   │ ● ◯ ◯ ◯ ◯ ◯ ◯ ◯ ◯ ◯ │ □ │     │ │ ●   │ ● ◯ ◯ ◯ ◯ ◯ ◯ ◯ ◯ ◯ │ □ │     │
                        ╰─────────────────────────────────────╯ ╰─────────────────────────────────────╯ ╰─────────────────────────────────────╯
                         COINS : 2                               COINS : 2                               COINS : 2
                         
                         NEW DASHBOARD GRAPHICS :
                        ────────────────────────────────────────╴ISLANDS╶────────────────────────────────────────
                        ╭──────╴1╶──────╮ ╭──────╴2╶──────╮ ╭──────╴3╶──────╮ ╭──────╴4╶──────╮ ╭──────╴5╶──────╮
                        │ 0 : △ │ 0 : ● │ │ 0 : △ │ 0 : ● │ │ 0 : △ │ 0 : ● │ │ 0 : △ │ 0 : ● │ │ 0 : △ │ 0 : ● │
                        │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │
                        │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │
                        ╰───────────────╯ ╰───────────────╯ ╰───────────────╯ ╰───────────────╯ ╰───────────────╯
                        ╭─────╴12╶──────╮                                                       ╭──────╴6╶──────╮
                        │ 0 : △ │ 0 : ● │                                                       │ 0 : △ │ 0 : ● │
                        │ 0 : ● │ 0 : ● │                                                       │ 0 : ● │ 0 : ● │
                        │ 0 : ● │ 0 : ● │                                                       │ 0 : ● │ 0 : ● │
                        ╰───────────────╯                                                       ╰───────────────╯
                        ╭─────╴11╶──────╮ ╭─────╴10╶──────╮ ╭──────╴9╶──────╮ ╭──────╴8╶──────╮ ╭──────╴7╶──────╮
                        │ 0 : △ │ 0 : ● │ │ 0 : △ │ 0 : ● │ │ 0 : △ │ 0 : ● │ │ 0 : △ │ 0 : ● │ │ 0 : △ │ 0 : ● │
                        │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │
                        │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │
                        ╰───────────────╯ ╰───────────────╯ ╰───────────────╯ ╰───────────────╯ ╰───────────────╯
                        ────────────────────────────────────────────────────────╴BOARDS╶───────────────────────────────────────────────────────
                        ╭──────────────╴Giuseppe╶─────────────╮ ╭──────────────╴Giuseppe╶─────────────╮ ╭──────────────╴Giuseppe╶─────────────╮
                        │  E             H            P    T  │ │  E             H            P    T  │ │  E             H            P    T  │
                        │ ● ● │ ● ● ● ◯ ◯ ◯ ◯ ◯ ◯ ◯ │ ■ │ ▲ ▲ │ │ ● ● │ ● ● ● ◯ ◯ ◯ ◯ ◯ ◯ ◯ │ ■ │ ▲ ▲ │ │ ● ● │ ● ● ● ◯ ◯ ◯ ◯ ◯ ◯ ◯ │ ■ │ ▲ ▲ │
                        │ ● ● │ ● ● ◯ ◯ ◯ ◯ ◯ ◯ ◯ ◯ │ □ │ ▲ ▲ │ │ ● ● │ ● ● ◯ ◯ ◯ ◯ ◯ ◯ ◯ ◯ │ □ │ ▲ ▲ │ │ ● ● │ ● ● ◯ ◯ ◯ ◯ ◯ ◯ ◯ ◯ │ □ │ ▲ ▲ │
                        │ ● ● │ ● ● ● ● ◯ ◯ ◯ ◯ ◯ ◯ │ ■ │ ▲ ▲ │ │ ● ● │ ● ● ● ● ◯ ◯ ◯ ◯ ◯ ◯ │ ■ │ ▲ ▲ │ │ ● ● │ ● ● ● ● ◯ ◯ ◯ ◯ ◯ ◯ │ ■ │ ▲ ▲ │
                        │ ● ● │ ● ● ◯ ◯ ◯ ◯ ◯ ◯ ◯ ◯ │ □ │     │ │ ● ● │ ● ● ◯ ◯ ◯ ◯ ◯ ◯ ◯ ◯ │ □ │     │ │ ● ● │ ● ● ◯ ◯ ◯ ◯ ◯ ◯ ◯ ◯ │ □ │     │
                        │ ●   │ ● ◯ ◯ ◯ ◯ ◯ ◯ ◯ ◯ ◯ │ □ │     │ │ ●   │ ● ◯ ◯ ◯ ◯ ◯ ◯ ◯ ◯ ◯ │ □ │     │ │ ●   │ ● ◯ ◯ ◯ ◯ ◯ ◯ ◯ ◯ ◯ │ □ │     │
                        ╰─────────────────────────────────────╯ ╰─────────────────────────────────────╯ ╰─────────────────────────────────────╯
                         COINS : 2                               COINS : 2                               COINS : 2
                        
                        EFFECT1 = MONK
                        EFFECT2 = FARMER
                        EFFECT3 = HERALD
                        EFFECT4 = POSTMAN
                        EFFECT5 = GRANDMA
                        EFFECT6 = CENTAUR
                        EFFECT7 = JESTER
                        EFFECT8 = KNIGHT
                        EFFECT9 = FUNGARO
                        EFFECT10 = MINSTREL
                        EFFECT11 = PRINCESS
                        EFFECT12 = PICAROON
                        
                        ╭───────────────╴CHARACTERS╶────────────────╮
                        │ ╭────╴1╶────╮ ╭────╴3╶────╮ ╭────╴2╶────╮ │
                        │ │   MONK    │ │  GRANDMA  │ │  JESTER   │ │
                        │ │  ● ● ● ●  │ │  ✗ ✗ ✗ ✗  │ │   ● ● ●   │ │
                        │ │           │ │           │ │   ● ● ●   │ │
                        │ │ COST : 1  │ │ COST : 1  │ │  COST : 1 │ │
                        │ ╰───────────╯ ╰───────────╯ ╰───────────╯ │
                        ╰───────────────────────────────────────────╯
                        ╭────╴2╶────╮
                        │  JESTER   │
                        │   ● ● ●   │
                        │   ● ● ●   │
                        │  COST : 1 │
                        ╰───────────╯
                        ╭────╴3╶────╮ ╭────╴3╶────╮
                        │  GRANDMA  │ │  GRANDMA  │
                        │  ✗ ✗ ✗ ✗  │ │           │
                        │           │ │           │
                        │ COST : 1  │ │   ✗ ✗ ✗   │
                        ╰───────────╯ ╰───────────╯
                        ╭────╴2╶────╮
                        │  WIZARD   │ │  WIZARD   │ │  WIZARD   │ │  WIZARD   │
                        │  GREEN    │ │   BLUE    │ │  YELLOW   │ │   PINK    │
                        │           │
                        │           │
                        ╰───────────╯
                        NEW CLOUD GRAPHICS
                        ╭──────╴1╶──────╮
                        │     0 : ●     │
                        │ 0 : ● │ 0 : ● │
                        │ 0 : ● │ 0 : ● │
                        ╰───────────────╯
                        NEW ISLAND GRAPHICS
                        ╭──────╴1╶──────╮
                        │ 0 : △ │ 0 : ● │
                        │ 0 : ● │ 0 : ● │
                        │ 0 : ● │ 0 : ● │
                        ╰───────────────╯
                        NEW ARCHIPELAGO GRAPHICS
                        ╭──────╴1╶──────╮ ╭──────╴2╶──────╮ ╭──────╴3╶──────╮ ╭──────╴4╶──────╮ ╭──────╴5╶──────╮
                        │ 0 : △ │ 0 : ● │ │ 0 : △ │ 0 : ● │ │ 0 : △ │ 0 : ● │ │ 0 : △ │ 0 : ● │ │ 0 : △ │ 0 : ● │
                        │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │
                        │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │
                        ╰───────────────╯ ╰───────────────╯ ╰───────────────╯ ╰───────────────╯ ╰───────────────╯
                        ╭─────╴12╶──────╮                                                       ╭──────╴6╶──────╮
                        │ 0 : △ │ 0 : ● │                                                       │ 0 : △ │ 0 : ● │
                        │ 0 : ● │ 0 : ● │                                                       │ 0 : ● │ 0 : ● │
                        │ 0 : ● │ 0 : ● │                                                       │ 0 : ● │ 0 : ● │
                        ╰───────────────╯                                                       ╰───────────────╯
                        ╭─────╴11╶──────╮ ╭─────╴10╶──────╮ ╭──────╴9╶──────╮ ╭──────╴8╶──────╮ ╭──────╴7╶──────╮
                        │ 0 : △ │ 0 : ● │ │ 0 : △ │ 0 : ● │ │ 0 : △ │ 0 : ● │ │ 0 : △ │ 0 : ● │ │ 0 : △ │ 0 : ● │
                        │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │
                        │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │
                        ╰───────────────╯ ╰───────────────╯ ╰───────────────╯ ╰───────────────╯ ╰───────────────╯
                       
                                ╭──────╴1╶──────╮ ╭──────╴2╶──────╮ ╭──────╴3╶──────╮ ╭──────╴4╶──────╮
                                │ 0 : △ │ 0 : ● │ │ 0 : △ │ 0 : ● │ │ 0 : △ │ 0 : ● │ │ 0 : △ │ 0 : ● │
                                │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │
                                │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │
                                ╰───────────────╯ ╰───────────────╯ ╰───────────────╯ ╰───────────────╯
                        ╭─────╴11╶──────╮                                                       ╭──────╴5╶──────╮
                        │ 0 : △ │ 0 : ● │                                                       │ 0 : △ │ 0 : ● │
                        │ 0 : ● │ 0 : ● │                                                       │ 0 : ● │ 0 : ● │
                        │ 0 : ● │ 0 : ● │                                                       │ 0 : ● │ 0 : ● │
                        ╰───────────────╯                                                       ╰───────────────╯
                        ╭─────╴10╶──────╮ ╭──────╴9╶──────╮ ╭──────╴8╶──────╮ ╭──────╴7╶──────╮ ╭──────╴6╶──────╮
                        │ 0 : △ │ 0 : ● │ │ 0 : △ │ 0 : ● │ │ 0 : △ │ 0 : ● │ │ 0 : △ │ 0 : ● │ │ 0 : △ │ 0 : ● │
                        │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │
                        │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │
                        ╰───────────────╯ ╰───────────────╯ ╰───────────────╯ ╰───────────────╯ ╰───────────────╯
                        
                        ╭──────╴1╶──────╮ ╭──────╴2╶──────╮ ╭──────╴3╶──────╮ ╭──────╴4╶──────╮
                        │ 0 : △ │ 0 : ● │ │ 0 : △ │ 0 : ● │ │ 0 : △ │ 0 : ● │ │ 0 : △ │ 0 : ● │
                        │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │
                        │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │
                        ╰───────────────╯ ╰───────────────╯ ╰───────────────╯ ╰───────────────╯
                        ╭─────╴10╶──────╮                                     ╭──────╴5╶──────╮
                        │ 0 : △ │ 0 : ● │                                     │ 0 : △ │ 0 : ● │
                        │ 0 : ● │ 0 : ● │                                     │ 0 : ● │ 0 : ● │
                        │ 0 : ● │ 0 : ● │                                     │ 0 : ● │ 0 : ● │
                        ╰───────────────╯                                     ╰───────────────╯
                        ╭──────╴9╶──────╮ ╭──────╴8╶──────╮ ╭──────╴7╶──────╮ ╭──────╴6╶──────╮
                        │ 0 : △ │ 0 : ● │ │ 0 : △ │ 0 : ● │ │ 0 : △ │ 0 : ● │ │ 0 : △ │ 0 : ● │
                        │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │
                        │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │
                        ╰───────────────╯ ╰───────────────╯ ╰───────────────╯ ╰───────────────╯
                        
                                ╭──────╴1╶──────╮ ╭──────╴2╶──────╮ ╭──────╴3╶──────╮
                                │ 0 : △ │ 0 : ● │ │ 0 : △ │ 0 : ● │ │ 0 : △ │ 0 : ● │
                                │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │
                                │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │
                                ╰───────────────╯ ╰───────────────╯ ╰───────────────╯
                        ╭──────╴9╶──────╮                                     ╭──────╴4╶──────╮
                        │ 0 : △ │ 0 : ● │                                     │ 0 : △ │ 0 : ● │
                        │ 0 : ● │ 0 : ● │                                     │ 0 : ● │ 0 : ● │
                        │ 0 : ● │ 0 : ● │                                     │ 0 : ● │ 0 : ● │
                        ╰───────────────╯                                     ╰───────────────╯
                        ╭──────╴8╶──────╮ ╭──────╴7╶──────╮ ╭──────╴6╶──────╮ ╭──────╴5╶──────╮
                        │ 0 : △ │ 0 : ● │ │ 0 : △ │ 0 : ● │ │ 0 : △ │ 0 : ● │ │ 0 : △ │ 0 : ● │
                        │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │
                        │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │
                        ╰───────────────╯ ╰───────────────╯ ╰───────────────╯ ╰───────────────╯
                        
                        ╭──────╴1╶──────╮ ╭──────╴2╶──────╮ ╭──────╴3╶──────╮
                        │ 0 : △ │ 0 : ● │ │ 0 : △ │ 0 : ● │ │ 0 : △ │ 0 : ● │
                        │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │
                        │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │
                        ╰───────────────╯ ╰───────────────╯ ╰───────────────╯
                        ╭──────╴8╶──────╮                   ╭──────╴4╶──────╮
                        │ 0 : △ │ 0 : ● │                   │ 0 : △ │ 0 : ● │
                        │ 0 : ● │ 0 : ● │                   │ 0 : ● │ 0 : ● │
                        │ 0 : ● │ 0 : ● │                   │ 0 : ● │ 0 : ● │
                        ╰───────────────╯                   ╰───────────────╯
                        ╭──────╴7╶──────╮ ╭──────╴6╶──────╮ ╭──────╴5╶──────╮
                        │ 0 : △ │ 0 : ● │ │ 0 : △ │ 0 : ● │ │ 0 : △ │ 0 : ● │
                        │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │
                        │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │
                        ╰───────────────╯ ╰───────────────╯ ╰───────────────╯
                        
                                ╭──────╴1╶──────╮ ╭──────╴2╶──────╮
                                │ 0 : △ │ 0 : ● │ │ 0 : △ │ 0 : ● │
                                │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │
                                │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │
                                ╰───────────────╯ ╰───────────────╯
                        ╭──────╴7╶──────╮                   ╭──────╴3╶──────╮
                        │ 0 : △ │ 0 : ● │                   │ 0 : △ │ 0 : ● │
                        │ 0 : ● │ 0 : ● │                   │ 0 : ● │ 0 : ● │
                        │ 0 : ● │ 0 : ● │                   │ 0 : ● │ 0 : ● │
                        ╰───────────────╯                   ╰───────────────╯
                        ╭──────╴6╶──────╮ ╭──────╴5╶──────╮ ╭──────╴4╶──────╮
                        │ 0 : △ │ 0 : ● │ │ 0 : △ │ 0 : ● │ │ 0 : △ │ 0 : ● │
                        │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │
                        │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │
                        ╰───────────────╯ ╰───────────────╯ ╰───────────────╯
                        
                                 ╭──────╴1╶──────╮ ╭──────╴2╶──────╮
                                 │ 0 : △ │ 0 : ● │ │ 0 : △ │ 0 : ● │
                                 │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │
                                 │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │
                                 ╰───────────────╯ ╰───────────────╯
                        ╭──────╴6╶──────╮                   ╭──────╴3╶──────╮
                        │ 0 : △ │ 0 : ● │                   │ 0 : △ │ 0 : ● │
                        │ 0 : ● │ 0 : ● │                   │ 0 : ● │ 0 : ● │
                        │ 0 : ● │ 0 : ● │                   │ 0 : ● │ 0 : ● │
                        ╰───────────────╯                   ╰───────────────╯
                                 ╭──────╴5╶──────╮ ╭──────╴4╶──────╮
                                 │ 0 : △ │ 0 : ● │ │ 0 : △ │ 0 : ● │
                                 │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │
                                 │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │
                                 ╰───────────────╯ ╰───────────────╯
                                 
                                          ╭──────╴1╶──────╮
                                          │ 0 : △ │ 0 : ● │
                                          │ 0 : ● │ 0 : ● │
                        ╭──────╴5╶──────╮ │ 0 : ● │ 0 : ● │ ╭──────╴2╶──────╮
                        │ 0 : △ │ 0 : ● │ ╰───────────────╯ │ 0 : △ │ 0 : ● │
                        │ 0 : ● │ 0 : ● │                   │ 0 : ● │ 0 : ● │
                        │ 0 : ● │ 0 : ● │                   │ 0 : ● │ 0 : ● │
                        ╰───────────────╯                   ╰───────────────╯
                                 ╭──────╴4╶──────╮ ╭──────╴3╶──────╮
                                 │ 0 : △ │ 0 : ● │ │ 0 : △ │ 0 : ● │
                                 │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │
                                 │ 0 : ● │ 0 : ● │ │ 0 : ● │ 0 : ● │
                                 ╰───────────────╯ ╰───────────────╯
                                 
                                          ╭──────╴1╶──────╮
                                          │ 0 : △ │ 0 : ● │
                                          │ 0 : ● │ 0 : ● │
                        ╭──────╴4╶──────╮ │ 0 : ● │ 0 : ● │ ╭──────╴2╶──────╮
                        │ 0 : △ │ 0 : ● │ ╰───────────────╯ │ 0 : △ │ 0 : ● │
                        │ 0 : ● │ 0 : ● │                   │ 0 : ● │ 0 : ● │
                        │ 0 : ● │ 0 : ● │ ╭──────╴3╶──────╮ │ 0 : ● │ 0 : ● │
                        ╰───────────────╯ │ 0 : △ │ 0 : ● │ ╰───────────────╯
                                          │ 0 : ● │ 0 : ● │
                                          │ 0 : ● │ 0 : ● │
                                          ╰───────────────╯
                                          
                                          ╭──────╴1╶──────╮
                                          │ 0 : △ │ 0 : ● │
                                          │ 0 : ● │ 0 : ● │
                        ╭──────╴3╶──────╮ │ 0 : ● │ 0 : ● │ ╭──────╴2╶──────╮
                        │ 0 : △ │ 0 : ● │ ╰───────────────╯ │ 0 : △ │ 0 : ● │
                        │ 0 : ● │ 0 : ● │                   │ 0 : ● │ 0 : ● │
                        │ 0 : ● │ 0 : ● │                   │ 0 : ● │ 0 : ● │
                        ╰───────────────╯                   ╰───────────────╯
                             
        
                        """);
        game.addPlayer("Giuseppe");
        game.addPlayer("Daniele");
        game.addPlayer("Samuele");
        player1 = game.getPlayer(1);
        player2 = game.getPlayer(2);
        player3 = game.getPlayer(3);
        players.add(player1);
        players.add(player2);
        players.add(player3);
        archipelago = game.getBoard().getArchipelago();
    }

    @Test
    void printLogo() {
        graphic.printLogo();
    }

    @Test
    void printArchipelago() {
        graphic.printArchipelago(archipelago);
        archipelago.getSingleIsland(3).setTowerColor(Tower.BLACK);
        archipelago.getSingleIsland(3).addStudent(Student.BLUE);
        graphic.printArchipelago(archipelago);
        archipelago.getSingleIsland(4).setTowerColor(Tower.BLACK);
        archipelago.getSingleIsland(8).setTowerColor(Tower.WHITE);
        archipelago.getSingleIsland(4).addStudent(Student.BLUE);
        graphic.printArchipelago(archipelago);
        archipelago.mergeIslands(3,4);
        graphic.printArchipelago(archipelago);
        archipelago.getSingleIsland(4).setTowerColor(Tower.BLACK);
        archipelago.getSingleIsland(4).addStudent(Student.BLUE);
        archipelago.getSingleIsland(4).setStop(true);
        graphic.printArchipelago(archipelago);
        archipelago.mergeIslands(3,4);
        archipelago.getSingleIsland(1).setTowerColor(Tower.GREY);
        graphic.printArchipelago(archipelago);
        archipelago.mergeIslands(3,4);
        graphic.printArchipelago(archipelago);
    }

    @Test
    void printPlances() {
        graphic.printPlances(players);
        for(Student student : Student.values())
            player1.getPlance().addStudentHall(student);
        player1.getPlance().addStudentHall(Student.YELLOW);
        player1.getPlance().addStudentHall(Student.RED);
        player1.getPlance().addStudentHall(Student.BLUE);
        player1.getPlance().addStudentHall(Student.BLUE);
        player1.getPlance().addProfessor(Professor.BLUE_UNICORN);
        player1.getPlance().removeStudentEntrance(player1.getPlance().getEntrance().get(1));
        player1.addCoins();
        player1.getPlance().removeTower();
        graphic.printPlances(players);
    }

    @Test
    void printAssistants() {
        graphic.printAssistants(player1.getAssistantCards(), Wizard.WIZARD_GREEN);
        player1.removeAssistant(Assistant.EAGLE);
        graphic.printAssistants(player1.getAssistantCards(), Wizard.WIZARD_YELLOW);
        player1.removeAssistant(Assistant.OSTRICH);
        graphic.printAssistants(player1.getAssistantCards(), Wizard.WIZARD_PINK);
        player1.removeAssistant(Assistant.DOG);
        graphic.printAssistants(player1.getAssistantCards(), Wizard.WIZARD_BLUE);
    }

    @Test
    void printClouds() {
        graphic.printClouds(game.getBoard().getClouds());
        game.getBoard().getClouds().get(1).setChoosen(true);
        graphic.printClouds(game.getBoard().getClouds());
        game.getBoard().getClouds().get(0).setChoosen(true);
        graphic.printClouds(game.getBoard().getClouds());
    }

    @Test
    void printCharacters() {
        ArrayList<Characters> characters = new ArrayList<>();
        Effect1 effect1 = new Effect1();
        Characters characters1 = new Characters(effect1);
        Effect2 effect2 = new Effect2();
        Characters characters2 = new Characters(effect2);
        Effect5 effect5 = new Effect5();
        Effect7 effect7 = new Effect7();
        Effect11 effect11 = new Effect11();
        Effect12 effect12 = new Effect12();
        Characters characters12 = new Characters(effect12);

        characters.add(characters1);
        characters.add(characters2);
        characters.add(new Characters(effect5));
        characters.add(new Characters(effect7));
        characters.add(new Characters(effect11));
        characters.add(characters12);
        if(!game.getBoard().getCharacters().get(0).getEffect().getName().equals(effect1.getName()))
            effect1.inizialize(game);
        if(!game.getBoard().getCharacters().get(0).getEffect().getName().equals(effect7.getName()))
            effect7.inizialize(game);
        if(!game.getBoard().getCharacters().get(0).getEffect().getName().equals(effect11.getName()))
            effect11.inizialize(game);

        graphic.printCharacters(characters, game.getEffectHandler());
        game.getEffectHandler().removeislandstop();
        game.getEffectHandler().removeStudentFromEffect1students(game.getEffectHandler().getEffect1students().get(1));
        game.getEffectHandler().removeStudentFromEffect7(game.getEffectHandler().getEffect7students().get(1));
        game.getEffectHandler().removeStudentFromEffect11students(game.getEffectHandler().getEffect11students().get(1));
        characters1.setUsed(true);
        graphic.printCharacters(characters, game.getEffectHandler());
        game.getEffectHandler().removeislandstop();
        game.getEffectHandler().removeStudentFromEffect1students(game.getEffectHandler().getEffect1students().get(1));
        game.getEffectHandler().removeStudentFromEffect7(game.getEffectHandler().getEffect7students().get(1));
        game.getEffectHandler().removeStudentFromEffect11students(game.getEffectHandler().getEffect11students().get(1));
        graphic.printCharacters(characters, game.getEffectHandler());
        game.getEffectHandler().removeislandstop();
        game.getEffectHandler().removeStudentFromEffect1students(game.getEffectHandler().getEffect1students().get(1));
        game.getEffectHandler().removeStudentFromEffect7(game.getEffectHandler().getEffect7students().get(1));
        game.getEffectHandler().removeStudentFromEffect11students(game.getEffectHandler().getEffect11students().get(1));
        characters12.setUsed(true);
        graphic.printCharacters(characters, game.getEffectHandler());
        game.getEffectHandler().removeislandstop();
        game.getEffectHandler().removeStudentFromEffect1students(game.getEffectHandler().getEffect1students().get(0));
        game.getEffectHandler().removeStudentFromEffect11students(game.getEffectHandler().getEffect11students().get(0));
        characters2.setUsed(true);
        graphic.printCharacters(characters, game.getEffectHandler());
    }

    @Test
    void printWizards() {
        graphic.printWizards(game.getWizardAvailable());
    }
}