package it.polimi.ingsw.model;

    public class MotherNature {
        private int islandID;

        //Quando madrenatura viene creata è posizionata in un isola random. Per semplicità preferisco far decidere l'isola prima
        //della sua creazione, senza avere un riferimento a tutte le isole in questa classe
        public MotherNature(int islandID){
            this.islandID=islandID;
        }

        public int isOn(){
            return islandID;
        }

        public void move(int islandID){
            if(islandID >= 1 && islandID <= 12)
                this.islandID=islandID;
        }
    }

