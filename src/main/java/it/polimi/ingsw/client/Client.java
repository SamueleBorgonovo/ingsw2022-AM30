package it.polimi.ingsw.client;

public class Client {

    /*
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        Client client = new Client();

        if(args.length > 0) {
            ArrayList<String> arguments = new ArrayList<>(Arrays.asList(args));
            if(arguments.contains("-cli")) client.setClientConfig(ClientConfig.CLI);
            else if(arguments.contains("-gui")) client.setClientConfig(ClientConfig.GUI);
            if((arguments.contains("-ip") && (arguments.contains("-port")))) {
                try {
                    client.setServerHandler(arguments.get(arguments.indexOf("-ip")+1), Integer.parseInt(arguments.get(arguments.indexOf("-port")+1)));
                } catch (Exception exception) {
                    String[] arg = new String[0];
                    main(arg);
                }
            }
        }

        try {
            client.run();
        } catch (Exception exception) {
            System.out.println();
            System.out.println("|!| I wasn't able to connect to the specified ip-port address. Press ENTER to try again.");
            stdin.nextLine();

            for (int i = 0; i < 50; ++i) System.out.println();

            main(args);
        }
    }

    public void run(){
        Scanner stdin = new Scanner(System.in);

        if(whichUI == ClientConfig.NOPREFERENCE){
            System.out.print("Would you like to start the GUI interface? -> ");
            if (!stdin.nextLine().toLowerCase().startsWith("n")) whichUI = it.polimi.ingsw.client.Client.ClientConfig.GUI;
            else whichUI = it.polimi.ingsw.client.Client.ClientConfig.CLI;
        }

        if(whichUI == ClientConfig.GUI){
            GUIstarter.setOwner(this);
            GUIstarter.main();
        } else {
            while(serverHandler == null) {
                try{
                    String ip;
                    int port;

                    System.out.print("Type the server ip -> ");
                    ip = stdin.nextLine();

                    System.out.print("now type the port number -> ");
                    port = stdin.nextInt();

                    setServerHandler(ip, port);

                } catch (IOException e) {
                    System.out.println();
                    System.out.println("|!| I wasn't able to connect to the specified ip-port address. Please try again.");
                    stdin.nextLine();
                }
            }
            System.out.println("\n\n\n\n\n\n");

            this.lessago = true;
            viewManager = new CliViewManager(TurnState.CHOOSINGLC);
            nextView = new BeginningOfGameView();
            runViewStateMachine();
        }
    }
    */
}
