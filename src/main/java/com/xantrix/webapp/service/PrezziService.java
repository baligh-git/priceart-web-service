package com.xantrix.webapp.service;

import com.xantrix.webapp.entities.DettListini;
 
public interface PrezziService
{
	public DettListini SelPrezzo(String CodArt, String Listino);

	public void InsPrezzo(DettListini dettListini);

	public void DelPrezzo(String CodArt, String IdList);

}
