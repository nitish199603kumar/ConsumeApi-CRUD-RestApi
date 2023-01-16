package com.nitish.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nitish.model.CommentModel;
import com.nitish.model.PostModel;
import com.nitish.service.PostService;

@RestController
@RequestMapping("/api/v1")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@GetMapping("/posts")
	public PostModel[] getAllPosts()
	{
		PostModel[] response=postService.getAllPosts();
		return response;
	}
	
	
	
	@GetMapping("/posts/{posId}/comments")
	public CommentModel[] getAllCommentsForApost(@PathVariable("posId") Long postId)
	{
		
		return postService.getAllCommentsForApost(postId);
		
		
		
	}

	@PostMapping("/posts")
	public PostModel createPost(@RequestBody PostModel postModel)
	{
		
		PostModel createPost = postService.createPost(postModel);
		return createPost;
	}
	
	@PutMapping("/posts/{postId}")
	public PostModel updatePost(@RequestBody PostModel postModel,@PathVariable("postId") Long postId)
	{
			PostModel updatePost = postService.updatePost(postModel,postId);
			return updatePost;
	}
	
	@DeleteMapping("/posts/{id}")
	public void deletePost(@PathVariable("id") Long id)
	{
		postService.deletePost(id);
	}
}
