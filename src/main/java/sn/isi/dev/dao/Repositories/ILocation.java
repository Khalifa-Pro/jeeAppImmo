package sn.isi.dev.dao.Repositories;

import java.util.List;

import sn.isi.dev.entities.Location;

public interface ILocation {
	public void louer(Location location);
	public List<Location> liste(String mc);
	public Location gestLocationById(long id);
	public void modifier(Location location);
	public void supprimer(long id);
}
