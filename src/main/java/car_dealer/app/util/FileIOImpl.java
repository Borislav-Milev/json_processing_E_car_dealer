package car_dealer.app.util;

import car_dealer.app.util.contract.FileIO;

import java.io.*;

public class FileIOImpl implements FileIO {

    @Override
    public String readFile(String filePath) {
        File file = new File(filePath);
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            while (true){
                String line = reader.readLine();
                if(line == null) break;
                builder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString().trim();
    }
}
