package it.polimi.ingsw.server;

public class Server {
    /*
    public static void main(String[] args){
        Scanner stdin = new Scanner(System.in);


        try {
            Server server = new Server();

            if(args.length >0) {
                ArrayList<String> arguments = new ArrayList<>(Arrays.asList(args));
                if(arguments.contains("-port"))  server.setServerSocket(Integer.parseInt(arguments.get(arguments.indexOf("-port")+1)));
            }

            while(server.getServerSocket() == null) {
                System.out.print("Please type the port number for your server -> ");
                int port = stdin.nextInt();

                server.setServerSocket(port);
            }

            System.out.println("To debug with this machine, type \"localhost\" or \"127.0.0.1\" and the PORT specified below in the client instance.\n");
            System.out.println("IP: " + Inet4Address.getLocalHost().getHostAddress());
            System.out.println("Server listening on port: " + server.serverSocket.getLocalPort());
            server.run();
        } catch (IOException e){
            System.out.println();
            System.out.println("|!| I wasn't able to open the specified port. Press ENTER to try again.");
            stdin.nextLine();

            for (int i = 0; i < 50; ++i) System.out.println();

            String[] arg = new String[0];
            main(arg);
        }
    }

    private void run(){
        while(isActive){
            try {
                Socket socket = serverSocket.accept();
                Connection connection = new Connection(socket, this);
                new Thread(connection).start();
                System.out.println("Connected to: IP=" + socket.getInetAddress());
            } catch (SocketException e){
                System.out.println("An error occurred while initializing a new Connection...");
                run();
            } catch (IOException e) {
                run();
            } catch (Exception e){
                System.out.println("An fatal error occurred while accepting or running a new Connection...");
                e.printStackTrace();
            }
        }
    }
    */
}
