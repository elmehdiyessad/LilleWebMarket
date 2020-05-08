package framework;


import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import framework.Validator;



public abstract class Entity
{
    public static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");


    public abstract Validator validate();


    public void hydrate(ResultSet rs) throws Exception
    {
        for(Entry<Method, String> setter : getSetters().entrySet()){
            Class<?> paramType = setter.getKey().getParameterTypes()[0];
            try {
                setter.getKey().invoke(
                    this,
                    paramType.cast(stringToObject(rs.getObject(setter.getValue()).toString(), paramType))
                );
            } catch(Exception e){}
        }
    }

    public void hydrate(HttpServletRequest request) throws Exception
    {
        for(Entry<Method, String> setter : getSetters().entrySet()){
            Class<?> paramType = setter.getKey().getParameterTypes()[0];
            if(request.getParameter(setter.getValue()) != null){
                setter.getKey().invoke(
                    this,
                    paramType.cast(stringToObject(request.getParameter(setter.getValue()).trim(), paramType))
                );
            }
        }
    }

    public static Object stringToObject(String string, Class<?> paramType) throws Exception
    {
        if(Boolean.class.equals(paramType))
            return Boolean.valueOf(string);
        else if(Double.class.equals(paramType))
            return Double.valueOf(string);
        else if(Integer.class.equals(paramType))
            return Integer.valueOf(string);
        else if(Date.class.equals(paramType))
            return formatter.parse(string);

        return string;
    }

    private Map<Method, String> getSetters()
    {
        Map<Method, String> setters = new HashMap<Method, String>();
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