package it.polimi.ingsw.model.board;

    public class MotherNature {
        private int islandID;

        //Quando madrenatura viene creata è posizionata in un isola random. Per semplicità preferisco far decidere l'isola prima
        //della sua creazione, senza avere un riferimento a tutte le isole in questa classe
        public MotherNature(int islandID) {
            this.islandID = islandID;
        }

        public int isOn() {
            return islandID;
        }

        public void move(int islandID) {
            if (islandID == 1 && this.islandID == 12) {
                this.islandID = islandID;
            } else {
                if (this.islandID+1 == islandID && islandID<=12 && islandID>=1) {
                    this.islandID = islandID;
                }
            }
        }
    }

