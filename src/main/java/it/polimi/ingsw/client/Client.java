package it.polimi.ingsw.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    ObjectOutputStream outputStream;
    ObjectInputStream input;
    private static int port;

    public void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner stdin = new Scanner(System.in);
        Client client = new Client();
        //Va chiesta se CLI o GUI
        if(!setup()) {
            System.out.println("Connesione non riuscita");
         }

        try {
            //client.run();
        } catch (Exception exception) {
            System.out.println();
            System.out.println("|!| I wasn't able to connect to the specified ip-port address. Press ENTER to try again.");
            stdin.nextLine();

            for (int i = 0; i < 50; ++i) System.out.println();

            main(args);
        }
    }
    public boolean setup() throws IOException, ClassNotFoundException {
        Scanner stdin = new Scanner(System.in);
        String ip;
        System.out.println("Please insert the ip-addres");
        ip = stdin.nextLine();
        System.out.println("Please insert port");
        int port;
        port=stdin.nextInt();
        Socket socket;
        try{
            socket = new Socket(ip, port);
        } catch (IOException e) {
            return false;
        }

        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
        receiveMessage();

        return true;
    }

    public void receiveMessage() throws IOException, ClassNotFoundException {
        while(true){
            Object messageFromServer = input.readObject();
            //view.((Message) messageFromServer).action();
        }
    }

    /*
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
