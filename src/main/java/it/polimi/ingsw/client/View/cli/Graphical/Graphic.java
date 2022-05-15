package it.polimi.ingsw.client.View.cli.Graphical;

import it.polimi.ingsw.model.board.Archipelago;
import it.polimi.ingsw.model.board.Characters;
import it.polimi.ingsw.model.board.Cloud;
import it.polimi.ingsw.model.board.Island;
import it.polimi.ingsw.model.game.EffectHandlerInterface;
import it.polimi.ingsw.model.game.Student;
import it.polimi.ingsw.model.player.Assistant;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.Professor;
import it.polimi.ingsw.model.player.Wizard;

import java.util.ArrayList;

public class Graphic {
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
                                                                           
                 ◾♦ ▲ △ □ ■ ◌ ◯ ● ♥ ─ ─ ● ╴╶ ✗
                        │  ●྾1╳╭ ╮ ╯ ╰│─
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

                        """);

    public void printLogo() {
        String logo = """
                           
                     ▄▄▄▄▄▄▄▄▄▄▄▄▄▄            ▄▄
                  ▄█▀▀  ▓██    ▀▀██           ▓███                        ▄
                 ▐█▀   ▓██▌  ▄▄▄  ▀                                      ▓█
                 ██    ▓██████▀▀█   ▓███▄████ ▓██▌   ▄██▀███ ▄███ ▓███ ▓████▌▄████ ▀███ ▓█▀▀▀██
                 ▀██   ▓████    ▀    ▓██▀ █▀   ▓█▌  ▓█▌  ███  ▓███▀▓██  ▓██    ▀███  ▓█ ▓███▄▄
                   ▀█▌ ▓███        ▄ ▓██       ▓█▌ ▓██▌ ▄███  ▓██  ▓██  ▓██     ▀███▄█   ▀█████
                       ▓██▌      ▄█▌ ▓██       ▓██ ▀████▀███▄ ▓██  ▓██▄ ▓██▄▄     ▓██   ▓▌  ▄██
                      ▄████▄▄▄▄███▀  ▀▀         ▀▀   ▀▀   ▀▀  ▀▀    ▀▀▀  ▀▀▀      ▓█    ▀▀▀▀▀
                                                                                ▄██
                                                                                ███   █
                                                                                ▀▀███▀▀
        """;
        System.out.println(logo);
    }

    public void printArchipelago(Archipelago archipelago) {
        //for (int i = 0; i < 50; ++i) System.out.println();
        ArrayList<Island> islands = archipelago.getIslands();
        int numOfIslands = archipelago.getNumOfIslands();
        int i;

        System.out.print("╭───");
        for(i=0; i<numOfIslands-2; i++)
            System.out.print("────");
        System.out.print("╴ISLANDS╶");
        for(i=0; i<numOfIslands-2; i++)
            System.out.print("────");
        System.out.println("───╮");
        System.out.print("│");
        for(Island island : islands) {
            if(archipelago.getMothernature().isOn() == island.getIslandID()) {
                if(island.getIslandID()>1 && island.getIslandID()<10)
                    System.out.print(Colors.ANSI_RED.getCode() + "    " + island.getIslandID() + Colors.ANSI_RESET.getCode());
                else
                    System.out.print(Colors.ANSI_RED.getCode() + "   " + island.getIslandID() + Colors.ANSI_RESET.getCode());
            } else {
                if(island.getIslandID()>1 && island.getIslandID()<10)
                    System.out.print("    " + island.getIslandID());
                else
                    System.out.print("   " + island.getIslandID());
            }
            if(island.isStop())
                System.out.print(" ✗ ");
            else
                System.out.print("   ");
        }
        System.out.println("│");
        for(Island island : islands){
            System.out.print("│ " + island.getNumOfTowers() + " : ");
            if(island.getTowerColor()!=null)
                System.out.print(Colors.values()[island.getTowerColor().ordinal()+5].getCode() + "▲ " + Colors.ANSI_RESET.getCode());
            else
                System.out.print("△ ");
        }
        System.out.println("│");
        for(Student student : Student.values()){
            for(Island island : islands) {
                i=0;
                for(Student s : island.getStudents())
                    if(s.ordinal()== student.ordinal())
                        i++;
                System.out.print("│ " + i + " : ");
                System.out.print(Colors.values()[student.ordinal()].getCode() + "● " + Colors.ANSI_RESET.getCode());
            }
            System.out.println("│");
        }
        System.out.print("╰───────");
        for(i=1; i<numOfIslands; i++)
            System.out.print("────────");
        System.out.println("╯");
    }

    public void printPlances(ArrayList<Player> players){
        int i;
        int studentEntrance = 0;

        for(Player player : players){
            System.out.print("╭");
            for(i=0; i<(35 - player.getNickname().length())/2; i++)
                System.out.print("─");
            System.out.print("╴" + player.getNickname() + "╶");
            for(i=0; i<(35 - player.getNickname().length())/2 + (37 - player.getNickname().length())%2; i++)
                System.out.print("─");
            System.out.print("╮ ");
        }
        System.out.println();
        for(i=0; i< players.size();i++)
            System.out.print("│  E             H            P    T  │ ");
        System.out.println();
        for(Student student : Student.values()) {
            for (Player player : players) {
                System.out.print("│ ");
                if (studentEntrance < player.getPlance().getEntrance().size())
                    System.out.print(Colors.values()[player.getPlance().getEntrance().get(studentEntrance).ordinal()].getCode() + "● " + Colors.ANSI_RESET.getCode());
                else
                    System.out.print("  ");
                if (studentEntrance + 1 < player.getPlance().getEntrance().size())
                    System.out.print(Colors.values()[player.getPlance().getEntrance().get(studentEntrance + 1).ordinal()].getCode() + "●" + Colors.ANSI_RESET.getCode());
                else
                    System.out.print(" ");
                System.out.print(" │ ");
                for(i=0; i<10; i++){
                    if(i < player.getPlance().getNumberOfStudentHall(student))
                        System.out.print(Colors.values()[student.ordinal()].getCode() + "● " + Colors.ANSI_RESET.getCode());
                    else
                        System.out.print("◯ ");
                }
                System.out.print("│ ");
                if(player.getPlance().getProfessors().contains(Professor.values()[student.ordinal()]))
                    System.out.print(Colors.values()[student.ordinal()].getCode() + "■ " + Colors.ANSI_RESET.getCode());
                else
                    System.out.print("□ ");
                System.out.print("│ ");
                if (studentEntrance < player.getPlance().getNumOfTowers())
                    System.out.print(Colors.values()[player.getPlance().getTower().ordinal() + 5].getCode() + "▲ " + Colors.ANSI_RESET.getCode());
                else
                    System.out.print("  ");
                if (studentEntrance + 1 < player.getPlance().getNumOfTowers())
                    System.out.print(Colors.values()[player.getPlance().getTower().ordinal() + 5].getCode() + "▲ " + Colors.ANSI_RESET.getCode());
                else
                    System.out.print("  ");
                System.out.print("│ ");
            }
            System.out.println();
            studentEntrance += 2;
        }
        for(Player player : players)
            System.out.print("╰─────────────────────────────────────╯ ");
        System.out.println();
        for(Player player : players)
            if(player.getCoins()>=0)
                System.out.print("COINS : " + player.getCoins() + "                               ");
        if(players.get(0).getCoins()>=0)
            System.out.println();
        System.out.println();
    }

    public void printAssistants(ArrayList<Assistant> assistants, Wizard wizard){
        int i;

        for(i=1; i<=assistants.size();i++){
            if(i<10)
                System.out.print("╭────╴" + i + "╶────╮ ");
            else
                System.out.print("╭───╴" + i + "╶────╮ ");
        }
        System.out.println();
        for(Assistant assistant : assistants){
            System.out.print("│");
            for(i=0; i<(11-assistant.toString().length())/2;i++)
                System.out.print(" ");
            if(wizard.equals(Wizard.WIZARD_GREEN))
                System.out.print(Colors.values()[wizard.ordinal()].getCode() + assistant + Colors.ANSI_RESET.getCode());
            else
                System.out.print(Colors.values()[wizard.ordinal() + 1].getCode() + assistant + Colors.ANSI_RESET.getCode());
            for(i=0; i<(11-assistant.toString().length())/2 + (11-assistant.toString().length())%2;i++)
                System.out.print(" ");
            System.out.print("│ ");
        }
        System.out.println();
        for(i=0; i<assistants.size();i++)
            System.out.print("│           │ ");
        System.out.println();
        for(Assistant assistant : assistants){
            if(assistant.getValue()<10)
                System.out.print("│  VAL : " + assistant.getValue() + "  │ ");
            else
                System.out.print("│  VAL : " + assistant.getValue() + " │ ");
        }
        System.out.println();
        for(Assistant assistant : assistants)
            System.out.print("│  MOV : " + assistant.getMovement() + "  │ ");
        System.out.println();
        for(i=0; i<assistants.size();i++)
            System.out.print("╰───────────╯ ");
        System.out.println();
    }

    public void printClouds(ArrayList<Cloud> clouds){
        int i;

        System.out.print("╭────");
        for(i=0; i<clouds.size()-2; i++)
            System.out.print("────");
        System.out.print("╴CLOUDS╶");
        for(i=0; i<clouds.size()-2; i++)
            System.out.print("────");
        System.out.println("───╮");
        System.out.print("│");
        for(Cloud cloud : clouds) {
            if(cloud.isChoosen()) {
                if(cloud.getCloudID()>1 && cloud.getCloudID()<10)
                    System.out.print(Colors.ANSI_WHITE.getCode() + "    " + cloud.getCloudID() + "   " + Colors.ANSI_RESET.getCode());
                else
                    System.out.print(Colors.ANSI_WHITE.getCode() + "   " + cloud.getCloudID() + "   " + Colors.ANSI_RESET.getCode());
            } else {
                if(cloud.getCloudID()>1 && cloud.getCloudID()<10)
                    System.out.print("    " + cloud.getCloudID() + "   ");
                else
                    System.out.print("   " + cloud.getCloudID() + "   ");
            }
        }
        System.out.println("│");
        for(Student student : Student.values()){
            for(Cloud cloud : clouds) {
                if(cloud.isChoosen()) {
                    System.out.print("│");
                    System.out.print(Colors.ANSI_WHITE.getCode() + " 0 : ● " + Colors.ANSI_RESET.getCode());
                } else {
                    i = 0;
                    for (Student s : cloud.getStudents())
                        if (s.ordinal() == student.ordinal())
                            i++;
                    System.out.print("│ " + i + " :");
                    System.out.print(Colors.values()[student.ordinal()].getCode() + " ● " + Colors.ANSI_RESET.getCode());
                }
            }
            System.out.println("│");
        }
        System.out.print("╰───────");
        for(i=1; i<clouds.size(); i++)
            System.out.print("────────");
        System.out.println("╯");
    }

    public void printCharacters(ArrayList<Characters> characters, EffectHandlerInterface effectHandler){
        int i;

        for(i=1; i<= characters.size();i++)
            System.out.print("╭────╴" + i + "╶────╮ ");
        System.out.println();

        for(Characters character : characters){
            System.out.print("│");
            for(i=0; i<(11-character.getEffect().getName().length())/2;i++)
                System.out.print(" ");
            System.out.print(Colors.values()[character.getCost() + 10].getCode() + character.getEffect().getName() + Colors.ANSI_RESET.getCode());
            for(i=0; i<(11-character.getEffect().getName().length())/2 + (11-character.getEffect().getName().length())%2;i++)
                System.out.print(" ");
            System.out.print("│ ");
        }
        System.out.println();

        for(Characters character : characters){
            if("JESTER".equals(character.getEffect().getName())){
                System.out.print("│   ");
                for(i=0;i<3;i++)
                    System.out.print(Colors.values()[effectHandler.getEffect7students().get(i).ordinal()].getCode() + "● " + Colors.ANSI_RESET.getCode());
                System.out.print("  │ ");
            } else if("MONK".equals(character.getEffect().getName()) && effectHandler.getEffect1students().size()!=0){
                System.out.print("│");
                for(i=0; i<6-effectHandler.getEffect1students().size();i++)
                    System.out.print(" ");
                for(Student student : effectHandler.getEffect1students())
                    System.out.print(Colors.values()[student.ordinal()].getCode() + "● " + Colors.ANSI_RESET.getCode());
                for(i=0; i<5-effectHandler.getEffect1students().size();i++)
                    System.out.print(" ");
                System.out.print("│ ");
            } else if("PRINCESS".equals(character.getEffect().getName()) && effectHandler.getEffect11students().size()!=0){
                System.out.print("│");
                for(i=0; i<6-effectHandler.getEffect11students().size();i++)
                    System.out.print(" ");
                for(Student student : effectHandler.getEffect11students())
                    System.out.print(Colors.values()[student.ordinal()].getCode() + "● " + Colors.ANSI_RESET.getCode());
                for(i=0; i<5-effectHandler.getEffect11students().size();i++)
                    System.out.print(" ");
                System.out.print("│ ");
            } else if("GRANDMA".equals(character.getEffect().getName()) && effectHandler.getNumofislandstops()!=0){
                System.out.print("│");
                for(i=0; i<6-effectHandler.getNumofislandstops();i++)
                    System.out.print(" ");
                for(i=0; i<effectHandler.getNumofislandstops();i++)
                    System.out.print("✗ ");
                for(i=0; i<5-effectHandler.getNumofislandstops();i++)
                    System.out.print(" ");
                System.out.print("│ ");
            }
            else
                System.out.print("│           │ ");
        }
        System.out.println();

        for(Characters character : characters){
            if("JESTER".equals(character.getEffect().getName())){
                System.out.print("│   ");
                for(i=3;i<6 && effectHandler.getEffect7students().size()!=0;i++) {
                    if (i < effectHandler.getEffect7students().size())
                        System.out.print(Colors.values()[effectHandler.getEffect7students().get(i).ordinal()].getCode() + "● " + Colors.ANSI_RESET.getCode());
                    else
                        System.out.print("  ");
                }
                System.out.print("  │ ");
            }
            else
                System.out.print("│           │ ");
        }
        System.out.println();

        for(Characters character : characters)
                System.out.print("│  COST : " + character.getCost() + " │ ");
        System.out.println();
        for(i=0; i<characters.size();i++)
            System.out.print("╰───────────╯ ");
        System.out.println();
    }
}
