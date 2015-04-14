package model;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;
import play.data.validation.Constraints.Required;

import java.util.*;

import javax.persistence.*;

import es.uniovi.asw.model.User;


public class Partida implements Model {
	
	public List<User> usuarios;
	
	public List<Long> idUsers;
	
	public Long id;
	public  List<Long> idAskedQuestions;
	public User activeUser;
	
	public Map<Long,Set<String>> quesitosPorJugador;
	
	
	public Partida()
	{
		if(partidaExistente())
	}
	
	/**
	 * Carga los usuarios en memoria
	 * @return
	 */
	public List<User> loadUsers()
	{
		
	}
	
	
	public boolean partidaExistente()
	{
		
	}
}
