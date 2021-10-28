package com.omni.cell.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.omni.cell.entity.Recipe;
import com.omni.cell.service.RecipeService;


@RestController
public class RecipeController {
	
	@Autowired
	RecipeService recipeService;
	
	@PostMapping(value="/recipe/addrecipe")
	 public ResponseEntity<?> addyourrecipe(@RequestParam("name") String name,
			 @RequestParam("category") String category,@RequestParam("label") String label, 
			  @RequestParam("price") String price, @RequestParam("description") String description,@RequestParam("file") MultipartFile uploadfile){
		
		
		if(name==null || name.isEmpty() || category==null && category.isEmpty() || label==null || label.isEmpty() || price==null || price.isEmpty() && description!=null || description.isEmpty() )
			return new ResponseEntity<>("{\"status\":300,\"message\":\"Please check mandoratory fields\"}", HttpStatus.OK);	 	
		else if(uploadfile.isEmpty())
			return new ResponseEntity<>("{\"status\":300,\"message\":\"Please add image of recipe\"}", HttpStatus.OK);	 
		  
		  String message= recipeService.addrecie(name, category, label, price, description,uploadfile);
		
		return new ResponseEntity<>("{\"status\":200,\"message\":\""+message+"\"}", HttpStatus.OK);	 	
		  
			
	} 
	@GetMapping(value="/recipe/getallrecipe")
	public ResponseEntity<?>getallproducts()
	{
	       List<Recipe> list=recipeService.getallproduct();
		   return new ResponseEntity<>(list,HttpStatus.ACCEPTED);
	}
	@GetMapping(value="/recipe/{id}")
	public ResponseEntity<?>getproductsbyid(@PathVariable int id)
	{
	       Recipe recipe=recipeService.getsingleproduct(id);
		   return new ResponseEntity<>(recipe,HttpStatus.ACCEPTED);
	}


}
