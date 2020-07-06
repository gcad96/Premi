package interact;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import premi.*;
import premi.competizioni.*;
import premi.competizioni.coppa.*;

public class Load 
{
	private static final String IN = "In/Input "; 
	private static final String SQUADRE = "In/Squadre"; 
	public static List<String> readData(String file)
	{
		List<String> lines = null;
		try(BufferedReader in = new BufferedReader(new FileReader(file)))
		{
			lines = in.lines().collect(Collectors.toList());
			return lines;
		}
		catch(IOException e) 
		{ 
			System.err.println(e.getMessage()); 
			return null; 
		}
	}
	
	public static void main(String[] args)
	{
		int giornata = 0;
		System.out.print("Giornata?");
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		try 
		{
			String riga = console.readLine();
			giornata = Integer.parseInt(riga);
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		Map<String, Squadra> m = new HashMap<>(); 
		for(String s: readData(SQUADRE))
		{
			Squadra sq = new Squadra((s.split(","))[0]);
			m.put((s.split(","))[0], sq);
			if(Integer.parseInt(((s.split(",")[1]).split(" "))[1])!=0)
				sq.setStadio(Stadio.PRIMASQUADRA, Integer.parseInt(((s.split(",")[1]).split(" "))[1]));
			if(Integer.parseInt(((s.split(",")[1]).split(" "))[2])!=0)
				sq.setStadio(Stadio.RISERVE, Integer.parseInt(((s.split(",")[1]).split(" "))[2]));
		}
		
		List<Competizione> competizioni = new LinkedList<>();
		Competizione c;
		c = new Campionato(); 
		competizioni.add(c); 
		c = new CoppaDiLega(); 
		competizioni.add(c);
		c = new CoppaTurno();
		competizioni.add(c);
		c = new SerieA(); 
		competizioni.add(c); 
		c = new SerieB(); 
		competizioni.add(c);
		c = new SerieC(); 
		competizioni.add(c);
		c = new ChampionsFinale();
		competizioni.add(c);
		c = new CampionatoRiserve(); 
		competizioni.add(c);
		
		for(Competizione comp: competizioni)
			comp.carica(m, readData(IN + comp.getNome()));

		List<Squadra> l = new LinkedList<>(m.values()); 
		Collections.sort(l); 
		
		System.out.println("PREMI " + giornata + "ma giornata");
		for(Squadra sq: l)
			System.out.println(sq);
	}
}
