package top.youlanqiang.alphajson.serialize;


import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.Map;
import java.util.Properties;

/**
 * @author youlanqiang
 * @version 1.0
 * @since 1.8
 * 序列化默认配置
 */
public class DefaultParseConfig implements ParseConfig {


    private String dateFormat;


    private String decimalFormat;


    public DefaultParseConfig(){
        defaultInit();
    }


    public DefaultParseConfig(File file) throws FileNotFoundException, ClassNotFoundException {
        if(file == null || file.exists() || !file.canRead()){
            defaultInit();
        }else {
            String ext = file.getName().substring(file.getName().lastIndexOf('.') + 1,
                    file.getName().length() - 1);
            switch (ext) {
                case "properties":
                    initByProperties(new FileInputStream(file));
                    break;
                case "yml":
                    initByYaml(new FileInputStream(file));
                    break;
                default:
                    defaultInit();
            }
        }
    }

    protected void initByProperties(InputStream in) {
        try {
            Properties pro = new Properties();
            pro.load(in);
            loadProperties(pro);
            in.close();
        }catch(IOException e){
            defaultInit();
        }
    }

    protected void initByYaml(InputStream in) throws ClassNotFoundException{
        try {
            Class clazz = Class.forName("org.yaml.snakeyaml.Yaml");
            Yaml yaml = (Yaml) clazz.newInstance();
            Map<String, String> map = yaml.load(in);
            loadMap(map);
            in.close();
        }catch(IOException | InstantiationException |IllegalAccessException e){
            defaultInit();
        }
    }

    private void defaultInit(){
        this.dateFormat = "yyyy-MM-dd HH:mm:ss";
        this.decimalFormat = "#.#####";
    }


    private void loadProperties(final Properties properties){
        this.dateFormat = properties.getProperty("date-format", "yyyy-MM-dd HH:mm:ss");
        this.decimalFormat = properties.getProperty("decimal-format", "#.#####");
    }

    private void loadMap(final Map<String, String> map){
        this.dateFormat = map.get("date-format");
        this.decimalFormat = map.get("decimal-format");
    }



    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public String getDecimalFormat() {
        return decimalFormat;
    }

    public void setDecimalFormat(String decimalFormat) {
        this.decimalFormat = decimalFormat;
    }

}
