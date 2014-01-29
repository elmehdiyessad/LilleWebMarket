package framework;


import java.sql.ResultSet;



public abstract class Entity
{
	public abstract String[] getFields();


	
	public abstract void hydrate(ResultSet rs)
	{
		
	}
}