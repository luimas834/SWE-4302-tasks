import java.lang.reflect.Field;
import java.util.List;
public class XMLSerializer {
    public static String parse(Object obj,int level) throws IllegalAccessException{
        Class<?> c =obj.getClass();
        String startTag= "<" +c.getName()+">\n";
        String endTag ="</" +c.getName()+">";
        //todo
        if(level!=0){
            startTag="";
            endTag="";
        }

        StringBuilder s =new StringBuilder(startTag);
        Field[] fields = c.getDeclaredFields();

        if(obj instanceof List<?> objectList){
            StringBuilder b = new StringBuilder();

            for(Object parseObj : objectList){
                b.append("<item>\n");
                b.append(parse(parseObj,level+1));
                b.append("</item>\n");
            }
            return b.toString();
        }
        if((obj instanceof String )||(obj instanceof Float)){
            return obj.toString();
        }
        for(Field f : fields){
            f.setAccessible(true);
            s.append("<"+f.getName()+">");
            s.append(parse(f.get(obj),level+1));
            s.append("</"+f.getName()+">\n");
        }
        s.append(endTag);
        return s.toString();
    }
}
