package framework;


import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import framework.Validator;



public abstract class Entity
{
    public static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    public abstract Validator validate();


    public void hydrate(ResultSet rs) throws Exception
    {
        for(Entry<Method, String> setter : getSetters().entrySet())
            try {
                setter.getKey().invoke(this, rs.getObject(setter.getValue()));
            } catch(Exception e){}
    }

    public void hydrate(HttpServletRequest request) throws Exception
    {
        for(Entry<Method, String> setter : getSetters().entrySet()){
            //try {
                Class<?> paramType = setter.getKey().getParameterTypes()[0];
                if(request.getParameter(setter.getValue()) != null){
                    setter.getKey().invoke(
                        this,
                        paramType.cast(stringToObject(request.getParameter(setter.getValue()), paramType))
                    );
                }
            /*} catch(Exception e){
                throw new Exception(setter.getValue());
            }/**/
        }
    }

    public static Object stringToObject(String string, Class<?> paramType) throws Exception
    {
        if(Boolean.class.equals(paramType))
            return Boolean.valueOf(string);
        else if(Integer.class.equals(paramType))
            return Integer.valueOf(string);
        else if(Date.class.equals(paramType))
            return formatter.parse(string);

        return string;
    }

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
}