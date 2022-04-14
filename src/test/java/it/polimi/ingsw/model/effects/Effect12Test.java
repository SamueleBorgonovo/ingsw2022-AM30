package it.polimi.ingsw.model.effects;

class Effect12Test {

    /*@Test
    void effect() {
        int IslandID;
        final int gameID=1;
        final GameMode gameMode = null;
        MotherNature mothernature = null;
        ArrayList<Player> listOfPlayers = new ArrayList<>();
        GameState gameState = null;
        ArrayList<Student> studentbag = new ArrayList<>();
        ArrayList<Student> studentonisland = new ArrayList<>();
        Island island = new Island(1);
        ArrayList<Island> islands = new ArrayList<>();
        islands.add(island);
        Archipelago archipelago = new Archipelago();
        final Board board = new Board(null,archipelago,null);


        Game game = new Game(gameID,gameMode.SIMPLEMODE, , board);

        Plance plance1 = new Plance(Tower.WHITE,8);
        Player player1 = new Player(null, null);
        game.addPlayer(player1, player1.getPlayerID());

        Plance plance2 = new Plance(Tower.BLACK,8);
        Player player2 = new Player(null, null);
        game.addPlayer(player2, player2.getPlayerID());

        //Set hall player1
        player1.getPlance().addStudentHall(Student.BLUE);
        player1.getPlance().addStudentHall(Student.RED);
        player1.getPlance().addStudentHall(Student.RED);

        //Set hall player2
        player2.getPlance().addStudentHall(Student.BLUE);
        player2.getPlance().addStudentHall(Student.BLUE);
        player2.getPlance().addStudentHall(Student.BLUE);
        player2.getPlance().addStudentHall(Student.PINK);

        Effect12 effect12 = new Effect12();

        int bagsize=game.getBoard().getNumOfStudentsBag();
        effect12.effect(game,player1.getPlayerID());

        int var=1;
        //Test that choosedstudent is removed from player1's hall
        if(player1.getPlance().getNumberOfStudentHall(Student.BLUE)!=0)
            var=-1;
        //Test that choosedstudent is removed from player2's hall
        if(player2.getPlance().getNumberOfStudentHall(Student.BLUE)!=0)
            var=-2;

        //Test that bag is filled with students
        if(game.getBoard().getNumOfStudentsBag()!= bagsize+4)
            var=-3;

        //Test that not choosedstudent are not changed in player1's hall
        if(player1.getPlance().getNumberOfStudentHall(Student.RED)!=2)
            var=-5;
        //Test that not choosedstudent are not changed in player2's hall
        if(player2.getPlance().getNumberOfStudentHall(Student.PINK)!=1)
            var=-6;

        assertEquals(1,var);
    }

    @Test
    public void TestEffect02(){
        int IslandID;
        final int gameID=1;
        final GameMode gameMode = null;
        VerifyType verifyType = null;
        MotherNature mothernature = null;
        ArrayList<Player>listOfPlayers = new ArrayList<>();
        GameState gameState = null;
        ArrayList<Student> studentbag = new ArrayList<>();
        ArrayList<Student> studentonisland = new ArrayList<>();
        Island island = new Island(1);
        ArrayList<Island> islands = new ArrayList<>();
        islands.add(island);
        Archipelago archipelago = new Archipelago();
        final Board board = new Board(null,archipelago,null);


        Game game = new Game(gameID,gameMode.SIMPLEMODE, , board);

        Plance plance1 = new Plance(Tower.WHITE,6);
        Player player1 = new Player(null, null);
        game.addPlayer(player1, player1.getPlayerID());

        Plance plance2 = new Plance(Tower.BLACK,6);
        Player player2 = new Player(null, null);
        game.addPlayer(player2, player2.getPlayerID());

        Plance plance3 = new Plance(Tower.GREY,6);
        Player player3 = new Player(null, null);
        game.addPlayer(player3, player3.getPlayerID());

        //Set hall player1
        player1.getPlance().addStudentHall(Student.BLUE);
        player1.getPlance().addStudentHall(Student.RED);
        player1.getPlance().addStudentHall(Student.RED);

        //Set hall player2
        player2.getPlance().addStudentHall(Student.BLUE);
        player2.getPlance().addStudentHall(Student.BLUE);
        player2.getPlance().addStudentHall(Student.BLUE);
        player2.getPlance().addStudentHall(Student.PINK);
        player2.getPlance().addStudentHall(Student.BLUE);


        //Set hall player3
        player3.getPlance().addStudentHall((Student.BLUE));
        player3.getPlance().addStudentHall((Student.BLUE));
        player3.getPlance().addStudentHall((Student.YELLOW));
        player3.getPlance().addStudentHall((Student.GREEN));

        Effect12 effect12 = new Effect12();

        int bagsize = game.getBoard().getNumOfStudentsBag();
        effect12.effect(game,player1.getPlayerID());

        int var=1;
        //Test that choosedstudent is removed from player1's hall
        if(player1.getPlance().getNumberOfStudentHall(Student.BLUE)!=0)
            var=-1;
        //Test that 3 choosedstudent is removed from player2's hall
        if(player2.getPlance().getNumberOfStudentHall(Student.BLUE)!=1)
            var=-2;
        //Test that choosedstudent is removed from player3's hall
        if(player3.getPlance().getNumberOfStudentHall(Student.BLUE)!=0)
            var=-3;
        //Test that bag is filled with removed students
        if(game.getBoard().getNumOfStudentsBag() != bagsize+6)
            var=-4;

        //Test that not choosedstudent are not changed in player1's hall
        if(player1.getPlance().getNumberOfStudentHall(Student.RED)!=2)
            var=-6;
        //Test that not choosedstudent are not changed in player2's hall
        if(player2.getPlance().getNumberOfStudentHall(Student.PINK)!=1)
            var=-7;
        if(player2.getPlance().getNumberOfStudentHall(Student.BLUE)!=1)
            var=-8;
        //Test that not choosedstudent are not changed in player3's hall
        if(player3.getPlance().getNumberOfStudentHall(Student.GREEN)!=1)
            var=-9;
        if(player3.getPlance().getNumberOfStudentHall(Student.YELLOW)!=1)
            var=-10;
        assertEquals(1,var);
    }*/
}