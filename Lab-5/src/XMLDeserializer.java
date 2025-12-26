import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.StringReader;
import java.lang.reflect.Field;
import org.xml.sax.InputSource;

public class XMLDeserializer {
    public static <T> void deserializeAndPrint(String xml, Class<T> clazz)throws Exception{
        //parse xml(dom)
        Document doc= DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(xml)));
        Element root=doc.getDocumentElement();
        System.out.println("class: "+clazz.getName());

        for(Field field : clazz.getDeclaredFields()){
            field.setAccessible(true);
            //todo
            String fieldName=field.getName();
            String value=null;
            if(root.getElementsByTagName(fieldName).getLength()>0){
                value=root.getElementsByTagName(fieldName).item(0).getTextContent();
            }
            System.out.println("Name: " +value +"\n");//todo
        }
    }
}