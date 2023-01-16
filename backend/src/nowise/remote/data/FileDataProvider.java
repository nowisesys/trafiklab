package nowise.remote.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileDataProvider implements DataProvider {

    @Override
    public String getStopData() throws FileNotFoundException {
        return getFileContent("stop.json");
    }

    @Override
    public String getJourData() throws FileNotFoundException {
        return getFileContent("jour.json");
    }

    private String getFileContent(String name) throws FileNotFoundException {
        File file = new File(getRootPath() + "/" + name);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder builder = new StringBuilder();
        String str;

        try {
            while ((str = reader.readLine()) != null) {
                builder.append(str);
            }
        } catch (IOException ex) {
            Logger.getLogger(FileDataProvider.class.getName()).log(Level.SEVERE, null, ex);
        }

        return builder.toString();
    }

    private String getRootPath() {
        return new File("docs").getAbsolutePath();
    }
}
