package com.sip.ams.controllers;

import java.awt.List;
import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sip.ams.entities.Etudiant;

//invoquer rquest apres path ! c plus specifique
@RequestMapping("/etudiant")
@Controller
public class EtudiantController {
	ArrayList<Etudiant> etudiants = new ArrayList<>();
	
	//block d'instance
	{
	
	etudiants.add(new Etudiant(1,"ali","ali@gmail.com"));
	etudiants.add(new Etudiant(2,"amine","amine@gmail.com"));
	etudiants.add(new Etudiant(3,"rami","rami@gmail.com"));
	etudiants.add(new Etudiant(4,"patrique","patrique@gmail.com"));
	}
	
	
	
	/*methode à invoquer à partir du url/home qui va afficher le msg sysout dans console 
	 et avec return il va faire la redirection vers la page info.html */
	@RequestMapping("/home")
	public String message(Model model) 
	{  
		//affichage au console
		System.out.println("bienvenue au bootcamp");
		
		//une variable 
		String formation = "fullstack 100% spring boot";
		String lieu = "sesame";
		/*ajouter att training qui correspnt au formation 
		model va rammer ces objt du java au html */
		model.addAttribute("training", formation);
		model.addAttribute("location", lieu);

		//redirection vers page info
		return "info" ; //vue 
	}
	
	@RequestMapping("/produits")
	public ModelAndView listeProduits (Model model)
	{ 
		//utiliser model et view au mm temps
		ModelAndView mv = new ModelAndView();
		
		ArrayList<String> produits = new ArrayList<>();
		produits.add("voiture");
		produits.add("camion");
		produits.add("Moto");
		produits.add("bus");
		//model.addAttribute("mesProduits", produits);
		
		mv.addObject("mesProduits", produits);
		mv.setViewName("products"); // nom du view products.html
		return mv;
		
		//return "products" ; //vue
	}
	
	@RequestMapping("/students")
	public ModelAndView listeStudents (Model model)
	{ 
		//utiliser model et view au mm temps
		ModelAndView mv = new ModelAndView();
		System.out.println(etudiants);
		mv.addObject("students", etudiants);
		mv.setViewName("listStudents"); // nom du view products.html
		return mv;
		
		//return "products" ; //vue
	}
	
	//@GetMapping("/add")
	@RequestMapping(value="/add", method =RequestMethod.GET)
	public ModelAndView addStudentForm (Model model)
	{ 
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("addStudent"); // nom du view products.html
		return mv;
		
	}
	
	
	@RequestMapping(value="/add", method =RequestMethod.POST)
	public String addStudent (
			@RequestParam("id") int id , 
			@RequestParam("nom")String nom , 
			@RequestParam("email") String email )
	{ 
		Etudiant e = new Etudiant ( id , nom , email) ;
		etudiants.add(e);
		return "redirect:students"; //redirect vers path
		
	}
	
	
}
