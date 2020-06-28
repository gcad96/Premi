package premi;

public class Stadio 
{
	public static final String PRIMASQUADRA = "primaSquadra";
	public static final String RISERVE = "riserve";
	
	private boolean presenza;
	private float premio;
	private String tipo;
	
	public Stadio(String tipo)
	{
		this.presenza = false;
		this.premio = 0; 
		this.tipo = tipo;
	}

	public boolean isPresente() 
	{
		return presenza;
	}

	public void setPresenza(boolean presenza) 
	{
		this.presenza = presenza;
	}

	public float getPremio() 
	{
		return premio;
	}

	public void setPremio(float premio) 
	{
		this.premio = premio;
	}

	public String getTipo() 
	{
		return tipo;
	}

	public void setTipo(String tipo) 
	{
		this.tipo = tipo;
	}
}
