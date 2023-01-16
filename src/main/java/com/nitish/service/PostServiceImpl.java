package com.nitish.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nitish.model.CommentModel;
import com.nitish.model.PostModel;

@Service
public class PostServiceImpl implements PostService {
	
	@Value("${BaseUrl}")
	public String BaseUrl;
	
	
	@Autowired
	private RestTemplate restTemplate;

	
	public PostModel[] getAllPosts()
	{
	
		PostModel[] result = restTemplate.getForObject(BaseUrl + "/posts", PostModel[].class);
		System.out.println(result);
		return result;
	}
	
	public PostModel createPost(PostModel postModel)
	{
		PostModel model =null;
		HttpHeaders headers=new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<PostModel> httpEntity=new HttpEntity(postModel,headers);
		ResponseEntity<PostModel> newPostEntity = restTemplate.postForEntity(BaseUrl + "/posts", httpEntity, PostModel.class);
		
		if(newPostEntity.getStatusCode()== HttpStatus.CREATED)
		{
		    model = newPostEntity.getBody();
		}

	
		return model;
	}
	
	
	public CommentModel[] getAllCommentsForApost(Long postId)
	{
		//https://jsonplaceholder.typicode.com/posts/1/comments/
		CommentModel[] result = restTemplate.getForObject(BaseUrl + "posts" + postId +"/comments",CommentModel[].class);
		
		return result;	
	}

	@Override
	public PostModel updatePost(PostModel postModel, Long id) {
		PostModel exchangeResponse=null;
		HttpHeaders headers=new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<PostModel> httpEntity=new HttpEntity<>(postModel,headers);
		
		ResponseEntity<PostModel> exchange = restTemplate.exchange(BaseUrl+"/posts/{id}", HttpMethod.PUT, httpEntity,PostModel.class,id);
		if(exchange.getStatusCode()==HttpStatus.OK)
		{
			exchangeResponse = exchange.getBody();
		}
		
		return exchangeResponse;
	}

	@Override
	public void deletePost(Long id) {
		// TODO Auto-generated method stub
		 restTemplate.delete(BaseUrl + "/posts/{id}",id);
	}

}
