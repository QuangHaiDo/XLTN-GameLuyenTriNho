package Game;

import Objects.Card;
import Objects.CardList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class GamePlay {

    public GamePlay() {

        CardList cardList = new CardList();
        Scanner sc = new Scanner(System.in);
        int score = 0;

        while(true) {

            /**
             *  Generate new cardList, number of card = score +1
             */
            cardList.generateCardList(score+1);
            cardList.showCardList();

            /**
             * Choose card to open with arr element order
             */
            ArrayList<Integer> arr = new ArrayList<Integer>();
            for (int i=0;i<score+1;i++){
                arr.add(i);
            }

            Collections.shuffle(arr); //after this, arr elements have a random order

            for(int e:arr){
                System.out.println("Card at:"+e);
                /**
                 * player answering
                 */
                /*
                int guestNumber = sc.nextInt();
                String guestSuit = sc.next();
                String guestColor = sc.next();

*/
                String playerAnswer = sc.nextLine();//player answer
                /*
                if (cardList.getList().get(e).compare(guestNumber,guestSuit.toLowerCase(),guestColor.toLowerCase())){
                    System.out.println("Correct !!!");
                }*/
                Card _this = cardList.getList().get(e);
                if (playerAnswer.indexOf(_this.getValue().toString() ) >-1 &&
                        playerAnswer.indexOf(_this.getSuit().toString() )>-1 &&
                        playerAnswer.indexOf(_this.getColor().toString())>-1)
                {
                    System.out.println("Correct !!!");
                }
                else {

                    /** Xu ly sai va thoat game*/

                    System.out.println("Wrong !!!");
                    System.out.println("Score: "+score);
                    System.exit(0);

                }
            }

            score++;
        }
    }
}
