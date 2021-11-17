package DBConnection;

import entity.СhoiceProperties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyInf {

    public Properties getProperties(СhoiceProperties choice) {
        InputStream fis = null;
        Properties properties = new Properties();

        try {
            if (choice == СhoiceProperties.CONNECTION){
                fis = PropertyInf.class.getClassLoader().getResourceAsStream("config.properties");
            }else if (choice == СhoiceProperties.SQL){
                fis = PropertyInf.class.getClassLoader().getResourceAsStream("sql.properties");
            }else {
                fis = PropertyInf.class.getClassLoader().getResourceAsStream("path.properties");

            }

            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

}