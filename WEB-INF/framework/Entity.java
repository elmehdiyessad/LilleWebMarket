package framework;


import java.io.PrintWriter;
import java.lang.reflect.Method;

import java.sql.ResultSet;



public abstract class Entity
{
    public void hydrate(ResultSet rs, PrintWriter out) throws Exception
    {
        Method[] methods = this.getClass().getMethods();
        String fieldName;

        for(Method method : methods) {
            if(method.getName().substring(0, 3).equals("set")){
                fieldName = method.getName().substring(3).replace("(.)([A-Z])", "$1_$2").toLowerCase();
                try {
                    method.invoke(this, rs.getObject(fieldName));
                } catch(Exception e){}
            }
        }
    }
}