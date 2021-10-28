package com.omni.cell.repository;

import org.springframework.data.repository.CrudRepository;

import com.omni.cell.entity.AwsS3cred;





public interface AwsS3credRepo extends CrudRepository<AwsS3cred, Integer>{

	AwsS3cred findByIsactive(boolean isactive);
}
