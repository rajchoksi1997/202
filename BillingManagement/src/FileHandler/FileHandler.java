package FileHandler;

import Model.Item.CartItem;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileHandler {

    public List<List<String>> read(String fileName){
        List<List<String>> data = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String eachLine;
            while ( (eachLine = reader.readLine()) != null) {
                String[] value = eachLine.split(",");
                data.add(Arrays.asList(value));
            }
        }catch (IOException e){
            throw new RuntimeException(e.getMessage());
        }

        return data;
    }

    public void write(String name, List<List<String>> data)  {
        File file = new File(name);
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
            for(List<String> record: data){
                for(String col: record){
                    writer.write(col);
                    writer.write(",");
                }
                writer.write(System.lineSeparator());
            }
            writer.flush();

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    public void write(String name, List<CartItem> data, String errorMessage){
        File file = new File(name);
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
            writer.write(errorMessage);
            writer.write(System.lineSeparator());
            for(CartItem item: data){
                writer.write(item.getName());
                writer.write(System.lineSeparator());
            }
            writer.flush();
        }catch(IOException e){
            throw new RuntimeException(e.getMessage());

        }

    }

}
