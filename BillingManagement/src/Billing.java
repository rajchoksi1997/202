import DB.BillDB;
import DB.CardDB;
import DB.CartDB;
import DB.InventoryDB;
import Model.Card;
import Model.Cart;
import Model.Inventory;
import FileHandler.FileHandler;
import Model.Item.CartItem;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.util.logging.Logger;

public class Billing {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger(Billing.class.getName());
        String inventoryFileName = "src/inventory.csv";
        Inventory inventory = Inventory.getInstance();
        InventoryDB inventoryDB = new InventoryDB();
        CardDB cardDB = new CardDB();
        CartDB cartDB = new CartDB();
        BillDB billDB = new BillDB();
        Card cards = Card.getInstance();
        Cart cart = Cart.getInstance();
        FileHandler fileHandler = new FileHandler();

        /*Create Inventory*/
        logger.info("Starting to create inventory");
        try{
            logger.info("Processing Inventory.csv file");

            List<List<String>> inventoryRecords = fileHandler.read(inventoryFileName);
            logger.info("Inventory.csv read successfully");

            int index = inventoryDB.createInventory(inventory,inventoryRecords);

            cards = cardDB.addCards(cards,index, inventoryRecords);
            logger.info("done Processing Inventory.csv file");
        }catch (Exception e){
            logger.severe("Error processing Inventory.csv ");
            throw new RuntimeException(e.getMessage());
        }

        /*Read Input File*/
        logger.info("Starting to read input file");
        try {

            String cartFileName = "src/Input.csv";
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            logger.info("Enter Input File name ");
            cartFileName = reader.readLine();

            cartFileName = cartFileName.strip();

            List<List<String>> cartRecords = fileHandler.read(cartFileName);

            cart = cartDB.createCart(cart,cartRecords);



            int maxEssential = 3;
            int maxLuxury = 4;
            int maxMisc = 6;
            /*check if the cart is valid*/
            List<CartItem> orderItems = cartDB.validateCart(cart,inventory, maxEssential, maxLuxury, maxMisc);


            if(orderItems.size()>0){

                logger.info("Cart is Invalid - generating output.txt file");

                String ouputFileName = "src/output.txt";

                fileHandler.write(ouputFileName, orderItems,"Please correct Quantity");


            }else{
                // generate Bill
                List<List<String>> records = billDB.createBill(cart,inventory);

                // add the card if not present in system
                cards.getCards().add(cart.getCardNumber());
//                cards.showCards();
                System.out.println("Cart Valid - generating output.csv file");
                System.out.println(records);

                //write
                String ouputFileName = "src/output.csv";

                fileHandler.write(ouputFileName, records);

            }

        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }


    }
}