package com.omni.cell.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.omni.cell.entity.AwsS3cred;
import com.omni.cell.entity.Recipe;
import com.omni.cell.helper.EntryPointS3;
import com.omni.cell.repository.AwsS3credRepo;
import com.omni.cell.repository.RecipeRepo;


@Service
public class RecipeService {
	
	@Autowired
	AwsS3credRepo awsS3credRepo;
	
	@Autowired
	EntryPointS3 entryPointS3;
	
	@Autowired
	RecipeRepo recipeRepo;
	
	public String addrecie(String name,String category,String label,String price,String description, MultipartFile uploadfile)
	{
		try
		{
			AwsS3cred awsS3cred=awsS3credRepo.findByIsactive(true);
			String imageurl= entryPointS3.savefileintos3(convertMultiPartToFile(uploadfile),awsS3cred);
			Recipe recipe=new Recipe();
			recipe.name=name;
			recipe.image=imageurl;
			recipe.category=category;
			recipe.label=label;
			recipe.price=price;
			recipe.description=description;
			
			recipeRepo.save(recipe);
			
			return "product added successfully";
		
		}catch (Exception e) {
			return "Failed to add the product";
		}
	}
	
	public List<Recipe> getallproduct()
	{
		return (List<Recipe>) recipeRepo.findAll();
		
	}
	public Recipe getsingleproduct(int id)
	{
		return recipeRepo.findById(id);
		
	}
	
	  private File convertMultiPartToFile(MultipartFile file ) throws IOException
	    {
	        File convFile = new File( file.getOriginalFilename() );
	        FileOutputStream fos = new FileOutputStream( convFile );
	        fos.write( file.getBytes() );
	        fos.close();
	        return convFile;
	    }


	
}
