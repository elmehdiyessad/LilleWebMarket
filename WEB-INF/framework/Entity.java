package framework;


import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map.Entry;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;



public abstract class Entity
{
    private HashMap<Method, String> getSetters()
    {
        HashMap<Method, String> setters = new HashMap<Method, String>();
        Method[] methods = this.getClass().getMethods();

        for(Method method : methods) {
            if(method.getName().substring(0, 3).equals("set")){
                setters.put(
                    method,
                    method.getName().substring(3).replaceAll("(.)([A-Z])", "$1_$2").toLowerCase()
                );
            }
        }

        return setters;
    }

    public void hydrate(ResultSet rs) throws Exception
    {
        for(Entry<Method, String> setter : getSetters().entrySet())
            try {
                setter.getKey().invoke(this, rs.getObject(setter.getValue()));
            } catch(Exception e){}
    }

    public void hydrate(HttpServletRequest request) throws Exception
    {
        for(Entry<Method, String> setter : getSetters().entrySet())
            try {
                setter.getKey().invoke(this, request.getParameter(setter.getValue()));
            } catch(Exception e){}
    }
}